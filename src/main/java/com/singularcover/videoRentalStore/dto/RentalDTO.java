package com.singularcover.videoRentalStore.dto;

public class RentalDTO {
	
	
	private Integer price;
	private Integer points;
	private Integer totalPoints;
	
	
	
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
	public Integer getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}
	@Override
	public String toString() {
		return "RentalDTO [price=" + price + ", points=" + points + ", totalPoints=" + totalPoints + "]";
	}

}
