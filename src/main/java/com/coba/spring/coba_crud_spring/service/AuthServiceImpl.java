package com.coba.spring.coba_crud_spring.service;

import com.coba.spring.coba_crud_spring.dto.LoginRequestDTO;
import com.coba.spring.coba_crud_spring.dto.RegisterRequestDTO;
import com.coba.spring.coba_crud_spring.exception.GlobalExceptionHandler;
import com.coba.spring.coba_crud_spring.exception.ResourceAlreadyExistException;
import com.coba.spring.coba_crud_spring.model.User;
import com.coba.spring.coba_crud_spring.repository.UserRepository;
import com.coba.spring.coba_crud_spring.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final PasswordEncoder passEncod;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginRequestDTO loginRequest) {

        // Proses autentikasi menggunakan Spring Security
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        // Menyimpan autentikasi ke SecurityContext
        SecurityContextHolder.getContext().setAuthentication(auth);
        return jwtTokenProvider.generateToken(auth);
    }

    @Override
    public User register(RegisterRequestDTO registerRequest) {

        // ngecek apakah username sudah ada
        if (userRepo.existsByUsername(registerRequest.getUsername())) {
            throw  new ResourceAlreadyExistException("Username " + registerRequest.getUsername() + " is already exist!");
        }

        if (userRepo.existsByEmail(registerRequest.getEmail())) {
            throw  new ResourceAlreadyExistException("Email " + registerRequest.getEmail() + " is already exist!");
        }

        User user = new User();
        user.setName(registerRequest.getName());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passEncod.encode(registerRequest.getPassword()));

        user.setRole("ROLE_USER"); // Mengatur role default

        return  userRepo.save(user);
    }
}
