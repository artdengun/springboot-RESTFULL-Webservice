package com.denigunawan.restfull;

import com.denigunawan.restfull.model.Product;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestObjectFactory {

    // berfungsi untuk membuat object dari class product. dalam method tersebut
    // terlihat kita menggunakan class RandomStringUtils yang merupakan
    // salah satu class dari library org.apache.commons
    public static Product createProduct(){
        final Product product = new Product();
        product.setId(new Random().nextLong());
        product.setNama(RandomStringUtils.randomAlphabetic(10));
        product.setHargaBeli(new Random().nextLong());
        product.setHargaJual(new Random().nextLong());

        return product;
    }
    // berfungsi untuk membuat object list dengan tipe data product dengan menggunakan parameter
    // size sehingga kita bisa menentukan jumlah data dalam list tersebut
    public static List<Product> createProductList(final int size) {
        final List<Product> result = new ArrayList<>();
        for(int i = 0; i < size; i++){
            result.add(createProduct());
        }
        return  result;
    }
}
