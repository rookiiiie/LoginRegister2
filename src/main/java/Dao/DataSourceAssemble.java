package Dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 连接数据库需要的4个参数：
 * url(database's ip,port,databaseName),driverClass(enable the connection),username,password
 * procedure:assemble dataSource to JdbcOperations(Interface)
 */
@Configuration
public class DataSourceAssemble {
    @Bean("dataSource")
    public DataSource getDataSource() throws PropertyVetoException {
        System.out.println("start assemble DataSource bean");
        String driverClass="com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/users?serverTimezone=UTC";
        ComboPooledDataSource c3P0PooledDataSource =new ComboPooledDataSource();
        c3P0PooledDataSource.setJdbcUrl(url);
        c3P0PooledDataSource.setDriverClass(driverClass);
        c3P0PooledDataSource.setUser("root");
        c3P0PooledDataSource.setPassword("root");
        c3P0PooledDataSource.setInitialPoolSize(5);
        c3P0PooledDataSource.setMaxPoolSize(10);
        return c3P0PooledDataSource;
    }
    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        System.out.println("JdbcTemplate assemble ok");
        return new JdbcTemplate(dataSource);
    }
}
