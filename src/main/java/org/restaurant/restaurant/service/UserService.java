package org.restaurant.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurant.components.JwtTokenUtil;
import org.restaurant.restaurant.dtos.users.LoginDTO;
import org.restaurant.restaurant.dtos.users.RegisterDTO;
import org.restaurant.restaurant.exceptions.DataNotFoundException;
import org.restaurant.restaurant.models.Role;
import org.restaurant.restaurant.models.User;
import org.restaurant.restaurant.repositories.RoleRepository;
import org.restaurant.restaurant.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
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
        user.setActive(true);
        if (user.getFacebookAccountId() == 0 && user.getGoogleAccountId() == 0) {
            String password = user.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
        }
        return userRepository.save(user);
    }

    @Override
    public String login(LoginDTO loginDTO) throws DataNotFoundException {
        Optional<User> user = userRepository.findByPhoneNumber(loginDTO.getPhoneNumber());
        if (user.isEmpty()) throw new DataNotFoundException("Invalid Phone number / Password");
        User existingUser = user.get();
        if (existingUser.getFacebookAccountId() == 0 && existingUser.getGoogleAccountId() == 0) {
            if (!passwordEncoder.matches(loginDTO.getPassword(), existingUser.getPassword())) {
                throw new BadCredentialsException("Wrong phone number or password");
            }
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getPhoneNumber(), loginDTO.getPassword(), existingUser.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(existingUser);
    }
}
