package com.tn.GestiondeStock.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Category")

public class Category extends AbstractEntity {

	@Column(name = "codeacategory")
	private String codeCategory;
	
	@Column(name = "designation")
	private String designation;
	
	@OneToMany(mappedBy = "category")
	private List<Article> articles;
}
