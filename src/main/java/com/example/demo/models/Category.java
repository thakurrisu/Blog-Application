package com.example.demo.models;





import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name = "Title", nullable = false)
	@NotBlank(message = "Title can not be null")
	private String categoryTitle;
	
	@Column(name = "Description")
	@jakarta.validation.constraints.NotBlank(message = "Description String Cannot be Null")
	private String descriptionString;
	
}
