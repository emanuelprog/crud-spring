package com.emanuel.crudspring.repository;

import com.emanuel.crudspring.model.Cliente;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class ClienteRepositoryTest {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should return all clientes successfully from DB")
    void findAllSuccess() {
        Cliente cliente = new Cliente();
        cliente.setName("Emanuel");
        cliente.setAge(23);

        this.createCliente(cliente);

        List<Cliente> result = this.clienteRepository.findAll();

        assertThat(result.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Should not return all clientes from DB when clientes not exists")
    void findAllError() {
        List<Cliente> result = this.clienteRepository.findAll();

        assertThat(result.isEmpty()).isTrue();
    }

    private void createCliente(Cliente data) {
        Cliente newCliente = new Cliente(data);
        this.entityManager.persist(newCliente);
    }

}