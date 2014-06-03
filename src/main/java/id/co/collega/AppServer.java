package id.co.collega;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class AppServer
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("server.xml");
    }
}
