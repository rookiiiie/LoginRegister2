package Controllers;

import Dao.Operations;
import POJO.User;
import Servicea.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.ServletContextScope;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;

/**
 * 类级别的Controller
 * 返回视图名
 */
@Controller
public class Controllers {
    @Value("#{service}")
    Service service;
    //    gain Login Page
    @RequestMapping(value = {"/login"},method = {RequestMethod.GET,RequestMethod.POST})
    public String getPage(){
        return "login";
    }
    //Judge Login
    @RequestMapping(value = {"/user/login"},method = {RequestMethod.POST})
    public ModelAndView Login(User user){
        return service.LoginValidate(user);
    }
    //Judge Register
    @RequestMapping(value = {"/user/register"},method = {RequestMethod.POST})
    public ModelAndView Register(User user){
        return service.RegisterValidate(user);
    }
}
