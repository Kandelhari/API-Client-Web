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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = DataBaseValues.PRODUCTCATEGORY_PRODUCT_ID,
            nullable = DataBaseValues.IS_NOT_NULLABLE
    )
    private Product productID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = DataBaseValues.PRODUCTCATEGORY_CATEGORY_ID,
            nullable = DataBaseValues.IS_NOT_NULLABLE
    )
    private Category categoryID;


    // Getters and Setters

    public Product getProductID() {
        return productID;
    }
    public void setProductID(Product productID) {
        this.productID = productID;
    }
    public Category getCategoryID() {
        return categoryID;
    }
    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
