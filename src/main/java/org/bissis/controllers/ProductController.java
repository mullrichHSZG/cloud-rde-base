package org.bissis.controllers;

import org.bissis.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Markus Ullrich
 */
@Controller
public class ProductController {

    private ProductService productService;

    @RequestMapping("/products")
    public String listProducts(Model model){

        model.addAttribute("products", productService.listAllProducts());

        return "products";
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
