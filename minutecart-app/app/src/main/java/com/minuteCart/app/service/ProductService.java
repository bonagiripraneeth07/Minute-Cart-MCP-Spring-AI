package com.minuteCart.app.service;


import com.minuteCart.app.DTO.ProductDTO;
import com.minuteCart.app.model.Order;
import com.minuteCart.app.model.Product;
import com.minuteCart.app.model.User;
import com.minuteCart.app.repo.OrderItemRepository;
import com.minuteCart.app.repo.OrderRepository;
import com.minuteCart.app.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    final private OrderRepository orderRepository;
    final private OrderItemRepository orderItemRepository;
    final private ProductRepository productRepository;
    final  public User user ;

    public ProductService(OrderItemRepository orderItemRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        user = new User(101,"Akhil Pittampally","Sri Sai Raj Residence Old Malakpet, Hyderabad","991120123221");
    }

    public User getUser(){
        return user;
    }

    public List<Product>getProducts(){
        return productRepository.findAll();
    }
    public Long getProductId(String productName){
        Product product = productRepository.findByProductNameIgnoreCase(productName);
        return product.getProductID();
    }
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
    public List<ProductDTO> getProductsByProductName(String productName){
        List<Product> products = new ArrayList<>();
         products = productRepository.findByProductNameContainingIgnoreCase(productName);

        return convertProductToDTO(products);
    }
    public ProductDTO getProductByName(String productName){
        Product foundProduct = new Product();
               foundProduct= productRepository.findByProductNameIgnoreCase(productName);
        if (foundProduct == null){
            return new ProductDTO();
        }
        ProductDTO pdto = new ProductDTO();
        pdto.setBrand(foundProduct.getBrand());
        pdto.setCategory(foundProduct.getCategory());
        pdto.setDescription(foundProduct.getDescription());
        pdto.setBrand(foundProduct.getBrand());
        pdto.setPrice(foundProduct.getPrice());
        pdto.setProductName(foundProduct.getProductName());

        pdto.setUnits(foundProduct.getUnits());
        return  pdto;
    }
    public List<ProductDTO> getProductsByCategory(String category){
        List<Product> products = new ArrayList<>();
               products = productRepository.findByCategoryIgnoreCase(category);
        return convertProductToDTO(products);
    }
    public List<ProductDTO> getProductsByBrand(String brandName){
        List<Product> products = new ArrayList<>();
         products = productRepository.findByBrandIgnoreCase(brandName);
        return convertProductToDTO(products);
    }
    public Product getProduct(Long id){
        System.out.println(id +" id of the product");
        Optional<Product> foundProduct = productRepository.findById(id);
                    return foundProduct.orElseThrow(()-> new RuntimeException("Product not found"));
    }
    public List<ProductDTO> convertProductToDTO(List<Product> products){
        if (products.isEmpty()){
            return new ArrayList<>();
        }
        List<ProductDTO> productDtos = new ArrayList<>();
        for(Product product : products ){
            ProductDTO pdto = new ProductDTO();
            pdto.setBrand(product.getBrand());
            pdto.setCategory(product.getCategory());
            pdto.setDescription(product.getDescription());
            pdto.setBrand(product.getBrand());
            pdto.setPrice(product.getPrice());
            pdto.setProductName(product.getProductName());

            pdto.setUnits(product.getUnits());
            productDtos.add(pdto);
        }
        return productDtos;
    }

}
