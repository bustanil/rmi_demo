package id.co.collega;

import org.springframework.jdbc.core.JdbcTemplate;

public class PersonServiceImpl implements PersonService {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void createPerson(String name) {
        jdbcTemplate.update("insert into person(name) values(?)", name);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
