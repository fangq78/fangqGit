package com.luysoft.framework.db.datasources;

import java.lang.reflect.Method;

import javax.annotation.PostConstruct;

import org.apache.commons.dbcp2.BasicDataSource;

import com.luysoft.framework.crypto.AESCoder;
import com.luysoft.framework.crypto.annotation.Encryption;

public class DataSourceBean extends BasicDataSource {

	@PostConstruct
	public void afterInjection() throws Exception {

		if (this.getClass().isAnnotationPresent(Encryption.class)) {
			Encryption encryption = this.getClass().getAnnotation(Encryption.class);
			for (String fieldname : encryption.fields()) {
				String mname = fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
				Method getM = this.getClass().getMethod("get" + mname);
				Method setM = this.getClass().getMethod("set" + mname, String.class);
				Object value = (String)getM.invoke(this);
				if (null != value && !"".equals(value)) {
					setM.invoke(this, AESCoder.decode(encryption.key(), encryption.charset(), value.toString()));
				}
			}
		}
	}
}
