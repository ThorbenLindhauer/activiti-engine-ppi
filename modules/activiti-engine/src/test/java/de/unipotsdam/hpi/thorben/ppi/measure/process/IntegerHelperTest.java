package de.unipotsdam.hpi.thorben.ppi.measure.process;

import org.junit.Assert;
import org.junit.Test;

public class IntegerHelperTest {

	private Integer valueA = new Integer(4);
	private Integer valueB = new Integer(2);
		
	@Test
	public void testSum() {
		IntegerHelper helper = new IntegerHelper();
		Integer sum = helper.add(valueA, valueB);
		Integer expectedSum = valueA + valueB;
		Assert.assertEquals(expectedSum, sum);
	}	
	
	@Test
	public void testDivision() {
		IntegerHelper helper = new IntegerHelper();
		Integer division = helper.divide(valueA, valueB);
		Integer expectedSum = valueA / valueB;
		Assert.assertEquals(expectedSum, division);
	}
}
