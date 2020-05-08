package scenario.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import scenario.entity.Person;
import scenario.entity.Role;
import scenario.jdbc.JDBCTool;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.Assert.*;

public class PersonDaoTest {

    private PersonDao personDao;

    @Before
    public void setUp() throws Exception {
        personDao = JDBCTool.getPersonDao();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAll() throws SQLException {
        assertEquals(4,personDao.findAll().size());
    }

    @Test
    public void addAndDelete() throws SQLException {
        Person person=new Person();
        person.setId(UUID.randomUUID().toString());
        person.setName("");
        person.setAge(21);
        person.setRole(new Role("r1","teacher"));
        person.setSex("male");
        person.setLocation("location");

        System.out.println(person);
        assertEquals(1,personDao.add(person));
        assertEquals(1,personDao.delete(person));
    }
}