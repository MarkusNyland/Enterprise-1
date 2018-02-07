package JEE.JpaDatabase;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

@Entity
public class Category {
    @Id @GeneratedValue
    private Long id;

    @Column (unique = true)
    @NotBlank
    @Size (min = 2, max = 128)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) //spørre om fetch type.
    private List<SubCategory> subCategories;

    public Category() {
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

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(SubCategory... subCategories) {
        this.subCategories = Arrays.asList(subCategories);
    }
}
