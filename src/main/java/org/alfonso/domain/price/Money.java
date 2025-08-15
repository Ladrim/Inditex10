package org.alfonso.domain.price;

public class Money
{
    private final CurrencyType currencyType;
    private final Float amount;

    public Money(CurrencyType currencyType, Float amount)
    {
        this.currencyType = currencyType;
        this.amount = amount;
    }

    public CurrencyType getCurrencyType()
    {   return currencyType;}

    public Float getAmount()
    {   return amount;}
}
