package de.htwk.leipzig.mib08.computergrafik.fraktal.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.gui.ModulViewPanelIF;
import de.htwk.leipzig.mib08.computergrafik.fraktal.controller.MainFrameController;

/**
 * @author Daniel und Enno
 * 
 */
public class MainFrame extends JFrame implements ModulViewPanelIF<MainFrameController, OpenGlPanel> {

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
	private final JRadioButtonMenuItem menuItemRekTiefe2 = new JRadioButtonMenuItem("2");
	private final JRadioButtonMenuItem menuItemRekTiefe3 = new JRadioButtonMenuItem("3");
	private final JRadioButtonMenuItem menuItemRekTiefe4 = new JRadioButtonMenuItem("4");
	private final JRadioButtonMenuItem menuItemRekTiefe5 = new JRadioButtonMenuItem("5");
	private final JRadioButtonMenuItem menuItemRekTiefe6 = new JRadioButtonMenuItem("MAX");
	private final JMenuItem menuItemInfo = new JMenuItem("Info");
	private final JPanel panelHeader = new JPanel();
	private final JTextArea textAreaHoehe = new JTextArea();
	private final JButton buttonHoehe = new JButton();

	public MainFrame() {
		super();
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
		getContetnPanel().add(getHeaderPanel(), BorderLayout.NORTH);
		getHeaderPanel().add(getTextAreaHoehe());
		getHeaderPanel().add(getButtonHoehe());
		getTextAreaHoehe().setColumns(3);
	}
	
	@Override
	public void setController(MainFrameController controller) {
		getSubPanel().setController(controller.getContentController());
		addMouseListener(controller.getMouseListener());
		addMouseWheelListener(controller.getMouseListener());
		getMenuItemRekTiefe1().setModel(controller.getRekTiefeEinsModel());
		getMenuItemRekTiefe2().setModel(controller.getRekTiefeZweiModel());
		getMenuItemRekTiefe3().setModel(controller.getRekTiefeDreiModel());
		getMenuItemRekTiefe4().setModel(controller.getRekTiefeVierModel());
		getMenuItemRekTiefe5().setModel(controller.getRekTiefeFuenfModel());
		getMenuItemRekTiefe6().setModel(controller.getRekTiefeSechsModel());
		getMenuItemInfo().setModel(controller.getInfoButtonModel());
		getMenuItemNeu().setModel(controller.getNeuButtonModel());
		getMenuItemBeenden().setModel(controller.getBeendenButtonModel());
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

	JPanel getHeaderPanel() {
		return panelHeader;
	}

	JButton getButtonHoehe() {
		return buttonHoehe;
	}

	JTextArea getTextAreaHoehe() {
		return textAreaHoehe;
	}

}