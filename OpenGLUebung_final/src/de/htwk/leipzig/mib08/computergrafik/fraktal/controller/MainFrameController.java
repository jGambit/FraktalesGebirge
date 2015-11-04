package de.htwk.leipzig.mib08.computergrafik.fraktal.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.BoundedRangeModel;
import javax.swing.ButtonModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.github.jgambit.emvc.controller.ModulViewController;
import com.github.jgambit.emvc.exception.ToBeHandledByApplicationException;

import de.htwk.leipzig.mib08.computergrafik.fraktal.gui.OpenGlPanel;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.DrawingMode;
import de.htwk.leipzig.mib08.computergrafik.fraktal.process.FraktalesGebirgeGuiProcess;
import de.htwk.leipzig.mib08.computergrafik.fraktal.valueobject.FraktalesGebirgeConfig;

public class MainFrameController extends ModulViewController<FraktalesGebirgeGuiProcess, FraktalesGebirgeConfig, OpenGlController> implements ActionListener, ChangeListener, ListDataListener {

	private ClickAndZoomMouseAdapter mosueListener;
	private ButtonModel buttonModelInfo;
	private ButtonModel buttonModelNeu;
	private ButtonModel buttonModelBeenden;
	private BoundedRangeModel sliderModelHeight;
	private BoundedRangeModel sliderModelDetail;
	private DefaultComboBoxModel<DrawingMode> comboModelDrawingMode;
	
	private class ClickAndZoomMouseAdapter extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent arg0) {
			setUpdatingForm();
			blockView();
			try {
				if (SwingUtilities.isLeftMouseButton(arg0)) {
					getContentController().rotateX();
					getModulProcess().repaint();
				}
				if (SwingUtilities.isRightMouseButton(arg0)) {
					getContentController().rotateY();
					getModulProcess().repaint();
				}
			} finally {
				unblockView();
				unSetUpdatingForm();
			}
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getWheelRotation() < 0) {
				// zoom in
				OpenGlPanel.zoomIn = true;
				getModulProcess().repaint();
			} else {
				// zoom out
				OpenGlPanel.zoomOut = true;
				getModulProcess().repaint();
			}
		}
	}
	
	public MainFrameController(FraktalesGebirgeGuiProcess modulProcess) {
		super(modulProcess);
	}

	@Override
	protected OpenGlController createContentControllerImpl() {
		return new OpenGlController(getModulProcess());
	}
	
	@Override
	protected void clearFormImpl() throws ToBeHandledByApplicationException {
		super.clearFormImpl();
		getComboModelDrawingMode().removeAllElements();
	}
	
	@Override
	protected void fillFormImpl(FraktalesGebirgeConfig config) throws ToBeHandledByApplicationException {
		super.fillFormImpl(config);
		DrawingMode[] values = DrawingMode.values();
		for (DrawingMode mode : values) {
			getComboModelDrawingMode().addElement(mode);
		}
	}
	
	public ClickAndZoomMouseAdapter getMouseListener() {
		if (mosueListener == null) {
			mosueListener = new ClickAndZoomMouseAdapter();
		}
		return mosueListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isUpdatingForm()) {
			return;
		}
		
		Object source = e.getSource();
		setUpdatingForm();
		blockView();
		try {
			if (source == getInfoButtonModel()) {
				getModulProcess().showInfoDialog();
			} else if (source == getNeuButtonModel()) {
				getModulProcess().createNewMountain(getHeightSliderModel().getValue());
			} else if (source == getBeendenButtonModel()) {
				getModulProcess().quit();
			}
		} finally {
			unblockView();
			unSetUpdatingForm();
		}
	}

	public ButtonModel getInfoButtonModel() {
		if (buttonModelInfo == null) {
			buttonModelInfo = new DefaultButtonModel();
			buttonModelInfo.addActionListener(this);
		}
		return buttonModelInfo;
	}

	public ButtonModel getNeuButtonModel() {
		if (buttonModelNeu == null) {
			buttonModelNeu = new DefaultButtonModel();
			buttonModelNeu.addActionListener(this);
		}
		return buttonModelNeu;
	}

	public ButtonModel getBeendenButtonModel() {
		if (buttonModelBeenden == null) {
			buttonModelBeenden = new DefaultButtonModel();
			buttonModelBeenden.addActionListener(this);
		}
		return buttonModelBeenden;
	}

	public BoundedRangeModel getHeightSliderModel() {
		if (sliderModelHeight == null) {
			sliderModelHeight = new DefaultBoundedRangeModel();
			sliderModelHeight.addChangeListener(this);
		}
		return sliderModelHeight;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (isUpdatingForm()) {
			return;
		}
		
		Object source = e.getSource();
		setUpdatingForm();
		blockView();
		try {
			if (source == getHeightSliderModel()) {
				getModulProcess().updateHeight(getCurrentObject(), getHeightSliderModel().getValue());
			} else if (source == getDetailSliderModel()) {
				getContentController().setRekTiefe(getDetailSliderModel().getValue());
				getModulProcess().repaint();
			}
		} finally {
			unblockView();
			unSetUpdatingForm();
		}
		
	}

	public BoundedRangeModel getDetailSliderModel() {
		if (sliderModelDetail == null) {
			sliderModelDetail = new DefaultBoundedRangeModel();
			sliderModelDetail.setExtent(1);
			sliderModelDetail.setMaximum(12);
			sliderModelDetail.setMinimum(1);
			sliderModelDetail.setValue(getContentController().getRekTiefe());
			sliderModelDetail.addChangeListener(this);
		}
		return sliderModelDetail;
	}

	public DefaultComboBoxModel<DrawingMode> getComboModelDrawingMode() {
		if (comboModelDrawingMode == null) {
			comboModelDrawingMode = new DefaultComboBoxModel<>();
			comboModelDrawingMode.addListDataListener(this);
		}
		return comboModelDrawingMode;
	}

	@Override
	public void intervalAdded(ListDataEvent e) {
		contentsChanged(e);
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
		contentsChanged(e);
	}

	@Override
	public void contentsChanged(ListDataEvent e) {
		if (isUpdatingForm()) {
			return;
		}
		
		Object source = e.getSource();
		setUpdatingForm();
		blockView();
		try {
			if (source == getComboModelDrawingMode()) {
				Object selected = getComboModelDrawingMode().getSelectedItem();
				DrawingMode mode = selected instanceof DrawingMode ? (DrawingMode) selected : DrawingMode.LINE_LOOP;
				getCurrentObject().setDrawingMode(mode);
			}
		} finally {
			unblockView();
			unSetUpdatingForm();
		}
	}

}
