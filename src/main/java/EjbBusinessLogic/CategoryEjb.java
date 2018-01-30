package EjbBusinessLogic;

import JpaDatabase.Category;
import JpaDatabase.SubCategory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CategoryEjb {

    @PersistenceContext
    private EntityManager entityManager;

    public Long createCategory(String name){
        Category category = new Category();
        category.setName(name);

        entityManager.persist(category);

        return category.getId();
    }

    public Long createSubCategory(long parentId, String name){
        Category category = entityManager.find(Category.class, parentId);

        if (category==null){
            throw new IllegalArgumentException("Category with id " + parentId + " does not exist");
        }

        SubCategory subCategory = new SubCategory();
        subCategory.setCategory(category);
        subCategory.setName(name);

        entityManager.persist(subCategory);

        return subCategory.getId();
    }

    public List<Category> getAllCategories(boolean withSubCategories){
        TypedQuery getAllCategories = entityManager.createQuery("select c from Category c",Category.class);
        List<Category> allCategories = getAllCategories.getResultList();

        if (withSubCategories){
            allCategories.forEach(category -> category.getSubCategories().size());
        }

        return allCategories;
    }

    public Category getCategory(long id, boolean withSubCategories){
        Category category = entityManager.find(Category.class, id);
        if (category==null){
            throw new IllegalArgumentException("Category with id " + id + " does not exist");
        }

        if (withSubCategories){
            category.getSubCategories().size();
        }

        return category;
    }

    public SubCategory getSubCategory(long id){
        SubCategory subCategory = entityManager.find(SubCategory.class, id);
        if (subCategory==null){
            throw new IllegalArgumentException("SubCategory with id " + id + " does not exist");
        }

        return subCategory;
    }
}
