package org.alfonso.spring;

import org.alfonso.application.DateFormatter;
import org.alfonso.application.ProductRepository;
import org.alfonso.domain.PriceUseCase;
import org.alfonso.domain.ProductUseCaseV2;
import org.alfonso.infraestructure.repositories.inh2.ProductInH2RepositoryV2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig
{
    @Bean
    public ProductUseCaseV2 productUseCaseV2(@Qualifier("inH2") ProductRepository productRepository)
    {
        return new ProductUseCaseV2(productRepository);
    }

    @Bean
    public PriceUseCase priceUseCase()

    {
        return new PriceUseCase();
    }
}
