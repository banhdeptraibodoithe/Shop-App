package org.restaurant.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.restaurant.restaurant.dtos.OrderDTO;
import org.restaurant.restaurant.exceptions.DataNotFoundException;
import org.restaurant.restaurant.models.Order;
import org.restaurant.restaurant.models.OrderStatus;
import org.restaurant.restaurant.models.User;
import org.restaurant.restaurant.repositories.OrderRepository;
import org.restaurant.restaurant.repositories.UserRepository;
import org.restaurant.restaurant.responses.OrderResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public OrderResponse createOrder(OrderDTO orderDTO) throws DataNotFoundException {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Can't find user with id = " + orderDTO.getUserId()));
        modelMapper.typeMap(OrderDTO.class, Order.class)
                .addMappings(mapper -> mapper.skip(Order::setId));
        Order order = new Order();
        modelMapper.map(orderDTO, order);
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setOrderStatus(OrderStatus.PENDING);
        LocalDate shippingDate = orderDTO.getShippingDate() == null ? LocalDate.now() : orderDTO.getShippingDate();
        if (shippingDate.isBefore(LocalDate.now()))
            throw new DataNotFoundException("Date must be at least today!");
        order.setActive(true);
        order.setShippingDate(shippingDate);
        orderRepository.save(order);
        modelMapper.typeMap(Order.class, OrderResponse.class);
        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public OrderResponse getOrder(Long id) throws DataNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can't find user with id = " + id));
        modelMapper.typeMap(Order.class, OrderResponse.class);
        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderDTO orderDTO) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }

    @Override
    public List<OrderResponse> findByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUser_Id(userId);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            modelMapper.typeMap(Order.class, OrderResponse.class);
            OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }
}
