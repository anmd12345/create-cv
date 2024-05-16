package com.tdmu.entity;

import java.io.Serializable;

import java.sql.Date;

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
@Table(name = "experiences")
public class Experiences implements Serializable {

	private static final long serialVersionUID = -5769443851515615218L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	@JoinColumn(name = "cvId", referencedColumnName = "id")
	private CV cv;

	@Column
	private String experiencesName;

	@Column
	private String description;

	@Column
	private String addressWork;

	@Column
	private Date startTime;

	@Column
	private Date endTime;

	@Column
	private Boolean isDeleted;

}
