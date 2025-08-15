package org.alfonso.infraestructure.rest.product.request;

public class ChangeNameDTO
{
    private final String productId;
    private final String productName;

    public ChangeNameDTO(String productId, String productName)
    {
        this.productId = productId;
        this.productName = productName;
    }

    public String getProductId()
    {   return productId;}

    public String getProductName()
    {   return productName;}
}
