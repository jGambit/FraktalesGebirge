package de.htwk.leipzig.mib08.computergrafik.fraktal.process;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.htwk.leipzig.mib08.computergrafik.fraktal.controller.MainFrame;

public class FraktalesGebirgeMainProcess {

	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (UnsupportedLookAndFeelException ex) {
	        ex.printStackTrace();
	    } catch (IllegalAccessException ex) {
	        ex.printStackTrace();
	    } catch (InstantiationException ex) {
	        ex.printStackTrace();
	    } catch (ClassNotFoundException ex) {
	        ex.printStackTrace();
	    }
		MainFrame app = new MainFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(900, 900);
		app.setResizable(false);
		app.setTitle("MainFrame");
		app.setLocation(100, 100);
	}

}
