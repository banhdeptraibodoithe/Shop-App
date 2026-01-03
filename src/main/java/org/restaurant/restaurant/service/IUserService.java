package org.restaurant.restaurant.service;

import org.restaurant.restaurant.dtos.users.LoginDTO;
import org.restaurant.restaurant.dtos.users.RegisterDTO;
import org.restaurant.restaurant.exceptions.DataNotFoundException;
import org.restaurant.restaurant.models.User;

public interface IUserService {
    User register(RegisterDTO registerDTO) throws DataNotFoundException;
    String login(LoginDTO loginDTO);
}
