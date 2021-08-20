package br.gov.sp.fatec.springbootapp.service;

import br.gov.sp.fatec.springbootapp.entity.Livros;

public interface LivrosService {

    public Livros cadastrarLivro(String nome, String autor, String categoria);
    
}

