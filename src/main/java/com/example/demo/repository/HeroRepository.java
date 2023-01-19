package com.example.demo.repository;

import com.example.demo.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Long> {

    List<Hero> findByName(String name);
}
