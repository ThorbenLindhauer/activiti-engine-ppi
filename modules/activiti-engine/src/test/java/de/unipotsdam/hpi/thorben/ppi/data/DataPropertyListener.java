package de.unipotsdam.hpi.thorben.ppi.data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DataPropertyListener implements InvocationHandler {
	
	private Object objectToTrace;
	private Field fieldToTrace;
	
	private Object currentValue;
	
	public DataPropertyListener(Object objectToTrace, Field fieldToTrace) {
		this.objectToTrace = objectToTrace;
		this.fieldToTrace = fieldToTrace;
		
		// set initial value
		try {
			this.currentValue = fieldToTrace.get(objectToTrace);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public Object getCurrentValue() {
		return currentValue;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		Object fieldValueBefore = fieldToTrace.get(objectToTrace);
		Object result = method.invoke(this.objectToTrace, args);
		Object fieldValueAfter = fieldToTrace.get(objectToTrace);
		
		if (!fieldValueBefore.equals(fieldValueAfter)) {
			currentValue = fieldValueAfter;
		}

		// TODO Auto-generated method stub
		return result;
	}
}
