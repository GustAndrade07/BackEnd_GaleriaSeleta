package com.galeriaseleta.repository;

import com.galeriaseleta.model.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Integer> {
    Optional<Cupom> findByCodigo(String codigo);
    List<Cupom> findByAtivo(Boolean ativo);
}
