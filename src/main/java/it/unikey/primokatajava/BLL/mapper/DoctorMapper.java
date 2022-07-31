package it.unikey.primokatajava.BLL.mapper;

import it.unikey.primokatajava.BLL.dto.DoctorDTO;
import it.unikey.primokatajava.DAL.entity.DoctorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorDTO fromDoctorDTOToDoctorEntity(DoctorEntity entity);
    DoctorEntity fromDoctorEntityToDoctorDTO(DoctorDTO dto);
}
