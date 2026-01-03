package org.restaurant.restaurant.controller;

import jakarta.validation.Valid;
import org.restaurant.restaurant.dtos.OrderDTO;
import org.restaurant.restaurant.dtos.OrderDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order-details")
public class OrderDetailController {
    @PostMapping("")
    public ResponseEntity<?> createNewOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()){
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Create order-details successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetailById(@Valid @PathVariable("id") Long id){
        return ResponseEntity.ok("Get order-detail with Id = " + id);
    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetailsByOrderId(@Valid @PathVariable("orderId") Long orderId){
        return ResponseEntity.ok("Get all order-details with order Id = " + orderId);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> editOrderDetail(@Valid @PathVariable("id") Long id,@Valid @RequestBody OrderDetailDTO orderDetailDTO, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()){
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Update order-detail with id = " + id + " successfully"
                                        + "\nNew information is: " + orderDetailDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@Valid @PathVariable("id") Long id){
        return ResponseEntity.ok("Delete order-detail with id = " + id + " successfully");
    }
}
