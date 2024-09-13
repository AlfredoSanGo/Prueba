package com.example.pruebatecnica.demo;

import com.example.pruebatecnica.demo.model.Product;
import com.example.pruebatecnica.demo.repository.ProductRepository;
import com.example.pruebatecnica.demo.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testCreateProduct() {
        Product product = new Product(null,"Test Product", "Description", 10.0, "1234567890");
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertThat(createdProduct.getName()).isEqualTo("Test Product");
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Test Product", "Description", 10.0, "1234567890");
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(1L);

        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getName()).isEqualTo("Test Product");
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}