package org.alfonso.infraestructure.rest.product.request;

public class PriceByProductIdPriceIdDTO
{
    private final String productId;
    private final String priceId;

    public PriceByProductIdPriceIdDTO(String productId, String priceId)
    {
        this.productId = productId;
        this.priceId = priceId;
    }

    public String getProductId()
    {   return productId;}

    public String getPriceId()
    {   return priceId;}
}
