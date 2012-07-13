package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance;

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
		// TODO implement
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

}
