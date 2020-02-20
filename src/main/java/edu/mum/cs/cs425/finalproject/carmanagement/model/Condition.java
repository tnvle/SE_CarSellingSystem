package edu.mum.cs.cs425.finalproject.carmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "conditions")
public class Condition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int conditionId;
	
	@NotBlank(message = "* Condition is required")
	@Column(nullable = false, unique = true)
	private String conditionName;
	
	public Condition() {
		
	}

	public Condition(@NotBlank(message = "* Condition is required") String conditionName) {
		super();
		this.conditionName = conditionName;
	}

	

	public int getConditionId() {
		return conditionId;
	}

	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	
	

}
