package pl.edu.agh.dronka.shop.model.util;

import pl.edu.agh.dronka.shop.model.Item;

import java.util.LinkedHashMap;
import java.util.Map;

public class PropertiesHelper {

	public static Map<String, Object> getPropertiesMap(Item item) {
		Map<String, Object> propertiesMap = new LinkedHashMap<>();
		
		propertiesMap.put("Nazwa", item.getName());
		propertiesMap.put("Cena", item.getPrice());
		propertiesMap.put("Kategoria", item.getCategory().getDisplayName()); 
		propertiesMap.put("Ilość", Integer.toString(item.getQuantity()));
		propertiesMap.put("Tanie bo polskie", item.isPolish());
		propertiesMap.put("Używany", item.isSecondhand());
		propertiesMap.putAll(item.getAdditionalProperties());
		
		return propertiesMap;
	}
}
