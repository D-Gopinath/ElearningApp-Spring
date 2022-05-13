package com.elearningapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="courses_list")
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="course_id")
	private Integer cId;
	
	@Column(name="course_type")
	private String category;
	
	@Column(name="course_name")
	private String cName;
	
	private String tutor;
	
	private String duration;
	@Column(name="video_url")
	private String url;
	
	@Column(name="image_url")
	private String imageurl;
	
	private String description;

	public Course(Integer id, String category, String cName, String tutor, String duration, String imageurl,
			String description) {
		this.id = id;
		this.category = category;
		this.cName = cName;
		this.tutor = tutor;
		this.duration = duration;
		this.imageurl = imageurl;
		this.description = description;
	}

	public Course(String cName, String tutor, String duration, String url, String description) {
		this.cName = cName;
		this.tutor = tutor;
		this.duration = duration;
		this.url = url;
		this.description = description;
	}
	
	

	
}
