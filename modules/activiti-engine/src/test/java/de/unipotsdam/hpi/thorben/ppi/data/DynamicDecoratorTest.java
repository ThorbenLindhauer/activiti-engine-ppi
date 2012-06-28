package de.unipotsdam.hpi.thorben.ppi.data;

import org.junit.Assert;
import org.junit.Test;

public class DynamicDecoratorTest {

	/**
	 * Summary: 
	 * 1. Create an object of a certain type. 
	 * 2. Decorate this object at runtime without knowing the objects type in order to
	 * 3. Catch all updates of a certain property X (through any operation that updates X). 
	 */
	@Test
	public void testDynamicDecoration() {
		// Phase 1
		ComponentToDecorate toDecorate = new ComponentToDecorate();
		
		// Phase 2
		// our decoration logic should be added to any object
		FieldTraceToolBox toolBox = FieldTraceToolBox.createNew(toDecorate, "number");
		DataPropertyListener listener = toolBox.getListener();
		ComponentToDecorate toDecorateProxy = (ComponentToDecorate) toolBox.getProxy();
		
		// Phase 3
		toDecorateProxy.setNumber(4);
		Assert.assertEquals(4, listener.getCurrentValue());
		
		// TODO: assertions
		
		toDecorateProxy.incrementNumber();
		Assert.assertEquals(5, listener.getCurrentValue());
		
		// TODO: assertions
		
		
		
	}
}
