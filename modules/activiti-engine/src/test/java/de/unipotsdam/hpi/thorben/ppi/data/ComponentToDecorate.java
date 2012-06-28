package de.unipotsdam.hpi.thorben.ppi.data;

public class ComponentToDecorate {

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
