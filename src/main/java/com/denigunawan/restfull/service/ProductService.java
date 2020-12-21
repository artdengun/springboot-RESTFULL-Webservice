package com.denigunawan.restfull.service;

import com.denigunawan.restfull.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts(); // untuk mengambil semua data yang ada di product

    Product findProductById(Long id) throws Exception; // mencari produk berdasarkan Id

    Product saveOrUpdateProduct(Product product); // simpan atau update data

    void deleteProduct(Long id); // menghapus data
}
