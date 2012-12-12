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

package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.ActivitiException;

public class TimeMeasureInstance extends BaseMeasureInstance {

	private List<SingleTimeMeasureValue> singleValues = new ArrayList<SingleTimeMeasureValue>();

	public List<SingleTimeMeasureValue> getSingleValues() {
		return singleValues;
	}

	public void addSingleValue(SingleTimeMeasureValue singleValue) {
		this.singleValues.add(singleValue);
	}

	/**
	 * Iterates over the list of single values and returns the difference of the
	 * latest to-timestamp and the earliest from-timestamp.
	 */
	@Override
	public Number calculate() {
		Date earliestFrom = null;
		Date latestTo = null;
		for (SingleTimeMeasureValue singleValue : singleValues) {
			Date currentFrom = null;
			Date currentTo = null;
			
			if (singleValue.isFrom()) {
				currentFrom = singleValue.getTimetamp();
			} else if (singleValue.isTo()) {
				currentTo = singleValue.getTimetamp();
			}

			if (currentFrom != null && (earliestFrom == null || currentFrom.compareTo(earliestFrom) < 0)) {
				earliestFrom = currentFrom;
			}
			if (currentTo != null && (latestTo == null || currentTo.compareTo(latestTo) > 0)) {
				latestTo = currentTo;
			}
		}

		if (earliestFrom == null || latestTo == null) {
			throw new ActivitiException(
					"Cannot calculate measure: Not enough data collected yet.");
		}
		return latestTo.getTime() - earliestFrom.getTime();

	}
}
