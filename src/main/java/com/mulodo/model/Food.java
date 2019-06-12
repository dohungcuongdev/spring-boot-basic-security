package com.mulodo.model;

public class Food {
	private String name;
	private String serving_size;
	private int calories;
	private int carbs;
	private int fat;
	private int protein;
	private Embedded _embedded;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServing_size() {
		return serving_size;
	}

	public void setServing_size(String serving_size) {
		this.serving_size = serving_size;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getCarbs() {
		return carbs;
	}

	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public Embedded get_embedded() {
		return _embedded;
	}

	public void set_embedded(Embedded _embedded) {
		this._embedded = _embedded;
	}

	public Food() {
	}

	public Food(String name, String serving_size, int calories, int carbs, int fat, int protein, Embedded _embedded) {
		super();
		this.name = name;
		this.serving_size = serving_size;
		this.calories = calories;
		this.carbs = carbs;
		this.fat = fat;
		this.protein = protein;
		this._embedded = _embedded;
	}

	public Embedded getEmbeddedInstance(int manufacturerId, String manufacturerName) {
		Embedded embedded = new Embedded();
		embedded.setManufacturer(new Embedded().new Manufacturer(manufacturerId, manufacturerName));
		return embedded;
	}

	public Food(String name, String serving_size, int calories, int carbs, int fat, int protein, int manufacturerId,
			String manufacturerName) {
		super();
		this.name = name;
		this.serving_size = serving_size;
		this.calories = calories;
		this.carbs = carbs;
		this.fat = fat;
		this.protein = protein;
		this._embedded = getEmbeddedInstance(manufacturerId, manufacturerName);
	}

	@Override
	public String toString() {
		return "Food [name=" + name + ", serving_size=" + serving_size + ", calories=" + calories + ", carbs=" + carbs
				+ ", fat=" + fat + ", protein=" + protein + ", _embedded=" + _embedded + "]";
	}

}

class Embedded {
	private Manufacturer manufacturer;

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "Embedded [manufacturer=" + manufacturer + "]";
	}

	class Manufacturer {
		private int id;
		private String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Manufacturer(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

		public Manufacturer() {
			super();
		}

		@Override
		public String toString() {
			return "Manufacturer [id=" + id + ", name=" + name + "]";
		}

	}
}
