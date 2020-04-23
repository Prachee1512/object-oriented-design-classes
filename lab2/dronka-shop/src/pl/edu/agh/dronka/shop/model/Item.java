package pl.edu.agh.dronka.shop.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Item {
	private String name;
	private Category category;
	private int price;
	private int quantity;
	private boolean secondhand;
	private boolean polish;

	protected Map<String, Consumer<Boolean>> booleanSettersMap = new HashMap<>();
	protected Map<String, Supplier<Boolean>> booleanGettersMap = new HashMap<>();

	public Item(String name, Category category, int price, int quantity) {
		this();
		this.name = name;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}

	public Item() {
		booleanSettersMap.put("Tanie bo polskie", this::setPolish);
		booleanGettersMap.put("Tanie bo polskie", this::isPolish);

		booleanSettersMap.put("Używany", this::setSecondhand);
		booleanGettersMap.put("Używany", this::isSecondhand);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public int getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setSecondhand(boolean secondhand) {
		this.secondhand = secondhand;
	}

	public boolean isSecondhand() {
		return secondhand;
	}

	public void setPolish(boolean polish) {
		this.polish = polish;
	}

	public boolean isPolish() {
		return polish;
	}

	@Override
	public String toString() {
		return getName();
	}

	public abstract Map<String, Object> getAdditionalProperties();

	public Map<String, Consumer<Boolean>> getPropertiesSetters(){
		return booleanSettersMap;
	}
	public Map<String, Supplier<Boolean>> getPropertiesGetters(){
		return booleanGettersMap;
	}
}
