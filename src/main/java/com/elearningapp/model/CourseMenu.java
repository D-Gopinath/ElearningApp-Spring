package com.elearningapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="course")
public class CourseMenu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="c_id")
	private Integer id;
	
	@Column(name="course_type")
	private String category;
	
	@Column(name="image_url")
	private String image;

	public CourseMenu(Integer id, String category, String image) {
		this.id = id;
		this.category = category;
		this.image = image;
	}

}
