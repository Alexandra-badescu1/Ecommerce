package ecom_project.demo.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ecom_project.demo.Model.Product;
import ecom_project.demo.Service.ProductService;
import ecom_project.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addProduct(
            @RequestPart("product") String productJson,
            @RequestPart("imageFile") MultipartFile imageFile
    ) {
        try {
            System.out.println("Received JSON: " + productJson);
            System.out.println("Received File: " + imageFile.getOriginalFilename());

            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(productJson, Product.class);

            productService.Add(product, imageFile);

            return ResponseEntity.ok("Product added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding product: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id)
    {
        Product product = productService.GetProduct(id);
        if(product != null)
        {
            productService.Delete(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Bad Request",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,
                                @RequestPart("product") Product product,
                                @RequestPart("imageFile") MultipartFile imageFile) throws IOException {
        Product productNew = productService.Update(product, id, imageFile);
        if(productNew != null)
            return new ResponseEntity<>("Update",HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product = productService.GetProduct(id);
        if(product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(productService.GetAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/get/{productid}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productid){
        Product product = productService.GetProduct(productid);
        byte[] image = product.getImageDate();
        if(product != null)
            return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(image);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/get/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        return new ResponseEntity<>(productService.searchProduct(keyword),HttpStatus.OK);
    }

}
