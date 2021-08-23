package br.gov.sp.fatec.springbootapp.service;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.springbootapp.entity.Categoria;
import br.gov.sp.fatec.springbootapp.entity.Livros;
import br.gov.sp.fatec.springbootapp.repository.CategoriaRepository;
import br.gov.sp.fatec.springbootapp.repository.LivrosRepository;

@Service
public class LivrosServiceImpl implements LivrosService {

    @Autowired
    private CategoriaRepository catRepo;

    @Autowired
    private LivrosRepository livrosRepo;

    @Transactional
    public Livros cadastrarLivro(String nome, String autor, String editora, String categoria) {
        Categoria cat = catRepo.findByNome(categoria);
        if(nome == "" || nome == null || autor == "" || autor == null || editora == "" || editora == null || categoria == "" || categoria == null) {
            throw new RuntimeException("Invalid Parameters");
        }
        if(cat == null) {
            cat = new Categoria();
            cat.setNome(categoria);
            catRepo.save(cat);
        }
        Livros livros = new Livros();
        livros.setNome(nome);
        livros.setAutor(autor);
        livros.setEditora(editora);
        livros.setCategorias(new HashSet<Categoria>());
        livros.getCategorias().add(cat);
        livrosRepo.save(livros);

        return livros;
    }
    
}
