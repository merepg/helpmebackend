package org.example.tp.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tp.entities.Usuario;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PsicologoDTO {

    private Integer usuario_id;
    private Usuario usuario;
    private double valoracion;
    private String urlCertificado;
    private int anios_experiencia;
}
