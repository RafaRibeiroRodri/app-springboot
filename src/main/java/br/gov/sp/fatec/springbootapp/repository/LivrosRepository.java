package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootapp.entity.Livros;

public interface LivrosRepository extends JpaRepository<Livros, Long> {

    public Livros findByNome(String nome);

    public Livros findByNomeAndAutor(String nome, String autor);

    @Query("select n from Livros n inner join n.categorias c where c.nome = ?1")
    public List<Livros> buscaPorNomeCategoria(String categoria);

}
