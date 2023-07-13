package br.com.banco.domain.interfaces;

import br.com.banco.domain.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    @Query("""
            select c from Conta c
            where c.idConta = :idConta
           """)
    Conta findByIdConta(Long idConta);
}