package com.tn.GestiondeStock.entities;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import sun.util.calendar.BaseCalendar;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	//@CreatedDate
	@Column(name = "creationDate")
	private Instant creationDate;
	
	//@LastModifiedDate
	@Column(name = "lastModifiedDate")
	private Instant lastModifiedDate;


	@PrePersist
	void prePersist() {
		creationDate = Instant.now();
	}

	@PreUpdate
	void preUpdate() {
		lastModifiedDate = Instant.now();
	}
}
