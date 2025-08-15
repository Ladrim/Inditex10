package org.alfonso.infraestructure.repositories.inh2;

import org.alfonso.application.ProductRepository;
import org.alfonso.domain.price.PriceId;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductInH2RepositoryV2 implements ProductRepository
{
    private final ProductEntityInH2Adapter adapter;
    private final NamedParameterJdbcTemplate jdbc;
    private final DataSource dataSource;

    public ProductInH2RepositoryV2(ProductEntityInH2Adapter adapter, DataSource dataSource)
    {
        this.adapter = adapter;
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    //SAVE PRODUCT------------------------------------------------------------------------------------------------------
    @Override
    public void save(Product product)
    {
        Optional<Product>productToFind = findByProductId(product.getId());

        if(productToFind.isPresent())
        {
            delete(product.getId());
        }

        insertProduct(product);

    }
    //DELETE PRODUCT----------------------------------------------------------------------------------------------------
    @Override @Transactional
    public void delete(ProductId productId)
    {
        MapSqlParameterSource productParams = new MapSqlParameterSource();
        productParams.addValue("PRODUCT_ID", productId.getValue().toString());

        jdbc.update("DELETE FROM PRICE WHERE PRODUCT_ID = :PRODUCT_ID", productParams);

        jdbc.update("DELETE FROM PRODUCT WHERE PRODUCT_ID = :PRODUCT_ID", productParams);
    }

    //FIND PRODUCT BY PRODUCT_ID----------------------------------------------------------------------------------------
    @Override
    public Optional<Product> findByProductId(ProductId productId)
    {
        MapSqlParameterSource productParams = new MapSqlParameterSource();
        productParams.addValue("productId", productId.getValue().toString());

        Optional<ProductInH2Entity> optionalProductInH2Entity = jdbc.query("SELECT * FROM PRODUCT WHERE PRODUCT_ID = :productId", productParams, this::toProductInH2Entity).stream().findFirst();
        List<PriceInH2Entity> priceInH2Entities = jdbc.query("SELECT * FROM PRICE WHERE PRODUCT_ID = :productId", productParams, this::toPriceInH2Entity);

        return optionalProductInH2Entity.map(it->adapter.toDomain(it,priceInH2Entities));
    }


    //FIND ALL PRODUCTS-------------------------------------------------------------------------------------------------
    @Override
    public List<Product> findAll()
    {
        List<ProductInH2Entity> productInH2Entities = jdbc.query("SELECT * FROM PRODUCT", this::toProductInH2Entity);
        List<PriceInH2Entity> priceInH2Entities = jdbc.query("SELECT * FROM PRICE", this::toPriceInH2Entity);
        Map<String, List<PriceInH2Entity>> priceInH2EntitiesMap = priceInH2Entities.stream().collect(Collectors.groupingBy(it->it.getProductId()));

        return productInH2Entities.stream().map(it-> adapter.toDomain(it, priceInH2EntitiesMap.get(it.getId()) )).toList();
    }

    //FIND PRODUCT BY PRICE_ID-------------------------------------------------------------------------------------------
    @Override
    public Optional<Product> findProductByPriceId(PriceId priceId)
    {
        MapSqlParameterSource priceParams = new MapSqlParameterSource();
        priceParams.addValue("PRICE_ID", priceId.getValue().toString());


        Optional<PriceInH2Entity> priceInH2Entity = jdbc.query("SELECT * FROM PRICE WHERE PRICE_ID = :PRICE_ID", priceParams, this::toPriceInH2Entity).stream().findFirst();
        return priceInH2Entity.flatMap(it-> findByProductId(adapter.toDomain(it.getProductId())));
    }

    //PRIVATE METHODS---------------------------------------------------------------------------------------------------
    private void insertProduct(Product product)
    {
        ProductInH2Entity productInH2Entity = adapter.toEntity(product);

        MapSqlParameterSource productParams = new MapSqlParameterSource();
        productParams.addValue("PRODUCT_ID", product.getId().getValue().toString());
        productParams.addValue("PRODUCT_NAME", product.getName().getValue());

        jdbc.update("INSERT INTO PRODUCT(PRODUCT_ID, PRODUCT_NAME) VALUES(:PRODUCT_ID, :PRODUCT_NAME)", productParams);

        List<PriceInH2Entity> priceInH2Entities = product.getPrices().stream().map(it->adapter.toEntity(product, it)).toList();

        priceInH2Entities.forEach(this::insertPrice);
    }

    private void insertPrice(PriceInH2Entity priceInH2Entity)
    {
        MapSqlParameterSource priceParams = new MapSqlParameterSource();
        priceParams.addValue("PRICE_ID", priceInH2Entity.getId());
        priceParams.addValue("BRAND_ID", priceInH2Entity.getBrandId());
        priceParams.addValue("START_DATE", priceInH2Entity.getStartDate());
        priceParams.addValue("END_DATE", priceInH2Entity.getEndDate());
        priceParams.addValue("PRIORITY", priceInH2Entity.getPriority());
        priceParams.addValue("CURRENCY_TYPE", priceInH2Entity.getCurrencyType());
        priceParams.addValue("AMOUNT", priceInH2Entity.getAmount());
        priceParams.addValue("PRODUCT_ID", priceInH2Entity.getProductId());

        jdbc.update("INSERT INTO PRICE(PRICE_ID, BRAND_ID, START_DATE, END_DATE, PRIORITY, CURRENCY_TYPE, AMOUNT, PRODUCT_ID) " +
            "VALUES(:PRICE_ID, :BRAND_ID, :START_DATE, :END_DATE, :PRIORITY, :CURRENCY_TYPE, :AMOUNT, :PRODUCT_ID)", priceParams);
    }

    private List<PriceInH2Entity> priceInH2EntitiesToFind (ProductInH2Entity productInH2Entity, List<PriceInH2Entity> priceInH2Entities)
    {
        return priceInH2Entities.stream().filter(it->it.getProductId().equals(productInH2Entity.getId())).toList();
    }




    //ROW MAPPERS-------------------------------------------------------------------------------------------------------

    public ProductInH2Entity toProductInH2Entity(ResultSet resultSet, int rownum) throws SQLException
    {
        return new ProductInH2Entity
        (
            resultSet.getString("PRODUCT_ID"),
            resultSet.getString("PRODUCT_NAME")
        );
    }

    public PriceInH2Entity toPriceInH2Entity(ResultSet resultSet, int rownum) throws SQLException
    {
        return new PriceInH2Entity
        (
            resultSet.getString("PRICE_ID"),
            resultSet.getString("BRAND_ID"),
            resultSet.getString("START_DATE"),
            resultSet.getString("END_DATE"),
            resultSet.getInt("PRIORITY"),
            resultSet.getString("CURRENCY_TYPE"),
            resultSet.getFloat("AMOUNT"),
            resultSet.getString("PRODUCT_ID")
        );
    }
}
