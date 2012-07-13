package de.unipotsdam.hpi.thorben.ppi.measure.process;

import org.junit.Assert;
import org.junit.Before;

public class DoubleHelperTest extends AbstractTypeHelperTest {

	private Double valueA = new Double(4.0);
	private Double valueB = new Double(2.0);
	private DoubleHelper helper;
	
	@Before
	public void setUp() {
		helper = new DoubleHelper();
	}
		
	public void testSum() {
		Double sum = helper.add(valueA, valueB);
		Double expectedSum = valueA + valueB;
		Assert.assertEquals(expectedSum, sum);
	}	
	
	public void testDivision() {
		Double division = helper.divide(valueA, valueB);
		Double expectedSum = valueA / valueB;
		Assert.assertEquals(expectedSum, division);
	}

	public void testZeroValue() {
		Double zeroValue = helper.createZero();
		Assert.assertEquals(new Double(0.0), zeroValue);
	}

	public void testMinValue() {
		Double minValue = helper.createMinValue();
		Assert.assertEquals(new Double(-Double.MAX_VALUE), minValue);
	}

	public void testMaxValue() {
		Double maxValue = helper.createMaxValue();
		Assert.assertEquals(new Double(Double.MAX_VALUE), maxValue);
	}

	public void testGreaterThan() {
		boolean greaterThan = helper.greaterThan(valueA, valueB);
		Assert.assertTrue(greaterThan);
	}

	public void testLowerThan() {
		boolean lowerThan = helper.lowerThan(valueA, valueB);
		Assert.assertFalse(lowerThan);
	}
}
