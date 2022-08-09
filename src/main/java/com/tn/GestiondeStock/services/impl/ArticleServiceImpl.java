package com.tn.GestiondeStock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tn.GestiondeStock.dto.ArticleDto;
import com.tn.GestiondeStock.entities.Article;
import com.tn.GestiondeStock.exception.EntityNotFoundException;
import com.tn.GestiondeStock.exception.ErrorCodes;
import com.tn.GestiondeStock.exception.InvalidEntityException;
import com.tn.GestiondeStock.repository.ArticleRepository;
import com.tn.GestiondeStock.services.ArticleService;
import com.tn.GestiondeStock.validator.ArticleValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

	private ArticleRepository articleRepository;
	
	
	@Autowired
	public ArticleServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	
	@Override
	public ArticleDto save(ArticleDto dto) {
		List<String> errors = ArticleValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Article is not valid", dto);
			throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
		}
		return ArticleDto.fromEntity(
				articleRepository.save(
						ArticleDto.toEntity(dto)
				));
	}

	
	@Override
	public ArticleDto findById(Integer id) {
		if (id == null) {
			log.error("Article ID is null");
			return null;
		}
		
		Optional<Article> article = articleRepository.findById(id); 
				
		return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(() -> 
		new EntityNotFoundException(
				"Aucun article avec l'ID =" + id + "n'ete trouve dans la BBD", 
				ErrorCodes.ARTICLE_NOT_FOUND)
		); 
	}

	
	@Override
	public ArticleDto findByCodeArticle(String codeArticle) {
		if (!StringUtils.hasLength(codeArticle)) {
			log.error("Article CODE is null");
			return null;
		}
		Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle); 
		
		return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(() -> 
		new EntityNotFoundException(
				"Aucun article avec le CODE =" + codeArticle + "n'ete trouve dans la BBD", 
				ErrorCodes.ARTICLE_NOT_FOUND)
		); 
	}

	
	@Override
	public List<ArticleDto> findAll() {
		return articleRepository.findAll().stream()
		 .map(ArticleDto::fromEntity)
		 .collect(Collectors.toList());
	}

	
	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Article ID is null");
			return;
		}
		articleRepository.deleteById(id);
	}

}
