package JEE.JpaDatabase;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Quiz {

    @Id @GeneratedValue
    private Long id;

    @Column (unique = true)
    @NotBlank
    @Size(min = 4, max = 256)
    private String question;

    @NotBlank
    @Size (min = 2, max = 128)
    private String answer1;

    @NotBlank
    @Size (min = 2, max = 128)
    private String answer2;

    @NotBlank
    @Size (min = 2, max = 128)
    private String answer3;

    @NotBlank
    @Size (min = 2, max = 128)
    private String answer4;

    @Column (unique = true)
    @NotNull
    @Range(min = 0, max = 3)
    private int rightAnswer;

    @NotNull
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

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public SubCategory getSubCategory() {
        return subCategory;
}

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}
