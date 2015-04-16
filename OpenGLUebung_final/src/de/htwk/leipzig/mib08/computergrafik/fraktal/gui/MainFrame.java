package de.htwk.leipzig.mib08.computergrafik.fraktal.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;

import com.github.jgambit.emvc.gui.iface.ModulViewPanelIF;

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
	private final JMenu menuHilfe = new JMenu("Hilfe");

	private final JMenuItem menuItemNeu = new JMenuItem("Neu");
	private final JMenuItem menuItemBeenden = new JMenuItem("Beenden");
	private final JRadioButtonMenuItem menuItemDreiecke = new JRadioButtonMenuItem("Linien");
	private final JRadioButtonMenuItem menuItemLegacy = new JRadioButtonMenuItem("Legacy");
	private final JRadioButtonMenuItem menuItemPyramiden = new JRadioButtonMenuItem("Flächen");
	private final JMenuItem menuItemInfo = new JMenuItem("Info");
	private final JPanel panelHeader = new JPanel();
	private Component labelHeight;
	private JSlider sliderHeight;
	private JLabel labelDetail;
	private JSlider sliderDetail;

	public MainFrame() {
		super();
		setSize(WINODW_SIZE);
		setTitle(TITLE);
		setLocation(WINDOW_LOCATION);

		getMenuLeiste().add(getMenuDatei());
		getMenuLeiste().add(getMenuModus());
		getMenuLeiste().add(getMenuHilfe());

		getMenuDatei().add(getMenuItemNeu());
		getMenuDatei().add(getMenuItemBeenden());

		getMenuModus().add(getMenuItemDreiecke());
		getMenuModus().add(getMenuItemLegacy());
//		getMenuModus().add(getMenuItemPyramiden());

		getMenuItemDreiecke().setSelected(true);

		getMenuHilfe().add(getMenuItemInfo());

		setJMenuBar(getMenuLeiste());

		setContentPane(getContetnPanel());
		getContetnPanel().setLayout(new BorderLayout());
		getContetnPanel().add(getSubPanel(), BorderLayout.CENTER);
		getContetnPanel().add(getHeaderPanel(), BorderLayout.NORTH);
		Box line1 = Box.createHorizontalBox();
		line1.add(getLabelHeight());
		line1.add(getSliderHeight());
		Box line2 = Box.createHorizontalBox();
		line2.add(getLabelDetail());
		line2.add(getSliderDetail());
		Box lines = Box.createVerticalBox();
		lines.add(line1);
		lines.add(line2);
		getHeaderPanel().add(lines);
	}
	
	JSlider getSliderDetail() {
		if (sliderDetail == null) {
			sliderDetail = new JSlider();
			sliderDetail.setMajorTickSpacing(5);
			sliderDetail.setMinorTickSpacing(1);
			sliderDetail.setPaintTicks(true);
			sliderDetail.setPaintLabels(true);
			sliderDetail.setToolTipText("Adjust the level of rekursion-detail.");
		}
		return sliderDetail;
	}

	private JLabel getLabelDetail() {
		if (labelDetail == null) {
			labelDetail = new JLabel("Rekursion detail: ");
		}
		return labelDetail;
	}

	JSlider getSliderHeight() {
		if (sliderHeight == null) {
			sliderHeight = new JSlider();
			sliderHeight.setMajorTickSpacing(25);
			sliderHeight.setMinorTickSpacing(5);
			sliderHeight.setPaintTicks(true);
			sliderHeight.setPaintLabels(true);
			sliderHeight.setToolTipText("Adjust the height of the mountain.");
		}
		return sliderHeight;
	}

	private Component getLabelHeight() {
		if (labelHeight == null) {
			labelHeight = new JLabel("Height: ");
		}
		return labelHeight;
	}

	@Override
	public void setController(MainFrameController controller) {
		getSubPanel().setController(controller.getContentController());
		addMouseListener(controller.getMouseListener());
		addMouseWheelListener(controller.getMouseListener());
		getMenuItemInfo().setModel(controller.getInfoButtonModel());
		getMenuItemNeu().setModel(controller.getNeuButtonModel());
		getMenuItemBeenden().setModel(controller.getBeendenButtonModel());
		getSliderHeight().setModel(controller.getHeightSliderModel());
		getSliderDetail().setModel(controller.getDetailSliderModel());
	}

	JMenu getMenuHilfe() {
		return menuHilfe;
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

	JRadioButtonMenuItem getMenuItemLegacy() {
		return menuItemLegacy;
	}

	JMenuItem getMenuItemInfo() {
		return menuItemInfo;
	}

	JPanel getHeaderPanel() {
		return panelHeader;
	}

}