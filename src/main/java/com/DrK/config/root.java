package com.DrK.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.DrK.Service", "com.DrK.Config"})
public class Root {

}
