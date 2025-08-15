package org.alfonso.infraestructure.repositories.inh2;

public class ProductInH2Entity
{
    private final String id;
    private final String name;

    public ProductInH2Entity(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getId()
    {   return id;}

    public String getName()
    {   return name;}
}
