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
