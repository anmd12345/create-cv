package com.tdmu.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "skill")
public class Skill implements Serializable {
	private static final long serialVersionUID = -1669425080928922788L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	@JoinColumn(name = "cvId", referencedColumnName = "id")
	private CV cv;

	@Column
	private String skillName;

	@Column
	private String icon;

	@Column
	private String level;

	@Column
	private Boolean isDeleted;
}
