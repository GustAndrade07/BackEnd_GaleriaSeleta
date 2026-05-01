package com.galeriaseleta.repository;

import com.galeriaseleta.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByCategoriaId(Integer categoriaId);
    List<Produto> findByStatus(String status);
    List<Produto> findByNovidade(Boolean novidade);
}
