package Config;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.xml.crypto.Data;

/**
 * 不仅可以用来注册DispatcherServlet
 * 还可以用来配置是根目录配置的类（也就是@Configuration配置的类）!!!
 */
public class RegisterDispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

    //根配置是什么意思呢？指的是通过@Configuration来声名为配置类的类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("RootConfigClasses load ok");
        return new Class[]{ContentLoadListener.class};
        //这个方法就是用来声名哪些类是 根配置类（也就是声名了@Configuration的类）！！！！
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("DispatcherServlet load ok");
        return new Class[]{DispatcherServlet.class};
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("getServletMappings ok");
        return new String[]{"/"};
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return super.createRootApplicationContext();
    }
}
