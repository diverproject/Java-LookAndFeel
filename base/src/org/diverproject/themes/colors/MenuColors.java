package org.diverproject.themes.colors;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;

public class MenuColors
{
	private ColorUIResource background;
	private ColorUIResource foreground;
	private ColorUIResource selectedBackground;
	private ColorUIResource selectedForeground;
	private ColorUIResource disabledBackground;
	private ColorUIResource disabledForeground;
	private ColorUIResource borderBrighter;
	private ColorUIResource borderDarker;

	public MenuColors(Color textColor, Color backgroundColor)
	{
		setBackground(new ColorUIResource(backgroundColor));
		setForeground(new ColorUIResource(textColor));
		setSelectedBackground(new ColorUIResource(backgroundColor.darker()));
		setForeground(new ColorUIResource(textColor));
		setDisabledBackground(new ColorUIResource(backgroundColor.darker()));
		setForeground(new ColorUIResource(background.brighter().brighter()));
		setBorderBrighter(new ColorUIResource(background.brighter()));
		setBorderDarker(new ColorUIResource(background.darker()));
	}

	public ColorUIResource getBackground()
	{
		return background;
	}

	public void setBackground(ColorUIResource Background)
	{
		this.background = Background;
	}

	public ColorUIResource getForeground()
	{
		return foreground;
	}

	public void setForeground(ColorUIResource Foreground)
	{
		this.foreground = Foreground;
	}

	public ColorUIResource getSelectedBackground()
	{
		return selectedBackground;
	}

	public void setSelectedBackground(ColorUIResource SelectedBackground)
	{
		this.selectedBackground = SelectedBackground;
	}

	public ColorUIResource getSelectedForeground()
	{
		return selectedForeground;
	}

	public void setSelectedForeground(ColorUIResource SelectedForeground)
	{
		this.selectedForeground = SelectedForeground;
	}

	public ColorUIResource getDisabledBackground()
	{
		return disabledBackground;
	}

	public void setDisabledBackground(ColorUIResource DisabledBackground)
	{
		this.disabledBackground = DisabledBackground;
	}

	public ColorUIResource getDisabledForeground()
	{
		return disabledForeground;
	}

	public void setDisabledForeground(ColorUIResource disabledForeground)
	{
		this.disabledForeground = disabledForeground;
	}

	public ColorUIResource getBorderBrighter()
	{
		return borderBrighter;
	}

	public void setBorderBrighter(ColorUIResource borderBrighter)
	{
		this.borderBrighter = borderBrighter;
	}

	public ColorUIResource getBorderDarker()
	{
		return borderDarker;
	}

	public void setBorderDarker(ColorUIResource borderDarker)
	{
		this.borderDarker = borderDarker;
	}
}
