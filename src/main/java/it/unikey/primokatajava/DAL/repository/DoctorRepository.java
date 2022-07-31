package it.unikey.primokatajava.DAL.repository;

import it.unikey.primokatajava.DAL.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer> {
}
