package com.userservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.userservice.entity.User;
import com.userservice.repository.UserRepository;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate; // Used for making HTTP requests

    // URL of the Order Microservice
    private final String orderServiceUrl = "http://localhost:8082/";

    public void cretaeUser(User user) {
        userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }
//  json node

    public JsonNode getAllOrdersForUser(Long userId) {
        String url = orderServiceUrl + "order/orders?userId=" + userId;
        // Execute the HTTP GET request and retrieve the response
        ResponseEntity<ArrayNode> response = restTemplate.getForEntity(url, ArrayNode.class);
        // Retrieve the list of orders from the response entity
        ArrayNode orders = response.getBody();
        // Convert array of Order objects to a List<Order>
        if (orders != null) {
            return orders;
        } else {
            return objectMapper.createObjectNode(); // Return an empty list if no orders were found
        }
    }
}
