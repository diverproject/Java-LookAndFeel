package org.diverproject.themes.colors;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;

public class TextColors
{
	private ColorUIResource background;
	private ColorUIResource foreground;
	private ColorUIResource hightlightBackground;
	private ColorUIResource hightlightForeground;
	private ColorUIResource disabledForeground;

	public TextColors(Color textColor, Color backgroundColor)
	{
		setBackground(new ColorUIResource(backgroundColor));
		setForeground(new ColorUIResource(textColor));
		setHightlightBackground(new ColorUIResource(backgroundColor));
		setHightlightForeground(new ColorUIResource(textColor.brighter()));
		setDisabledForeground(new ColorUIResource(textColor.darker()));
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

	public ColorUIResource getHightlightBackground()
	{
		return hightlightBackground;
	}

	public void setHightlightBackground(ColorUIResource hightlightBackground)
	{
		this.hightlightBackground = hightlightBackground;
	}

	public ColorUIResource getHightlightForeground()
	{
		return hightlightForeground;
	}

	public void setHightlightForeground(ColorUIResource hightlightForeground)
	{
		this.hightlightForeground = hightlightForeground;
	}

	public ColorUIResource getDisabledForeground()
	{
		return disabledForeground;
	}

	public void setDisabledForeground(ColorUIResource disabledForeground)
	{
		this.disabledForeground = disabledForeground;
	}
}
