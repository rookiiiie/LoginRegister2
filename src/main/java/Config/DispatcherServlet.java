package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"Controllers"})
public class DispatcherServlet {
    @Bean
    public ViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver internalResourceViewResolver
                =new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/Views/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setExposeContextBeansAsAttributes(true);
        System.out.println("ViewResolver Config OK");
        return internalResourceViewResolver;
    }
}
