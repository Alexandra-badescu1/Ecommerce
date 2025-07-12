package ecom_project.demo.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(
                1L,
                "Laptop",
                "High performance laptop",
                "Dell",
                new BigDecimal("999.99"),
                10,
                "Electronics",
                new Date(),
                true,
                "laptop.png",
                "image/png",
                new byte[]{1, 2, 3}
        );
    }

    @AfterEach
    void tearDown() {
        product = null;
    }

    @Test
    void getId() {
        assertEquals(1L, product.getId());
    }

    @Test
    void getName() {
        assertEquals("Laptop", product.getName());
    }

    @Test
    void getDescription() {
        assertEquals("High performance laptop", product.getDescription());
    }

    @Test
    void getBrand() {
        assertEquals("Dell", product.getBrand());
    }

    @Test
    void getPrice() {
        assertEquals(new BigDecimal("999.99"), product.getPrice());
    }

    @Test
    void getStockQuantity() {
        assertEquals(10, product.getStockQuantity());
    }

    @Test
    void getCategory() {
        assertEquals("Electronics", product.getCategory());
    }

    @Test
    void getReleaseDate() {
        assertNotNull(product.getReleaseDate());
    }

    @Test
    void isProductAvailable() {
        assertTrue(product.isProductAvailable());
    }

    @Test
    void getImageName() {
        assertEquals("laptop.png", product.getImageName());
    }

    @Test
    void getImageType() {
        assertEquals("image/png", product.getImageType());
    }

    @Test
    void getImageDate() {
        assertArrayEquals(new byte[]{1, 2, 3}, product.getImageDate());
    }

    @Test
    void setId() {
        product.setId(2L);
        assertEquals(2L, product.getId());
    }

    @Test
    void setName() {
        product.setName("Tablet");
        assertEquals("Tablet", product.getName());
    }

    @Test
    void setDescription() {
        product.setDescription("New tablet");
        assertEquals("New tablet", product.getDescription());
    }

    @Test
    void setBrand() {
        product.setBrand("HP");
        assertEquals("HP", product.getBrand());
    }

    @Test
    void setPrice() {
        product.setPrice(new BigDecimal("499.99"));
        assertEquals(new BigDecimal("499.99"), product.getPrice());
    }

    @Test
    void setStockQuantity() {
        product.setStockQuantity(20);
        assertEquals(20, product.getStockQuantity());
    }

    @Test
    void setCategory() {
        product.setCategory("Gadgets");
        assertEquals("Gadgets", product.getCategory());
    }

    @Test
    void setReleaseDate() {
        Date newDate = new Date();
        product.setReleaseDate(newDate);
        assertEquals(newDate, product.getReleaseDate());
    }

    @Test
    void setProductAvailable() {
        product.setProductAvailable(false);
        assertFalse(product.isProductAvailable());
    }

    @Test
    void setImageName() {
        product.setImageName("tablet.png");
        assertEquals("tablet.png", product.getImageName());
    }

    @Test
    void setImageType() {
        product.setImageType("image/jpeg");
        assertEquals("image/jpeg", product.getImageType());
    }

    @Test
    void setImageDate() {
        byte[] newImageData = new byte[]{4, 5, 6};
        product.setImageDate(newImageData);
        assertArrayEquals(newImageData, product.getImageDate());
    }
}