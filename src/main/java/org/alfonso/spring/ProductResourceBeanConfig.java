package org.alfonso.spring;

import org.alfonso.domain.PriceUseCase;
import org.alfonso.domain.ProductUseCaseV2;
import org.alfonso.infraestructure.rest.product.ProductResource;
import org.alfonso.infraestructure.rest.product.ProductResourceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductResourceBeanConfig
{
    @Bean
    public ProductResource productResource(ProductUseCaseV2 productUseCaseV2, PriceUseCase priceUseCase, ProductResourceAdapter adapter)
    {
        return new ProductResource(productUseCaseV2, priceUseCase, adapter);
    }
}
