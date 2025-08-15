package org.alfonso.infraestructure.rest.product.request;

import java.util.List;

public class CreateProductDTO
{
    private final String id;
    private final String name;
    private final List<PriceDTO> priceDTOs;

    public CreateProductDTO(String id, String name, List<PriceDTO> priceDTOs)
    {
        this.id = id;
        this.name = name;
        this.priceDTOs = priceDTOs;
    }

    public String getId()
    {   return id;}

    public String getName()
    {   return name;}

    public List<PriceDTO> getPriceDTOs()
    {   return priceDTOs;}
}
