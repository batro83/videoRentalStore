package com.singularcover.videoRentalStore.dto;

public class RentalDTO {
	
	
	private Integer price;
	private Integer points;	
	
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	@Override
	public String toString() {
		return "RentalDTO [price=" + price + ", points=" + points + "]";
	}

}
