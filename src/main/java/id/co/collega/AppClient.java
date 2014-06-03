package id.co.collega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
@EnableAutoConfiguration
public class AppClient {

    @Bean(name = "personService")
    PersonService personService() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(PersonService.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1199/PersonService");
        rmiProxyFactoryBean.afterPropertiesSet();
        return (PersonService) rmiProxyFactoryBean.getObject();
    }

    public static void main(String[] args) {
        ApplicationContext client = SpringApplication.run(AppClient.class, args);
        PersonService personService = client.getBean(PersonService.class);
        personService.createPerson("Bustanil");
    }

}
