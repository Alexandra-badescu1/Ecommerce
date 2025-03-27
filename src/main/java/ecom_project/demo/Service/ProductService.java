package ecom_project.demo.Service;

import ecom_project.demo.Model.Product;
import ecom_project.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void Add(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageDate(imageFile.getBytes());
        productRepository.save(product);
    }
    public void Delete(int id){

        productRepository.deleteById(id);
    }
    public Product Update(Product product, int id, MultipartFile imageFile) throws IOException {
        product.setImageType(imageFile.getContentType());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageDate(imageFile.getBytes());

       return productRepository.save(product);
    }

    public Product GetProduct(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null); // Evită erorile dacă produsul nu există
    }

    public List<Product> GetAllProduct(){
        return productRepository.findAll();
    }

    public List<Product> searchProduct(String keyword){
      //  return productRepository.findByCategory(category);
        return productRepository.searchProduct(keyword);
    }
}
