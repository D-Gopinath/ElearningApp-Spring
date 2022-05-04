package com.elearningapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="course")
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="c_id")
	private Integer id;
	
	@Column(name="course_name")
	private String cName;
	
	private String tutor;
	
	private String duration;
	
	private String url;
	
	@Column(name="image_url")
	private String imageurl;

	public Course(String cName, String tutor, String duration, String imageurl) {
		this.cName = cName;
		this.tutor = tutor;
		this.duration = duration;
		this.imageurl = imageurl;
	}
}
