package org.alfonso.infraestructure.rest.product;

import org.alfonso.application.PriceByProductIdBrandIdDateCommand;
import org.alfonso.application.PricebyProductIdPriceIdCommand;
import org.alfonso.domain.PriceUseCase;
import org.alfonso.domain.ProductUseCaseV2;
import org.alfonso.domain.price.Price;
import org.alfonso.domain.price.PriceId;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.domain.product.ProductName;
import org.alfonso.infraestructure.rest.product.request.*;
import org.alfonso.infraestructure.rest.product.response.PriceResponseDTO;
import org.alfonso.infraestructure.rest.product.response.ProductResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductResource
{
    private final ProductUseCaseV2 productUseCaseV2;
    private final PriceUseCase priceUseCase;
    private final ProductResourceAdapter adapter;

    public ProductResource(ProductUseCaseV2 productUseCaseV2, PriceUseCase priceUseCase, ProductResourceAdapter adapter)
    {
        this.productUseCaseV2 = productUseCaseV2;
        this.priceUseCase = priceUseCase;
        this.adapter = adapter;
    }

    //CREATE PRODUCT----------------------------------------------------------------------------------------------------

    @PostMapping("product")
    public ProductResponseDTO saveProduct(@RequestBody CreateProductDTO request)
    {
        Product product = adapter.toDomain(request);
        productUseCaseV2.saveProduct(product);
        return adapter.toRessource(product);
    }

    //DELETE PRODUCT BY PRODUCT_ID--------------------------------------------------------------------------------------

    @DeleteMapping("product/{id}")
    public void deleteProduct(@PathVariable String id)
    {
        ProductId productId = new ProductId(UUID.fromString(id));
        productUseCaseV2.deleteProduct(productId);
    }


    //FIND PRODUCT BY PRODUCT_ID----------------------------------------------------------------------------------------

    @GetMapping("product/{id}")
    public ProductResponseDTO findProduct(@PathVariable String id)
    {
        ProductId productId = new ProductId(UUID.fromString(id));

        return productUseCaseV2.findProduct(productId)
            .map(adapter::toRessource)
            .orElse(null);
    }


    //FIND ALL PRODUCTS-------------------------------------------------------------------------------------------------

    @GetMapping("products")
    public List<ProductResponseDTO> findAllProducts()
    {
        return productUseCaseV2.findAllProducts().stream().map(adapter::toRessource).toList();
    }

    //FIND PRODUCT BY PRICE_ID
    @GetMapping("product/findbypriceid/{id}")
    public ProductResponseDTO findProductByPriceId(@PathVariable String id)
    {
        PriceId priceId = new PriceId(UUID.fromString(id));
        return productUseCaseV2.findProductByPriceId(priceId)
            .map(adapter::toRessource)
            .orElse(null);
    }

    //FIND PRICE BY PRODUCT_ID, PRICE_ID--------------------------------------------------------------------------------

    @GetMapping("price/{productId}/{priceId}")
    public PriceResponseDTO findPrice(@PathVariable String productId, @PathVariable String priceId)
    {
        PricebyProductIdPriceIdCommand command = adapter.toCommand(productId, priceId);

        return productUseCaseV2.findPriceByProductIdPriceId(command)
            .map(adapter::toRessource)
            .orElse(null);
    }


    //FIND PRICE BY PRODUCT_ID, BRAND_ID, DATE--------------------------------------------------------------------------

    @PostMapping("price")
    public PriceResponseDTO findPrice(@RequestBody PriceByProductIdBrandIdDateDTO request)
    {
        PriceByProductIdBrandIdDateCommand command = adapter.toCommand(request);

        return productUseCaseV2.findPriceByProductIdBrandIdDate(command)
            .map(adapter::toRessource)
            .orElse(null);
    }

    /*
    //CHANGE PRODUCT NAME-----------------------------------------------------------------------------------------------

    @PutMapping("product/changename")
    public void changeName(@RequestBody ChangeNameDTO request)
    {
        ProductId id = new ProductId(UUID.fromString(request.getProductId()));
        ProductName name = new ProductName(request.getProductName());


        productUseCaseV2.changeName(id, name);
    }
     */

    //ADD PRICE TO PRODUCT----------------------------------------------------------------------------------------------

    @PostMapping("product/addprice")
    public void addPrice(@RequestBody CreatePriceDTO request)
    {
        Price price = adapter.toDomain(request);
        ProductId id = new ProductId(UUID.fromString(request.getProductId()));

        productUseCaseV2.addPrice(id, price);
    }
}
