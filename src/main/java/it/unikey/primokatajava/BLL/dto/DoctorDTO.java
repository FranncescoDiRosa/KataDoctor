package it.unikey.primokatajava.BLL.dto;

import lombok.Data;

import java.util.Set;

@Data
public class DoctorDTO {
    private Integer id;
    private String name;
    private String surname;
    private Set<PatientDTO> patients;
}
