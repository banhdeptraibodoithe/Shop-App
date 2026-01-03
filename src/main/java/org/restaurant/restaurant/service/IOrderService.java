package org.restaurant.restaurant.service;

import org.restaurant.restaurant.dtos.OrderDTO;
import org.restaurant.restaurant.exceptions.DataNotFoundException;
import org.restaurant.restaurant.responses.OrderResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder(OrderDTO orderDTO) throws DataNotFoundException;
    OrderResponse getOrder(Long id) throws DataNotFoundException;
    OrderResponse updateOrder(Long id, OrderDTO orderDTO);
    void deleteOrder(Long id);
    List<OrderResponse> findByUserId(Long userId);
}
