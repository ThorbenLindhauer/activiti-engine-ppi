package de.unipotsdam.hpi.thorben.ppi.measure;

import org.apache.log4j.Logger;

public class BaseMeasure {
	private Logger log = Logger.getLogger(BaseMeasure.class);
	private String id;

	public BaseMeasure(String id) {
		this.id = id;
	}
	
	public void measure() {
		log.info("executed measure");
	}
}
