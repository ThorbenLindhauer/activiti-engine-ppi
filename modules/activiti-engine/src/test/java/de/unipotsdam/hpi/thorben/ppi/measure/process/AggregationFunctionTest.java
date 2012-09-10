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

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ActivitiException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.DataMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.typehelper.IntegerHelper;

public class AggregationFunctionTest {

	private static final Integer INSTANCES_COUNT = new Integer(10);
	private static final Integer DEFAULT_COUNT_VALUE = new Integer(5);

	List<CountMeasureInstance> instances;

	@Before
	public void setUp() {
		instances = new ArrayList<CountMeasureInstance>();
		for (int i = 0; i < INSTANCES_COUNT; i++) {
			CountMeasureInstance instance = Mockito
					.mock(CountMeasureInstance.class);
			Mockito.when(instance.calculate()).thenReturn(DEFAULT_COUNT_VALUE);
			instances.add(instance);
		}
	}

	@Test
	public void testAverageFunction() {
		AverageFunction<Integer, CountMeasureInstance> averageFunction = new AverageFunction<Integer, CountMeasureInstance>(
				new IntegerHelper());
		Integer average = averageFunction.calculate(instances);
		Assert.assertEquals(DEFAULT_COUNT_VALUE, average);
	}

	@Test
	public void testSumFunction() {
		SumFunction<Integer, CountMeasureInstance> averageFunction = new SumFunction<Integer, CountMeasureInstance>(
				new IntegerHelper());
		Integer average = averageFunction.calculate(instances);
		Integer expectedSum = DEFAULT_COUNT_VALUE * INSTANCES_COUNT;
		Assert.assertEquals(expectedSum, average);
	}
	
	@Test
	public void testMaxFunction() {
		MaximumFunction<Integer, CountMeasureInstance> maxFunction = new MaximumFunction<Integer, CountMeasureInstance>(new IntegerHelper());
		
		Integer maximum = new Integer(6);
		CountMeasureInstance maximumInstance = Mockito
				.mock(CountMeasureInstance.class);
		Mockito.when(maximumInstance.calculate()).thenReturn(maximum);
		
		instances.add(maximumInstance);
		Integer calculatedMaximum = maxFunction.calculate(instances);
		Assert.assertEquals(maximum, calculatedMaximum);
	}
	
	@Test
	public void testMinFunction() {
		MinimumFunction<Integer, CountMeasureInstance> minFunction = new MinimumFunction<Integer, CountMeasureInstance>(new IntegerHelper());
		
		Integer minimum = new Integer(4);
		CountMeasureInstance minimumInstance = Mockito
				.mock(CountMeasureInstance.class);
		Mockito.when(minimumInstance.calculate()).thenReturn(minimum);
		
		instances.add(minimumInstance);
		Integer calculatedMinimum = minFunction.calculate(instances);
		Assert.assertEquals(minimum, calculatedMinimum);
	}

	/**
	 * Tests whether return values of another type are treated as the specified
	 * parameterized type in the aggregation function.
	 */
	@Test
	public void testDifferentReturnTypes() {
		Double instanceValue = new Double(4.2);

		instances = new ArrayList<CountMeasureInstance>();
		for (int i = 0; i < INSTANCES_COUNT; i++) {
			CountMeasureInstance instance = Mockito
					.mock(CountMeasureInstance.class);
			Mockito.when(instance.calculate()).thenReturn(instanceValue);
			instances.add(instance);
		}

		AverageFunction<Integer, CountMeasureInstance> averageFunction = new AverageFunction<Integer, CountMeasureInstance>(
				new IntegerHelper());
		Integer average = averageFunction.calculate(instances);
		Assert.assertEquals(new Integer(4), average);
	}
	
	@Test
	public void testNonExistingInstanceValues() {
		instances = new ArrayList<CountMeasureInstance>();
		AverageFunction<Integer, CountMeasureInstance> averageFunction = new AverageFunction<Integer, CountMeasureInstance>(
				new IntegerHelper());
		try {
			averageFunction.calculate(instances);
			Assert.fail("Should not be successful");
		} catch (ActivitiException e) {
			// happy path
		}
	}
	
	@Test
	public void testNonExistingInstances() {
		AverageFunction<Integer, DataMeasureInstance> avgFunction = new AverageFunction<Integer, DataMeasureInstance>(new IntegerHelper());
		
		try {
			avgFunction.calculate(null);
			Assert.fail("The function should not be calculated successfully.");
		} catch (ActivitiException e) {
			// happy path
		}
		
	}

}
