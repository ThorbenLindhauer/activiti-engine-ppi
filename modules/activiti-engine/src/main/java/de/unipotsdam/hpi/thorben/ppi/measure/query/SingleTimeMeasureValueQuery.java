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

package de.unipotsdam.hpi.thorben.ppi.measure.query;

import org.activiti.engine.query.Query;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.SingleTimeMeasureValue;

public interface SingleTimeMeasureValueQuery extends Query<SingleTimeMeasureValueQuery, SingleTimeMeasureValue> {

	SingleTimeMeasureValueQuery processInstanceId(String processInstanceId);
	
	SingleTimeMeasureValueQuery timeMeasureId(String timeMeasureId);
	
	SingleTimeMeasureValueQuery processDefinitionId(String processDefinitionId);
}
