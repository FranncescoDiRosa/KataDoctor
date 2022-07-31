package it.unikey.primokatajava.BLL.service.impl;

import it.unikey.primokatajava.BLL.dto.PatientDTO;
import it.unikey.primokatajava.BLL.exceptions.NotFoundException;
import it.unikey.primokatajava.BLL.mapper.PatientMapper;
import it.unikey.primokatajava.BLL.service.generic.CrudService;
import it.unikey.primokatajava.DAL.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //da togliere appena finito questo porcodio di service
public class PatientServiceImpl implements CrudService<PatientDTO> {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientDTO insert(PatientDTO entity) {
        return patientMapper
                .fromPatientEntityToPatientDTO(patientRepository
                        .save(patientMapper
                                .fromPatientDTOToPatientEntity(entity)));
    }

    @Override
    public PatientDTO getById(Integer id) throws NotFoundException {
        return patientMapper
                .fromPatientEntityToPatientDTO(patientRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("The patient you're trying to get not found in DB")));
    }

    @Override
    public List<PatientDTO> getAll() {
        return patientMapper.fromPatientEntityListToPatientDTOList(patientRepository.findAll());
    }

    @Override
    public PatientDTO update(PatientDTO entity) throws NotFoundException {
        if(!patientRepository.existsById(entity.getId())){
            throw new NotFoundException("The patient you're trying to update not found in DB");
        }
        return patientMapper
                .fromPatientEntityToPatientDTO(patientRepository
                        .save(patientMapper
                                .fromPatientDTOToPatientEntity(entity)));
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        if(!patientRepository.existsById(id)){
            throw new NotFoundException("The patient you're trying to delete not found in DB");
        }
        patientRepository.deleteById(id);
    }
}
