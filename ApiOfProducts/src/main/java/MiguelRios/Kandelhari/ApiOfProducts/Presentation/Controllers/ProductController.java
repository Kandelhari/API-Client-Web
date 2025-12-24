package MiguelRios.Kandelhari.ApiOfProducts.Presentation.Controllers;

import MiguelRios.Kandelhari.ApiOfProducts.Core.Entities.Product;
import MiguelRios.Kandelhari.ApiOfProducts.Infrastructure.DTOs.ProductListResultDTO;
import MiguelRios.Kandelhari.ApiOfProducts.Infrastructure.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            ProductListResultDTO result = productService.getInitialProducts(page, size);
            Map<String, Object> response = productService.buildProductResponse(result, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Server error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProducts(
            @RequestParam String searched,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try {
            ProductListResultDTO result = productService.searchProducts(searched, page, size);
            Map<String, Object> response = productService.buildProductResponse(result, searched);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Server error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            if (product == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Product data is required");
                return ResponseEntity.badRequest().body(error);
            }

            Product createdProduct = productService.createProduct(product);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Product created successfully");
            response.put("productId", createdProduct.getId());
            response.put("product", createdProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Internal server error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @RequestBody Product productDetails) {

        try {
            if (productDetails == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Product update data is required");
                return ResponseEntity.badRequest().body(error);
            }
            Product updatedProduct = productService.updateProduct(id, productDetails);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Product updated successfully");
            response.put("product", updatedProduct);
            response.put("productId", updatedProduct.getId());
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Internal server error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            if (id == null || id <= 0) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Invalid product ID");
                return ResponseEntity.badRequest().body(error);
            }

            productService.deleteProduct(id);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Product deleted successfully");
            response.put("deletedId", id);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            error.put("status", "NOT_FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to delete product");
            error.put("details", e.getMessage());
            error.put("status", "INTERNAL_SERVER_ERROR");
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @DeleteMapping("/batch")
    public ResponseEntity<?> deleteProducts(@RequestBody List<Long> ids) {
        try {
            if (ids == null || ids.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Product IDs list cannot be empty");
                return ResponseEntity.badRequest().body(error);
            }

            for (Long id : ids) {
                if (id == null || id <= 0) {
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "Invalid product ID: " + id);
                    error.put("invalidId", String.valueOf(id));
                    return ResponseEntity.badRequest().body(error);
                }
            }
            productService.deleteProducts(ids);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Products deleted successfully");
            response.put("deletedIds", ids);
            response.put("deletedCount", ids.size());
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            error.put("status", "NOT_FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to delete products");
            error.put("details", e.getMessage());
            error.put("status", "INTERNAL_SERVER_ERROR");
            return ResponseEntity.internalServerError().body(error);
        }
    }
}