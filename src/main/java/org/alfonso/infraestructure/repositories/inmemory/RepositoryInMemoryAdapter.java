package org.alfonso.infraestructure.repositories.inmemory;

import org.alfonso.application.DateFormatter;

public class RepositoryInMemoryAdapter
{
    private final DateFormatter dateFormatter;

    public RepositoryInMemoryAdapter(DateFormatter dateFormatter)
    {
        this.dateFormatter = dateFormatter;
    }
}
