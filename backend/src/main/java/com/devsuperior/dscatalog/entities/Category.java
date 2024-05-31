package com.devsuperior.dscatalog.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Sempre que for usa uma data coloca esse anotetion
    // TIMESTAMP SEM FUSO HORÁRIO
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    // Sempre que for usa uma data coloca esse anotetion
    // TIMESTAMP SEM FUSO HORÁRIO
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    /*
    @PrePersist
Como o nome sugere; Ele é executado logo antes da entidade associada ser
salva pela primeira vez no banco de dados.
Se houver transações que desejamos realizar neste momento, este método resolverá o problema.
     */
    // Salva o momento que foi criado
    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    /*
    @Pré-atualização
Novamente, como o nome sugere; É um evento de vida
JPA que será acionado sempre que o banco de dados da entidade associada for atualizado.
     */
    // Salva a  atualização
    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
