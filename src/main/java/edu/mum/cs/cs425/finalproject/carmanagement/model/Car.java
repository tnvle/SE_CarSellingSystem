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
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long carId;
	
	@NotNull(message = "* Year is required")
	@Column(nullable = false)
	private int year;
	
	@NotNull(message = "* Price is required")
	@Column(nullable = false)
	private double price;
	
	@NotNull(message = "* Mileage is required")
	@Column(nullable = false)
	private long mileage;
	
//	@NotBlank(message = "* Image path is required")
//	@Column(nullable = false)
	private String imagePath;

	private String zipCode;
	private LocalDate uploadDate;
	
	@ManyToOne
	@JoinColumn(name = "make_id", nullable = false)
	private Make make;
	
	@ManyToOne
	@JoinColumn(name = "model_id", nullable = false)
	private CarModel carModel;
	
	@ManyToOne
	@JoinColumn(name = "style_id", nullable = false)
	private Style style;
	
	@ManyToOne
	@JoinColumn(name = "condition_id", nullable = false)
	private Condition condition;
	
	@ManyToOne
	@JoinColumn(name = "dealer_id", nullable = false)
	private Dealer dealer;
	
	public Car() {
		
	}

	public Car(@NotNull(message = "* Year is required") int year,
			@NotNull(message = "* Price is required") double price,
			@NotNull(message = "* Mileage is required") long mileage,
			@NotBlank(message = "* Image path is required") String imagePath, Make make, CarModel carModel, Style style,
			Condition condition, String zipCode) {
		super();
		this.year = year;
		this.price = price;
		this.mileage = mileage;
		this.imagePath = imagePath;
		this.make = make;
		this.carModel = carModel;
		this.style = style;
		this.condition = condition;
		this.zipCode = zipCode;
		this.uploadDate = LocalDate.now();
	}

	

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getMileage() {
		return mileage;
	}

	public void setMileage(long mileage) {
		this.mileage = mileage;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Make getMake() {
		return make;
	}

	public void setMake(Make make) {
		this.make = make;
	}

	public CarModel getCarModel() {
		return carModel;
	}

	public void setCarModel(CarModel carModel) {
		this.carModel = carModel;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}
	
	


	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}
}
