package br.cassianoalves.statemachine.exception;

public class StimulusNotExpected extends Exception {
	private static final long serialVersionUID = -7302859184135994782L;
	private int state;
	private int stimuli;
	
	public StimulusNotExpected(int sta, int sti) {
		state = sta;
		stimuli = sti;
	}
	
	@Override
	public String getMessage() {
		return "Maquina de estado recebeu um estimulo nao esperado para o estado atual. state: " + state + "stimulus: " + stimuli;
	}

}
