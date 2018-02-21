package JEE.EjbBusinessLogic;

import JEE.JpaDatabase.Quiz;
import JEE.JpaDatabase.SubCategory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class QuizEjb {

    @PersistenceContext
    private EntityManager entityManager;

    public long createQuiz(long subCategoryId, String question, String firstAnswer, String secondAnswer, String thirdAnswer, String fourthAnswer, int indexOfCorrectAnswer){
        if (entityManager.find(SubCategory.class, subCategoryId) == null ){
            throw new IllegalArgumentException("No subCategory with that id");
        }

        Quiz quiz = new Quiz();
        quiz.setQuestion(question);
        quiz.setAnswer1(firstAnswer);
        quiz.setAnswer2(secondAnswer);
        quiz.setAnswer3(thirdAnswer);
        quiz.setAnswer4(fourthAnswer);
        quiz.setRightAnswer(indexOfCorrectAnswer);

        entityManager.persist(quiz);

        return quiz.getId();
    }

    public List<Quiz> getQuizzes(){
        TypedQuery<Quiz> allQuizzesQuery = entityManager.createQuery("select q from Quiz q",Quiz.class);
        return allQuizzesQuery.getResultList();
    }

    public Quiz getQuiz(int quizId){
        Quiz quiz = entityManager.find(Quiz.class, quizId);
        if (quiz==null) throw new IllegalArgumentException("No quiz with such id");

        return quiz;
    }

    public List<Quiz> getRandomQuizzes(int n, long subCategoryId){
        TypedQuery<Long> numberOfQuizzes = entityManager.createQuery("select count(q) from Quiz q where q.subCategory.id=?1", Long.class);
        numberOfQuizzes.setParameter(1, subCategoryId);
        long size = numberOfQuizzes.getSingleResult();

        if (n>size) throw  new IllegalArgumentException("The database contains less than " + n + " and can not meet your request");

        HashSet<Quiz> foundQuizzes = new HashSet<>();

        while (foundQuizzes.size()<n){
            TypedQuery<Quiz> quizQuery = entityManager.createQuery("select q from Quiz q where q.subCategory.id=?1", Quiz.class);
            quizQuery.setParameter(1, subCategoryId);
            quizQuery.setMaxResults(1);
            quizQuery.setFirstResult(new Random().nextInt(((int)size)));
            foundQuizzes.add(quizQuery.getSingleResult());
        }

        return new ArrayList<>(foundQuizzes);
    }
}
