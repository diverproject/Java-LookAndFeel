package org.diverproject.themes.component;

import static org.diverproject.themes.component.ThemesUIFunctions.*;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicMenuUI;

public class SimpleMenuUI extends BasicMenuUI
{
	public static SimpleMenuUI createUI(JComponent c)
	{
		return new SimpleMenuUI();
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
	protected void installDefaults()
	{
		super.installDefaults();
		Boolean rolloverEnabled = (Boolean) UIManager.get("MenuBar.rolloverEnabled");

		if (rolloverEnabled != null && rolloverEnabled.booleanValue())
			menuItem.setRolloverEnabled(true);
	}

	@Override
	public void update(Graphics g, JComponent c)
	{
        paintBackground(g, c, 0, 0, c.getWidth(), c.getHeight());
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
			g.setColor(MenuColors().getDisabledBackground());
			g.fillRect(0, 0, width, height);
		}

		if (model.isArmed() || model.isRollover() || (c instanceof JMenu && model.isSelected()))
		{
			g.setColor(MenuColors().getSelectedBackground());
			g.fillRect(0, 0, width, height);
		}

		else
		{
			g.setColor(MenuColors().getBackground());
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

		if (!model.isEnabled())
		{
			g2D.setColor(MenuColors().getDisabledForeground().brighter());
			BasicGraphicsUtils.drawStringUnderlineCharAt(g2D, text, mnemIndex, textRect.x,  textRect.y + fm.getAscent());
			g2D.setColor(MenuColors().getDisabledForeground().darker());
			BasicGraphicsUtils.drawStringUnderlineCharAt(g2D, text, mnemIndex, textRect.x - 1,  textRect.y + fm.getAscent() - 1);
		}
		else
		{
			if (model.isArmed() || model.isRollover() || model.isSelected())
				g2D.setColor(MenuColors().getSelectedForeground());
			else
				g2D.setColor(MenuColors().getForeground());

			BasicGraphicsUtils.drawStringUnderlineCharAt(g2D, text, mnemIndex, textRect.x,  textRect.y + fm.getAscent());
		}
	}

	@Override
	protected MouseInputListener createMouseInputListener(JComponent c)
	{
		return new InternalMouseInputHandler();
	}

	protected class InternalMouseInputHandler extends BasicMenuUI.MouseInputHandler
	{
		@Override
		public void mouseEntered(java.awt.event.MouseEvent e)
		{
			super.mouseEntered(e);

			JMenu menu = (JMenu) e.getSource();

			if (menu.isTopLevelMenu() && menu.isRolloverEnabled())
			{
				menu.getModel().setRollover(true);
				menuItem.repaint();
			}
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			super.mouseExited(e);

			JMenu menu = (JMenu) e.getSource();

			if (menu.isRolloverEnabled())
			{
				menu.getModel().setRollover(true);
				menuItem.repaint();
			}
		}
	}
}
