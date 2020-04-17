package AOP;

import Dao.Operations;
import POJO.User;
import Servicea.Service;
import org.apache.ibatis.javassist.compiler.ProceedHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 每次注册成功一个用户后将更新后的数据库写入文件以便查看
 * 切面声名于Root Application Context，切不了mvc组件~(root容器不可以访问mvc容器，mvc容器可以访问root容器！)
 */
@Component
@Aspect
@EnableAspectJAutoProxy
public class DataBaseListener {
    @Value("#{service}")
    Service service;
    @Autowired
    ServletContext servletContext;
@AfterReturning(value = "execution(* Servicea.Service.RegisterValidate(..))")
    public void dataBaseListener() throws Throwable {
//        System.out.println("start execute ShowDataBase()");
        this.ShowDataBase();
    }
    //show database's data
    public void ShowDataBase() throws IOException {
        String ShowCommand="select * from user;";
        List<User> list=service.findUser(ShowCommand);
        System.out.println(servletContext.getRealPath("/"));
        File file=new File(servletContext.getRealPath("/")+"\\Data.txt");
        if(!file.exists()) file.createNewFile();
        PrintWriter printWriter=new PrintWriter(new FileOutputStream(file),true);
        printWriter.println(new Date());
        for(User user:list){
            printWriter.println("user = "+user.getName()+"  password = "+user.getPassword());
        }
        printWriter.println("\n\n\n");
    }
}
