package edu.mum.cs.cs425.finalproject.carmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "models")
public class CarModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int carModelId;
	
	@NotBlank(message = "* Model is required")
	@Column(nullable = false, unique = true)
	private String carModelName;
	
	@ManyToOne
	@JoinColumn(name = "make_id", nullable = false)
	private Make make;
	
	public CarModel() {
		
	}

	public CarModel(@NotBlank(message = "* Model is required") String carModelName, Make make) {
		super();
		this.carModelName = carModelName;
		this.make = make;
	}

	

	public int getCarModelId() {
		return carModelId;
	}

	public void setCarModelId(int carModelId) {
		this.carModelId = carModelId;
	}

	public String getCarModelName() {
		return carModelName;
	}

	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
	}

	public Make getMake() {
		return make;
	}

	public void setMake(Make make) {
		this.make = make;
	}
	
	

}
