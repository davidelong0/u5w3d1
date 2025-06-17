package it.epicode.u5w3d1.controller;

import it.epicode.u5w3d1.dto.LoginDTO;
import it.epicode.u5w3d1.dto.LoginResponseDTO;
import it.epicode.u5w3d1.model.Dipendente;
import it.epicode.u5w3d1.security.JwtTool;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTool jwtTool;

    public AuthController(AuthenticationManager authenticationManager, JwtTool jwtTool) {
        this.authenticationManager = authenticationManager;
        this.jwtTool = jwtTool;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        Dipendente utente = (Dipendente) authentication.getPrincipal();
        String token = jwtTool.createToken(utente);

        return LoginResponseDTO.builder()
                .token(token)
                .email(utente.getEmail())
                .nome(utente.getNome())
                .cognome(utente.getCognome())
                .username(utente.getUsername())
                .build();
    }
}




