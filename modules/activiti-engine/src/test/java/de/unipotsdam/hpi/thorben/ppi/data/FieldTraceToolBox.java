package de.unipotsdam.hpi.thorben.ppi.data;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javassist.util.proxy.ProxyFactory;

public class FieldTraceToolBox {
	
	private DataPropertyListener listener;
	private Object proxy;

	public FieldTraceToolBox(DataPropertyListener listener, Object proxy) {
		this.listener = listener;
		this.proxy = proxy;
	}

	public DataPropertyListener getListener() {
		return listener;
	}

	public Object getProxy() {
		return proxy;
	}

	public static FieldTraceToolBox createNew(Object objectToTrace, String property) {
		Class<?> dataObjectClass = objectToTrace.getClass();
		System.out.println("Class: " + dataObjectClass);
		

		FieldTraceToolBox toolbox = null;
		
		try {
			Field fieldToTrace = dataObjectClass.getDeclaredField(property);
			fieldToTrace.setAccessible(true);

			DataPropertyListener listener = new DataPropertyListener(objectToTrace, fieldToTrace);
			ProxyFactory factory = new ProxyFactory();
			factory.setSuperclass(dataObjectClass);
			
			Object dynamicProxy = factory.create(new Class<?>[0], new Object[0], listener);
			toolbox = new FieldTraceToolBox(listener, dynamicProxy);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} 
		
		
		return toolbox;
	}
	
}
