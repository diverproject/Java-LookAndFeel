package org.diverproject.themes.colors;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;

public class WindowColors
{
	private ColorUIResource background;
	private ColorUIResource foreground;
	private ColorUIResource border;

	public WindowColors(Color textcolor, Color background)
	{
		setBackground(new ColorUIResource(background));
		setForeground(new ColorUIResource(textcolor));
		setBorder(new ColorUIResource(background.darker()));
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
}
