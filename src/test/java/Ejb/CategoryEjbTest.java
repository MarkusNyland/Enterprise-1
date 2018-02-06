package Ejb;

import EjbBusinessLogic.CategoryEjb;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CategoryEjbTest {

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addPackage("westerdals.nylmar15.quiz")
                .addClasses(ResetEjb.class, CategoryEjb.class)
                .addAsResource("META-INF/persistence.xml");
    }

    @EJB
    ResetEjb resetEjb;

    @EJB
    CategoryEjb categoryEjb;

    public CategoryEjbTest(){}

    @Before
    public void resetDatabase(){
        resetEjb.deleteAllInDatabase();
//        resetEjb.resetDatabase();
    }

    @Test
    public void testNoCatagory(){
        assertNull(categoryEjb.getAllCategories(false));
    }
}
