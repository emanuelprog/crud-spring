package com.emanuel.crudspring.service;

import java.util.List;
import java.util.Optional;

import com.emanuel.crudspring.exception.BadRequestException;
import com.emanuel.crudspring.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.emanuel.crudspring.model.Cliente;
import com.emanuel.crudspring.repository.ClienteRepository;

@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes() {
        var clientes = clienteRepository.findAll();

        if (clientes.isEmpty()) {
            throw new NotFoundException("Nenhum cliente encontrado!");
        }

        return clientes;
    }

    public Cliente saveCliente(Cliente cliente) {
        try {
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new BadRequestException("Dados inválidos");
        }
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteDB = clienteRepository.findById(id);

        if (clienteDB.isPresent()) {
            cliente.setId(id);
           return clienteRepository.save(cliente);
        } else {
            throw new BadRequestException("Não foi possível atualizar o cliente");
        }
    }

    public void deleteCliente(Long id) {
        Optional<Cliente> clienteDB = clienteRepository.findById(id);

        if (clienteDB.isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new BadRequestException("Não foi possível deletar o cliente");
        }
    }
    
}
