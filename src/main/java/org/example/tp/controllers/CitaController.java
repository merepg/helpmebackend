package org.example.tp.controllers;

import org.example.tp.dtos.CitaDTO;
import org.example.tp.entities.Cita;
import org.example.tp.services.CitaServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CitaController {
    @Autowired
    private CitaServiceImpl citaServiceImpl;

    @GetMapping("/citas")
    public List<CitaDTO> obtenerCitas() {
        ModelMapper modelMapper= new ModelMapper();
        List<Cita> cita=citaServiceImpl.obtenercitas();
        List<CitaDTO> citaDTOS= Arrays.asList(modelMapper.map(cita, CitaDTO[].class));
        return citaDTOS;
    }

    @PostMapping("/cita")
    public CitaDTO registrar(@RequestBody CitaDTO citaDTO){
        ModelMapper mapper= new ModelMapper();
        Cita cita= mapper.map(citaDTO, Cita.class);
        cita=citaServiceImpl.registrar(cita);
        return mapper.map(cita, CitaDTO.class);
    }


    @DeleteMapping("/cita/{id}")
    public void eliminar(@PathVariable Integer id) throws Exception{
        ModelMapper mapper= new ModelMapper();
        try{
            citaServiceImpl.eliminar(id);
        } catch (Exception e) {
            throw new Exception("Disculpe la molestia, no se ha podido eliminar");
        }
    }

    @PutMapping("/cita")
    public ResponseEntity<CitaDTO> actualizar(@RequestBody CitaDTO citaDTO) throws Exception{
        ModelMapper mapper= new ModelMapper();
        try{
            Cita cita= mapper.map(citaDTO, Cita.class);
            cita=citaServiceImpl.actualizar(cita);
            citaDTO=mapper.map(cita, CitaDTO.class);
        } catch (Exception e) {
            return new ResponseEntity<>(citaDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(citaDTO);
    }

    // HU09
    @PutMapping("/cita/completar-paciente")
    public void marcarCitaCompletadaPorPaciente(@RequestParam Integer citaId, @RequestParam Integer pacienteId) {
        citaServiceImpl.marcarCitaCompletadaPorPaciente(citaId, pacienteId);
    }

    // HU09
    @PutMapping("/cita/completar-psicologo")
    public void marcarCitaCompletadaPorPsicologo(@RequestParam Long citaId, @RequestParam Long psicologoId) {
        citaServiceImpl.marcarCitaCompletadaPorPsicologo(citaId, psicologoId);
    }

    // HU14
    @PutMapping("/cita/resultado")
    public void actualizarResultadoCita(@RequestParam Long citaId, @RequestParam Long psicologoId, @RequestBody String resultado) {
        citaServiceImpl.actualizarResultadoCita(citaId, psicologoId, resultado);
    }

    // HU19
    @PutMapping("/cita/aceptar")
    public void aceptarCita(@RequestParam Long citaId, @RequestParam Long psicologoId) {
        citaServiceImpl.aceptarCita(citaId, psicologoId);
    }

    @PutMapping("cita/cancelar")
    public void cancelarCita (@RequestParam Long citaId, @RequestParam Long pacienteId){
        citaServiceImpl.cancelarCita(citaId, pacienteId);
    }

    // HU15
    @GetMapping("/citasPorPaciente")
    public List<CitaDTO> obtenerCitasPorId(@RequestParam Long id){
        return citaServiceImpl.ListarCitasPorPaciente(id);
    }





}
