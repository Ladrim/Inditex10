package org.alfonso.domain.price;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Price
{
    private final PriceId id;
    private final BrandId brandId;
    private final DateInRange dateInRange;
    private final Priority priority;
    private final Money money;

    public Price(PriceId priceId, BrandId brandId, DateInRange dateInRange, Priority priority, Money money)
    {
        this.id = priceId;
        this.brandId = brandId;
        this.dateInRange = dateInRange;
        this.priority = priority;
        this.money = money;
    }

    public PriceId getId()
    {   return id;}

    public BrandId getBrandId()
    {   return brandId;}

    public DateInRange getDateInRange()
    {   return dateInRange;}

    public Priority getPriority()
    {   return priority;}

    public Money getMoney()
    {   return money;}


    public Price changeBrandId(BrandId brandId)
    {
        return new Price(this.id, brandId , dateInRange, priority, money);
    }

    public Price changeStartDate(DateInRange dateInRange)
    {
        return new Price(id, brandId, dateInRange, priority, money);
    }

    public Price changeEndDate(DateInRange dateInRange)
    {
        return new Price(id, brandId, dateInRange , priority, money);
    }

    public Price changePriority(Priority priority)
    {
        return new Price(id, brandId, dateInRange, priority, money);
    }

    public Price changeCurrencyType(Money money)
    {
        return new Price(id, brandId, dateInRange, priority, money);
    }

    public Price changeAmount(Money money)
    {
        return new Price(id, brandId, dateInRange, priority, money);
    }
}
