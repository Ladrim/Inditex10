package org.alfonso.infraestructure.rest.product.request;

public class PriceByProductIdBrandIdDateDTO
{
    private final String productId;
    private final String brandId;
    private final String date;

    public PriceByProductIdBrandIdDateDTO(String productId, String brandId, String date)
    {
        this.productId = productId;
        this.brandId = brandId;
        this.date = date;
    }

    public String getProductId()
    {   return productId;}

    public String getBrandId()
    {   return brandId;}

    public String getDate()
    {   return date;}
}
