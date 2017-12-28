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

	public static Color brighter(Color color, double percentage)
	{
		if (color == null)
			return null;

		double r = color.getRed();
		double g = color.getGreen();
		double b = color.getBlue();

		double rd = 255.0 - r;
		double gd = 255.0 - g;
		double bd = 255.0 - b;

		r += (rd * percentage) / 100.0;
		g += (gd * percentage) / 100.0;
		b += (bd * percentage) / 100.0;

		return createColor((int) r, (int) g, (int) b);
	}

	public static Color darker(Color color, double percentage)
	{
		if (color == null)
			return null;

		double r = color.getRed();
		double g = color.getGreen();
		double b = color.getBlue();

		r -= (r * percentage) / 100.0;
		g -= (g * percentage) / 100.0;
		b -= (b * percentage) / 100.0;

		return createColor((int) r, (int) g, (int) b);
	}

	public static Color createColor(int r, int g, int b)
	{
		return new Color(((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF)));
	}
}
