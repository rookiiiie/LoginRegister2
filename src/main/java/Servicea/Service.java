package Servicea;

import POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class Service {
    JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        System.out.println("service assemble ok");
    }
    //create table user:
    public void createTable(){
        String CreateCommand="create table if not exists user(" +
                "name varchar(20) not null," +
                "password varchar(20) not null," +
                "primary key(name)" +
                ") default charset=utf8;";
        jdbcTemplate.execute(CreateCommand);
    }

    /**实现RowMapper interface：
     *  可以将查询到的结果封装成一个java自定义类，返回List<T>
     */
    public List<User> findUser(String command){
        return jdbcTemplate.query(command,new RowMapper<User>(){
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return new User(resultSet.getString("name"),resultSet.getString("password"));
            }
        });
    }
    protected void InsertUser(User user){
        String InsertCommand="insert into user " +
                "(name,password) values ('"+
                user.getName()+"','"+
                user.getPassword()+"');";
        System.out.println(InsertCommand);
        jdbcTemplate.execute(InsertCommand);
        System.out.println(user.getName()+" 注册成功");
    }
    public ModelAndView LoginValidate(User user){
        this.createTable();
        System.out.println("name = "+user.getName());
        System.out.println("password = "+user.getPassword());
        String findUserCommand="select * from user " +
                "where name = '"+user.getName()+"'"+
                "and password = '"+user.getPassword()+"'";
//        System.out.println("findCommand = "+findCommand);
        List<User> userList=this.findUser(findUserCommand);
//        System.out.println("query ok");
        if(!userList.isEmpty()) {
            System.out.println("query "+user.getName()+" 成功");
            return new ModelAndView("loginOK");
        }
        System.out.println("query "+user.getName()+" 失败");
        ModelAndView modelAndView=new ModelAndView("login");
        modelAndView.addObject("isWrong","用户名或密码错误，请重试");
        return modelAndView;
    }
    public ModelAndView RegisterValidate(User user){
        this.createTable();
        ModelAndView modelAndView=new ModelAndView("login");
        if(user.getName().trim().equals("")||user.getPassword().trim().equals("")){
            modelAndView.addObject("register","请输入用户名和密码！");
            return modelAndView;
        }
        String findNameCommand="select * from user " +
                "where name = '"+user.getName()+"'";
        List<User> list=this.findUser(findNameCommand);
        if(!list.isEmpty()){
            System.out.println("用户已存在，请登录！");
            modelAndView.addObject("register","用户已存在，请登录！");
            return modelAndView;
        }
        modelAndView.addObject("register","注册成功！");
        this.InsertUser(user);
        return modelAndView;
    }
}
