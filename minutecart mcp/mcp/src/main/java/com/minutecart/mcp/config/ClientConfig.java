package com.minutecart.mcp.config;

import com.minutecart.mcp.service.OrderMcpServices;
import com.minutecart.mcp.service.ProductMcpServices;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.List;

@Configuration
public class ClientConfig {

    @Bean
    public RestClient userServiceClient( ) {
        return  RestClient.builder()
                .baseUrl("http://localhost:8080/api/")
                .build();
    }

}
