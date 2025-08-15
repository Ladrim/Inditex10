package org.alfonso.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseBeanConfig
{
    @Value("${spring.datasource.url}")
    public String url;

    @Value("${spring.datasource.driverClassName}")
    public String driverClassName;

    @Value("${spring.datasource.username}")
    public String username;

    @Value("${spring.datasource.password}")
    public String password;

    public String getUrl()
    {   return url;}

    public String getDriverClassName()
    {   return driverClassName;}

    public String getUsername()
    {   return username;}

    public String getPassword()
    {   return password;}
}
