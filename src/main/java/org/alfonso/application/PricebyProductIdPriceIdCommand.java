package org.alfonso.application;

import org.alfonso.domain.price.PriceId;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;

public class PricebyProductIdPriceIdCommand
{
    private final ProductId productId;
    private final PriceId priceId;


    public PricebyProductIdPriceIdCommand(ProductId productId, PriceId priceId) {
        this.productId = productId;
        this.priceId = priceId;
    }

    public ProductId getProductId()
    {   return productId;}

    public PriceId getPriceId()
    {   return priceId;}
}
