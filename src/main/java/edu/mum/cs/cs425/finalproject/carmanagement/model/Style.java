package edu.mum.cs.cs425.finalproject.carmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "styles")
public class Style {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int styleId;
	
	@NotBlank(message = "* Style is required")
	@Column(nullable = false, unique = true)
	private String styleName;

	public Style() {
		
	}
	public Style(@NotBlank(message = "* Style is required") String styleName) {
		super();
		this.styleName = styleName;
	}
	
	
	public int getStyleId() {
		return styleId;
	}
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	
	

}
