package JEE.JpaEntities;

import JEE.JpaDatabase.Category;
import JEE.JpaDatabase.Quiz;
import JEE.JpaDatabase.SubCategory;
import org.junit.Test;

import static org.junit.Assert.*;

import javax.persistence.*;
import java.util.List;

public class QuizEntityTest extends EntityTestBase {

    @Test
    public void testQuiz(){
        Quiz quiz = new Quiz();
        quiz.setQuestion("Hvem døde under slaget ved stikklestad");
        quiz.setAnswer1("Olav Haraldsson");
        quiz.setAnswer2("Olav Tryggvason");
        quiz.setAnswer3("Harald Hårfagre");
        quiz.setAnswer4("Harald Gråfell");
        quiz.setRightAnswer(1L);

        assertNull(quiz.getId());

        assertFalse(persistInTransaction(quiz));

        assertNotNull(quiz.getId());
        System.out.println(quiz.getRightAnswer());
    }

    @Test
    public void testQuizWithSubcategory(){
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

        c1.setSubCategories(sc1);
        sc1.setCategory(c1);
        q1.setSubCategory(sc1);

        assertNull(c1.getId());
        assertNull(sc1.getId());
        assertNull(q1.getId());

        assertTrue(persistInTransaction(c1,sc1,q1));

        assertNotNull(c1.getId());
        assertNotNull(sc1.getId());
        assertNotNull(q1.getId());
    }

    @Test
    public void testQueries(){
        Category jeeCategory = new Category();
        jeeCategory.setName("JEE");

        SubCategory jpaSubCategory = new SubCategory();
        jpaSubCategory.setCategory(jeeCategory);
        jpaSubCategory.setName("JPA");
        SubCategory ejbSubCategory = new SubCategory();
        ejbSubCategory.setCategory(jeeCategory);
        ejbSubCategory.setName("EJB");
        SubCategory jsfSubCategory = new SubCategory();
        jsfSubCategory.setCategory(jeeCategory);
        jsfSubCategory.setName("JSF");

        jeeCategory.setSubCategories(jpaSubCategory,ejbSubCategory,jsfSubCategory);

        Quiz jpaCatQuiz = new Quiz();
        jpaCatQuiz.setQuestion("Hva sier katten?");
        jpaCatQuiz.setAnswer1("Meow");
        jpaCatQuiz.setAnswer2("Møøø");
        jpaCatQuiz.setAnswer3("Woof");
        jpaCatQuiz.setAnswer4("Pip");
        jpaCatQuiz.setRightAnswer(0L);
        jpaCatQuiz.setSubCategory(jpaSubCategory);

        Quiz jpaDogQuiz = new Quiz();
        jpaDogQuiz.setQuestion("Hva sier hunden?");
        jpaDogQuiz.setAnswer1("Meow");
        jpaDogQuiz.setAnswer2("Møøø");
        jpaDogQuiz.setAnswer3("Woof");
        jpaDogQuiz.setAnswer4("Pip");
        jpaDogQuiz.setRightAnswer(1L);
        jpaDogQuiz.setSubCategory(jpaSubCategory);

        Quiz ejbCowQuiz = new Quiz();
        ejbCowQuiz.setQuestion("Hva sier kuen?");
        ejbCowQuiz.setAnswer1("Meow");
        ejbCowQuiz.setAnswer2("Møøø");
        ejbCowQuiz.setAnswer3("Woof");
        ejbCowQuiz.setAnswer4("Pip");
        ejbCowQuiz.setRightAnswer(2L);
        ejbCowQuiz.setSubCategory(ejbSubCategory);

        Quiz jsfFugleQuiz = new Quiz();
        jsfFugleQuiz.setQuestion("Hva sier fuglen?");
        jsfFugleQuiz.setAnswer1("Meow");
        jsfFugleQuiz.setAnswer2("Møøø");
        jsfFugleQuiz.setAnswer3("Woof");
        jsfFugleQuiz.setAnswer4("Pip");
        jsfFugleQuiz.setRightAnswer(3L);
        jsfFugleQuiz.setSubCategory(jsfSubCategory);

        assertTrue(persistInTransaction(jeeCategory,jpaSubCategory,ejbSubCategory,jsfSubCategory,jpaCatQuiz,jpaDogQuiz,ejbCowQuiz,jsfFugleQuiz));

        TypedQuery<Quiz> queryForJpaQuizzes = entityManager.createQuery("select q from Quiz q where q.subCategory.name = 'JPA'",Quiz.class);
        List<Quiz> quizzesFound = queryForJpaQuizzes.getResultList();

        assertEquals(2,quizzesFound.size());

        TypedQuery<Quiz> gueryForJEEQuizes = entityManager.createQuery("select q from Quiz q where q.subCategory.category.name = 'JEE'", Quiz.class);
        List<Quiz> quizesFound = gueryForJEEQuizes.getResultList();

        assertEquals(4, quizesFound.size());

    }
}
