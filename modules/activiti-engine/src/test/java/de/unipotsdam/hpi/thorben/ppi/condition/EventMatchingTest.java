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

package de.unipotsdam.hpi.thorben.ppi.condition;

import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.unipotsdam.hpi.thorben.ppi.condition.event.ActivityEndEvent;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ActivityStartEvent;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;

public class EventMatchingTest {

	private ActivityImpl firingActivity;
	private ActivityImpl anotherActivity;
	
	@Before
	public void setUp() {
		firingActivity = Mockito.mock(ActivityImpl.class);
		anotherActivity = Mockito.mock(ActivityImpl.class);
	}
	
	@Test
	public void testStartConditionFulfilled() {
		ConditionEvent event = new ActivityStartEvent(firingActivity);
		PPICondition condition = new ActivityStartCondition(firingActivity);
		boolean conditionFulfilled = condition.isFulfilledBy(event);
		Assert.assertTrue(conditionFulfilled);
	}
	
	@Test
	public void testEndConditionFulfilled() {
		ConditionEvent event = new ActivityEndEvent(firingActivity);
		PPICondition condition = new ActivityEndCondition(firingActivity);
		boolean conditionFulfilled = condition.isFulfilledBy(event);
		Assert.assertTrue(conditionFulfilled);
	}
	
	@Test
	public void testConditionUnfulfilled() {
		ConditionEvent event = new ActivityStartEvent(firingActivity);
		PPICondition condition = new ActivityStartCondition(anotherActivity);
		boolean conditionFulfilled = condition.isFulfilledBy(event);
		Assert.assertFalse(conditionFulfilled);
	}
	
	@Test
	public void testDifferentConditionUnfulfilled() {
		ConditionEvent event = new ActivityStartEvent(firingActivity);
		PPICondition condition = new ActivityEndCondition(firingActivity);
		boolean conditionFulfilled = condition.isFulfilledBy(event);
		Assert.assertFalse(conditionFulfilled);
	}

}
