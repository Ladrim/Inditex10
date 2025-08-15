/*
package org.alfonso.infraestructure.repositories.inh2;

import org.alfonso.application.ProductRepository;
import org.alfonso.domain.price.Price;
import org.alfonso.domain.price.PriceId;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductInH2Repository implements ProductRepository
{
    private final RepositoryInH2Adapter adapter;
    private final DataSource dataSource;
    private final NamedParameterJdbcTemplate jdbc;

    public ProductInH2Repository(RepositoryInH2Adapter adapter, DataSource dataSource)
    {
        this.adapter = adapter;
        this.dataSource = dataSource;
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    //SAVE--------------------------------------------------------------------------------------------------------------

    @Override
    public void save(Product product)
    {
        Optional<Product> optionalProduct = findProduct(product.getId());

        if(optionalProduct.isEmpty())
        {
            insertProduct(product);
        }
        else
        {
            delete(product.getId());
            insertProduct(product);
        }
    }

    //DELETE------------------------------------------------------------------------------------------------------------

    @Override @Transactional
    public void delete(ProductId productId)
    {
        MapSqlParameterSource productParams = new MapSqlParameterSource();
        productParams.addValue("productId", productId.getValue().toString());

        jdbc.update("DELETE FROM PRICE WHERE PRODUCT_ID = :productId", productParams);
        jdbc.update("DELETE FROM PRODUCT WHERE PRODUCT_ID = :productId", productParams);
    }

    //FIND PRODUCT------------------------------------------------------------------------------------------------------

    @Override
    public Optional<Product> findProduct(ProductId productId)
    {
        MapSqlParameterSource productParams = new MapSqlParameterSource();
        productParams.addValue("productId", productId.getValue().toString());

        Optional<ProductInH2Entity> optionalProductInH2Entity = jdbc.query("SELECT * FROM PRODUCT WHERE PRODUCT_ID = :productId", productParams , this::toProductInH2Entity).stream().findFirst();
        List<PriceInH2Entity> priceInH2Entities = jdbc.query("SELECT * FROM PRICE WHERE PRODUCT_ID = :productId", productParams, this::toPriceInH2Entity);

        return optionalProductInH2Entity.map(it->adapter.toDomain(it, priceInH2Entities));
    }

    //FIND ALL PRODUCTS-------------------------------------------------------------------------------------------------

    @Override
    public List<Product> findAllProducts()
    {
        List<ProductInH2Entity> productInH2Entities = jdbc.query("SELECT * FROM PRODUCT", this::toProductInH2Entity);
        List<PriceInH2Entity> priceInH2Entities =jdbc.query("SELECT * FROM PRICE ", this::toPriceInH2Entity);
        Map<String, List<PriceInH2Entity>> priceInH2EntityMap = priceInH2Entities.stream().collect(Collectors.groupingBy(it -> it.getProductId()));

        return productInH2Entities.stream().map(it-> adapter.toDomain(it, priceInH2EntityMap.get(it.getId()))).toList();
    }

    //FIND PRICE BY ID--------------------------------------------------------------------------------------------------

    @Override
    public Optional<Price> findPrice(PriceId priceId)
    {
        MapSqlParameterSource priceParams = new MapSqlParameterSource();
        priceParams.addValue("id", priceId.getValue().toString());

        Optional<PriceInH2Entity> optionalPriceInH2Entity = jdbc.query("SELECT * FROM PRICE WHERE PRICE_ID = :id", priceParams, this::toPriceInH2Entity).stream().findFirst();

        return optionalPriceInH2Entity.map(adapter::toDomain);
    }

    private void insertProduct(Product product)
    {
        ProductInH2Entity productInH2Entity = adapter.toEntity(product);
        MapSqlParameterSource productParams = new MapSqlParameterSource();
        productParams.addValue("id", productInH2Entity.getId());
        productParams.addValue("name", productInH2Entity.getName());

        jdbc.update("INSERT INTO PRODUCT(PRODUCT_ID, PRODUCT_NAME) VALUES(:id, :name)" , productParams);

        List<PriceInH2Entity> priceInH2Entities = product.getPrices().stream().map(it-> adapter.toEntity(product,it)).toList();

        priceInH2Entities.forEach(this::insertPrice);
    }

    private void insertPrice(PriceInH2Entity priceInH2Entity)
    {
        MapSqlParameterSource priceParams = new MapSqlParameterSource();
        priceParams.addValue("priceId", priceInH2Entity.getId());
        priceParams.addValue("brandId", priceInH2Entity.getBrandId());
        priceParams.addValue("startDate", priceInH2Entity.getStartDate());
        priceParams.addValue("endDate", priceInH2Entity.getEndDate());
        priceParams.addValue("priority", priceInH2Entity.getPriority());
        priceParams.addValue("currency", priceInH2Entity.getCurrencyType());
        priceParams.addValue("amount", priceInH2Entity.getAmount());
        priceParams.addValue("productId", priceInH2Entity.getProductId());

        jdbc.update("INSERT INTO PRICE(PRICE_ID, BRAND_ID, START_DATE, END_DATE, PRIORITY, CURRENCY_TYPE, AMOUNT, PRODUCT_ID) " +
                "VALUES(:priceId, :brandId, :startDate, :endDate, :priority, :currency, :amount, :productId)", priceParams);
    }

    //ROWMAPPERS--------------------------------------------------------------------------------------------------------

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
*/
