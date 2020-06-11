package com.remd.spring.model;

public class MedicinePrescription {
	private String generalMedicineName;
	private String brandedMedicineName;
	private String recommendedDosage;
	private String medicineNotes;
	public MedicinePrescription() {
		this.generalMedicineName = "";
		this.brandedMedicineName = "";
		this.recommendedDosage = "";
		this.medicineNotes = "";
	}
	
	public MedicinePrescription(String generalMedicineName, String brandedMedicineName, String recommendedDosage,
			String medicineNotes) {
		this.generalMedicineName = generalMedicineName;
		this.brandedMedicineName = brandedMedicineName;
		this.recommendedDosage = recommendedDosage;
		this.medicineNotes = medicineNotes;
	}

	public String getGeneralMedicineName() {
		return generalMedicineName;
	}
	public void setGeneralMedicineName(String generalMedicineName) {
		this.generalMedicineName = generalMedicineName;
	}
	public String getBrandedMedicineName() {
		return brandedMedicineName;
	}
	public void setBrandedMedicineName(String brandedMedicineName) {
		this.brandedMedicineName = brandedMedicineName;
	}
	public String getRecommendedDosage() {
		return recommendedDosage;
	}
	public void setRecommendedDosage(String recommendedDosage) {
		this.recommendedDosage = recommendedDosage;
	}
	public String getMedicineNotes() {
		return medicineNotes;
	}
	public void setMedicineNotes(String medicineNotes) {
		this.medicineNotes = medicineNotes;
	}
	
	
}
