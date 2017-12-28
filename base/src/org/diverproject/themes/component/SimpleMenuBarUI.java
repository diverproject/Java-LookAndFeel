package org.diverproject.themes.component;

import static org.diverproject.themes.component.ThemesUIFunctions.*;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.plaf.basic.BasicMenuBarUI;

public class SimpleMenuBarUI extends BasicMenuBarUI
{
	public static SimpleMenuBarUI createUI(JComponent c)
	{
		return new SimpleMenuBarUI();
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
