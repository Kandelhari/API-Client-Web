package MiguelRios.Kandelhari.ApiOfProducts.Infrastructure.Repositories.ForEntities;

import MiguelRios.Kandelhari.ApiOfProducts.Core.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
