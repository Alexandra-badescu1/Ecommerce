package ecom_project.demo.Repository;

import ecom_project.demo.Model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByName_shouldReturnProduct() {
        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("Gaming laptop");
        product.setPrice(BigDecimal.valueOf(1000.0));
        productRepository.save(product);

        Optional<Product> found = productRepository.findByName("Laptop");

        assertTrue(found.isPresent());
        assertEquals("Laptop", found.get().getName());
    }

    @Test
    void searchProduct_shouldReturnMatchingProducts() {
        Product p1 = new Product();
        p1.setName("Laptop");
        p1.setDescription("Gaming laptop");
        p1.setPrice(BigDecimal.valueOf(1000.0));

        Product p2 = new Product();
        p2.setName("Phone");
        p2.setDescription("Smartphone with great camera");
        p2.setPrice(BigDecimal.valueOf(600.0));

        productRepository.save(p1);
        productRepository.save(p2);

        List<Product> results = productRepository.searchProduct("camera");

        assertEquals(1, results.size());
        assertEquals("Phone", results.get(0).getName());
    }
}
