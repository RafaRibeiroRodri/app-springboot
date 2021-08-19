package br.gov.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootapp.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    public Categoria findByNome(String nome);

}