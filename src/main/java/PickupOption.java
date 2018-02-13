package main.java;

import java.util.Date;

public class PickupOption {
	
	private String name;
	private boolean checked;
	private double price;
	
	public PickupOption(String name2, double price2) {
		name = name2;
		price = price2;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
