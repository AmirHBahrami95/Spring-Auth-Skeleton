package com.amir.app.config;

import java.util.List;

import javax.sql.DataSource;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

@Configuration
public class DBConfig {
		
	@Bean
	public Jdbi jdbi(DataSource ds, List<JdbiPlugin> jdbiPlugins, List<RowMapper<?>> rowMappers) {        
    TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(ds);        
    Jdbi jdbi = Jdbi.create(proxy);
    jdbiPlugins.forEach(jdbi::installPlugin);
    rowMappers.forEach(jdbi::registerRowMapper);
    return jdbi;
	}
	
}
