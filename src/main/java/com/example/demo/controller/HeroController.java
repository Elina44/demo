package com.example.demo.controller;

import com.example.demo.model.Hero;
import com.example.demo.repository.HeroRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/heroes")
public class HeroController {
    @Autowired
    HeroRepository heroRepository;
    @Autowired
    EntityManager entityManager;

    @GetMapping
    public List<Hero> findAll() {
        return heroRepository.findAll();
    }

    @GetMapping("/{id}")
    public Hero show(@PathVariable long id) {
        return this.heroRepository.findById(id).get();
    }

    @PostMapping("/addhero")
    public Hero create(@Valid @RequestBody Hero hero) {
        return heroRepository.save(hero);
    }


    @PutMapping("/{id}")
    public Hero update(@PathVariable long id, @RequestBody Hero hero) {
        /*System.out.println(hero.getId());*/
        hero.setName(hero.getName());
        return heroRepository.save(hero);
    }


    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id") long id ) {
        heroRepository.deleteById(id);
        return true;
    }


}
