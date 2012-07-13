package de.unipotsdam.hpi.thorben.ppi.measure.process;

import org.junit.Assert;
import org.junit.Test;

public class DoubleHelperTest {

	private Double valueA = new Double(4.0);
	private Double valueB = new Double(2.0);
		
	@Test
	public void testSum() {
		DoubleHelper helper = new DoubleHelper();
		Double sum = helper.add(valueA, valueB);
		Double expectedSum = valueA + valueB;
		Assert.assertEquals(expectedSum, sum);
	}	
	
	@Test
	public void testDivision() {
		DoubleHelper helper = new DoubleHelper();
		Double division = helper.divide(valueA, valueB);
		Double expectedSum = valueA / valueB;
		Assert.assertEquals(expectedSum, division);
	}
}
