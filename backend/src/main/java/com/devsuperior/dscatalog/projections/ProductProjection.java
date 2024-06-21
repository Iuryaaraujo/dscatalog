package com.devsuperior.dscatalog.projections;

//- usando OO Genérica
public interface ProductProjection extends IdProjection<Long> {
    // Dados que quero que retorne na consulta do
    // banco de dados ProductRepository
    //Long getId(); -herança da interface Genérica
    String getName();
}
