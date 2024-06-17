package com.emanuel.crudspring.controller;

import com.emanuel.crudspring.model.DefaultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuel.crudspring.model.Cliente;
import com.emanuel.crudspring.service.ClienteService;

import lombok.AllArgsConstructor;
import com.emanuel.crudspring.utils.ResponseUtil;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {

    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<DefaultResponse> buscarTodos() {
        return ResponseUtil.generateResponse("Clientes encontrados!", HttpStatus.OK, clienteService.getClientes());
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> adicionar(@RequestBody Cliente cliente) {
        return ResponseUtil.generateResponse("Cliente adicionado com sucesso!", HttpStatus.CREATED, clienteService.saveCliente(cliente));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DefaultResponse> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseUtil.generateResponse("Cliente atualizado com sucesso!", HttpStatus.OK, clienteService.updateCliente(id, cliente));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DefaultResponse> excluir(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseUtil.generateResponse("Cliente deletado com sucesso!", HttpStatus.OK, null);
    }
}
