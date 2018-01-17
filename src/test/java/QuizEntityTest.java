import org.junit.Test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class QuizEntityTest {

    @Test
    public void testQuiz(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("H2-Database");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Quiz quiz = new Quiz();
        quiz.setQuestion("Hvem døde under slaget ved stikklestad");
        quiz.setAnswer1("Olav Haraldsson");
        quiz.setAnswer2("Olav Tryggvason");
        quiz.setAnswer3("Harald Hårfagre");
        quiz.setAnswer4("Harald Gråfell");
        quiz.setRightAnswer(1L);

        assertNull(quiz.getId());

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            entityManager.persist(quiz);

            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
            fail();
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        assertNotNull(quiz.getId());
        System.out.println(quiz.getRightAnswer());
    }

    @Test
    public void testQuizWithSubcategory(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("H2-Database");
        EntityManager em = emf.createEntityManager();

        Category c1 = new Category();
        c1.setName("Historie");

        SubCategory sc1 = new SubCategory();
        sc1.setName("Norsk historie");

        Quiz q1 = new Quiz();
        q1.setQuestion("Hvem døde under slaget ved stikklestad");
        q1.setAnswer1("Olav Haraldsson");
        q1.setAnswer2("Olav Tryggvason");
        q1.setAnswer3("Harald Hårfagre");
        q1.setAnswer4("Harald Gråfell");
        q1.setRightAnswer(1L);

        //c1.setSubCategories(sc1);
        //sc1.setCategory(c1);
        //q1.setSubCategory(sc1);

        assertNull(c1.getId());
        assertNull(sc1.getId());
        assertNull(q1.getId());

        EntityTransaction et = em.getTransaction();
        et.begin();

        try {
            em.persist(c1);
            em.persist(sc1);
            em.persist(q1);

            et.commit();
        }catch (Exception e){
            et.rollback();
            fail();
        }finally {
            em.close();
            emf.close();
        }

        assertNotNull(c1.getId());
        assertNotNull(sc1.getId());
        assertNotNull(q1.getId());
    }
}
