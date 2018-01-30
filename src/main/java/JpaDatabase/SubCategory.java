package JpaDatabase;

import JpaDatabase.Category;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class SubCategory {

    @Id @GeneratedValue
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 128)
    private  String name;

    @NotNull
    @ManyToOne
    private Category category;

    public SubCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
