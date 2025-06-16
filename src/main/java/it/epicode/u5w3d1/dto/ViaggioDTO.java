package it.epicode.u5w3d1.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ViaggioDTO {
    private String destinazione;
    private LocalDate data;
}
