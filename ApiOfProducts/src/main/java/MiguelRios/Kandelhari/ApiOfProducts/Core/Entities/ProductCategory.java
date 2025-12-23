package MiguelRios.Kandelhari.ApiOfProducts.Core.Entities;

import MiguelRios.Kandelhari.ApiOfProducts.Core.Entities.ConstantValues.DataBaseValues;
import jakarta.persistence.*;

@Entity
@Table(name = DataBaseValues.TABLE_PRODUCT_CATEGORY)
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DataBaseValues.ID)
    private Long id;

    @Column(
            name = DataBaseValues.PRODUCTCATEGORY_PRODUCT_ID,
            nullable = DataBaseValues.IS_NOT_NULLABLE
    )
    private String productID;

    @Column(
            name = DataBaseValues.PRODUCTCATEGORY_CATEGORY_ID,
            nullable = DataBaseValues.IS_NOT_NULLABLE
    )
    private String categoryID;


    // Getters and Setters

    public String getCategoryID() {
        return categoryID;
    }
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
    public String getProductID() {
        return productID;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
