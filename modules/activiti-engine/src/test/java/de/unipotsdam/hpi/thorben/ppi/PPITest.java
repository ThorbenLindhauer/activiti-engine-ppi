package de.unipotsdam.hpi.thorben.ppi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.unipotsdam.hpi.thorben.ppi.typehelper.IntegerHelper;

public class PPITest {

	private PPI ppi;
	
	@Before
	public void setUp() {
		ppi = new PPI();
		ppi.setTargetValue(new Integer(5));
	}
	
	@Test
	public void testPPILowerThanTargetFunction() {
		ppi.setTargetFunction(new LowerThanFunction<Integer>(new IntegerHelper()));
		boolean fulfilled = ppi.isFulfilledBy(new Integer(4));
		Assert.assertTrue(fulfilled);
		
		fulfilled = ppi.isFulfilledBy(new Integer(5));
		Assert.assertFalse(fulfilled);
	}
	
	@Test
	public void testPPIGreaterThanTargetFunction() {
		ppi.setTargetFunction(new GreaterThanFunction<Integer>(new IntegerHelper()));
		boolean fulfilled = ppi.isFulfilledBy(new Integer(4));
		Assert.assertFalse(fulfilled);
		
		fulfilled = ppi.isFulfilledBy(new Integer(6));
		Assert.assertTrue(fulfilled);
	}
}
