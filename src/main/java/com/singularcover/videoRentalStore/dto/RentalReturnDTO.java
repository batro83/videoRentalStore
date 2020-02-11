package com.singularcover.videoRentalStore.dto;

public class RentalReturnDTO {

	private Integer surcharges;

	public RentalReturnDTO(Integer surcharges) {
		this.surcharges = surcharges;
	}

	public RentalReturnDTO() {
		super();
	}

	public Integer getSurcharges() {
		return surcharges;
	}

	public void setSurcharges(Integer surcharges) {
		this.surcharges = surcharges;
	}

}
