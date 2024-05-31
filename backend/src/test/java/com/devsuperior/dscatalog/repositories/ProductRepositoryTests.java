package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private long exintingId;
    private long nonExistingId;
    private long countTotalProducts;

    // É executando antes de cada teste
    @BeforeEach
    void setUp() throws Exception {
        exintingId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 25L;
    }

    // salvar deve persistir com incremento automático quando o ID é nulo
    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        Product product = Factory.createProduct();
        product.setId(null);

        product = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId());
    }

    // deleta deve deleta objeto não vazio quando o ID existir
    @Test
    public void deleteSholdDeleteObjctWhenIdExists() {
        // Action
        repository.deleteById(exintingId);
        Optional<Product> result = repository.findById(exintingId);
        // ASSERT
        // isPresent() testa se existe um objeto dentro do Optional
        Assertions.assertFalse(result.isPresent());
    }

    // Retorna um Optional<Product> não vazio quando o id existir
    @Test
    public void idExistsSholdReturnNonEmptyOptionalWhenIdExists() {

        Optional<Product> result = repository.findById(exintingId);

        Assertions.assertTrue(result.isPresent());
    }

    // Retorna um Optional<Product> vazio quando o id não existir
    @Test
    public void idExistsSholdReturnEmptyOptionalWhenIdDoesNotExist() {

        Optional<Product> result = repository.findById(nonExistingId);

        // isEmpty() testa se Optinal está vazio
        Assertions.assertTrue(result.isEmpty());
    }

    // delete deve lançar exceção de acesso a dados de resultado vazio quando o ID não existe
//    @Test
//    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
//        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
//            repository.deleteById(nonExistingId);
//        });
//    }

}
