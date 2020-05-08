package scenario.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import scenario.entity.Grade;
import scenario.entity.Student;
import scenario.jdbc.JDBCTool;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class GradeDaoTest {

    private GradeDao gradeDao;

    @Before
    public void setUp() throws Exception{
        gradeDao = JDBCTool.getGradeDao();
    }

    @Test
    public void findByStudent() throws SQLException {
        Student student=new Student();
        student.setId("s1");
        assertEquals(2,gradeDao.findByStudent(student).size());
    }

    @Test
    public void addAndDelete() throws SQLException {
        Grade grade=new Grade();
        grade.setGid("testg");
        grade.setSid("s1");
        grade.setCourse("c++");
        grade.setMark(100);
        grade.setTerm("term1");

        System.out.println(grade);

        assertEquals(1,gradeDao.add(grade));

        assertEquals(1,gradeDao.delete(grade));
    }

    @Test
    public void deleteBySid() throws SQLException {
        Grade grade=new Grade();
        grade.setGid("testg");
        grade.setSid("tests");
        grade.setCourse("c++");
        grade.setMark(100);
        grade.setTerm("term1");

        System.out.println(grade);

        assertEquals(0,gradeDao.deleteBySid(grade.getSid()));
    }

    @After
    public void tearDown() throws Exception{

    }
}