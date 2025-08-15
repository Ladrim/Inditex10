package org.alfonso.infraestructure.rest.product.response;

public class PriceResponseDTO
{
    private final String id;
    private final String brandId;
    private final String startDate;
    private final String endDate;
    private final Integer priority;
    private final String currencyType;
    private final Float amount;

    public PriceResponseDTO(String id, String brandId, String startDate, String endDate, Integer priority, String currencyType, Float amount)
    {
        this.id = id;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.currencyType = currencyType;
        this.amount = amount;
    }

    public String getId()
    {   return id;}

    public String getBrandId()
    {   return brandId;}

    public String getStartDate()
    {   return startDate;}

    public String getEndDate()
    {   return endDate;}

    public Integer getPriority()
    {   return priority;}

    public String getCurrencyType()
    {   return currencyType;}

    public Float getAmount()
    {   return amount;}
}
