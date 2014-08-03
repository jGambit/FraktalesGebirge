package de.htwk.leipzig.mib08.computergrafik.fraktal.base.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.nio.channels.IllegalSelectorException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import de.htwk.leipzig.mib08.computergrafik.fraktal.base.exception.ToBeHandledByApplicationException;
import de.htwk.leipzig.mib08.computergrafik.fraktal.base.process.ModulProcessIF;
import de.htwk.leipzig.mib08.computergrafik.fraktal.test.Foo;


public class ModulControllerTest {

	private class TestUpdatingModulController extends
			TestModulController {
		public TestUpdatingModulController(ModulProcessIF modulProcess) {
			super(modulProcess);
		}

		@Override
		protected void setUpdatingForm() {
			setUpdatingMock.bar();
		}

		@Override
		protected void unSetUpdatingForm() {
			unSetUpdatingMock.bar();
		}
	}

	private class TestModulController extends
			ModulController<ModulProcessIF, Foo> {

		public TestModulController(ModulProcessIF modulProcess) {
			super(modulProcess);
		}

		@Override
		protected void clearFormImpl() throws ToBeHandledByApplicationException {
		}

		@Override
		protected void fillFormImpl(Foo config)
				throws ToBeHandledByApplicationException {
		}
	}
	
	private class ExceptionFoo {

		void bar() throws ToBeHandledByApplicationException {
			throw new ToBeHandledByApplicationException("Bar");
		}
		
	}

	ModulController<ModulProcessIF, Foo> toTest;
	@Mock private Foo setUpdatingMock;
	@Mock private Foo unSetUpdatingMock;
	@Mock private Foo implMock;
	@Mock private ModulProcessIF modulProcess;
	@Mock private Foo currentObject;
	private ExceptionFoo exceptionFoo;
	
	@Before
	public void before() {
		initMocks(this);
		toTest = new TestModulController(modulProcess);
		exceptionFoo = new ExceptionFoo();
	}
	
	@Test
	public void testSetUpdatingForm() throws Exception {
		assertFalse(toTest.isUpdatingForm());
		
		toTest.setUpdatingForm();
		
		assertTrue(toTest.isUpdatingForm());
	}

	@Test
	public void testUnSetUpdatingForm() throws Exception {
		toTest.setUpdatingForm();
		toTest.unSetUpdatingForm();
		assertFalse(toTest.isUpdatingForm());
	}

	@Test
	public void testClearForm() throws Exception {
		toTest = new TestUpdatingModulController(modulProcess) {
			@Override
			protected void clearFormImpl() {
				implMock.bar();
			}
		};
		toTest.setCurrentObject(currentObject);
		
		toTest.clearForm();
		
		InOrder order = inOrder(setUpdatingMock, implMock, unSetUpdatingMock);
		order.verify(setUpdatingMock).bar();
		order.verify(implMock).bar();
		order.verify(unSetUpdatingMock).bar();
		assertTrue(toTest.isCleared());
		assertNull(toTest.getCurrentObject());
	}
	
	@Test
	public void testClearFormWithException() throws Exception {
		doThrow(new IllegalSelectorException()).when(modulProcess).startExceptionDialog(any(ToBeHandledByApplicationException.class));
		toTest = new TestUpdatingModulController(modulProcess) {
			@Override
			protected void clearFormImpl() throws ToBeHandledByApplicationException {
				exceptionFoo.bar();
			}
		};
		toTest.setCurrentObject(currentObject);
		
		try {
			toTest.clearForm();
		} catch (IllegalSelectorException e) {}
		
		verify(setUpdatingMock).bar();
		verify(unSetUpdatingMock).bar();
		assertFalse(toTest.isCleared());
		assertNotNull(toTest.getCurrentObject());
	}

	@Test
	public void testFillForm() throws Exception {
		toTest = new TestUpdatingModulController(modulProcess) {
			@Override
			protected void fillFormImpl(Foo config)
					throws ToBeHandledByApplicationException {
				implMock.bar();
			}
		};
		
		toTest.fillForm(currentObject);
		
		InOrder order = inOrder(setUpdatingMock, implMock, unSetUpdatingMock);
		order.verify(setUpdatingMock).bar();
		order.verify(implMock).bar();
		order.verify(unSetUpdatingMock).bar();
	}
	
	@Test
	public void testFillFormWithException() throws Exception {
		doThrow(new IllegalStateException()).when(modulProcess).startExceptionDialog(any(ToBeHandledByApplicationException.class));
		toTest = new TestUpdatingModulController(modulProcess) {
			@Override
			protected void fillFormImpl(Foo config)
					throws ToBeHandledByApplicationException {
				exceptionFoo.bar();
			}
		};
		
		try {
			toTest.fillForm(currentObject);
		} catch (IllegalStateException e) {}
		
		verify(setUpdatingMock).bar();
		verify(unSetUpdatingMock).bar();
	}
	
}
