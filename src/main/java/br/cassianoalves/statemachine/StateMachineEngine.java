package br.cassianoalves.statemachine;

import java.util.Observable;

import br.cassianoalves.statemachine.exception.StimulusNotExpected;

/**
 * Motor da maquina de estado.
 * 
 * Recebe uma tabela estado x estimulo e um metodo a ser executado
 * no momento da mudanca de estado que executara a acao para cada estado.
 * Este metodo pode retornar um proximo estimulo a ser enviado 'a maquina
 * de estado.
 * 
 * Como utilizar:
 * - Defina os estados e estimulos
 * - Crie uma tabela atraves de uma variavel Integer[][]
 * onde o elemento sera o estado provocado pelo estimulo ex,:
 *     proximoEstado[estadoAtual][estimuloSobreEsteEstado]
 * - Implemente a interface StateActions. O metodo executeStateAction deve:
 *   - Receber o estado atual
 *   - Executar a acao (metodo) daquele estado
 *   - Retornar, se necessario, o proximo estimulo que se quer enviar a maquina de estado
 *     Caso nao se queira enviar um novo estimulo 'a maquina de estados, retorne null
 *      
 * @author cassiano
 *
 */
public class StateMachineEngine extends Observable {
	
	private Integer[][] stateTable;
	private StateActions stateActions;
	private Integer state;
	
	public void setStateActions(StateActions stateActions) {
		this.stateActions = stateActions;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer newstate) {
		if (newstate.intValue() != state.intValue()) setChanged();
		this.state = newstate;
		notifyObservers();
	}
	

	public StateMachineEngine(Integer[][] tab, StateActions act) {
		stateTable = tab;
		stateActions = act;
		state = new Integer(0); // Estado inicial default
	}
	
	
	public void go(Integer startStimulus) throws StimulusNotExpected {	
		setChanged(); notifyObservers(); // Para indicar aos observers o inicio e o estado inicial
		iterateStates(state, startStimulus);
	}
	
	/**
	 * Metodo recursivo com um processo iterativo que 
	 * movimenta a maquina de estado
	 * 
	 * @param iterState Estado Inicial
	 * @param stimulus Estimulo aplicado no estado inicial
	 * @throws StimulusNotExpected Caso o estimulo nao esteja previsto para aquele estado
	 */
	private void iterateStates(Integer iterState, Integer stimulus) throws StimulusNotExpected {
		Integer nextState = stateTable[iterState.intValue()][stimulus.intValue()];
		
		
		if (nextState == null) {
			throw new StimulusNotExpected(iterState.intValue(), stimulus.intValue());
		}
		setState(nextState);
		Integer nextStimulus = stateActions.executeStateAction(nextState);
		
		if (nextStimulus != null) {
			iterateStates(nextState, nextStimulus);
		} 
	}
	
	

}
