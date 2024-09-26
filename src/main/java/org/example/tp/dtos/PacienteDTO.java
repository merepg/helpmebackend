package org.example.tp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tp.entities.Usuario;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {

    private int usuario_id;
    private Usuario usuario;
    private String distrito;
    private String direccion;

}
