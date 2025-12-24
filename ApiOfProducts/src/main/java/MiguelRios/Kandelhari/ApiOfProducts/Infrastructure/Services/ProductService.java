package MiguelRios.Kandelhari.ApiOfProducts.Infrastructure.Services;

import MiguelRios.Kandelhari.ApiOfProducts.Core.Entities.Product;
import MiguelRios.Kandelhari.ApiOfProducts.Infrastructure.DTOs.ProductListResultDTO;
import MiguelRios.Kandelhari.ApiOfProducts.Infrastructure.Repositories.ForEntities.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductListResultDTO getInitialProducts(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAllOrderedById(pageable);
        long totalProducts = productRepository.countTotalProducts();
        return new ProductListResultDTO(productPage, totalProducts);
    }

    public ProductListResultDTO searchProducts(String searchTerm, int page, int size) {

        String cleanSearchTerm = (searchTerm != null) ? searchTerm.trim() : "";
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage;
        long totalResults;

        if (!cleanSearchTerm.isEmpty()) {
            productPage = productRepository.findByNameContainingOrderedById(cleanSearchTerm, pageable);
            totalResults = productRepository.countByNameContaining(cleanSearchTerm);
        } else {
            return getInitialProducts(page, size);
        }

        return new ProductListResultDTO(productPage, totalResults);
    }
}
