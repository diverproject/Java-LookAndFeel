package org.diverproject.themes.colors;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;

public class RootPaneColors implements IBoderLinedColors
{
	private ColorUIResource background;
	private ColorUIResource border;
	private ColorUIResource disabledBorder;

	public RootPaneColors(Color background, Color border)
	{
		setBackground(new ColorUIResource(background));
		setBorder(new ColorUIResource(border));
		setDisabledBorder(new ColorUIResource(border.darker()));
	}

	@Override
	public ColorUIResource getBackground()
	{
		return background;
	}

	public void setBackground(ColorUIResource background)
	{
		this.background = background;
	}

	@Override
	public ColorUIResource getBorder()
	{
		return border;
	}

	public void setBorder(ColorUIResource border)
	{
		this.border = border;
	}

	@Override
	public ColorUIResource getDisabledBorder()
	{
		return disabledBorder;
	}

	public void setDisabledBorder(ColorUIResource disabledBorder)
	{
		this.disabledBorder = disabledBorder;
	}
}
