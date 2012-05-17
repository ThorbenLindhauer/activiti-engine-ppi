package de.unipotsdam.hpi.thorben.ppi.measure;

import org.apache.log4j.Logger;

public class BaseMeasure {
	private Logger log = Logger.getLogger(BaseMeasure.class);

	public void measure() {
		log.info("executed measure");
	}
}
