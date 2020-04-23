package pl.edu.agh.dronka.shop.model.filter;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.Item;
import pl.edu.agh.dronka.shop.model.ItemFactory;

public class ItemFilter {

	private Item itemSpec;

	public ItemFilter(Category cat){
		this.itemSpec = ItemFactory.getItem(cat);
	}

	public Item getItemSpec() {
		return itemSpec;
	}
	public boolean appliesTo(Item item) {
		if (itemSpec.getName() != null
				&& !itemSpec.getName().equals(item.getName())) {
			return false;
		}
		if (itemSpec.getCategory() != null
				&& !itemSpec.getCategory().equals(item.getCategory())) {
			return false;
		}

		var getters = itemSpec.getPropertiesGetters();
		var getters_toCheck = item.getPropertiesGetters();
		for (String displayName : getters.keySet()) {
			var itemSpecValue = getters.get(displayName).get();
			var itemToCheckValue = getters_toCheck.get(displayName).get();
			if(itemSpecValue && !itemToCheckValue){
				return false;
			}
		}

		return true;
	}
}