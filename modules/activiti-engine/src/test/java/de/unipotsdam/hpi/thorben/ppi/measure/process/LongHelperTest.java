package de.unipotsdam.hpi.thorben.ppi.measure.process;

import org.junit.Assert;
import org.junit.Test;

public class LongHelperTest {

	private Long valueA = new Long(4);
	private Long valueB = new Long(2);
		
	@Test
	public void testSum() {
		LongHelper helper = new LongHelper();
		Long sum = helper.add(valueA, valueB);
		Long expectedSum = valueA + valueB;
		Assert.assertEquals(expectedSum, sum);
	}	
	
	@Test
	public void testDivision() {
		LongHelper helper = new LongHelper();
		Long division = helper.divide(valueA, valueB);
		Long expectedSum = valueA / valueB;
		Assert.assertEquals(expectedSum, division);
	}
}
