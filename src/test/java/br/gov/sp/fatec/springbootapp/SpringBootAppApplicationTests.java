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
import br.gov.sp.fatec.springbootapp.repository.CategoriaRepository;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

    @Autowired
    private CategoriaRepository catRepo;

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
}
