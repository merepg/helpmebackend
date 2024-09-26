package org.example.tp.repository;

import org.example.tp.dtos.ImportesDTO;
import org.example.tp.dtos.PagoDTO;
import org.example.tp.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
    // HU23: Seleccionar método de pago
    // T25: Incluir campo de método de pago en la US24
    @Modifying
    @Query("UPDATE Pago p SET p.metodoPago = :metodoPago " +
            "WHERE p.cita.id = :citaId")
    void actualizarMetodoPago(@Param("citaId") Long citaId,
                              @Param("metodoPago") String metodoPago);

    // HU29: Filtrado de pagos
    // Por un rango de fechas
    @Query("SELECT new org.example.tp.dtos.PagoDTO(p.id, p.monto, p.metodoPago, p.fechaEmision, p.fechaVencimiento, p.cita, p.estadoPago) " +
            "FROM Pago p " +
            "WHERE DATE(p.fechaEmision) BETWEEN :fechaInicio AND :fechaFin " +
            "AND p.cita.paciente.usuario_id = :pacienteId")
    List<PagoDTO> listarPagosPorRangoFechas(@Param("fechaInicio") LocalDate fechaInicio,
                                            @Param("fechaFin") LocalDate fechaFin,
                                            @Param("pacienteId") Long pacienteId);

    // HU21
    @Query("SELECT new org.example.tp.dtos.PagoDTO(p.id, p.monto, p.metodoPago, p.fechaEmision, p.fechaVencimiento, p.cita, p.estadoPago) " +
            "FROM Pago p " +
            "WHERE p.cita.id = :citaId")
    List<PagoDTO> listarPagoPorCita(@Param("citaId") Long citaId);

    @Query("SELECT new org.example.tp.dtos.ImportesDTO(p.cita.id, p.monto) " +
            "FROM Pago p " +
            "WHERE p.cita.paciente.usuario_id = :pacienteId")
    List<ImportesDTO> listarImportes(@Param("pacienteId") Long pacienteId);
}
