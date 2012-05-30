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
