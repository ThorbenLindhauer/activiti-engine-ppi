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
