package org.example.tp.services;

import jakarta.transaction.Transactional;
import org.example.tp.dtos.CitaDTO;
import org.example.tp.entities.Cita;
import org.example.tp.interfaces.CitaService;
import org.example.tp.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {
    @Autowired
    private CitaRepository citaRepository;

    @Override
    public List<Cita> obtenercitas() {
        return citaRepository.findAll();
    }

    @Transactional
    @Override
    public Cita registrar(Cita cita) {
        return citaRepository.save(cita);
    }

    @Transactional
    @Override
    public void eliminar(Integer id) {
        citaRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Cita actualizar(Cita cita) {
        if(citaRepository.findById(cita.getId()).isPresent()) {
            return citaRepository.save(cita);
        }
        return null;
    }

    // US09: Marca de compleción de cita como paciente/psicólogo
    @Transactional
    @Override
    public void marcarCitaCompletadaPorPaciente(Integer citaId,Integer pacienteId){
        citaRepository.marcarCitaCompletadaPorPaciente(citaId,pacienteId);
    }

    // T20: Creación de función PUT sobre la entidad cita respecto a su atributo de estado de cita como psicólogo
    @Transactional
    @Override
    public void marcarCitaCompletadaPorPsicologo(Long citaId, Long psicologoId){
        citaRepository.marcarCitaCompletadaPorPsicologo(citaId,psicologoId);
    }

    // US14: Subir reporte de la cita elaborado por el psicólogo
    // T21: Creación de función PUT sobre la entidad cita que altera el campo de resultado
    @Transactional
    @Override
    public void actualizarResultadoCita(Long citaId, Long psicologoId, String resultado){
        citaRepository.actualizarResultadoCita( citaId,psicologoId,resultado);
    }

    // US19: Aceptación de cita
    @Transactional
    @Override
    public void aceptarCita(Long citaId, Long psicologoId){
        citaRepository.aceptarCita( citaId,psicologoId);
    }

    @Override
    public List<CitaDTO> ListarCitasPorPaciente(Long pacienteId){
        return citaRepository.ListarCitasPorPaciente(pacienteId);
    }

    @Transactional
    @Override
    public void cancelarCita(Long citaId, Long pacienteId){
        citaRepository.cancelarCita(citaId,pacienteId);
    }

}
