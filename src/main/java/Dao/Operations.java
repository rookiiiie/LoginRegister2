package Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component("operations")
public class Operations {
    JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
//        System.out.println("jdbcTemplate inject in Operations Ok");
    }

    public void addTest(){
        System.out.println("addTEST() method enable !");
        String addCommand="create table if not exists user(" +
                "name varchar(20) not null," +
                "password varchar(20) not null," +
                "primary key(name)" +
                ") default charset=utf8;";
        jdbcTemplate.execute(addCommand);
        System.out.println("create table ok");
    }
}
