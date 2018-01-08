package org.diverproject.themes.component;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicMenuItemUI;

import org.diverproject.themes.AbstractLookAndFeel;
import org.diverproject.themes.AbstractTheme;
import org.diverproject.themes.DiverProjectTheme;
import org.diverproject.themes.colors.MenuColors;

public class SimpleMenuItemUI extends BasicMenuItemUI
{
	public static SimpleMenuItemUI createUI(JComponent c)
	{
		return new SimpleMenuItemUI();
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
		return DiverProjectTheme.getCurrentLookAndFeel().getTheme().getMenuItemColors();
	}

	@Override
	public void installUI(JComponent c)
	{
		super.installUI(c);
		c.setOpaque(false);
	}

	@Override
	public void uninstallUI(JComponent c)
	{
		c.setOpaque(true);
		super.uninstallUI(c);
	}

	@Override
	public void update(Graphics g, JComponent c)
	{
		paintBackground(g, menuItem, 0, 0, c.getWidth(), c.getHeight());
		paint(g, c);
	}

	@Override
	protected void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor)
	{
		if (menuItem.isOpaque())
			paintBackground(g, menuItem, 0, 0, menuItem.getWidth(), menuItem.getHeight());
	}

	private void paintBackground(Graphics g, JComponent c, int x, int y, int width, int height)
	{
		JMenuItem mi = (JMenuItem) c;
		ButtonModel model = mi.getModel();

		if (!model.isEnabled())
		{
			g.setColor(Colors().getDisabledBackground());
			g.fillRect(0, 0, width, height);
		}

		else if (model.isArmed() || model.isRollover() || model.isSelected())
		{
			g.setColor(Colors().getSelectedBackground());
			g.fillRect(0, 0, width, height);
		}

		else
		{
			g.setColor(Colors().getBackground());
			g.fillRect(0, 0, width, height);
		}
	}

	@Override
	protected void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text)
	{
		Graphics2D g2D = (Graphics2D) g;
		ButtonModel model = menuItem.getModel();
		FontMetrics fm = g2D.getFontMetrics();
		int mnemIndex = menuItem.getDisplayedMnemonicIndex();

		Object currentRenderingHint = g2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
		{
			g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			if (!model.isEnabled())
			{
				g2D.setColor(Colors().getDisabledForeground().brighter());
				BasicGraphicsUtils.drawStringUnderlineCharAt(g2D, text, mnemIndex, textRect.x,  textRect.y + fm.getAscent());
				g2D.setColor(Colors().getDisabledForeground().darker());
				BasicGraphicsUtils.drawStringUnderlineCharAt(g2D, text, mnemIndex, textRect.x - 1,  textRect.y + fm.getAscent() - 1);
			}
			else
			{
				if (model.isArmed() || model.isRollover() || model.isSelected())
					g2D.setColor(Colors().getSelectedForeground());
				else
					g2D.setColor(Colors().getForeground());

				BasicGraphicsUtils.drawStringUnderlineCharAt(g2D, text, mnemIndex, textRect.x,  textRect.y + fm.getAscent());
			}
		}
		g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, currentRenderingHint);
	}
}
