package it.epicode.u5w3d1.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.u5w3d1.dto.DipendenteDTO;
import it.epicode.u5w3d1.model.Dipendente;
import it.epicode.u5w3d1.service.DipendenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final Cloudinary cloudinary;
    private final DipendenteService dipendenteService;

    @PostMapping("/dipendente/{id}")
    public ResponseEntity<String> upload(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());


        String url = uploadResult.get("url").toString();


        Dipendente d = dipendenteService.trovaPerId(id);


        d.setImmagineProfiloUrl(url);


        dipendenteService.aggiorna(id, DipendenteDTO.builder()
                .username(d.getUsername())
                .nome(d.getNome())
                .cognome(d.getCognome())
                .email(d.getEmail())
                .build());


        return ResponseEntity.ok(url);
    }
}

