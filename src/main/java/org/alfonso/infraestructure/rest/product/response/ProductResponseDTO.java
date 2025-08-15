package org.alfonso.infraestructure.rest.product.response;

import java.util.List;

public class ProductResponseDTO
{
    private final String id;
    private final String name;
    private final List<PriceResponseDTO> priceResponsDTOS;

    public ProductResponseDTO(String id, String name, List<PriceResponseDTO> priceResponsDTOS)
    {
        this.id = id;
        this.name = name;
        this.priceResponsDTOS = priceResponsDTOS;
    }

    public String getId()
    {   return id;}

    public String getName()
    {   return name;}

    public List<PriceResponseDTO> getPriceResponses()
    {   return priceResponsDTOS;}
}
