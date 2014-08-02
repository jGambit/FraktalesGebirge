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
	
		JMenuBar Leiste = new JMenuBar();
		JMenu Datei = new JMenu("Datei");
		JMenu Modus = new JMenu("Modus");
		JMenu Rekursionstiefe = new JMenu("Rekursionstiefe");
		JMenu Hilfe = new JMenu("Hilfe");
	
		
		
		
		JMenuItem Neu = new JMenuItem("Neu");
		JMenuItem Beenden = new JMenuItem("Beenden");
		JRadioButtonMenuItem Dreiecke = new JRadioButtonMenuItem("Linien");
		JRadioButtonMenuItem Pyramiden = new JRadioButtonMenuItem("Flächen");
		JRadioButtonMenuItem Beides = new JRadioButtonMenuItem("Linien und Flächen");
		JRadioButtonMenuItem rekTiefe1 = new JRadioButtonMenuItem("1");
		JRadioButtonMenuItem rekTiefe2 = new JRadioButtonMenuItem("3");
		JRadioButtonMenuItem rekTiefe3 = new JRadioButtonMenuItem("6");
		JRadioButtonMenuItem rekTiefe4 = new JRadioButtonMenuItem("8");
		JRadioButtonMenuItem rekTiefe5 = new JRadioButtonMenuItem("10");
		JRadioButtonMenuItem rekTiefe6 = new JRadioButtonMenuItem("13");
		
	
		JMenuItem Info = new JMenuItem("Info");
		
		JPanel hoehePanel = new JPanel();
		JTextArea hoeheTextArea = new JTextArea();
		JButton hoeheButton = new JButton();
	
public MainFrame() {
	

	Leiste.add(Datei);
	Leiste.add(Modus);
	Leiste.add(Rekursionstiefe);
	Leiste.add(Hilfe);

	
	Datei.add(Neu);
	Datei.add(Beenden);
	
	Modus.add(Dreiecke);
	Modus.add(Pyramiden);
	Modus.add(Beides);
	
	Rekursionstiefe.add(rekTiefe1);
	Rekursionstiefe.add(rekTiefe2);
	Rekursionstiefe.add(rekTiefe3);
	Rekursionstiefe.add(rekTiefe4);
	Rekursionstiefe.add(rekTiefe5);
	Rekursionstiefe.add(rekTiefe6);
	
	rekTiefe2.setSelected(true);
	Dreiecke.setSelected(true);
	
	Hilfe.add(Info);
	
	setJMenuBar(Leiste);

	cp = (JPanel) this.getContentPane();
	cp.setLayout(new BorderLayout());
	cp.add(gp, BorderLayout.CENTER);
	cp.add(hoehePanel, BorderLayout.NORTH);
	hoehePanel.add(hoeheTextArea);
	hoehePanel.add(hoeheButton);
	hoeheTextArea.setColumns(3);
	
	

	Neu.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
			gp.flagX = false;
			gp.flagY = false;
			gp.rekTiefe = 5;
			gp.display();
			
		}
	});
	
	
	Beenden.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
			int a;
			a=JOptionPane.showConfirmDialog(getRootPane(), "Beenden, echt jetz oder?");
			System.out.println("a:"+a);
			if(a==0)System.exit(0);
		}
	});
	

	Dreiecke.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
			if(Beides.isSelected())Beides.setSelected(false);
			if(Pyramiden.isSelected())Pyramiden.setSelected(false);
			Dreieck.mode = 0;
			gp.display();
		}
	});
	
	Pyramiden.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
			if(Dreiecke.isSelected())Dreiecke.setSelected(false);
			if(Beides.isSelected())Beides.setSelected(false);
			Dreieck.mode = 1;
			gp.display();
		}
	});
	
	Beides.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
			if(Dreiecke.isSelected())Dreiecke.setSelected(false);
			if(Pyramiden.isSelected())Pyramiden.setSelected(false);
			Dreieck.mode = 2;
			gp.display();
		}
	});
	
	rekTiefe1.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
	
			if(rekTiefe2.isSelected())rekTiefe2.setSelected(false);
			if(rekTiefe3.isSelected())rekTiefe3.setSelected(false);
			if(rekTiefe4.isSelected())rekTiefe4.setSelected(false);
			if(rekTiefe5.isSelected())rekTiefe5.setSelected(false);
			if(rekTiefe6.isSelected())rekTiefe6.setSelected(false);

			MyPanel.rekTiefe = 1;
			gp.display();
		}
	});
	
	rekTiefe2.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
	
			if(rekTiefe1.isSelected())rekTiefe1.setSelected(false);
			if(rekTiefe3.isSelected())rekTiefe3.setSelected(false);
			if(rekTiefe4.isSelected())rekTiefe4.setSelected(false);
			if(rekTiefe5.isSelected())rekTiefe5.setSelected(false);
			if(rekTiefe6.isSelected())rekTiefe6.setSelected(false);
			
			MyPanel.rekTiefe = 3;
			gp.display();
		}
	});
	
	rekTiefe3.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
		
			if(rekTiefe2.isSelected())rekTiefe2.setSelected(false);
			if(rekTiefe1.isSelected())rekTiefe1.setSelected(false);
			if(rekTiefe4.isSelected())rekTiefe4.setSelected(false);
			if(rekTiefe5.isSelected())rekTiefe5.setSelected(false);
			if(rekTiefe6.isSelected())rekTiefe6.setSelected(false);
			MyPanel.rekTiefe = 6;	
			gp.display();

		}
	});
	rekTiefe4.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
	
			if(rekTiefe2.isSelected())rekTiefe2.setSelected(false);
			if(rekTiefe3.isSelected())rekTiefe3.setSelected(false);
			if(rekTiefe1.isSelected())rekTiefe1.setSelected(false);
			if(rekTiefe5.isSelected())rekTiefe5.setSelected(false);
			if(rekTiefe6.isSelected())rekTiefe6.setSelected(false);
			MyPanel.rekTiefe = 8;
			
			gp.display();
		}
	});
	rekTiefe5.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
	
			if(rekTiefe2.isSelected())rekTiefe2.setSelected(false);
			if(rekTiefe3.isSelected())rekTiefe3.setSelected(false);
			if(rekTiefe4.isSelected())rekTiefe4.setSelected(false);
			if(rekTiefe1.isSelected())rekTiefe1.setSelected(false);
			if(rekTiefe6.isSelected())rekTiefe6.setSelected(false);
			MyPanel.rekTiefe = 10;
			gp.display();
		}
	});
	rekTiefe6.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
	
			if(rekTiefe2.isSelected())rekTiefe2.setSelected(false);
			if(rekTiefe3.isSelected())rekTiefe3.setSelected(false);
			if(rekTiefe4.isSelected())rekTiefe4.setSelected(false);
			if(rekTiefe5.isSelected())rekTiefe5.setSelected(false);
			if(rekTiefe1.isSelected())rekTiefe1.setSelected(false);
			MyPanel.rekTiefe = 13;
			gp.display();
		}
	});
	
	Info.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e) {
		show_info();
		}
	});
	
	hoeheButton.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e) {
			Dreieck.hoehe = new Integer(hoeheTextArea.getText());
			System.out.println(hoeheTextArea.getText());
			gp.display();
			
		}
	});
	
	this.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent arg0) {
			
			if(SwingUtilities.isLeftMouseButton(arg0)){
			gp.flagX = true;
			gp.display();
			}
			if(SwingUtilities.isRightMouseButton(arg0)){
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
			}
			else {
				// zoom out
				gp.zoomOut = true;
				gp.display();
			}
			
		}
	});
	

}



private void show_info(){
	JOptionPane.showMessageDialog(this,"Version 0.67 Beta","Copyright Dani & Enno",JOptionPane.INFORMATION_MESSAGE);
}

}