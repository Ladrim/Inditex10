package org.alfonso.domain;

import org.alfonso.application.PriceByProductIdBrandIdDateCommand;
import org.alfonso.application.PricebyProductIdPriceIdCommand;
import org.alfonso.application.ProductRepository;
import org.alfonso.domain.price.Price;
import org.alfonso.domain.price.PriceId;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.domain.product.ProductName;

import java.util.*;
import java.util.function.BiFunction;

public class ProductUseCaseV2
{
    private final ProductRepository productRepository;

    Map<String, Integer> testMap = new HashMap<>();


    public ProductUseCaseV2(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    //SAVE PRODUCT------------------------------------------------------------------------------------------------------
    public void saveProduct(Product product)
    {
        Optional<Product> savedProduct = productRepository.findByProductId(product.getId());

        if(savedProduct.isEmpty())
        {
            productRepository.save(product);
        }
        else
        {
            throw new RuntimeException("Ya existe un producto con este Id");
        }
    }

    //DELETE PRODUCT----------------------------------------------------------------------------------------------------
    public void deleteProduct(ProductId productId)
    {
        Optional<Product> productToFind = productRepository.findByProductId(productId);

        if(productToFind.isEmpty())
        {
            throw new RuntimeException("No se encuentra el producto");
        }
        else
        {
            productRepository.delete(productId);
        }
    }

    //FIND PRODUCT------------------------------------------------------------------------------------------------------
    public Optional<Product> findProduct(ProductId productId)
    {
        return productRepository.findByProductId(productId);
    }

    //FIND ALL PRODUCTS-------------------------------------------------------------------------------------------------
    public List<Product> findAllProducts()
    {
        return productRepository.findAll();
    }

    //FIND PRODUCT BY PRICE_ID------------------------------------------------------------------------------------------
    public Optional<Product> findProductByPriceId(PriceId priceId)
    {
        return productRepository.findProductByPriceId(priceId);
    }

    //FIND PRICE BY PRODUCT_ID, PRICE_ID--------------------------------------------------------------------------------
    public Optional<Price> findPriceByProductIdPriceId(PricebyProductIdPriceIdCommand command)
    {
        Optional<Product> productToFind = productRepository.findByProductId(command.getProductId());

        if(productToFind.isPresent())
        {
           return productToFind.get().findPriceByPriceId(command.getPriceId());
        }
        else
        {
            throw new RuntimeException("El producto no existe");
        }
    }

    //FIND PRICE BY PRODUCT_ID, BRAND_ID, DATE--------------------------------------------------------------------------
    public Optional<Price> findPriceByProductIdBrandIdDate(PriceByProductIdBrandIdDateCommand command)
    {
        Optional<Product> productToFind = productRepository.findByProductId(command.getProductId());

        if(productToFind.isPresent())
        {
            return productToFind.get().findPriceByBrandIdDate(command.getBrandId(), command.getDate());
        }
        else
        {
            throw new RuntimeException("El producto no existe");
        }
    }

    //ADD PRICE TO PRODUCT----------------------------------------------------------------------------------------------
    public void addPrice(ProductId productId, Price price)
    {
        Optional<Product> productToFind = productRepository.findByProductId(productId)
            .map(it-> it.addPrice(price));
    }
}



