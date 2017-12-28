package org.diverproject.themes.component;

import org.diverproject.themes.AbstractLookAndFeel;
import org.diverproject.themes.AbstractTheme;
import org.diverproject.themes.DiverProjectTheme;
import org.diverproject.themes.colors.MenuColors;

public class ThemesUIFunctions
{
	public static AbstractLookAndFeel LookAndFeel()
	{
		return DiverProjectTheme.getCurrentLookAndFeel();
	}

	public static AbstractTheme Theme()
	{
		return DiverProjectTheme.getCurrentLookAndFeel().getTheme();
	}

	public static MenuColors MenuColors()
	{
		return DiverProjectTheme.getCurrentLookAndFeel().getTheme().getMenuColors();
	}

	public static MenuColors MenuItemColors()
	{
		return DiverProjectTheme.getCurrentLookAndFeel().getTheme().getMenuItemColors();
	}
}
