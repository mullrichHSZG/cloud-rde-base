package org.bissis.services;

import org.bissis.config.JpaIntegrationConfig;
import org.bissis.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Markus Ullrich
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class ProductServiceJpaDaoImplTest {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testListMethod() {
        List<Product> productList = (List<Product>) productService.listAll();

        assert productList.size() == 5;
    }

    @Test
    public void testGetByIdMethod() {
        Product result = productService.getById(1);
        assert result != null;
        result = productService.getById(6);
        assert result == null;
    }

    @Test
    public void testSaveOrUpdateMethod() {
        Product expected = new Product();
        Integer id = 6;
        String description = "newProduct";
        BigDecimal price = new BigDecimal("13.00");
        String imageUrl = "url/fgh";
        expected.setImageUrl(imageUrl);
        expected.setPrice(price);
        expected.setDescription(description);
        Product result = productService.saveOrUpdate(expected);
        expected.setId(id);
        assert expected.getDescription().equals(result.getDescription());
        assert expected.getImageUrl().equals(result.getImageUrl());
        assert expected.getPrice().equals(result.getPrice());
    }

    @Test
    public void testDeleteMethod() {
        productService.delete(1);
        assert productService.listAll().size() == 4;
    }
}
