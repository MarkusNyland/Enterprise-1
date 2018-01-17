import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Quiz {

    @Id @GeneratedValue
    private Long id;

    private String question;

    private String answer1;

    private String answer2;

    private String answer3;

    private String answer4;

    private Long rightAnswer;

    @OneToOne
    private SubCategory subCategory;

    public Quiz() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public Long getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Long rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public SubCategory getSubCategory() {
        return subCategory;
}

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}