package de.unipotsdam.hpi.thorben.ppi.data;


import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

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
			ClassLoader cl = dataObjectClass.getClassLoader();
			Object dynamicProxy = Proxy.newProxyInstance(cl, new Class[] { dataObjectClass }, listener);
			toolbox = new FieldTraceToolBox(listener, dynamicProxy);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		
		return toolbox;
	}
	
}
