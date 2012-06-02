package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.TimeMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureValue;

public class AggregatedMeasureTest {

	private TimeMeasure measure;
	private TimeMeasureValue val1;
	private TimeMeasureValue val2;
	private TimeMeasureValue val3;
	private LongHelper longHelper;
	@Before
	public void setUp() throws Exception {
		val1 = Mockito.mock(TimeMeasureValue.class);
		val2 = Mockito.mock(TimeMeasureValue.class);
		val3 = Mockito.mock(TimeMeasureValue.class);
		Mockito.when(val1.calculate()).thenReturn(4500);
		Mockito.when(val2.calculate()).thenReturn(3000);
		Mockito.when(val3.calculate()).thenReturn(7500);
		
		List<TimeMeasureValue> values = new ArrayList<TimeMeasureValue>();
		values.add(val1);
		values.add(val2);
		values.add(val3);
		
		measure = Mockito.mock(TimeMeasure.class);
		Mockito.when(measure.getAllValues()).thenReturn(values);
		longHelper = new LongHelper();
	}

	@Test
	public void testAverageTimeAggregation() {
		AggregatedMeasure<TimeMeasure, TimeMeasureValue, Long> aggMeasure = new AggregatedMeasure<TimeMeasure, TimeMeasureValue, Long>();
		aggMeasure.setBaseMeasure(measure);
		AggregationFunction<Long, TimeMeasureValue> function = new AverageFunction<Long, TimeMeasureValue>(longHelper);
		aggMeasure.setAggregationFunction(function);
		
		Long averageTime = aggMeasure.calculate();
		Assert.assertEquals(new Long(5000), averageTime);
	}

}
