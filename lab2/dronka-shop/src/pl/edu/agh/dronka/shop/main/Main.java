package pl.edu.agh.dronka.shop.main;

import pl.edu.agh.dronka.shop.controller.ShopController;
import pl.edu.agh.dronka.shop.model.provider.ShopProvider;
import pl.edu.agh.dronka.shop.view.ShopFrame;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e) {
				
				e.printStackTrace();
				return;
			}
	
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ShopController shopController = new ShopController();
				shopController.setModel(ShopProvider.getExampleShop());
				ShopFrame shopFrame = new ShopFrame(shopController);
				shopController.logIn(ShopProvider.getExampleUser());
				shopFrame
						.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				
				shopFrame.setIconImage(new ImageIcon("resources/images/dronka.jpeg").getImage());
				shopFrame.setVisible(true);
			}
		});
	}
}
