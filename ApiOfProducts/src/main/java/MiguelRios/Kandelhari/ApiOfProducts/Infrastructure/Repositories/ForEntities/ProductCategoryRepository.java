package MiguelRios.Kandelhari.ApiOfProducts.Infrastructure.Repositories.ForEntities;

import MiguelRios.Kandelhari.ApiOfProducts.Core.Entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
