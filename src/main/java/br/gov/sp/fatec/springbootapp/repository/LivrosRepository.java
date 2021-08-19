package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootapp.entity.Livros;

public interface LivrosRepository extends JpaRepository<Livros, Long> {
    
    public List<Livros> findByCategoriasNome(String categoria);

}
