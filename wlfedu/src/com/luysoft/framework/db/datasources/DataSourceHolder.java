package com.luysoft.framework.db.datasources;

public class DataSourceHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  

	public static void setDataSource(String dataSource){  
		contextHolder.set(dataSource);  
	}

	public static String getDataSource(){  
		return contextHolder.get();  
	}

	public static void clearDataSource(){  
		contextHolder.remove();  
	}
}
