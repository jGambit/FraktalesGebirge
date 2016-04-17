package de.htwk.leipzig.mib08.computergrafik.fraktal.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.github.jgambit.emvc.gui.iface.ModulViewPanelIF;

import de.htwk.leipzig.mib08.computergrafik.fraktal.controller.MainFrameController;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.DrawingMode;

/**
 * @author Daniel und Enno
 *
 */
public class MainFrame extends JFrame implements ModulViewPanelIF<MainFrameController, OpenGlPanel> {

	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Fraktales Gebirge";
	private static final Dimension WINODW_SIZE = new Dimension(900, 900);
	private static final Point WINDOW_LOCATION = new Point(100, 100);
	private JPanel contetnPanel;
	private OpenGlPanel glPanel;
	private JMenuBar menuLeiste;
	private JMenu menuDatei;
	private JMenu menuHilfe;
	private JMenuItem menuItemNeu;
	private JMenuItem menuItemBeenden;
	private JMenuItem menuItemInfo;
	private JPanel panelHeader;
	private JLabel labelHeight;
	private JSlider sliderHeight;
	private JLabel labelDetail;
	private JSlider sliderDetail;
	private JLabel labelModus;
	private JComboBox<DrawingMode> comboBoxDrawingMode;

	public MainFrame() {
		super();
		setSize(WINODW_SIZE);
		setTitle(TITLE);
		setLocation(WINDOW_LOCATION);

		getMenuLeiste().add(getMenuDatei());
		getMenuLeiste().add(getMenuHilfe());

		getMenuDatei().add(getMenuItemNeu());
		getMenuDatei().add(getMenuItemBeenden());

		getMenuHilfe().add(getMenuItemInfo());
		setJMenuBar(getMenuLeiste());

		setContentPane(getContetnPanel());
		getContetnPanel().setLayout(new BorderLayout());
		getContetnPanel().add(getSubPanel(), BorderLayout.CENTER);
		getContetnPanel().add(getHeaderPanel(), BorderLayout.NORTH);
		Box line1 = Box.createHorizontalBox();
		line1.add(getLabelHeight());
		line1.add(getSliderHeight());
		line1.add(getLabelMode());
		line1.add(getComboBoxDrawingMode());
		Box line2 = Box.createHorizontalBox();
		line2.add(getLabelDetail());
		line2.add(getSliderDetail());
		Box lines = Box.createVerticalBox();
		lines.add(line1);
		lines.add(line2);
		getHeaderPanel().add(lines);
	}

	private JComboBox<DrawingMode> getComboBoxDrawingMode() {
		if (comboBoxDrawingMode == null) {
			comboBoxDrawingMode = new JComboBox<>();
		}
		return comboBoxDrawingMode;
	}

	private JLabel getLabelMode() {
		if (labelModus == null) {
			labelModus = new JLabel("Drawing-mode:");
		}
		return labelModus;
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

	private JLabel getLabelHeight() {
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
		getComboBoxDrawingMode().setModel(controller.getComboModelDrawingMode());
	}

	JMenu getMenuHilfe() {
		if (menuHilfe == null) {
			menuHilfe = new JMenu("Hilfe");
		}
		return menuHilfe;
	}

	JMenu getMenuDatei() {
		if (menuDatei == null) {
			menuDatei = new JMenu("Datei");
		}
		return menuDatei;
	}

	JMenuBar getMenuLeiste() {
		if (menuLeiste == null) {
			menuLeiste = new JMenuBar();
		}
		return menuLeiste;
	}

	@Override
	public OpenGlPanel getSubPanel() {
		if (glPanel == null) {
			GLProfile glprofile = GLProfile.getDefault();
			GLCapabilities glcapabilities = new GLCapabilities(glprofile);
			glPanel = new OpenGlPanel(glcapabilities);
		}
		return glPanel;
	}

	JPanel getContetnPanel() {
		if (contetnPanel == null) {
			contetnPanel = new JPanel();
		}
		return contetnPanel;
	}

	JMenuItem getMenuItemNeu() {
		if (menuItemNeu == null) {
			menuItemNeu = new JMenuItem("Neu");
		}
		return menuItemNeu;
	}

	JMenuItem getMenuItemBeenden() {
		if (menuItemBeenden == null) {
			menuItemBeenden = new JMenuItem("Beenden");
		}
		return menuItemBeenden;
	}

	JMenuItem getMenuItemInfo() {
		if (menuItemInfo == null) {
			menuItemInfo = new JMenuItem("Info");
		}
		return menuItemInfo;
	}

	JPanel getHeaderPanel() {
		if (panelHeader == null) {
			panelHeader = new JPanel();
//			panelHeader.setLayout(new BorderLayout());
		}
		return panelHeader;
	}

}
