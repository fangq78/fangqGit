package com.luysoft.framework.db.datasources;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSource extends AbstractRoutingDataSource{
	@Override  
	protected Object determineCurrentLookupKey() {  
		return DataSourceHolder.getDataSource();  
	}
}
