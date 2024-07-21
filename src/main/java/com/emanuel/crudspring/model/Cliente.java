package com.emanuel.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
    @SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_seq", allocationSize = 1)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 2, nullable = false)
    private Integer age;

    public Cliente(Cliente data) {
        this.name = data.name;
        this.age = data.age;
    }
}
