package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

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
    void livrosServiceCadastrarLivroTest() {
        livrosService.cadastrarLivro("Introdução a programação", "autor", "Educação");

        assertNotNull(livrosRepo.findByNome("Introdução a programação").getId());
    }

    @Test
    void livrosServiceFindByNomeAndAutorTest() {
        livrosService.cadastrarLivro("Introdução a programação", "autor", "Educação");

        assertNotNull(livrosRepo.findByNomeAndAutor("Introdução a programação", "autor").getId());
    }

    @Test
    void livrosRepositoryBuscaPorCategoriaTest() {
        livrosService.cadastrarLivro("Introdução a programação", "autor", "Educação");

        assertFalse(livrosRepo.buscaPorNomeCategoria("Educação").isEmpty());
    }

    @Test
    void livrosServiceErrorNomeTest() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> livrosService.cadastrarLivro("", "autor", "Educação"));
        assertTrue(runtimeException.getMessage().contains("Invalid Parameters"));
    }

    @Test
    void livrosServiceErrorAutorTest() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> livrosService.cadastrarLivro("Introdução a programação", "", "Educação"));
        assertTrue(runtimeException.getMessage().contains("Invalid Parameters"));
    }

    @Test
    void livrosServiceErrorCategoriaTest() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> livrosService.cadastrarLivro("Introdução a programação", "autor", ""));
        assertTrue(runtimeException.getMessage().contains("Invalid Parameters"));
    }
}
