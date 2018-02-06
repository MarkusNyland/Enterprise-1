package Ejb;

import JpaDatabase.Category;
import JpaDatabase.Quiz;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ResetEjb {

    public ResetEjb(){}

    @PersistenceContext
    EntityManager entityManager;

    public void deleteAllInDatabase(){
        TypedQuery allCategoriesQuery = entityManager.createQuery("select c from Category c", Category.class);
        List<Category> allCategories = allCategoriesQuery.getResultList();

        allCategories.forEach(category -> entityManager.remove(category));

        TypedQuery allQuizzesQuery = entityManager.createQuery("select  q from Quiz q", Quiz.class);
        List<Quiz> allQuizzes = allQuizzesQuery.getResultList();

        allQuizzes.forEach(quiz -> entityManager.remove(quiz));
    }
}
