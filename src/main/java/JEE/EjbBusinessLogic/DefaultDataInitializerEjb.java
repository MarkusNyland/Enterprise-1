package JEE.EjbBusinessLogic;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.PersistenceContext;
import java.util.function.Supplier;

@Singleton
@Startup
public class DefaultDataInitializerEjb {

    @PersistenceContext
    CategoryEjb categoryEjb;

    @PersistenceContext
    QuizEjb quizEjb;

    @PostConstruct
    public void init(){

        Long geoCategoryId = attempt(() ->categoryEjb.createCategory("Geography"));
        Long histCategoryId = attempt(() -> categoryEjb.createCategory("History"));

        Long norGeo = attempt(() -> categoryEjb.createSubCategory(geoCategoryId, "Norwegian Geography"));
        Long sweGeo = attempt(() -> categoryEjb.createSubCategory(geoCategoryId, "Swedish Geography"));
        Long engGeo = attempt(() -> categoryEjb.createSubCategory(geoCategoryId, "English Geography"));
        Long scoGeo = attempt(() -> categoryEjb.createSubCategory(geoCategoryId, "Scottish Geography"));
        Long jpnGeo = attempt(() -> categoryEjb.createSubCategory(geoCategoryId, "Japanese Geography"));

        Long norHist = attempt(() -> categoryEjb.createSubCategory(histCategoryId, "Norwegian History"));
        Long sweHist = attempt(() -> categoryEjb.createSubCategory(histCategoryId, "Swedish History"));
        Long engHist = attempt(() -> categoryEjb.createSubCategory(histCategoryId, "English History"));
        Long scoHist = attempt(() -> categoryEjb.createSubCategory(histCategoryId, "Scottish History"));
        Long jpnHist = attempt(() -> categoryEjb.createSubCategory(histCategoryId, "Japanese History"));


        quizAttempt(norGeo,
                "What is norway's longest fjord?",
                "Hardangerfjorden",
                "Oslofjorden",
                "Sognefjorden",
                "Geirangerfjorden",
                3);

        quizAttempt(sweGeo,
                "Sweden's longest river is?",
                "Torne",
                "Kalix",
                "Lule",
                "Ume",
                1);

       quizAttempt(engGeo,
               "In what county do you find Stonehenge?",
               "Staffordshire",
               "Wiltshire",
               "Cornwall",
               "Herefordshire",
               2);

        quizAttempt(scoGeo,
                "What scottish city is known as 'The Granite City'?",
                "Perth",
                "Aberdeen",
                "Dundee",
                "Stirling",
                2);

        quizAttempt(jpnGeo,
                "Name japan's highest mountain",
                "Mount Kita",
                "Mount Hotakadake",
                "Mount Yari",
                "Mount Fuji",
                4);

        quizAttempt(norHist,
                "Slaget ved Stikklestad fant sted i hvilket år?",
                "1030",
                "1040",
                "1050",
                "1060",
                1);

        quizAttempt(sweHist,
                "When was the prime minister Olof Palme killed?",
                "1980",
                "1982",
                "1984",
                "1986",
                4);

        quizAttempt(engHist,
                "Who was the previous king of England?",
                "George VI",
                "Henry VII",
                "William IV",
                "Edward VII",
                1);

        quizAttempt(scoHist,
                "What was Sir William Wallice's weapon of choice?",
                "A bow",
                "A spear",
                "A sword",
                "An axe",
                3);

        quizAttempt(jpnHist,
                "What period came after the Meiji era?",
                "The Edo period",
                "The Taisho period",
                "The Showa period",
                "The Heisei period",
                2);

    }

    private void quizAttempt(long subCategoryId, String question, String firstAnswer, String secondAnswer,
                             String thirdAnswer, String fourthAnswer, int indexOfCorrectAnswer){

        attempt(() -> quizEjb.createQuiz(subCategoryId,question,firstAnswer,secondAnswer,
                thirdAnswer,fourthAnswer,indexOfCorrectAnswer));
    }

    private <T> T attempt(Supplier<T> lambda){
        try {
            return lambda.get();
        }
        catch (Exception e){
            return null;
        }
    }

}
