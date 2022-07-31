package it.unikey.primokatajava.PL.controller;

import it.unikey.primokatajava.BLL.dto.PatientDTO;
import it.unikey.primokatajava.BLL.exceptions.NotFoundException;
import it.unikey.primokatajava.BLL.service.impl.PatientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// ho messo @RequestMapping invece che @RequestBody nel metodo put
@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    private final PatientServiceImpl service;

    @PostMapping
    private ResponseEntity<List<PatientDTO>> getAllPatients(){
        List<PatientDTO> patients = service.getAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    private ResponseEntity<PatientDTO> getPatientById(@PathVariable("id") Integer id){
        try {
            PatientDTO patient = service.getById(id);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    private ResponseEntity<PatientDTO> postPatient(@RequestBody PatientDTO patient){
        PatientDTO patientSaved = service.insert(patient);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PutMapping
    private ResponseEntity<PatientDTO> putPatient(@RequestMapping PatientDTO patient){
        try {
            PatientDTO patientUpdated = service.update(patient);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity<Void> deletePatient(@PathVariable("id") Integer id){
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
