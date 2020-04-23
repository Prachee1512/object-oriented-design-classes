package pl.edu.agh.dronka.shop.view;

import pl.edu.agh.dronka.shop.controller.ShopController;
import pl.edu.agh.dronka.shop.model.filter.ItemFilter;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PropertiesPanel extends JPanel {

	private static final long serialVersionUID = -2804446079853846996L;
	private ShopController shopController;

	public PropertiesPanel(ShopController shopController) {
		this.shopController = shopController;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	public void fillProperties() {
		removeAll();

		ItemFilter filter = new ItemFilter(shopController.getCurrentCategory());
		filter.getItemSpec().setCategory(shopController.getCurrentCategory());

		var setters = filter.getItemSpec().getPropertiesSetters();
		for (String displayName : setters.keySet()) {
			add(createPropertyCheckbox(displayName, event -> {
				setters.get(displayName).accept(((JCheckBox) event.getSource()).isSelected());

				shopController.filterItems(filter);
			}));
		}
	}

	private JCheckBox createPropertyCheckbox(String propertyName,
			ActionListener actionListener) {

		JCheckBox checkBox = new JCheckBox(propertyName);
		checkBox.setSelected(false);
		checkBox.addActionListener(actionListener);

		return checkBox;
	}

}
