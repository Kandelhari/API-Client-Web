package MiguelRios.Kandelhari.ApiOfProducts.Infrastructure.Services;

import MiguelRios.Kandelhari.ApiOfProducts.Core.Entities.Product;
import MiguelRios.Kandelhari.ApiOfProducts.Infrastructure.DTOs.ProductListResultDTO;
import MiguelRios.Kandelhari.ApiOfProducts.Infrastructure.Repositories.ForEntities.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ProductListResultDTO getInitialProducts(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAllOrderedById(pageable);
        long totalProducts = productRepository.countTotalProducts();
        return new ProductListResultDTO(productPage, totalProducts);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product createProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name of product is required");
        }
        product.setId(null);
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not founded product with id: " + id));

        if (productDetails.getName() != null) {
            existingProduct.setName(productDetails.getName());
        }

        if (productDetails.getAmount() != null) {
            existingProduct.setAmount(productDetails.getAmount());
        }

        return productRepository.save(existingProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Not founded product with id: " + id);
        }

        productRepository.deleteById(id);
    }

    @Transactional
    public void deleteProducts(List<Long> ids) {
        for (Long id : ids) {
            if (!productRepository.existsById(id)) {
                throw new RuntimeException("Not founded product with id: " + id);
            }
        }

        productRepository.deleteAllById(ids);
    }

    public Map<String, Object> buildProductResponse(ProductListResultDTO result, String searchTerm) {
        Map<String, Object> response = new HashMap<>();

        response.put("products", result.getProductPage().getContent());
        response.put("totalItems", result.getTotalItems());

        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            response.put("searchTerm", searchTerm);
            response.put("searchResultsCount", result.getTotalItems());
        }

        return response;
    }

}
