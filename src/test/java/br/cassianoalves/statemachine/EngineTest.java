package br.cassianoalves.statemachine;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.cassianoalves.statemachine.exception.StimulusNotExpected;

public class EngineTest {
	
	ArrayList<Integer> checkList;
	ArrayList<Integer> checkNotify;
	StateActions stateActions = new StateActions() {
		
                @Override
		public Integer executeStateAction(Integer state) {
			checkList.add(state);
			Integer s = null;
			
			switch (state) {
			case 0:
				s = null;
				break;
			case 1:
				s = 0;
				break;
			case 2:
				s = 1;
				break;
			}

			return s;
		}
	};
	
	@Test
	public void testExecuteMachine() throws StimulusNotExpected {
		Integer[][] stateTable = {
		         /* IN1    IN2  */    
		/* ST1 */ {  1   , null },
		/* ST2 */ {  2   , null },
		/* ST3 */ { null ,  0   },
		};
		
		Observer stateObserver = new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				StateMachineEngine sm = (StateMachineEngine) o; 
				checkNotify.add(sm.getState());
			}
		};
		
		StateMachineEngine instance = new StateMachineEngine(stateTable, stateActions);
		checkNotify = new ArrayList<Integer>();
		checkList = new ArrayList<Integer>();
		ArrayList<Integer> expectStates = new ArrayList<Integer>();
		expectStates.add(1);
		expectStates.add(2);
		expectStates.add(0);
		
		ArrayList<Integer> expectNotify = new ArrayList<Integer>();
		expectNotify.add(0);
		expectNotify.add(1);
		expectNotify.add(2);
		expectNotify.add(0);

		instance.addObserver(stateObserver);
		instance.setState(0); // Estado inicial ST1 (0)
		instance.go(0);       // Estimulo inicial IN1 (0)
		
		assertEquals("Sequencia de estados incorreta", expectStates, checkList);
		assertEquals("Notificacoes indicaram sequencia de estados incorreta", expectNotify, checkNotify);
		assertEquals(0, instance.getState().intValue());
	}
	
	@Test
	public void testMachineTryInvalidState() {
		Integer[][] stateTable = {
		         /* IN1    IN2  */    
		/* ST1 */ {  1   , null },
		/* ST2 */ {  2   , null },
		/* ST3 */ { null , null },
		};

		StateMachineEngine instance = new StateMachineEngine(stateTable, stateActions);
		checkList = new ArrayList<Integer>();
		ArrayList<Integer> expectStates = new ArrayList<Integer>();
		expectStates.add(1);
		expectStates.add(2);
		
		instance.setState(0); // Estado inicial ST1 (0)
		
		boolean exceptionThrowed = false;
		try {
			instance.go(0); // Estimulo inicial IN1 (0)
		} catch (StimulusNotExpected e) {
			exceptionThrowed = true;
		}
		
		assertTrue("Excecao nao lancada", exceptionThrowed);
		assertEquals("Sequencia de estados incorreta", expectStates, checkList);		
		assertEquals(2, instance.getState().intValue());
	}
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}


}
