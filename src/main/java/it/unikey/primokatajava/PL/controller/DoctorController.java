package it.unikey.primokatajava.PL.controller;

import it.unikey.primokatajava.BLL.dto.DoctorDTO;
import it.unikey.primokatajava.BLL.exceptions.NotFoundException;
import it.unikey.primokatajava.BLL.service.impl.DoctorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// Ho invertito le annotation @PutMapping e @PostMapping nei relativi metodi
@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorServiceImpl service;

    @GetMapping
    private ResponseEntity<List<DoctorDTO>> getAllDoctors(){
        List<DoctorDTO> doctorsList =  service.getAll();
        return new ResponseEntity<>(doctorsList, HttpStatus.OK);
    };

    @GetMapping(path = "/{id}")
    private ResponseEntity<DoctorDTO> getDoctorById(@PathVariable("id") Integer id){
        try {
            DoctorDTO doctor = service.getById(id);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    };

    @PutMapping
    private ResponseEntity<DoctorDTO> postDoctor(@RequestBody DoctorDTO doctor){
        DoctorDTO doctorCreated = service.insert(doctor);
        return new ResponseEntity<>(doctorCreated, HttpStatus.CREATED);
    }

    @PostMapping
    private ResponseEntity<DoctorDTO> putDoctor(@RequestBody DoctorDTO doctor){
        try {
          DoctorDTO doctorUpdated = service.update(doctor);
          return new ResponseEntity<>(doctorUpdated, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity<Void> deleteDoctor(@PathVariable("id") Integer id){
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
