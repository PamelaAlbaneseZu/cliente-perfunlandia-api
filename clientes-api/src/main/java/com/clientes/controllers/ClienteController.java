package com.clientes.controllers;

import com.clientes.dto.ClienteDTO;
import com.clientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Integer id, @RequestBody ClienteDTO dto) {
        return service.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return service.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    //  MÉTODOS HATEOAS
    // Crear cliente con enlaces HATEOAS
    @PostMapping("/hateoas")
    public ResponseEntity<EntityModel<ClienteDTO>> crearHateoas(@RequestBody ClienteDTO dto) {
        ClienteDTO clienteGuardado = service.guardar(dto);
        
        // Enlaces que apuntan al API Gateway
        Link selfLink = Link.of("http://localhost:8888/api-gateway/clientes/" + clienteGuardado.getIdCliente(), "self");
        Link collectionLink = Link.of("http://localhost:8888/api-gateway/clientes", "collection");
        Link updateLink = Link.of("http://localhost:8888/api-gateway/clientes/" + clienteGuardado.getIdCliente(), "update");
        Link deleteLink = Link.of("http://localhost:8888/api-gateway/clientes/" + clienteGuardado.getIdCliente(), "delete");
        
        EntityModel<ClienteDTO> resource = EntityModel.of(clienteGuardado, selfLink, collectionLink, updateLink, deleteLink);
        return ResponseEntity.ok(resource);
    }

    // Listar clientes con enlaces HATEOAS
    @GetMapping("/hateoas")
    public ResponseEntity<CollectionModel<EntityModel<ClienteDTO>>> listarHateoas() {
        List<ClienteDTO> clientes = service.listar();
        
        List<EntityModel<ClienteDTO>> clientesResources = clientes.stream()
                .map(cliente -> {
                    Link selfLink = Link.of("http://localhost:8888/api-gateway/clientes/" + cliente.getIdCliente(), "self");
                    Link updateLink = Link.of("http://localhost:8888/api-gateway/clientes/" + cliente.getIdCliente(), "update");
                    Link deleteLink = Link.of("http://localhost:8888/api-gateway/clientes/" + cliente.getIdCliente(), "delete");
                    return EntityModel.of(cliente, selfLink, updateLink, deleteLink);
                })
                .collect(Collectors.toList());
        
        Link collectionLink = Link.of("http://localhost:8888/api-gateway/clientes", "self");
        Link createLink = Link.of("http://localhost:8888/api-gateway/clientes", "create");
        
        CollectionModel<EntityModel<ClienteDTO>> resources = CollectionModel.of(clientesResources, collectionLink, createLink);
        return ResponseEntity.ok(resources);
    }

    // Obtener cliente por ID con enlaces HATEOAS
    @GetMapping("/hateoas/{id}")
    public ResponseEntity<EntityModel<ClienteDTO>> obtenerHateoas(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(cliente -> {
                    // Enlaces que apuntan al API Gateway
                    Link selfLink = Link.of("http://localhost:8888/api-gateway/clientes/" + cliente.getIdCliente(), "self");
                    Link collectionLink = Link.of("http://localhost:8888/api-gateway/clientes", "collection");
                    Link updateLink = Link.of("http://localhost:8888/api-gateway/clientes/" + cliente.getIdCliente(), "update");
                    Link deleteLink = Link.of("http://localhost:8888/api-gateway/clientes/" + cliente.getIdCliente(), "delete");
                    
                    EntityModel<ClienteDTO> resource = EntityModel.of(cliente, selfLink, collectionLink, updateLink, deleteLink);
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar cliente con enlaces HATEOAS
    @PutMapping("/hateoas/{id}")
    public ResponseEntity<EntityModel<ClienteDTO>> actualizarHateoas(@PathVariable Integer id, @RequestBody ClienteDTO dto) {
        return service.actualizar(id, dto)
                .map(cliente -> {
                    // Enlaces que apuntan al API Gateway
                    Link selfLink = Link.of("http://localhost:8888/api-gateway/clientes/" + cliente.getIdCliente(), "self");
                    Link collectionLink = Link.of("http://localhost:8888/api-gateway/clientes", "collection");
                    Link updateLink = Link.of("http://localhost:8888/api-gateway/clientes/" + cliente.getIdCliente(), "update");
                    Link deleteLink = Link.of("http://localhost:8888/api-gateway/clientes/" + cliente.getIdCliente(), "delete");
                    
                    EntityModel<ClienteDTO> resource = EntityModel.of(cliente, selfLink, collectionLink, updateLink, deleteLink);
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar cliente con enlaces HATEOAS
    @DeleteMapping("/hateoas/{id}")
    public ResponseEntity<Void> eliminarHateoas(@PathVariable Integer id) {
        return service.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}