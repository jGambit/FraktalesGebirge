package de.htwk.leipzig.mib08.computergrafik.fraktal.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import javax.swing.JMenuItem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.htwk.leipzig.mib08.computergrafik.fraktal.gui.MyPanel;

public class MainViewControllerTest {
	
	private MainFrame mainFrame;
	@Mock private JMenuItem mockItem;
	@Mock private MyPanel myPanel;
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		mainFrame = new MainFrame();
	}
	
	@Test
	public void testVisible() {
		assertTrue(mainFrame.isVisible());
		
		mainFrame.setVisible(false);
		
		assertFalse(mainFrame.isVisible());
	}
	
	@Test
	public void testMenu() {
		mainFrame.gp = myPanel;
		
		mainFrame.menuItemNeu.doClick();
		
		verify(myPanel).display();
	}
	
}
