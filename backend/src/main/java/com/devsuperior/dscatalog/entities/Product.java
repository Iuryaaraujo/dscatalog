package com.devsuperior.dscatalog.entities;

import com.devsuperior.dscatalog.projections.IdProjection;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product implements IdProjection<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // para aceitar TEXTO mais longo
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private String imgUrl;

    // Sempre que for usa uma data colocar esse anotetion
    // TIMESTAMP SEM FUSO HORÁRIO
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant date;

    @ManyToMany
    @JoinTable(name = "tb_product_category",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> categories = new HashSet<>(); // não colocar no construtor e retira o set

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String imgUrl, Instant parse) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    @Override // Para o compilador saber que está implementando a interface IdProjection<E>
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
