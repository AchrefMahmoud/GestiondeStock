package com.tn.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.Category;


public interface CategoryRepository extends JpaRepository<Integer, Category>{

}
