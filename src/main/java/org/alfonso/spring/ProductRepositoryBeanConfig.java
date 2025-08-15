package org.alfonso.spring;

import org.alfonso.application.ProductRepository;
import org.alfonso.infraestructure.repositories.inh2.ProductEntityInH2Adapter;
import org.alfonso.infraestructure.repositories.inh2.ProductInH2RepositoryV2;
import org.alfonso.infraestructure.repositories.inmemory.ProductInMemoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ProductRepositoryBeanConfig
{
    @Bean @Qualifier("inH2")
    public ProductRepository productInH2Repository(ProductEntityInH2Adapter adapter, DataSource dataSource)
    {
        return new ProductInH2RepositoryV2(adapter, dataSource);
    }

    @Bean @Qualifier("inMemory")
    public ProductRepository productInMemoryRepository()
    {
        return new ProductInMemoryRepository();
    }
}
