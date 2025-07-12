package ecom_project.demo.Service;

import ecom_project.demo.Model.Product;
import ecom_project.demo.Repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private MultipartFile imageFile;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
    }

    @Test
    void addProduct_shouldSaveProduct() throws IOException {
        // Arrange
        when(imageFile.getOriginalFilename()).thenReturn("image.jpg");
        when(imageFile.getContentType()).thenReturn("image/jpeg");
        when(imageFile.getBytes()).thenReturn(new byte[]{});

        // Act
        productService.Add(product, imageFile);

        // Assert
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void updateProduct_shouldUpdateProduct() throws IOException {
        // Arrange
        when(imageFile.getOriginalFilename()).thenReturn("updated_image.jpg");
        when(imageFile.getContentType()).thenReturn("image/jpeg");
        when(imageFile.getBytes()).thenReturn(new byte[]{});

        // Crează un obiect Product nou pentru a verifica actualizarea
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Test Product");
        updatedProduct.setImageName("updated_image.jpg");
        updatedProduct.setImageType("image/jpeg");
        updatedProduct.setImageDate(new byte[]{});

        // Act
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.Update(updatedProduct, 1, imageFile);

        // Assert
        assertNotNull(result);
        assertEquals("updated_image.jpg", result.getImageName());  // Verifică modificarea
        verify(productRepository, times(1)).save(any(Product.class));
    }


    @Test
    void getProduct_shouldReturnProduct() {
        // Arrange
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // Act
        Product foundProduct = productService.GetProduct(1);

        // Assert
        assertEquals(product, foundProduct);
    }

    @Test
    void getProduct_shouldReturnNullIfNotFound() {
        // Arrange
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Product foundProduct = productService.GetProduct(1);

        // Assert
        assertNull(foundProduct);
    }

    @Test
    void deleteProduct_shouldDeleteProduct() {
        // Act
        productService.Delete(1);

        // Assert
        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    void searchProduct_shouldReturnProductList() {
        // Arrange
        // When search is called with a keyword, mock the productRepository search
        when(productRepository.searchProduct("test")).thenReturn(List.of(product));

        // Act
        var productList = productService.searchProduct("test");

        // Assert
        assertFalse(productList.isEmpty());
    }
}
