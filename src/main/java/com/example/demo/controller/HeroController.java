package com.example.demo.controller;

import com.example.demo.model.Hero;
import com.example.demo.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Hero> findAll() {
        return heroRepository.findAll();
    }


    @PostMapping("/addhero")
    public Hero addHero(@Valid @RequestBody Hero hero) {
        return heroRepository.save(hero);
    }

    @PutMapping("/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Hero newHero) {
        Hero heroToUpdate = this.heroRepository.findById(id).get();
        heroToUpdate.setId(newHero.getId());
        heroToUpdate.setName(newHero.getName());
        return heroRepository.save(heroToUpdate);
    }

    @PostMapping("/update/{id}")
    public String updateHero(@PathVariable("id") long id, @Valid Hero hero,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            hero.setId(id);
            return "update-hero";
        }

        heroRepository.save(hero);
        return "redirect:/api/heroes";
    }

    @GetMapping("/delete/{id}")
    public String deleteHero(@PathVariable("id") long id, Model model) {
        Hero hero = heroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid heros Id:" + id));
        heroRepository.delete(hero);
        return "redirect:/api/heroes";
    }

}
