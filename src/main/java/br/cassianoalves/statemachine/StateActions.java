package br.cassianoalves.statemachine;


public interface StateActions {
	/**
	 * Metodo executado a cada mudanca de estado.
	 * Implemente o metodo executeStateAction com um
	 * switch/case relacionando cada estado a um metodo
	 * a ser executado. Este metodo deve retornar o
	 * proximo Estimulo a ser enviado 'a maquina de 
	 * estado para ser executada.
	 * 
	 * @param state Estado em que a maquina entrou
	 * @return Proximo Estimulo a ser enviado 'a maquina de estado
	 */
	Integer executeStateAction(Integer state);
}
