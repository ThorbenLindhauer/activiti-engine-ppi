package de.unipotsdam.hpi.thorben.ppi.data;

import java.io.Serializable;

public class ComponentToDecorate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int number;
	
	public ComponentToDecorate() {
		number = 1;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public void incrementNumber() {
		number++;
	}
}
