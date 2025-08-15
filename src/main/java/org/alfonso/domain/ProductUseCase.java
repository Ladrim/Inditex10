/*
package org.alfonso.domain;

import org.alfonso.application.PriceByProductIdBrandIdDateCommand;
import org.alfonso.application.PricebyProductIdPriceIdCommand;
import org.alfonso.application.ProductRepository;
import org.alfonso.domain.price.Price;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.domain.product.ProductName;

import java.util.List;
import java.util.Optional;

public class ProductUseCase
{
    private final ProductRepository repository;

    public ProductUseCase(ProductRepository repository)
    {
        this.repository = repository;
    }

    //SAVE PRODUCT------------------------------------------------------------------------------------------------------
    public void saveProduct(Product product)
    {
        Optional<Product> optionalProduct = findProduct(product.getId());

        if(optionalProduct.isEmpty())
        {
            repository.save(product);
        }
        else
        {
            throw new RuntimeException("Ya existe un producto con este Id");
        }
    }

    //DELETE PRODUCT----------------------------------------------------------------------------------------------------
    public void deleteProduct(ProductId productId)
    {
        repository.delete(productId);
    }

    //FIND PRODUCT------------------------------------------------------------------------------------------------------
    public Optional<Product> findProduct(ProductId productId)
    {
        return repository.findProduct(productId);
    }

    //FIND ALL PRODUCTS-------------------------------------------------------------------------------------------------
    public List<Product> findAllProducts()
    {
        return repository.findAllProducts();
    }


    //FIND PRICE BY PRODUCTID, PRICEID----------------------------------------------------------------------------------
    public Optional<Price> findPriceByProductIdAndPriceId(PricebyProductIdPriceIdCommand command)
    {
        Optional<Product> productToFind = repository.findProduct(command.getProductId());

        if(productToFind.isEmpty())
        {
            throw new RuntimeException("No se ha encontrado ningun precio con este id");
        }
        else
        {
            return repository.findPrice(command.getPriceId());
        }
    }

    //FIND PRICE BY PRODUCTID, BRANDID AND DATE-------------------------------------------------------------------------
    public Optional<Price> findPrice(PriceByProductIdBrandIdDateCommand command)
    {
        ProductId productId = command.getProductId();

        Optional<Product> productToFind = repository.findProduct(productId);

        if(productToFind.isEmpty())
        {
            throw new RuntimeException("No se ha encontrado ningun precio");
        }
        else
        {
            return productToFind.flatMap(it->it.findPrice(command.getBrandId(), command.getDate()));
        }
    }


    //CHANGE NAME-------------------------------------------------------------------------------------------------------
    public void changeName(ProductId id, ProductName name)
    {
        Optional<Product> optionalProduct = findProduct(id);

        if(optionalProduct.isEmpty())
        {
            throw new RuntimeException("El producto no existe");
        }
        else
        {
            Product product =  optionalProduct.map(it->it.changeName(name)).get();
            repository.save(product);
        }
    }

    //ADD PRICE---------------------------------------------------------------------------------------------------------
    public void addPrice(ProductId id, Price price)
    {
        Optional<Product> productToFind = findProduct(id);
        productToFind.map(it->it.addPrice(price));

        if(productToFind.isEmpty())
        {
            throw new RuntimeException("El producto no existe");
        }
        else
        {
            repository.save(productToFind.get());
        }
    }
}
*/
