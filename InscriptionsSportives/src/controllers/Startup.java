package controllers;

import java.util.ResourceBundle;

import views.Console.MenuCL;
import views.IHM.FenetreWelcome;

public class Startup {

	public static void main(String[] args)
	{
		Application.start();
		Application.importDataFromDatabase();
		
		ResourceBundle config = ResourceBundle.getBundle("config.config");
		
		if( config.getString("application.mode").equals("ihm") ) {
			FenetreWelcome fenetreParent = new FenetreWelcome(Application.getApplication());
			fenetreParent.setVisible(true);
		}else {
			MenuCL menu = new MenuCL();
			menu.homeMenu().start();
		}
	}
}
