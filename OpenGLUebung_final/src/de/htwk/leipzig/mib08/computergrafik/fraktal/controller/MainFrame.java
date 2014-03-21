package de.htwk.leipzig.mib08.computergrafik.fraktal.controller;

import java.awt.BorderLayout;
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

import de.htwk.leipzig.mib08.computergrafik.fraktal.gui.MyPanel;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Dreieck;

/**
 * @author Daniel und Enno
 * 
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel cp;
	MyPanel gp = new MyPanel();

	JMenuBar menuBarLeiste = new JMenuBar();
	JMenu menuDatei = new JMenu("Datei");
	JMenu menuModus = new JMenu("Modus");
	JMenu menuRekursionstiefe = new JMenu("Rekursionstiefe");
	JMenu menuHilfe = new JMenu("Hilfe");

	JMenuItem menuItemNeu = new JMenuItem("Neu");
	JMenuItem menuItemBeenden = new JMenuItem("Beenden");
	JRadioButtonMenuItem menuItemDreiecke = new JRadioButtonMenuItem("Linien");
	JRadioButtonMenuItem menuItemPyramiden = new JRadioButtonMenuItem("Fl�chen");
	JRadioButtonMenuItem menuItemBeides = new JRadioButtonMenuItem("Linien und Fl�chen");
	JRadioButtonMenuItem menuItemRekTiefe1 = new JRadioButtonMenuItem("1");
	JRadioButtonMenuItem menuItemRekTiefe2 = new JRadioButtonMenuItem("3");
	JRadioButtonMenuItem menuItemRekTiefe3 = new JRadioButtonMenuItem("6");
	JRadioButtonMenuItem menuItemRekTiefe4 = new JRadioButtonMenuItem("8");
	JRadioButtonMenuItem menuItemRekTiefe5 = new JRadioButtonMenuItem("10");
	JRadioButtonMenuItem menuItemRekTiefe6 = new JRadioButtonMenuItem("13");

	JMenuItem menuItemInfo = new JMenuItem("Info");

	JPanel panelHoehe = new JPanel();
	JTextArea textAreaHoehe = new JTextArea();
	JButton buttonHoehe = new JButton();

	public MainFrame() {

		menuBarLeiste.add(menuDatei);
		menuBarLeiste.add(menuModus);
		menuBarLeiste.add(menuRekursionstiefe);
		menuBarLeiste.add(menuHilfe);

		menuDatei.add(menuItemNeu);
		menuDatei.add(menuItemBeenden);

		menuModus.add(menuItemDreiecke);
		menuModus.add(menuItemPyramiden);
		menuModus.add(menuItemBeides);

		menuRekursionstiefe.add(menuItemRekTiefe1);
		menuRekursionstiefe.add(menuItemRekTiefe2);
		menuRekursionstiefe.add(menuItemRekTiefe3);
		menuRekursionstiefe.add(menuItemRekTiefe4);
		menuRekursionstiefe.add(menuItemRekTiefe5);
		menuRekursionstiefe.add(menuItemRekTiefe6);

		menuItemRekTiefe2.setSelected(true);
		menuItemDreiecke.setSelected(true);

		menuHilfe.add(menuItemInfo);

		setJMenuBar(menuBarLeiste);
		this.setVisible(true);

		cp = (JPanel) this.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(gp, BorderLayout.CENTER);
		cp.add(panelHoehe, BorderLayout.NORTH);
		panelHoehe.add(textAreaHoehe);
		panelHoehe.add(buttonHoehe);
		textAreaHoehe.setColumns(3);

		menuItemNeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gp.flagX = false;
				gp.flagY = false;
				gp.rekTiefe = 5;
				gp.display();

			}
		});

		menuItemBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a;
				a = JOptionPane.showConfirmDialog(getRootPane(),
						"Beenden, echt jetz oder?");
				System.out.println("a:" + a);
				if (a == 0)
					System.exit(0);
			}
		});

		menuItemDreiecke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (menuItemBeides.isSelected())
					menuItemBeides.setSelected(false);
				if (menuItemPyramiden.isSelected())
					menuItemPyramiden.setSelected(false);
				Dreieck.mode = 0;
				gp.display();
			}
		});

		menuItemPyramiden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (menuItemDreiecke.isSelected())
					menuItemDreiecke.setSelected(false);
				if (menuItemBeides.isSelected())
					menuItemBeides.setSelected(false);
				Dreieck.mode = 1;
				gp.display();
			}
		});

		menuItemBeides.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (menuItemDreiecke.isSelected())
					menuItemDreiecke.setSelected(false);
				if (menuItemPyramiden.isSelected())
					menuItemPyramiden.setSelected(false);
				Dreieck.mode = 2;
				gp.display();
			}
		});

		menuItemRekTiefe1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (menuItemRekTiefe2.isSelected())
					menuItemRekTiefe2.setSelected(false);
				if (menuItemRekTiefe3.isSelected())
					menuItemRekTiefe3.setSelected(false);
				if (menuItemRekTiefe4.isSelected())
					menuItemRekTiefe4.setSelected(false);
				if (menuItemRekTiefe5.isSelected())
					menuItemRekTiefe5.setSelected(false);
				if (menuItemRekTiefe6.isSelected())
					menuItemRekTiefe6.setSelected(false);

				gp.rekTiefe = 1;
				gp.display();
			}
		});

		menuItemRekTiefe2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (menuItemRekTiefe1.isSelected())
					menuItemRekTiefe1.setSelected(false);
				if (menuItemRekTiefe3.isSelected())
					menuItemRekTiefe3.setSelected(false);
				if (menuItemRekTiefe4.isSelected())
					menuItemRekTiefe4.setSelected(false);
				if (menuItemRekTiefe5.isSelected())
					menuItemRekTiefe5.setSelected(false);
				if (menuItemRekTiefe6.isSelected())
					menuItemRekTiefe6.setSelected(false);

				gp.rekTiefe = 3;
				gp.display();
			}
		});

		menuItemRekTiefe3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (menuItemRekTiefe2.isSelected())
					menuItemRekTiefe2.setSelected(false);
				if (menuItemRekTiefe1.isSelected())
					menuItemRekTiefe1.setSelected(false);
				if (menuItemRekTiefe4.isSelected())
					menuItemRekTiefe4.setSelected(false);
				if (menuItemRekTiefe5.isSelected())
					menuItemRekTiefe5.setSelected(false);
				if (menuItemRekTiefe6.isSelected())
					menuItemRekTiefe6.setSelected(false);
				gp.rekTiefe = 6;
				gp.display();

			}
		});
		menuItemRekTiefe4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (menuItemRekTiefe2.isSelected())
					menuItemRekTiefe2.setSelected(false);
				if (menuItemRekTiefe3.isSelected())
					menuItemRekTiefe3.setSelected(false);
				if (menuItemRekTiefe1.isSelected())
					menuItemRekTiefe1.setSelected(false);
				if (menuItemRekTiefe5.isSelected())
					menuItemRekTiefe5.setSelected(false);
				if (menuItemRekTiefe6.isSelected())
					menuItemRekTiefe6.setSelected(false);
				gp.rekTiefe = 8;

				gp.display();
			}
		});
		menuItemRekTiefe5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (menuItemRekTiefe2.isSelected())
					menuItemRekTiefe2.setSelected(false);
				if (menuItemRekTiefe3.isSelected())
					menuItemRekTiefe3.setSelected(false);
				if (menuItemRekTiefe4.isSelected())
					menuItemRekTiefe4.setSelected(false);
				if (menuItemRekTiefe1.isSelected())
					menuItemRekTiefe1.setSelected(false);
				if (menuItemRekTiefe6.isSelected())
					menuItemRekTiefe6.setSelected(false);
				gp.rekTiefe = 10;
				gp.display();
			}
		});
		menuItemRekTiefe6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (menuItemRekTiefe2.isSelected())
					menuItemRekTiefe2.setSelected(false);
				if (menuItemRekTiefe3.isSelected())
					menuItemRekTiefe3.setSelected(false);
				if (menuItemRekTiefe4.isSelected())
					menuItemRekTiefe4.setSelected(false);
				if (menuItemRekTiefe5.isSelected())
					menuItemRekTiefe5.setSelected(false);
				if (menuItemRekTiefe1.isSelected())
					menuItemRekTiefe1.setSelected(false);
				gp.rekTiefe = 13;
				gp.display();
			}
		});

		menuItemInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show_info();
			}
		});

		buttonHoehe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateHoehe();
				gp.display();

			}

			private void updateHoehe() {
				Integer newHoehe;
				try {
					newHoehe = Integer.valueOf(textAreaHoehe.getText());
				} catch (NumberFormatException e) {
					newHoehe = null;
				}
				Dreieck.hoehe = newHoehe == null ? 0 : newHoehe.intValue();
				System.out.println(newHoehe);
			}
		});

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				if (SwingUtilities.isLeftMouseButton(arg0)) {
					gp.flagX = true;
					gp.display();
				}
				if (SwingUtilities.isRightMouseButton(arg0)) {
					gp.flagY = true;
					gp.display();
				}

			}

		});
		this.addMouseWheelListener(new MouseAdapter() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getWheelRotation() < 0) {
					// zoom in
					gp.zoomIn = true;
					gp.display();
				} else {
					// zoom out
					gp.zoomOut = true;
					gp.display();
				}

			}
		});

	}

	private void show_info() {
		JOptionPane.showMessageDialog(this, "Version 0.67 Beta",
				"Copyright Dani & Enno", JOptionPane.INFORMATION_MESSAGE);
	}

}