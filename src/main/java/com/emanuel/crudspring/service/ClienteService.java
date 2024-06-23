package com.emanuel.crudspring.service;

import com.emanuel.crudspring.exception.BadRequestException;
import com.emanuel.crudspring.exception.NotFoundException;
import com.emanuel.crudspring.model.Cliente;
import com.emanuel.crudspring.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

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
