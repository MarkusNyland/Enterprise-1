package JpaEntities;

import JpaDatabase.Category;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryEntityTest extends EntityTestBase {

    @Test
    public void testTooLongName(){
        Category category = new Category();
        category.setName(new String(new char[500]));

        assertFalse(persistInTransaction(category));

        category.setId(null);
        category.setName("Mandal");

        assertTrue(persistInTransaction(category));
    }

    @Test
    public void testUniqueName(){
        Category category1 = new Category();
        category1.setName("Meow");

        assertTrue(persistInTransaction(category1));

        Category category2 = new Category();
        category2.setName("Meow");

        assertFalse(persistInTransaction(category2));
    }
}
