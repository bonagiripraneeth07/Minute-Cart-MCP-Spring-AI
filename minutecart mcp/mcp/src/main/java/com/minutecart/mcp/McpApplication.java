package com.minutecart.mcp;

import com.minutecart.mcp.service.OrderMcpServices;
import com.minutecart.mcp.service.ProductMcpServices;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class McpApplication {

	public static void main(String[] args) {
		SpringApplication.run(McpApplication.class, args);
	}
	@Bean
	public List<ToolCallback> tools(ProductMcpServices productMcpServices, OrderMcpServices orderMcpServices){
		return List.of(ToolCallbacks.from(productMcpServices,orderMcpServices ));
	}
}
