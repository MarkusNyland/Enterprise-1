package JEE.Ejb;

import JEE.EjbBusinessLogic.CategoryEjb;
import JEE.JpaDatabase.Category;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CategoryEjbTest {

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "JEE")
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
        assertTrue(categoryEjb.getAllCategories(false).isEmpty());
    }

    @Test
    public void testCreateCategory(){
        testNoCatagory();
        categoryEjb.createCategory("Historie");
        assertEquals(1, categoryEjb.getAllCategories(false).size());
    }

    @Test
    public void testGetCategory(){
        String name = "eksistensialisme";
        Long id = categoryEjb.createCategory(name);
        assertEquals(name, categoryEjb.getCategory(id, false).getName());
    }

    @Test
    public void testCreateSubCategory(){
        String categoryName = "Historie";
        String subCategoryName = "JapanskHistorie";
        Long id = categoryEjb.createCategory(categoryName);
        Long subId = categoryEjb.createSubCategory(id, subCategoryName);
        assertEquals(subCategoryName, categoryEjb.getSubCategory(subId).getName());
        assertEquals(categoryName, categoryEjb.getSubCategory(subId).getCategory().getName());
    }

    @Test
    public void testGetAllCategories(){
        Long id1 = categoryEjb.createCategory("a1");
        Long id2 = categoryEjb.createCategory("b2");
        Long id3 = categoryEjb.createCategory("c3");

        categoryEjb.createSubCategory(id1, "1a");
        categoryEjb.createSubCategory(id2, "2b");
        categoryEjb.createSubCategory(id3, "3c");

        List<Category> categories = categoryEjb.getAllCategories(false);
        assertEquals(3, categories.size());

        Category first = categories.get(0);

        try {
            first.getSubCategories().size();
            fail();
        }catch (Exception e){
            //expected
        }

        categories = categoryEjb.getAllCategories(true);
        assertEquals(3, categories.size());

        first = categories.get(0);

        assertEquals("1a", first.getSubCategories().get(0).getName());
    }

    @Test
    public void testCreateTwice(){
        String categoryName = "meow";
        categoryEjb.createCategory(categoryName);

        try {
            categoryEjb.createCategory(categoryName);
            fail();
        }catch (Exception e){
            //expected
        }
        assertEquals(1,categoryEjb.getAllCategories(false).size());
    }
}
