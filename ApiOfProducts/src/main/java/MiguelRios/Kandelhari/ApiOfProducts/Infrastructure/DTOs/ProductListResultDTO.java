package MiguelRios.Kandelhari.ApiOfProducts.Infrastructure.DTOs;

import MiguelRios.Kandelhari.ApiOfProducts.Core.Entities.Product;
import org.springframework.data.domain.Page;

public class ProductListResultDTO {

    private final Page<Product> productPage;
    private final long totalItems;

    public ProductListResultDTO(Page<Product> productPage, long totalItems) {
        this.productPage = productPage;
        this.totalItems = totalItems;
    }

    public Page<Product> getProductPage() {
        return productPage;
    }

    public long getTotalItems() {
        return totalItems;
    }
}
