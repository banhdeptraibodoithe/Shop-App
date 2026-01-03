package org.restaurant.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurant.dtos.users.LoginDTO;
import org.restaurant.restaurant.dtos.users.RegisterDTO;
import org.restaurant.restaurant.exceptions.DataNotFoundException;
import org.restaurant.restaurant.models.Role;
import org.restaurant.restaurant.models.User;
import org.restaurant.restaurant.repositories.RoleRepository;
import org.restaurant.restaurant.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public User register(RegisterDTO registerDTO) throws DataNotFoundException {
        if (userRepository.existsByPhoneNumber(registerDTO.getPhoneNumber()))
            throw new DataIntegrityViolationException("This phone number already exists");
        User user = User.builder()
                .fullName(registerDTO.getName())
                .phoneNumber(registerDTO.getPhoneNumber())
                .password(registerDTO.getPassword())
                .address(registerDTO.getAddress())
                .dateOfBirth(registerDTO.getDateOfBirth())
                .facebookAccountId(registerDTO.getFacebookId())
                .googleAccountId(registerDTO.getGoogleId())
                .build();
        Role role = roleRepository.findById(registerDTO.getRoleId()).orElseThrow(()-> new DataNotFoundException("Role not found!"));
        user.setRole(role);
        if (user.getFacebookAccountId() == 0 && user.getGoogleAccountId() == 0) {
            String password = user.getPassword();
        }
        return userRepository.save(user);
    }

    @Override
    public String login(LoginDTO loginDTO) {
        return "";
    }
}
