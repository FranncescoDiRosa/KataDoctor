package it.unikey.primokatajava.DAL.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
//Ho scritto malamente il campo del mappedBy.. ho scritto doctors mentre nella PatientEntity ho il campo denominato con
//doctor
@Getter
@Setter
@Entity
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @OneToMany(mappedBy = "doctors")
    private Set<PatientEntity> patients;
}
