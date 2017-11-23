package org.diverproject.themes;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;

public class ThemeColors
{
	public static final ColorUIResource DEFAULT_BACKGROUND = new ColorUIResource(Color.WHITE);
	public static final ColorUIResource DEFAULT_FOREGROUND = new ColorUIResource(Color.BLACK);

	private ColorUIResource background;
	private ColorUIResource foreground;

	public ThemeColors()
	{
		setBackground(DEFAULT_BACKGROUND).setForeground(DEFAULT_FOREGROUND);
	}

	public ColorUIResource getBackground()
	{
		return background;
	}

	public ThemeColors setBackground(ColorUIResource background)
	{
		this.background = background;
		return this;
	}

	public ColorUIResource getForeground()
	{
		return foreground;
	}

	public ThemeColors setForeground(ColorUIResource foreground)
	{
		this.foreground = foreground;
		return this;
	}

	public static Color bright(Color color, int times)
	{
		for (int i = 0; i < times; i++)
			color = color.brighter();

		return color;
	}

	public static Color darker(Color color, int times)
	{
		for (int i = 0; i < times; i++)
			color = color.darker();

		return color;
	}
}
