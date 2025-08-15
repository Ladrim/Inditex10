package org.alfonso.infraestructure.repositories.inmemory;

import org.alfonso.application.ProductRepository;
import org.alfonso.domain.price.Price;
import org.alfonso.domain.price.PriceId;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;

import java.util.List;
import java.util.Optional;

public class ProductInMemoryRepository implements ProductRepository
{
    @Override
    public void save(Product product)
    {

    }

    @Override
    public void delete(ProductId productId)
    {

    }

    @Override
    public Optional<Product> findByProductId(ProductId productId)
    {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll()
    {
        return List.of();
    }

    @Override
    public Optional<Product> findProductByPriceId(PriceId priceId)
    {
        return Optional.empty();
    }
}
