package com.denigunawan.restfull.service;

import com.denigunawan.restfull.model.Product;
import com.denigunawan.restfull.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // menandakan kelas serice
public class ProductServiceImpl implements ProductService {

    @Autowired // menginject semua perintah di ProductRepository
    ProductRepository productRepository;


    // method yang digunakan untuk mengembalikan nilai product di databse yang nantinya akan
    // dikembalikan ke controller sebagai pemanggil method ini
    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }


    // mencari data berdasarkan id. tapi disini kita beri logic , jika Idnya tidak ada
    // maka class ini akan mengamblikan nilai kosong
    @Override
    public Product findProductById(Long id) throws Exception {
        Product product = productRepository.findById(id).orElse(new Product());

        return product;
    }

    // menyimpan data product dan memperbarui data product
    @Override
    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    // menghapus data product, dsini saya memberikan logic, apabila datanya kosong
    // maka akan mengembalikan nilai Product Kosong
    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(new Product());
        productRepository.delete(product);
    }
}
