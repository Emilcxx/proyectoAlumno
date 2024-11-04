package com.proyecto.alumno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.alumno.models.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    
}
