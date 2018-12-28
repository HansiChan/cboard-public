package com.dachen.cboardpublic.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ImpalaUtil {
	@Value("${impala.url}")
	private String url;
	@Value("${impala.user}")
	private String user;
	@Value("${impala.password}")
	private String password;
	
    private ImpalaUtil() {
    }
   
    static {
    	 /**
         * 驱动注册
         */
        try {
            Class.forName("com.cloudera.impala.jdbc4.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
