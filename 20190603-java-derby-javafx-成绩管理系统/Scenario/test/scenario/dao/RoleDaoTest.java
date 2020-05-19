package scenario.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import scenario.jdbc.JDBCTool;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class RoleDaoTest {

    private RoleDao roleDao;

    @Before
    public void setUp() throws Exception {
        roleDao = JDBCTool.getRoleDao();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAll() throws SQLException {
        assertEquals(3, roleDao.findAll().size());
    }
}