package org.alfonso.domain.product;

import org.alfonso.domain.price.BrandId;
import org.alfonso.domain.price.Price;
import org.alfonso.domain.price.PriceId;

import java.util.*;

public class Product
{
    private final ProductId id;
    private final ProductName name;
    private final List<Price> prices;

    public Product(ProductId id, ProductName name, List<Price> prices)
    {
        this.id = id;
        this.name = name;
        this.prices = prices;
    }

    public ProductId getId()
    {   return id;}

    public ProductName getName()
    {   return name;}

    public List<Price> getPrices()
    {   return prices;}

    public Product changeName(ProductName productName)
    {
        return new Product(id, productName, prices);
    }

    public Product addPrice(Price price)
    {
        Optional<Price> priceToFind = prices.stream()
            .filter(it->it.getId().equals(price.getId()))
            .findFirst();

        if(priceToFind.isPresent())
        {
            throw new RuntimeException("Ya existe un precio con este id");
        }
        else
        {
            List<Price> newPrices = new ArrayList<>(prices);

            return new Product(id, name, newPrices);
        }
    }

    public Optional<Price> findPriceByBrandIdDate(BrandId brandId, Date date)
    {
        return prices.stream()
            .filter(it->it.getBrandId().equals(brandId))
            .filter(it->it.getDateInRange().isInRange(date))
            .max(Comparator.comparing(it->it.getPriority().getValue()));
    }

    public Optional<Price> findPriceByPriceId(PriceId priceId)
    {
        return prices.stream()
            .filter(it->it.getId().equals(priceId))
            .findFirst();
    }
}
