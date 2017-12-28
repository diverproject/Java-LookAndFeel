package org.diverproject.themes;

import javax.swing.UIDefaults;

public class ComponentProperties
{
	private ComponentPropertie desktop; // Background color of desktop
	private ComponentPropertie activeCaption; // Background color for captions
	private ComponentPropertie activeCaptionText; // Text color for captions
	private ComponentPropertie activeCaptionBorder; // Border color for caption text
	private ComponentPropertie inactiveCaption; // Background color for inactive captions
	private ComponentPropertie inactiveCaptionText; // Text color for inactive captions
	private ComponentPropertie inactiveCaptionBorder; // Border color for inactive captions

	private ComponentPropertie window; // Background for windows
	private ComponentPropertie windowBorder; // Color of window border frame
	private ComponentPropertie windowText; // Text color inside windows

	private ComponentPropertie control; // Background color for controls
	private ComponentPropertie controlText; // Text color for controls
	private ComponentPropertie controlLtHighlight; // Light highlight color for controls
	private ComponentPropertie controlHighlight; // Highlight color for controls
	private ComponentPropertie controlShadow; // Shadow color for controls
	private ComponentPropertie controlDkShadow; // Dark shadow color for controls

	private ComponentPropertie text; // Background color for text
	private ComponentPropertie textText; // Text color for text
	private ComponentPropertie textInactiveText; // Text color for inactive controls
	private ComponentPropertie textHighlight; // Background color for highlighted text
	private ComponentPropertie textHighlightText; // Text color for highlighted text

	private ComponentPropertie scrollbar; //  Background color for scrollbars
	private ComponentPropertie info; //  Background color for spot-help text
	private ComponentPropertie infoText; //  Text color for spot-help text

	private ComponentPropertie rootPane; // Painel principal (Janela)
	private ComponentPropertie menuBar;
	private ComponentPropertie menu; // Background for menus
	private ComponentPropertie menuText; // Text color for menus
	private ComponentPropertie popupMenu;
	private ComponentPropertie menuItem;
	private ComponentPropertie popupMenuItem;

	public ComponentProperties()
	{
		desktop = new ComponentPropertie("desktop");
		activeCaption = new ComponentPropertie("activeCaption");
		activeCaptionText = new ComponentPropertie("activeCaptionText");
		activeCaptionBorder = new ComponentPropertie("activeCaptionBorder");
		inactiveCaption = new ComponentPropertie("inactiveCaption");
		inactiveCaptionText = new ComponentPropertie("inactiveCaptionText");
		inactiveCaptionBorder = new ComponentPropertie("inactiveCaptionBorder");

		control = new ComponentPropertie("control");
		controlText = new ComponentPropertie("controlText");
		controlLtHighlight = new ComponentPropertie("controlLtHighlight");
		controlHighlight = new ComponentPropertie("controlHighlight");
		controlShadow = new ComponentPropertie("controlShadow");
		controlDkShadow = new ComponentPropertie("controlDkShadow");

		window = new ComponentPropertie("window");
		windowBorder = new ComponentPropertie("windowBorder");
		windowText = new ComponentPropertie("windowText");

		scrollbar = new ComponentPropertie("scrollbar");
		info = new ComponentPropertie("info");
		infoText = new ComponentPropertie("infoText");

		text = new ComponentPropertie("text");
		textText = new ComponentPropertie("textText");
		textHighlight = new ComponentPropertie("textHighlight");
		textHighlightText = new ComponentPropertie("textHighlightText");
		textInactiveText = new ComponentPropertie("textInactiveText");

		rootPane = new ComponentPropertie("RootPane");
		menuBar = new ComponentPropertie("MenuBar");
		menu = new ComponentPropertie("Menu");
		popupMenu = new ComponentPropertie("PopupMenu");
		menuItem = new ComponentPropertie("MenuItem");
		popupMenuItem = new ComponentPropertie("PopupMenuItem");
	}

	public void setUIDefaults(UIDefaults uiDefaults)
	{
		desktop.uiDefaults = uiDefaults;
		activeCaption.uiDefaults = uiDefaults;
		activeCaptionText.uiDefaults = uiDefaults;
		activeCaptionBorder.uiDefaults = uiDefaults;
		inactiveCaption.uiDefaults = uiDefaults;
		inactiveCaptionText.uiDefaults = uiDefaults;
		inactiveCaptionBorder.uiDefaults = uiDefaults;

		control.uiDefaults = uiDefaults;
		controlText.uiDefaults = uiDefaults;
		controlLtHighlight.uiDefaults = uiDefaults;
		controlHighlight.uiDefaults = uiDefaults;
		controlShadow.uiDefaults = uiDefaults;
		controlDkShadow.uiDefaults = uiDefaults;

		window.uiDefaults = uiDefaults;
		windowBorder.uiDefaults = uiDefaults;
		windowText.uiDefaults = uiDefaults;

		text.uiDefaults = uiDefaults;
		textText.uiDefaults = uiDefaults;
		textHighlight.uiDefaults = uiDefaults;
		textHighlightText.uiDefaults = uiDefaults;
		textInactiveText.uiDefaults = uiDefaults;

		scrollbar.uiDefaults = uiDefaults;
		info.uiDefaults = uiDefaults;
		infoText.uiDefaults = uiDefaults;

		rootPane.uiDefaults = uiDefaults;
		menuBar.uiDefaults = uiDefaults;
		menu.uiDefaults = uiDefaults;
		popupMenu.uiDefaults = uiDefaults;
		menuItem.uiDefaults = uiDefaults;
		popupMenuItem.uiDefaults = uiDefaults;
	}

	public ComponentPropertie getDesktop()
	{
		return desktop;
	}

	public ComponentPropertie getActiveCaption()
	{
		return activeCaption;
	}

	public ComponentPropertie getActiveCaptionText()
	{
		return activeCaptionText;
	}

	public ComponentPropertie getActiveCaptionBorder()
	{
		return activeCaptionBorder;
	}

	public ComponentPropertie getInactiveCaption()
	{
		return inactiveCaption;
	}

	public ComponentPropertie getInactiveCaptionText()
	{
		return inactiveCaptionText;
	}

	public ComponentPropertie getInactiveCaptionBorder()
	{
		return inactiveCaptionBorder;
	}

	public ComponentPropertie getWindow()
	{
		return window;
	}

	public ComponentPropertie getWindowBorder()
	{
		return windowBorder;
	}

	public ComponentPropertie getWindowText()
	{
		return windowText;
	}

	public ComponentPropertie getText()
	{
		return text;
	}

	public ComponentPropertie getTextText()
	{
		return textText;
	}

	public ComponentPropertie getTextHighlight()
	{
		return textHighlight;
	}

	public ComponentPropertie getTextHighlightText()
	{
		return textHighlightText;
	}

	public ComponentPropertie getTextInactiveText()
	{
		return textInactiveText;
	}

	public ComponentPropertie getRootPane()
	{
		return rootPane;
	}

	public ComponentPropertie getMenuBar()
	{
		return menuBar;
	}

	public ComponentPropertie getMenu()
	{
		return menu;
	}

	public ComponentPropertie getMenuText()
	{
		return menuText;
	}

	public ComponentPropertie getPopupMenu()
	{
		return popupMenu;
	}

	public ComponentPropertie getMenuItem()
	{
		return menuItem;
	}

	public ComponentPropertie getPopupMenuItem()
	{
		return popupMenuItem;
	}
}
