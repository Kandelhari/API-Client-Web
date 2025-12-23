package MiguelRios.Kandelhari.ApiOfProducts.Core.Entities;

import MiguelRios.Kandelhari.ApiOfProducts.Core.Entities.ConstantValues.DataBaseValues;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = DataBaseValues.TABLE_CATEGORY)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DataBaseValues.ID)
    private Long id;

    @NotBlank(message = DataBaseValues.CATEGORY_NAME_BLANKED_MESSAGE)
    @Size(
            max = DataBaseValues.CATEGORY_NAME_MAX_SIZE,
            message = DataBaseValues.CATEGORY_NAME_OVER_MAX_SIZE_MESSAGE
    )
    @Column(
            name = DataBaseValues.CATEGORY_NAME,
            nullable = DataBaseValues.IS_NOT_NULLABLE,
            length = DataBaseValues.CATEGORY_NAME_MAX_SIZE
            )
    private String name;


    // Getters y Setters

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
