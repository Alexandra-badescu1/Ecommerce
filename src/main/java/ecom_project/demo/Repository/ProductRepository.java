package ecom_project.demo.Repository;

import ecom_project.demo.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByName(String Name);

    @Query("SELECT p FROM Product p WHERE "+
            "LOWER(p.name) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%',:keyword,'%')) OR "+
            "LOWER(p.brand) LIKE LOWER(CONCAT('%',:keyword,'%')) OR "+
            "LOWER(p.category) LIKE LOWER(CONCAT('%',:keyword,'%'))"
    )
    List<Product> searchProduct(String keyword);

}
