package com.smartcode.data;

/**
 * @author amit
 * 
 */
public class MenuDaoFactory {

	// This is not thread-safe and is used as a quick and dirty implementation for
	// learning web development.
	// In a production system you would use Spring, Hibernate or another framework
	// to do this and more!

	private static MenuDao menuDao;

	public static MenuDao getMenuDao() {
		if (menuDao == null) {
			menuDao = new MenuDao();
		}
		return menuDao;
	}
}
