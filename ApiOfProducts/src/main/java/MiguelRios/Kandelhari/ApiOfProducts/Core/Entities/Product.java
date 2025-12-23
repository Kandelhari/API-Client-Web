package MiguelRios.Kandelhari.ApiOfProducts.Core.Entities;

import MiguelRios.Kandelhari.ApiOfProducts.Core.Entities.ConstantValues.DataBaseValues;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = DataBaseValues.TABLE_PRODUCT)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DataBaseValues.ID)
    private Long id;

    @NotBlank(message = DataBaseValues.PRODUCT_NAME_BLANKED_MESSAGE)
    @Size(
            max = DataBaseValues.PRODUCT_NAME_MAX_SIZE,
            message = DataBaseValues.PRODUCT_NAME_OVER_MAX_SIZE_MESSAGE
    )
    @Column(
            length = DataBaseValues.PRODUCT_NAME_MAX_SIZE,
            name = DataBaseValues.PRODUCT_NAME,
            nullable = DataBaseValues.IS_NOT_NULLABLE,
            unique = DataBaseValues.IS_UNIQUE
    )
    private String name;

    @NotNull(message = DataBaseValues.PRODUCT_AMOUNT_NULL_MESSAGE)
    @Max(
            value = DataBaseValues.PRODUCT_AMOUNT_MAX_VALUE,
            message = DataBaseValues.PRODUCT_AMOUNT_OVER_MAX_VALUE_MESSAGE
    )
    @Min(
            value = DataBaseValues.PRODUCT_AMOUNT_MIN_VALUE,
            message = DataBaseValues.PRODUCT_AMOUNT_UNDER_MIN_VALUE_MESSAGE
    )
    @Column(
            nullable = DataBaseValues.IS_NOT_NULLABLE,
            name = DataBaseValues.PRODUCT_AMOUNT
    )
    private Integer amount;

    @OneToMany(mappedBy = "productID", fetch = FetchType.LAZY)
    private List<ProductCategory> productCategories = new ArrayList<>();


    // Getters and Setters

    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }
    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
