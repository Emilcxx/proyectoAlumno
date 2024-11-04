package com.proyecto.alumno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.proyecto.alumno.models.Alumno;
import com.proyecto.alumno.repository.AlumnoRepository;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

    //Inyecta el repositorio de JPA
    @Autowired
    private AlumnoRepository alumnoRepository;

    //muestra lista de alumnos
    @GetMapping
    public String verAlumnos(Model model) {
        model.addAttribute("lista", alumnoRepository.findAll()); // Recupera alumnos de la BD
        return "alumnos";
    }

    //muestra el formulario de agregar nuevo alumno
    @GetMapping("/nuevo")
    public String agregarAlumno(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "nuevoAlumno"; // Vista de formulario
    }

    //procesa el formulario de nuevo alumno
    @PostMapping("/guardar")
    public String guardarAlumno(@ModelAttribute("alumno") Alumno alumno) {
        alumnoRepository.save(alumno); // Guarda el alumno en la BD
        return "redirect:/alumnos";
    }

    //muestra el formulario de edición de alumno
    @GetMapping("/editar/{id}")
    public String editarAlumno(@PathVariable int id, Model model) {
        Alumno alumno = alumnoRepository.findById(id).orElse(null); // Busca alumno por ID
        model.addAttribute("alumno", alumno);
        return "editarAlumno";
    }

    //elimina un alumno por ID
    @GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable int id) {
        alumnoRepository.deleteById(id); 
        return "redirect:/alumnos";
    }
}
