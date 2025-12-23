package MiguelRios.Kandelhari.ApiOfProducts.Infrastructure.Repositories.ForEntities;

import MiguelRios.Kandelhari.ApiOfProducts.Core.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p ORDER BY p.id")
    Page<Product> findAllOrderedById(Pageable pageable);
    @Query("SELECT COUNT(p) FROM Product p")
    long countTotalProducts();

    @Query("SELECT p FROM Product p " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "ORDER BY p.id")
    Page<Product> findByNameContainingOrderedById(@Param("searchTerm") String searchTerm, Pageable pageable);
    @Query("SELECT COUNT(p) FROM Product p " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    long countByNameContaining(@Param("searchTerm") String searchTerm);


}
