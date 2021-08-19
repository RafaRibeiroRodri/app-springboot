package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Categoria;
import br.gov.sp.fatec.springbootapp.entity.Livros;
import br.gov.sp.fatec.springbootapp.repository.CategoriaRepository;
import br.gov.sp.fatec.springbootapp.repository.LivrosRepository;
import br.gov.sp.fatec.springbootapp.service.LivrosService;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

    @Autowired
    private CategoriaRepository catRepo;

    @Autowired
    private LivrosRepository livrosRepo;

    @Autowired
    private LivrosService livrosService;

	@Test
	void contextLoads() {
    }

    @Test
    void categoriaRepositorySaveTest() {
        Categoria cat = new Categoria();
        cat.setNome("Educacao");
        catRepo.save(cat);
        assertNotNull(cat.getId());
    }

    @Test
    void categoriaRepositoryFindByNomeTest() {
        Categoria cat = new Categoria();
        cat.setNome("Terror");
        catRepo.save(cat);
        assertNotNull(catRepo.findByNome("Terror"));
    }

    @Test
    void livrosRepositorySaveTest() {
        Categoria cat = new Categoria();
        cat.setNome("Educação");
        catRepo.save(cat);
        Livros livros = new Livros();
        livros.setNome("Introdução a programação");
        livros.setAutor("autor");
        livros.setCategorias(new HashSet<Categoria>());
        livros.getCategorias().add(cat);
        livrosRepo.save(livros);
        assertNotNull(livros.getId());
    }

    @Test
    void livrosRepositoryFindByCategoriasNomeTest() {
        Categoria cat = new Categoria();
        cat.setNome("Educação");
        catRepo.save(cat);
        Livros livros = new Livros();
        livros.setNome("Introdução a programação");
        livros.setAutor("autor");
        livros.setCategorias(new HashSet<Categoria>());
        livros.getCategorias().add(cat);
        livrosRepo.save(livros);

        List<Livros> livro = livrosRepo.findByCategoriasNome("Educação");

        assertFalse(livro.isEmpty());
    }

    @Test
    void livrosServiceCadastrarLivroTest() {
        livrosService.cadastrarLivro("Introdução a programação", "autor", "Educação");

        List<Livros> livro = livrosRepo.findByCategoriasNome("Educação");

        assertFalse(livro.isEmpty());
    }
}
