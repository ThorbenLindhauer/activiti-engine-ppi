package de.unipotsdam.hpi.thorben.ppi.measure.process;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.unipotsdam.hpi.thorben.ppi.typehelper.IntegerHelper;

public class IntegerHelperTest extends AbstractTypeHelperTest {

	private Integer valueA = new Integer(4);
	private Integer valueB = new Integer(2);
	private IntegerHelper helper;

	@Before
	public void setUp() {
		helper = new IntegerHelper();
	}

	@Test
	public void testSum() {
		Integer sum = helper.add(valueA, valueB);
		Integer expectedSum = valueA + valueB;
		Assert.assertEquals(expectedSum, sum);
	}

	@Test
	public void testDivision() {
		Integer division = helper.divide(valueA, valueB);
		Integer expectedSum = valueA / valueB;
		Assert.assertEquals(expectedSum, division);
	}

	@Override
	public void testZeroValue() {
		Integer zeroValue = helper.createZero();
		Assert.assertEquals(new Integer(0), zeroValue);
	}

	@Override
	public void testMinValue() {
		Integer minValue = helper.createMinValue();
		Assert.assertEquals(new Integer(Integer.MIN_VALUE), minValue);
	}

	@Override
	public void testMaxValue() {
		Integer maxValue = helper.createMaxValue();
		Assert.assertEquals(new Integer(Integer.MAX_VALUE), maxValue);
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
