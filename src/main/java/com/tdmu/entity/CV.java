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
@Table(name = "cv")
public class CV implements Serializable {

	private static final long serialVersionUID = -3628855922082696636L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	private User user;

	@Column
	private String cvName;

	@Column
	private String avatar;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String viTriUngTuyen;

	@Column
	private String about;

	@Column
	private String address;

	@Column
	private String phone;

	@Column
	private String email;

	@Column
	private String imgSrc;

	@Column
	private Boolean isDeleted;

}
