package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.TimeMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.typehelper.LongHelper;

public class AggregatedMeasureTest {

	private TimeMeasure measure;
	private TimeMeasureInstance val1;
	private TimeMeasureInstance val2;
	private TimeMeasureInstance val3;
	private LongHelper longHelper;
	@Before
	public void setUp() throws Exception {
		val1 = Mockito.mock(TimeMeasureInstance.class);
		val2 = Mockito.mock(TimeMeasureInstance.class);
		val3 = Mockito.mock(TimeMeasureInstance.class);
		Mockito.when(val1.calculate()).thenReturn(4500);
		Mockito.when(val2.calculate()).thenReturn(3000);
		Mockito.when(val3.calculate()).thenReturn(7500);
		
		List<TimeMeasureInstance> values = new ArrayList<TimeMeasureInstance>();
		values.add(val1);
		values.add(val2);
		values.add(val3);
		
		measure = Mockito.mock(TimeMeasure.class);
		Mockito.when(measure.getAllValues()).thenReturn(values);
		longHelper = new LongHelper();
	}

	@Test
	public void testAverageTimeAggregation() {
		AggregatedMeasure<TimeMeasure, TimeMeasureInstance, Long> aggMeasure = new AggregatedMeasure<TimeMeasure, TimeMeasureInstance, Long>();
		aggMeasure.setBaseMeasure(measure);
		AggregationFunction<Long, TimeMeasureInstance> function = new AverageFunction<Long, TimeMeasureInstance>(longHelper);
		aggMeasure.setAggregationFunction(function);
		
		Long averageTime = aggMeasure.calculate();
		Assert.assertEquals(new Long(5000), averageTime);
	}

}
