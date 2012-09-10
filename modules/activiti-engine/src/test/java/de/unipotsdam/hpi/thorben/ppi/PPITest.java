/* Copyright 2012 Thorben Lindhauer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
