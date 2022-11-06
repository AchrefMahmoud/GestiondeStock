package com.tn.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.LigneVente;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {

    List<LigneVente> findAllByArticleId(Integer idArticle);

    List<LigneVente> findAllByVentesId(Integer id);

    @Query("select L from LigneVente L where L.article.id = :idArticle")
    List<LigneVente> findAllByArticle(Integer idArticle);
}
