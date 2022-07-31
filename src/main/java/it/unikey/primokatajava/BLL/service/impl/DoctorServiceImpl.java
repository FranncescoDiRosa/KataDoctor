package it.unikey.primokatajava.BLL.service.impl;

import it.unikey.primokatajava.BLL.dto.DoctorDTO;
import it.unikey.primokatajava.BLL.exceptions.NotFoundException;
import it.unikey.primokatajava.BLL.mapper.DoctorMapper;
import it.unikey.primokatajava.BLL.service.generic.CrudService;
import it.unikey.primokatajava.DAL.entity.DoctorEntity;
import it.unikey.primokatajava.DAL.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//Ho invertito il metodo nel DoctorMapper..
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements CrudService<DoctorDTO> {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    public DoctorDTO insert(DoctorDTO entity) {
        DoctorEntity doctorSaved = doctorRepository.save(doctorMapper.fromDoctorDTOToDoctorEntity(entity));
        return doctorMapper.fromDoctorEntityToDoctorDTO(doctorSaved);
    }

    @Override
    public DoctorDTO getById(Integer id) throws NotFoundException {
        return doctorMapper.fromDoctorEntityToDoctorDTO(doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The doctor you're trying to get not found in DB")));
    }

    @Override
    public List<DoctorDTO> getAll() {
        return doctorRepository.findAll().stream().map(doctorMapper::fromDoctorEntityToDoctorDTO).collect(Collectors.toList());
    }

    @Override
    public DoctorDTO update(DoctorDTO entity) throws NotFoundException {
        if(!doctorRepository.existsById(entity.getId())){
            throw new NotFoundException("The doctor you're trying to update not found in DB");
        }
        DoctorEntity doctorUpdated = doctorRepository.save(doctorMapper.fromDoctorDTOToDoctorEntity(entity));
        return doctorMapper.fromDoctorEntityToDoctorDTO(doctorUpdated);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        if(!doctorRepository.existsById(id)){
            throw new NotFoundException("The doctor you're trying to delete not found in DB");
        }
        doctorRepository.deleteById(id);
    }
}
