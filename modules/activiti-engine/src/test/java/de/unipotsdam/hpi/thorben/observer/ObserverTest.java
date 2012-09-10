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

package de.unipotsdam.hpi.thorben.observer;

import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.unipotsdam.hpi.thorben.ppi.condition.event.ActivityEndEvent;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ActivityStartEvent;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;

/**
 * We need our own Observer implementation as the {@link Observable} implementation puts state in the Observable
 * that will change during process runtime (i.e. the changed-flag). As we want to observe e.g. {@link ActivityImpl} objects,
 * this could lead to concurrent modifications by several executions, e.g. from different process instances, which could
 * ultimately lead to undesired Observer-behavior.
 * @author Thorben
 *
 */
public class ObserverTest {

	private ActivityImpl activity;
	@Before
	public void setUp() {
		activity = Mockito.mock(ActivityImpl.class);
	}
	
	@Test
	public void testSingleNotification() {
		TestObserver testObserver = new TestObserver();
		Observable testObservable = new TestObservable();
		
		testObservable.addObserver(testObserver);
		ConditionEvent event = new ActivityStartEvent(activity);
		testObservable.notifyObservers(event);
		
		Assert.assertEquals(1, testObserver.getTimesNotified());
		
	}
	
	@Test
	public void testMultipleNotifications() {
		TestObserver testObserver = new TestObserver();
		Observable testObservable = new TestObservable();
		
		testObservable.addObserver(testObserver);
		ConditionEvent event = new ActivityStartEvent(activity);
		testObservable.notifyObservers(event);
		ConditionEvent anotherEvent = new ActivityEndEvent(activity);
		testObservable.notifyObservers(anotherEvent);
		
		Assert.assertEquals(2, testObserver.getTimesNotified());
		
	}
	
	/**
	 * Though we add the observer twice, we only want to be notifed once.
	 */
	@Test
	public void testSingleNotificationPerObserver() {
		TestObserver testObserver = new TestObserver();
		Observable testObservable = new TestObservable();
		
		testObservable.addObserver(testObserver);
		testObservable.addObserver(testObserver);
		
		ConditionEvent event = new ActivityStartEvent(activity);
		testObservable.notifyObservers(event);
		
		Assert.assertEquals(1, testObserver.getTimesNotified());
		
	}
	

}
