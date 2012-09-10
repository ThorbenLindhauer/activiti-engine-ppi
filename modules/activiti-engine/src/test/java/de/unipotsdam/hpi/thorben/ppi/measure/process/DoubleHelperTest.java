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

package de.unipotsdam.hpi.thorben.ppi.measure.process;

import org.junit.Assert;
import org.junit.Before;

import de.unipotsdam.hpi.thorben.ppi.typehelper.DoubleHelper;

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
