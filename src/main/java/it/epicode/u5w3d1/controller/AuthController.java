package it.epicode.u5w3d1.controller;

import it.epicode.u5w3d1.dto.LoginDTO;
import it.epicode.u5w3d1.model.Dipendente;
import it.epicode.u5w3d1.security.JwtTool;
import it.epicode.u5w3d1.service.DipendenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final DipendenteService dipendenteService;
    private final JwtTool jwtTool;

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDTO loginDto) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword());

        authenticationManager.authenticate(auth);

        Dipendente user = dipendenteService.findByEmail(loginDto.getEmail());
        return jwtTool.createToken(user);
    }
}


