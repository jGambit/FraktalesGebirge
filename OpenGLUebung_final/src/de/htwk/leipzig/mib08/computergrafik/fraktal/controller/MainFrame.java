package de.htwk.leipzig.mib08.computergrafik.fraktal.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.gui.ModulViewPanelIF;
import de.htwk.leipzig.mib08.computergrafik.fraktal.gui.OpenGlPanel;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Dreieck;

/**
 * @author Daniel und Enno
 * 
 */
public class MainFrame extends JFrame implements ModulViewPanelIF<MainFrameController, OpenGlPanel> {

	private class ClickAndZoomMouseAdapter extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent arg0) {

			if (SwingUtilities.isLeftMouseButton(arg0)) {
				getSubPanel().flagX = true;
				getSubPanel().display();
			}
			if (SwingUtilities.isRightMouseButton(arg0)) {
				getSubPanel().flagY = true;
				getSubPanel().display();
			}

		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getWheelRotation() < 0) {
				// zoom in
				getSubPanel().zoomIn = true;
				getSubPanel().display();
			} else {
				// zoom out
				getSubPanel().zoomOut = true;
				getSubPanel().display();
			}
		}
	}

	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Fraktales Gebirge";
	private static final Dimension WINODW_SIZE = new Dimension(900, 900);
	private static final Point WINDOW_LOCATION = new Point(100, 100);
	private final JPanel contetnPanel = new JPanel();
	private final OpenGlPanel glPanel = new OpenGlPanel();

	private final JMenuBar menuLeiste = new JMenuBar();
	private final JMenu menuDatei = new JMenu("Datei");
	private final JMenu menuModus = new JMenu("Modus");
	private final JMenu menuRekursionstiefe = new JMenu("Rekursionstiefe");
	private final JMenu menuHilfe = new JMenu("Hilfe");

	private final JMenuItem menuItemNeu = new JMenuItem("Neu");
	private final JMenuItem menuItemBeenden = new JMenuItem("Beenden");
	private final JRadioButtonMenuItem menuItemDreiecke = new JRadioButtonMenuItem("Linien");
	private final JRadioButtonMenuItem menuItemPyramiden = new JRadioButtonMenuItem("Flächen");
	private final JRadioButtonMenuItem menuItemBeides = new JRadioButtonMenuItem("Linien und Flächen");
	private final JRadioButtonMenuItem menuItemRekTiefe1 = new JRadioButtonMenuItem("1");
	private final JRadioButtonMenuItem menuItemRekTiefe2 = new JRadioButtonMenuItem("3");
	private final JRadioButtonMenuItem menuItemRekTiefe3 = new JRadioButtonMenuItem("6");
	private final JRadioButtonMenuItem menuItemRekTiefe4 = new JRadioButtonMenuItem("8");
	private final JRadioButtonMenuItem menuItemRekTiefe5 = new JRadioButtonMenuItem("10");
	private final JRadioButtonMenuItem menuItemRekTiefe6 = new JRadioButtonMenuItem("13");

	private final JMenuItem menuItemInfo = new JMenuItem("Info");

	private final JPanel panelHoehe = new JPanel();
	private final JTextArea textAreaHoehe = new JTextArea();
	private final JButton buttonHoehe = new JButton();
	private ClickAndZoomMouseAdapter mosueListener;

	public MainFrame() {
		setSize(WINODW_SIZE);
		setTitle(TITLE);
		setLocation(WINDOW_LOCATION);

		getMenuLeiste().add(getMenuDatei());
		getMenuLeiste().add(getMenuModus());
		getMenuLeiste().add(getMenuRekursionstiefe());
		getMenuLeiste().add(getMenuHilfe());

		getMenuDatei().add(getMenuItemNeu());
		getMenuDatei().add(getMenuItemBeenden());

		getMenuModus().add(getMenuItemDreiecke());
		getMenuModus().add(getMenuItemPyramiden());
		getMenuModus().add(getMenuItemBeides());

		getMenuRekursionstiefe().add(getMenuItemRekTiefe1());
		getMenuRekursionstiefe().add(getMenuItemRekTiefe2());
		getMenuRekursionstiefe().add(getMenuItemRekTiefe3());
		getMenuRekursionstiefe().add(getMenuItemRekTiefe4());
		getMenuRekursionstiefe().add(getMenuItemRekTiefe5());
		getMenuRekursionstiefe().add(getMenuItemRekTiefe6());

		getMenuItemRekTiefe2().setSelected(true);
		getMenuItemDreiecke().setSelected(true);

		getMenuHilfe().add(getMenuItemInfo());

		setJMenuBar(getMenuLeiste());

		setContentPane(getContetnPanel());
		getContetnPanel().setLayout(new BorderLayout());
		getContetnPanel().add(getSubPanel(), BorderLayout.CENTER);
		getContetnPanel().add(getPanelHoehe(), BorderLayout.NORTH);
		getPanelHoehe().add(getTextAreaHoehe());
		getPanelHoehe().add(getButtonHoehe());
		getTextAreaHoehe().setColumns(3);

		getMenuItemNeu().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getSubPanel().flagX = false;
				getSubPanel().flagY = false;
				getSubPanel().rekTiefe = 5;
				getSubPanel().display();

			}
		});

		getMenuItemBeenden().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int a;
				a = JOptionPane.showConfirmDialog(getRootPane(),
						"Beenden, echt jetz oder?");
				if (a == JOptionPane.YES_OPTION) {
					setVisible(false);
					dispose();
				}
			}
		});

		getMenuItemDreiecke().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getMenuItemBeides().isSelected())
					getMenuItemBeides().setSelected(false);
				if (getMenuItemPyramiden().isSelected())
					getMenuItemPyramiden().setSelected(false);
				Dreieck.mode = 0;
				getSubPanel().display();
			}
		});

		getMenuItemPyramiden().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getMenuItemDreiecke().isSelected())
					getMenuItemDreiecke().setSelected(false);
				if (getMenuItemBeides().isSelected())
					getMenuItemBeides().setSelected(false);
				Dreieck.mode = 1;
				getSubPanel().display();
			}
		});

		getMenuItemBeides().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getMenuItemDreiecke().isSelected())
					getMenuItemDreiecke().setSelected(false);
				if (getMenuItemPyramiden().isSelected())
					getMenuItemPyramiden().setSelected(false);
				Dreieck.mode = 2;
				getSubPanel().display();
			}
		});

		getMenuItemRekTiefe1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (getMenuItemRekTiefe2().isSelected())
					getMenuItemRekTiefe2().setSelected(false);
				if (getMenuItemRekTiefe3().isSelected())
					getMenuItemRekTiefe3().setSelected(false);
				if (getMenuItemRekTiefe4().isSelected())
					getMenuItemRekTiefe4().setSelected(false);
				if (getMenuItemRekTiefe5().isSelected())
					getMenuItemRekTiefe5().setSelected(false);
				if (getMenuItemRekTiefe6().isSelected())
					getMenuItemRekTiefe6().setSelected(false);

				OpenGlPanel.rekTiefe = 1;
				getSubPanel().display();
			}
		});

		getMenuItemRekTiefe2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (getMenuItemRekTiefe1().isSelected())
					getMenuItemRekTiefe1().setSelected(false);
				if (getMenuItemRekTiefe3().isSelected())
					getMenuItemRekTiefe3().setSelected(false);
				if (getMenuItemRekTiefe4().isSelected())
					getMenuItemRekTiefe4().setSelected(false);
				if (getMenuItemRekTiefe5().isSelected())
					getMenuItemRekTiefe5().setSelected(false);
				if (getMenuItemRekTiefe6().isSelected())
					getMenuItemRekTiefe6().setSelected(false);

				OpenGlPanel.rekTiefe = 3;
				getSubPanel().display();
			}
		});

		getMenuItemRekTiefe3().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (getMenuItemRekTiefe2().isSelected())
					getMenuItemRekTiefe2().setSelected(false);
				if (getMenuItemRekTiefe1().isSelected())
					getMenuItemRekTiefe1().setSelected(false);
				if (getMenuItemRekTiefe4().isSelected())
					getMenuItemRekTiefe4().setSelected(false);
				if (getMenuItemRekTiefe5().isSelected())
					getMenuItemRekTiefe5().setSelected(false);
				if (getMenuItemRekTiefe6().isSelected())
					getMenuItemRekTiefe6().setSelected(false);
				OpenGlPanel.rekTiefe = 6;
				getSubPanel().display();

			}
		});
		getMenuItemRekTiefe4().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (getMenuItemRekTiefe2().isSelected())
					getMenuItemRekTiefe2().setSelected(false);
				if (getMenuItemRekTiefe3().isSelected())
					getMenuItemRekTiefe3().setSelected(false);
				if (getMenuItemRekTiefe1().isSelected())
					getMenuItemRekTiefe1().setSelected(false);
				if (getMenuItemRekTiefe5().isSelected())
					getMenuItemRekTiefe5().setSelected(false);
				if (getMenuItemRekTiefe6().isSelected())
					getMenuItemRekTiefe6().setSelected(false);
				OpenGlPanel.rekTiefe = 8;

				getSubPanel().display();
			}
		});
		getMenuItemRekTiefe5().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (getMenuItemRekTiefe2().isSelected())
					getMenuItemRekTiefe2().setSelected(false);
				if (getMenuItemRekTiefe3().isSelected())
					getMenuItemRekTiefe3().setSelected(false);
				if (getMenuItemRekTiefe4().isSelected())
					getMenuItemRekTiefe4().setSelected(false);
				if (getMenuItemRekTiefe1().isSelected())
					getMenuItemRekTiefe1().setSelected(false);
				if (getMenuItemRekTiefe6().isSelected())
					getMenuItemRekTiefe6().setSelected(false);
				OpenGlPanel.rekTiefe = 10;
				getSubPanel().display();
			}
		});
		getMenuItemRekTiefe6().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (getMenuItemRekTiefe2().isSelected())
					getMenuItemRekTiefe2().setSelected(false);
				if (getMenuItemRekTiefe3().isSelected())
					getMenuItemRekTiefe3().setSelected(false);
				if (getMenuItemRekTiefe4().isSelected())
					getMenuItemRekTiefe4().setSelected(false);
				if (getMenuItemRekTiefe5().isSelected())
					getMenuItemRekTiefe5().setSelected(false);
				if (getMenuItemRekTiefe1().isSelected())
					getMenuItemRekTiefe1().setSelected(false);
				OpenGlPanel.rekTiefe = 13;
				getSubPanel().display();
			}
		});

		getMenuItemInfo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				show_info();
			}
		});

		getButtonHoehe().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Dreieck.hoehe = new Integer(getTextAreaHoehe().getText());
				System.out.println(getTextAreaHoehe().getText());
				getSubPanel().display();

			}
		});

		addMouseListener(getMouseListener());
		addMouseWheelListener(getMouseListener());
	}
	
	@Override
	public void setController(MainFrameController controller) {
		getSubPanel().setController(controller.getContentController());
	}

	private ClickAndZoomMouseAdapter getMouseListener() {
		if (mosueListener == null) {
			mosueListener = new ClickAndZoomMouseAdapter();
		}
		return mosueListener;
	}

	private void show_info() {
		JOptionPane.showMessageDialog(this, "Version 0.67 Beta",
				"Copyright Dani & Enno", JOptionPane.INFORMATION_MESSAGE);
	}

	JMenu getMenuHilfe() {
		return menuHilfe;
	}

	JMenu getMenuRekursionstiefe() {
		return menuRekursionstiefe;
	}

	JMenu getMenuModus() {
		return menuModus;
	}

	JMenu getMenuDatei() {
		return menuDatei;
	}

	JMenuBar getMenuLeiste() {
		return menuLeiste;
	}

	@Override
	public OpenGlPanel getSubPanel() {
		return glPanel;
	}

	JPanel getContetnPanel() {
		return contetnPanel;
	}

	JMenuItem getMenuItemNeu() {
		return menuItemNeu;
	}

	JMenuItem getMenuItemBeenden() {
		return menuItemBeenden;
	}

	JRadioButtonMenuItem getMenuItemDreiecke() {
		return menuItemDreiecke;
	}

	JRadioButtonMenuItem getMenuItemPyramiden() {
		return menuItemPyramiden;
	}

	JRadioButtonMenuItem getMenuItemBeides() {
		return menuItemBeides;
	}

	JRadioButtonMenuItem getMenuItemRekTiefe1() {
		return menuItemRekTiefe1;
	}

	JRadioButtonMenuItem getMenuItemRekTiefe2() {
		return menuItemRekTiefe2;
	}

	JRadioButtonMenuItem getMenuItemRekTiefe3() {
		return menuItemRekTiefe3;
	}

	JRadioButtonMenuItem getMenuItemRekTiefe4() {
		return menuItemRekTiefe4;
	}

	JRadioButtonMenuItem getMenuItemRekTiefe5() {
		return menuItemRekTiefe5;
	}

	JRadioButtonMenuItem getMenuItemRekTiefe6() {
		return menuItemRekTiefe6;
	}

	JMenuItem getMenuItemInfo() {
		return menuItemInfo;
	}

	JPanel getPanelHoehe() {
		return panelHoehe;
	}

	JButton getButtonHoehe() {
		return buttonHoehe;
	}

	JTextArea getTextAreaHoehe() {
		return textAreaHoehe;
	}

}