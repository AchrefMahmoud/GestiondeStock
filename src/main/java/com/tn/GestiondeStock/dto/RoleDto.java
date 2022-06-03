package com.tn.GestiondeStock.dto;


import com.tn.GestiondeStock.entities.Role;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class RoleDto {

	private Integer id;

	private String roleNom;
	
	private UtilisateurDto utilisateur;

	
	
	public static RoleDto fromEntity(Role role) {
		if (role == null) {
			return null;
	}
	
		return RoleDto.builder()
				.id(role.getId())
				.roleNom(role.getRoleNom())
				.utilisateur(UtilisateurDto.fromEntity(role.getUtilisateur()))
				.build();
	}
	
	
				
	public Role toEntity(RoleDto roleDto) {
		if (roleDto == null) {
			return null;
			//TODO throw an exception 
		}
		Role role = new Role();
		role.setId(roleDto.getId());
		role.setRoleNom(roleDto.getRoleNom());
		role.setId(roleDto.getId());
		
	return role;
	}
}
