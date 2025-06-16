package it.epicode.u5w3d1.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDTO {
    private Long dipendenteId;
    private Long viaggioId;
    private LocalDate dataRichiesta;
    private String note;
}
