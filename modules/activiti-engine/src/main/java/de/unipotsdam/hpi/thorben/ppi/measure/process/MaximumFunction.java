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

import java.util.List;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.typehelper.TypeHelper;

public class MaximumFunction<T extends Number, K extends BaseMeasureInstance>
		extends AbstractAggregationFunction<T, K> {
	
	public MaximumFunction(TypeHelper<T> typeHelper) {
		super(typeHelper);
	}

	protected T performCalculation(List<K> baseMeasureValues) {
		T maximum = helper.createMinValue();
		for (K k : baseMeasureValues) {
			T currentValue = helper.asType(k.calculate());
			if (helper.greaterThan(currentValue, maximum)) {
				maximum = currentValue;
			}
		}
		return maximum;
	}

}
