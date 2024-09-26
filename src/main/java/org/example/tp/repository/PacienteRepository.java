package org.example.tp.repository;

import org.example.tp.dtos.CitaDTO;
import org.example.tp.dtos.PacienteDTO;
import org.example.tp.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Integer> {

    // HU10
    @Query("SELECT new org.example.tp.dtos.CitaDTO(c.id, c.fechaCita, c.hora, c.urlReunion, c.resultado, c.psicologo, c.paciente, c.estadoCita) " +
            "FROM Cita c " +
            "WHERE c.paciente.usuario.id = :pacienteId " +
            "AND c.fechaCita >= CURRENT_DATE " +
            "AND c.estadoCita.estado NOT IN ('Cancelada') " +
            "ORDER BY c.fechaCita ASC, c.hora ASC")
    List<CitaDTO> ListarCitasProximasPorPaciente(@Param("pacienteId") Long pacienteId);

    // HU12
    // Como paciente, deseo poder revisar la información de la cita
    // pendiente, para así poder confirmar detalles previamente.
    @Query("SELECT new org.example.tp.dtos.CitaDTO(c.id, c.fechaCita, c.hora, c.urlReunion, c.resultado, c.psicologo, c.paciente, c.estadoCita) " +
            "FROM Cita c " +
            "WHERE c.paciente.usuario.id = :pacienteId " +
            "AND c.estadoCita.id = 1")
    List<CitaDTO> ListarCitasPorPaciente(@Param("pacienteId") Long pacienteId);

    // HU13
    @Query("SELECT new org.example.tp.dtos.PacienteDTO(p.usuario_id, p.usuario, p.distrito, p.direccion) " +
            "FROM Paciente p " +
            "WHERE p.usuario_id = :pacienteId")
    List<PacienteDTO> InfoPaciente(@Param("pacienteId") Long pacienteId);

    // HU10
    @Query("SELECT new org.example.tp.dtos.CitaDTO(c.id, c.fechaCita, c.hora, c.urlReunion, c.resultado, c.psicologo, c.paciente, c.estadoCita) " +
            "FROM Cita c " +
            "WHERE c.paciente.usuario.id = :pacienteId " +
            "AND c.id = :citaId")
    List<CitaDTO> ListarCitaPorPaciente(@Param("pacienteId") Long pacienteId, @Param("citaId") Long citaId);
}
