package org.diverproject.themes;

import javax.swing.UIDefaults;

public class ComponentProperties
{
	private ComponentPropertie menuBar;
	private ComponentPropertie menu;
	private ComponentPropertie popupMenu;

	public ComponentProperties()
	{
		menuBar = new ComponentPropertie("MenuBar");
		menu = new ComponentPropertie("Menu");
		popupMenu = new ComponentPropertie("PopupMenu");
	}

	public void setUIDefaults(UIDefaults uiDefaults)
	{
		menuBar.uiDefaults = uiDefaults;
		menu.uiDefaults = uiDefaults;
		popupMenu.uiDefaults = uiDefaults;
	}

	public ComponentPropertie getMenuBar()
	{
		return menuBar;
	}

	public ComponentPropertie getMenu()
	{
		return menu;
	}

	public ComponentPropertie getPopupMenu()
	{
		return popupMenu;
	}
}
