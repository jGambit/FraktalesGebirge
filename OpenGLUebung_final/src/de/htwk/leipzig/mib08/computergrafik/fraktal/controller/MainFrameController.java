package de.htwk.leipzig.mib08.computergrafik.fraktal.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.ButtonModel;
import javax.swing.DefaultButtonModel;
import javax.swing.SwingUtilities;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller.ModulViewController;
import de.htwk.leipzig.mib08.computergrafik.fraktal.gui.OpenGlPanel;
import de.htwk.leipzig.mib08.computergrafik.fraktal.model.Triangle3D;
import de.htwk.leipzig.mib08.computergrafik.fraktal.process.FraktalesGebirgeModulProcess;

public class MainFrameController extends ModulViewController<FraktalesGebirgeModulProcess, Triangle3D, OpenGlController> implements ActionListener {

	private ClickAndZoomMouseAdapter mosueListener;
	private ButtonModel buttonModelRekTiefe1;
	private ButtonModel buttonModelRekTiefe3;
	private ButtonModel buttonModelRekTiefeZwei;
	private ButtonModel buttonModelRekVier;
	private ButtonModel buttonModelRekFuenf;
	private ButtonModel buttonModelRekSechs;
	private ButtonModel buttonModelInfo;
	private ButtonModel buttonModelNeu;
	private ButtonModel buttonModelBeenden;
	
	private class ClickAndZoomMouseAdapter extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent arg0) {

			if (SwingUtilities.isLeftMouseButton(arg0)) {
				getContentController().rotateX();
				getModulProcess().repaint();
			}
			if (SwingUtilities.isRightMouseButton(arg0)) {
				getContentController().rotateY();
				getModulProcess().repaint();
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

	public ButtonModel getRekTiefeEinsModel() {
		if (buttonModelRekTiefe1 == null) {
			buttonModelRekTiefe1 = new DefaultButtonModel();
			buttonModelRekTiefe1.addActionListener(this);
		}
		return buttonModelRekTiefe1;
	}
	
	public ButtonModel getRekTiefeDreiModel() {
		if (buttonModelRekTiefe3 == null) {
			buttonModelRekTiefe3 = new DefaultButtonModel();
			buttonModelRekTiefe3.addActionListener(this);
		}
		return buttonModelRekTiefe3;
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
			if (source == getRekTiefeEinsModel()) {
				getContentController().setRekTiefe(1);
			} else if (source == getRekTiefeDreiModel()) {
				getContentController().setRekTiefe(3);
			} else if (source == getRekTiefeZweiModel()) {
				getContentController().setRekTiefe(2);
			} else if (source == getRekTiefeVierModel()) {
				getContentController().setRekTiefe(4);
			} else if (source == getRekTiefeFuenfModel()) {
				getContentController().setRekTiefe(5);
			} else if (source == getRekTiefeSechsModel()) {
				getContentController().setRekTiefe(6);
			} else if (source == getInfoButtonModel()) {
				getModulProcess().showInfoDialog();
			} else if (source == getNeuButtonModel()) {
				getModulProcess().start();
			} else if (source == getBeendenButtonModel()) {
				getModulProcess().quit();
			}
//			getModulProcess().repaint();
		} finally {
			unblockView();
			unSetUpdatingForm();
		}
	}

	public ButtonModel getRekTiefeZweiModel() {
		if (buttonModelRekTiefeZwei == null) {
			buttonModelRekTiefeZwei = new DefaultButtonModel();
			buttonModelRekTiefeZwei.addActionListener(this);
		}
		return buttonModelRekTiefeZwei;
	}

	public ButtonModel getRekTiefeSechsModel() {
		if (buttonModelRekSechs == null) {
			buttonModelRekSechs = new DefaultButtonModel();
			buttonModelRekSechs.addActionListener(this);
		}
		return buttonModelRekSechs;
	}

	public ButtonModel getRekTiefeFuenfModel() {
		if (buttonModelRekFuenf == null) {
			buttonModelRekFuenf = new DefaultButtonModel();
			buttonModelRekFuenf.addActionListener(this);
		}
		return buttonModelRekFuenf;
	}

	public ButtonModel getRekTiefeVierModel() {
		if (buttonModelRekVier == null) {
			buttonModelRekVier = new DefaultButtonModel();
			buttonModelRekVier.addActionListener(this);
		}
		return buttonModelRekVier;
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
	
}
