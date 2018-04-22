package org.bissis.services;

import org.bissis.domain.Product;

import java.util.List;

/**
 * @author Markus Ullrich
 */
public interface ProductService {

    List<Product> listAllProducts();
}
