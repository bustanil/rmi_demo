package id.co.collega;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.remoting.rmi.RmiServiceExporter;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
public class AppServer implements CommandLineRunner {

    @Bean
    DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/rmi_demo");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("");
        return basicDataSource;
    }

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean
    PersonService personService(JdbcTemplate jdbcTemplate){
        PersonServiceImpl personService = new PersonServiceImpl();
        personService.setJdbcTemplate(jdbcTemplate);
        return personService;
    }

    @Bean
    RmiServiceExporter rmiServiceExporter(PersonService personService){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("PersonService");
        rmiServiceExporter.setServiceInterface(PersonService.class);
        rmiServiceExporter.setService(personService);
        rmiServiceExporter.setRegistryPort(1199);
        return rmiServiceExporter;
    }

    public static void main( String[] args )
    {
        SpringApplication.run(AppServer.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Hello world");
    }
}
