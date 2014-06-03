package id.co.collega;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppClient {

    public static void main(String[] args) {
        ApplicationContext client = new ClassPathXmlApplicationContext("client.xml");
        PersonService personService = client.getBean(PersonService.class);
        personService.createPerson("Bustanil");
    }

}
