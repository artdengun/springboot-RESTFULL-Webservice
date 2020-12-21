package com.denigunawan.restfull;

import com.denigunawan.restfull.model.Product;
import com.denigunawan.restfull.repository.ProductRepository;
import com.denigunawan.restfull.service.ProductService;
import com.denigunawan.restfull.service.ProductServiceImpl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService = new ProductServiceImpl();;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void  setup(){
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(productService, "productRepository",
                productRepository);
    }

    @Test
    public void testFindAll(){
        final List<Product>  datas = TestObjectFactory.createProductList(10);

        Mockito.when(productRepository.findAll()).thenReturn(datas);

        final List<Product> actual = productService.findAllProducts();

        MatcherAssert.assertThat(actual.size(), Matchers.equalTo(datas.size()));
    }

    @Test
    public void testProductById() throws Exception{
        final Long id = new Random().nextLong();;
        final Product product = TestObjectFactory.createProduct();

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));
            final Product actual = productService.findProductById(id);

            MatcherAssert.assertThat(actual.getId(), Matchers.equalTo(product.getId()));
            MatcherAssert.assertThat(actual.getNama(), Matchers.equalTo(product.getNama()));
            MatcherAssert.assertThat(actual.getHargaBeli(), Matchers.equalTo(product.getHargaBeli()));
            MatcherAssert.assertThat(actual.getHargaJual(), Matchers.equalTo(product.getHargaJual()));
    }
//
//    @Test
//    public void testProductByIdWithNullDataFromDB() throws Exception{
//        final Long id = new Random().nextLong();
//        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());
//        final Product actual = productService.findProductById(id);
//        MatcherAssert.assertThat(actual, Matchers.nullValue());
//    }

    @Test
    public void testSavePrUpdateProduct(){
        final Product product = TestObjectFactory.createProduct();
        Mockito.when(productRepository.save(product)).thenReturn(product);
        final Product actual = productService.saveOrUpdateProduct(product);
        MatcherAssert.assertThat(actual, Matchers.notNullValue());
    }


    @Test
    public void testDeleteProduct(){
        final Long id = new Random().nextLong();
        Product product = TestObjectFactory.createProduct();

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));
        Mockito.doNothing().when(productRepository).delete(product);

        productService.deleteProduct(id);

        Mockito.verify(productRepository,Mockito.times(1)).delete(product);
    }
}
