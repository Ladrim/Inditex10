package org.alfonso.application;

import org.alfonso.domain.price.PriceId;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;

import java.util.List;
import java.util.Optional;

public interface ProductRepository
{
    void save(Product product);

    void delete(ProductId productId);

    Optional<Product> findByProductId(ProductId productId);

    List<Product> findAll();

    Optional<Product> findProductByPriceId(PriceId priceId);

}
