package com.clientes.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteDTO extends RepresentationModel<ClienteDTO> {
    private Integer idCliente;
    private Integer idUsuario;
    private Integer idDireccion;
    private String telefono;
}