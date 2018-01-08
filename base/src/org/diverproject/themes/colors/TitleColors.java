package org.diverproject.themes.colors;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;

public class TitleColors
{
	private ColorUIResource activeForeground;
	private ColorUIResource activeBackground;
	private ColorUIResource inactiveForeground;
	private ColorUIResource inactiveBackground;

	public TitleColors(Color foreground, Color background)
	{
		activeForeground = new ColorUIResource(foreground);
		activeBackground = new ColorUIResource(background.brighter());
		inactiveForeground = new ColorUIResource(activeForeground.darker());
		inactiveBackground = new ColorUIResource(activeBackground.darker());
	}

	public ColorUIResource getActiveForeground()
	{
		return activeForeground;
	}

	public void setActiveForeground(ColorUIResource activeForeground)
	{
		this.activeForeground = activeForeground;
	}

	public ColorUIResource getActiveBackground()
	{
		return activeBackground;
	}

	public void setActiveBackground(ColorUIResource activeBackground)
	{
		this.activeBackground = activeBackground;
	}

	public ColorUIResource getInactiveForeground()
	{
		return inactiveForeground;
	}

	public void setInactiveForeground(ColorUIResource inactiveForeground)
	{
		this.inactiveForeground = inactiveForeground;
	}

	public ColorUIResource getInactiveBackground()
	{
		return inactiveBackground;
	}

	public void setInactiveBackground(ColorUIResource inactiveBackground)
	{
		this.inactiveBackground = inactiveBackground;
	}
}
