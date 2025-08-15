package org.alfonso.infraestructure.rest.product;

import org.alfonso.application.DateFormatter;
import org.alfonso.application.PriceByProductIdBrandIdDateCommand;
import org.alfonso.application.PricebyProductIdPriceIdCommand;
import org.alfonso.domain.price.*;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.domain.product.ProductName;
import org.alfonso.infraestructure.rest.product.request.*;
import org.alfonso.infraestructure.rest.product.response.PriceResponseDTO;
import org.alfonso.infraestructure.rest.product.response.ProductResponseDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ProductResourceAdapter
{
    private final DateFormatter dateFormatter;

    public ProductResourceAdapter(DateFormatter dateFormatter)
    {
        this.dateFormatter = dateFormatter;
    }

    public Product toDomain(CreateProductDTO createProductDTO)
    {
        ProductId id = new ProductId(UUID.fromString(createProductDTO.getId()));
        ProductName name = new ProductName(createProductDTO.getName());
        List<Price> prices = createProductDTO.getPriceDTOs().stream().map(it->toDomain(it)).toList();

        return new Product(id, name, prices);
    }

    public Price toDomain(PriceDTO priceDTO)
    {
        PriceId priceId = new PriceId();
        BrandId brandId = new BrandId(UUID.fromString(priceDTO.getBrandId()));
        Date startDate = dateFormatter.parseString(priceDTO.getStartDate());
        Date endDate = dateFormatter.parseString(priceDTO.getEndDate());
        DateInRange dateInRange = new DateInRange(startDate, endDate);
        Priority priority = new Priority(priceDTO.getPriority());
        CurrencyType currencyType = CurrencyType.valueOf(priceDTO.getCurrencyType());
        Float amount = priceDTO.getAmount();
        Money money = new Money(currencyType, amount);

        return new Price(priceId, brandId, dateInRange, priority, money);
    }

    public ProductResponseDTO toRessource(Product product)
    {
        String id = product.getId().getValue().toString();
        String name = product.getName().getValue();
        List<PriceResponseDTO> priceResponsDTOS = product.getPrices().stream().map(it->toRessource(it)).toList();

        return new ProductResponseDTO(id, name, priceResponsDTOS);
    }

    public PriceResponseDTO toRessource(Price price)
    {
        String id = price.getId().getValue().toString();
        String brandId = price.getBrandId().getValue().toString();
        String startDate = dateFormatter.parseDate(price.getDateInRange().getStartDate());
        String endDate = dateFormatter.parseDate(price.getDateInRange().getEndDate());
        Integer priority = price.getPriority().getValue();
        String currencyType = price.getMoney().getCurrencyType().toString();
        Float amount = price.getMoney().getAmount();

        return new PriceResponseDTO(id, brandId, startDate, endDate, priority, currencyType, amount);
    }

    public Price toDomain(CreatePriceDTO createPriceDTO)
    {
        PriceId id = new PriceId(UUID.fromString(createPriceDTO.getId()));
        BrandId brandId = new BrandId(UUID.fromString(createPriceDTO.getBrandId()));
        Date startDate = dateFormatter.parseString(createPriceDTO.getStartDate());
        Date endDate = dateFormatter.parseString(createPriceDTO.getEndDate());
        DateInRange dateInRange = new DateInRange(startDate, endDate);
        Priority priority = new Priority(createPriceDTO.getPriority());
        CurrencyType currencyType = CurrencyType.valueOf(createPriceDTO.getCurrencyType());
        Float amount = createPriceDTO.getAmount();
        Money money = new Money(currencyType, amount);

        return new Price(id, brandId, dateInRange, priority, money);
    }

    public PriceByProductIdBrandIdDateCommand toCommand(PriceByProductIdBrandIdDateDTO request)
    {
        ProductId productId = new ProductId(UUID.fromString(request.getProductId()));
        BrandId brandId = new BrandId(UUID.fromString(request.getBrandId()));
        Date date = dateFormatter.parseString(request.getDate());

        return new PriceByProductIdBrandIdDateCommand(productId, brandId, date);
    }

    public PricebyProductIdPriceIdCommand toCommand(String productIdString, String priceIdString)
    {
        ProductId productId = new ProductId(UUID.fromString(productIdString));
        PriceId priceId = new PriceId(UUID.fromString(priceIdString));

        return new PricebyProductIdPriceIdCommand(productId, priceId);
    }
}
