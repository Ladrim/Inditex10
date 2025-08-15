/*
package org.alfonso.infraestructure.repositories.inh2;

import org.alfonso.application.DateFormatter;
import org.alfonso.domain.price.*;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.domain.product.ProductName;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ProductRepositoryInH2Adapter
{
    private final DateFormatter dateFormatter;

    public ProductRepositoryInH2Adapter(DateFormatter dateFormatter)
    {
        this.dateFormatter = dateFormatter;
    }


    public ProductInH2Entity toEntity(Product product)
    {
        String id = product.getId().getValue().toString();
        String name = product.getName().getValue();

        return new ProductInH2Entity(id, name);
    }

    public PriceInH2Entity toEntity(Product product, Price price)
    {
        String id = price.getId().getValue().toString();
        String brandId = price.getBrandId().getValue().toString();
        String startDate = dateFormatter.parseDate(price.getDateInRange().getStartDate());
        String endDate = dateFormatter.parseDate(price.getDateInRange().getEndDate());
        Integer priority = price.getPriority().getValue();
        String currencyType = price.getMoney().getCurrencyType().getValue();
        Float amount = price.getMoney().getAmount();
        String productId = product.getId().getValue().toString();

        return new PriceInH2Entity(id, brandId, startDate, endDate, priority, currencyType, amount, productId);
    }

    public Product toDomain(ProductInH2Entity productInH2Entity, List<PriceInH2Entity> priceInH2Entities)
    {
        ProductId id = new ProductId(UUID.fromString(productInH2Entity.getId()));
        ProductName name = new ProductName(productInH2Entity.getName());
        List<Price> prices = priceInH2Entities.stream().map(it->toDomain(it)).toList();

        return new Product(id, name, prices);
    }

    public Price toDomain(PriceInH2Entity priceInH2Entity)
    {
        PriceId id = new PriceId(UUID.fromString(priceInH2Entity.getId()));
        BrandId brandId = new BrandId(UUID.fromString(priceInH2Entity.getBrandId()));
        Date startDate = dateFormatter.parseString(priceInH2Entity.getStartDate());
        Date endDate = dateFormatter.parseString(priceInH2Entity.getEndDate());
        DateInRange dateInRange = new DateInRange(startDate, endDate);
        Priority priority = new Priority(priceInH2Entity.getPriority());
        CurrencyType type = CurrencyType.valueOf(priceInH2Entity.getCurrencyType());
        Float amount = priceInH2Entity.getAmount();
        Money money = new Money(type, amount);

        return new Price(id, brandId, dateInRange, priority, money);
    }

}
*/
