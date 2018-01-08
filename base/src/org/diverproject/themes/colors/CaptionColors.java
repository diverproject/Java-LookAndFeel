package org.diverproject.themes.colors;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;

import org.diverproject.themes.ThemeColors;

public class CaptionColors
{
	private ColorUIResource background;
	private ColorUIResource foreground;
	private ColorUIResource border;
	private ColorUIResource inactiveBackground;
	private ColorUIResource inactiveForeground;
	private ColorUIResource inactiveBorder;

	public CaptionColors(Color textcolor, Color background)
	{
		setBackground(new ColorUIResource(background));
		setForeground(new ColorUIResource(textcolor));
		setBorder(new ColorUIResource(background.darker()));
		setInactiveBackground(new ColorUIResource(ThemeColors.darker(background, 25f)));
		setInactiveForeground(new ColorUIResource(ThemeColors.darker(textcolor, 25f)));
		setInactiveBorder(new ColorUIResource(ThemeColors.darker(background.darker(), 25f)));
	}

	public ColorUIResource getBackground()
	{
		return background;
	}

	public void setBackground(ColorUIResource background)
	{
		this.background = background;
	}

	public ColorUIResource getForeground()
	{
		return foreground;
	}

	public void setForeground(ColorUIResource foreground)
	{
		this.foreground = foreground;
	}

	public ColorUIResource getBorder()
	{
		return border;
	}

	public void setBorder(ColorUIResource border)
	{
		this.border = border;
	}

	public ColorUIResource getInactiveBackground()
	{
		return inactiveBackground;
	}

	public void setInactiveBackground(ColorUIResource inactiveBackground)
	{
		this.inactiveBackground = inactiveBackground;
	}

	public ColorUIResource getInactiveForeground()
	{
		return inactiveForeground;
	}

	public void setInactiveForeground(ColorUIResource inactiveForeground)
	{
		this.inactiveForeground = inactiveForeground;
	}

	public ColorUIResource getInactiveBorder()
	{
		return inactiveBorder;
	}

	public void setInactiveBorder(ColorUIResource inactiveBorder)
	{
		this.inactiveBorder = inactiveBorder;
	}
}
