package it.unikey.primokatajava.BLL.mapper;

import it.unikey.primokatajava.BLL.dto.PatientDTO;
import it.unikey.primokatajava.DAL.entity.PatientEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PatientMapper {
    PatientDTO fromPatientEntityToPatientDTO(PatientEntity entity);
    PatientEntity fromPatientDTOToPatientEntity(PatientDTO dto);
    List<PatientDTO> fromPatientEntityListToPatientDTOList (List<PatientEntity> entities);
}
