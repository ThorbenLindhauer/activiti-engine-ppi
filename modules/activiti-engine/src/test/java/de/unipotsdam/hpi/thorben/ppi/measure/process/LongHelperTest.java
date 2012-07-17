package de.unipotsdam.hpi.thorben.ppi.measure.process;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.unipotsdam.hpi.thorben.ppi.typehelper.LongHelper;

public class LongHelperTest extends AbstractTypeHelperTest {

	private Long valueA = new Long(4);
	private Long valueB = new Long(2);
	private LongHelper helper;

	@Before
	public void setUp() {
		helper = new LongHelper();
	}
	
	@Test
	public void testSum() {
		Long sum = helper.add(valueA, valueB);
		Long expectedSum = valueA + valueB;
		Assert.assertEquals(expectedSum, sum);
	}	
	
	@Test
	public void testDivision() {
		Long division = helper.divide(valueA, valueB);
		Long expectedSum = valueA / valueB;
		Assert.assertEquals(expectedSum, division);
	}
	
	@Override
	public void testZeroValue() {
		Long zeroValue = helper.createZero();
		Assert.assertEquals(new Long(0), zeroValue);
	}

	@Override
	public void testMinValue() {
		Long minValue = helper.createMinValue();
		Assert.assertEquals(new Long(Long.MIN_VALUE), minValue);
	}

	@Override
	public void testMaxValue() {
		Long maxValue = helper.createMaxValue();
		Assert.assertEquals(new Long(Long.MAX_VALUE), maxValue);
	}

	@Override
	public void testGreaterThan() {
		boolean greaterThan = helper.greaterThan(valueA, valueB);
		Assert.assertTrue(greaterThan);

	}

	@Override
	public void testLowerThan() {
		boolean lowerThan = helper.lowerThan(valueA, valueB);
		Assert.assertFalse(lowerThan);
	}
}
