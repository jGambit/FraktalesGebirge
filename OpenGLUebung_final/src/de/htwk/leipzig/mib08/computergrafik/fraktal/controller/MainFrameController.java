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
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.github.jgambit.emvc.controller.ModulViewController;

import de.htwk.leipzig.mib08.computergrafik.fraktal.gui.OpenGlPanel;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Triangle3D;
import de.htwk.leipzig.mib08.computergrafik.fraktal.process.FraktalesGebirgeModulProcess;

public class MainFrameController extends ModulViewController<FraktalesGebirgeModulProcess, Triangle3D, OpenGlController> implements ActionListener, ChangeListener {

	private ClickAndZoomMouseAdapter mosueListener;
	private ButtonModel buttonModelInfo;
	private ButtonModel buttonModelNeu;
	private ButtonModel buttonModelBeenden;
	private BoundedRangeModel sliderModelHeight;
	private BoundedRangeModel sliderModelDetail;
	
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
	
	public MainFrameController(FraktalesGebirgeModulProcess modulProcess) {
		super(modulProcess);
	}

	@Override
	protected OpenGlController createContentControllerImpl() {
		return new OpenGlController(getModulProcess());
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
//				getContentController().reset();
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
	
}
