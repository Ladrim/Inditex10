package org.alfonso.spring;

import org.alfonso.application.DateFormatter;
import org.alfonso.infraestructure.repositories.inh2.ProductEntityInH2Adapter;
import org.alfonso.infraestructure.rest.product.ProductResourceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductAdapterBeanConfig
{
    @Bean
    public DateFormatter dateFormatter()
    {
        return new DateFormatter();
    }

    @Bean
    public ProductResourceAdapter productResourceAdapter(DateFormatter dateFormatter)
    {
        return new ProductResourceAdapter(dateFormatter);
    }

    @Bean
    public ProductEntityInH2Adapter productEntityInH2Adapter(DateFormatter dateFormatter)
    {
        return new ProductEntityInH2Adapter(dateFormatter);
    }
}
