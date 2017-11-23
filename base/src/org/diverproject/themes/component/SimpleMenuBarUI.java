package org.diverproject.themes.component;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.plaf.basic.BasicMenuBarUI;

import org.diverproject.themes.AbstractLookAndFeel;
import org.diverproject.themes.AbstractTheme;
import org.diverproject.themes.DiverProjectTheme;
import org.diverproject.themes.colors.MenuColors;

public class SimpleMenuBarUI extends BasicMenuBarUI
{
	public static SimpleMenuBarUI createUI(JComponent c)
	{
		return new SimpleMenuBarUI();
	}

	public AbstractLookAndFeel LookAndFeel()
	{
		return DiverProjectTheme.getCurrentLookAndFeel();
	}

	public AbstractTheme Theme()
	{
		return DiverProjectTheme.getCurrentLookAndFeel().getTheme();
	}

	public MenuColors Colors()
	{
		return DiverProjectTheme.getCurrentLookAndFeel().getTheme().getMenuColors();
	}

	@Override
	public void installUI(JComponent c)
	{
		super.installUI(c);

		if (c instanceof JMenuBar)
			((JMenuBar) c).setBorder(LookAndFeel().getBorderFactory().getMenuBarBorder());
	}

	@Override
	public void paint(Graphics g, JComponent c)
	{
		g.setColor(c.getBackground() == null ? Theme().getMenuBackground() : c.getBackground());
		g.fillRect(0, 0, c.getWidth(), c.getHeight());
	}
}
