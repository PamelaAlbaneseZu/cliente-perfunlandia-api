package com.clientes.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "cliente")
@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends RepresentationModel<Cliente> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "id_direccion", nullable = false)
    private Integer idDireccion;

    @Column(name = "telefono")
    private String telefono;
}