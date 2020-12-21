package com.denigunawan.restfull.controller;

import com.denigunawan.restfull.model.Product;
import com.denigunawan.restfull.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // anotasi yang menandakan class ini akan membuat restfull
@RequestMapping("/api/products") // anotasi yang memberikan URl yang bisa kita akses
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct( @Validated @RequestBody Product product) {
        return new ResponseEntity<>(productService.saveOrUpdateProduct(product), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOneProduct(@PathVariable Long id) throws Exception{
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "Delete Sukses";
    }

}
