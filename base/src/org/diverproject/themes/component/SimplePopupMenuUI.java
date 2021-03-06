package org.diverproject.themes.component;

import static org.diverproject.themes.component.ThemesUIFunctions.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Popup;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicPopupMenuUI;

public class SimplePopupMenuUI extends BasicPopupMenuUI
{
	private static Robot robot;

	static
	{
		try {
			robot = new Robot();
		} catch (Exception e) {
		}
	}

	public static SimplePopupMenuUI createUI(JComponent c)
	{
		return new SimplePopupMenuUI();
	}

	protected BufferedImage screenImage;
	protected FadePopupListener fadeListener;

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
	protected void installListeners()
	{
		super.installListeners();

		if (!isMenuOpaque())
			popupMenu.addPopupMenuListener((fadeListener = new FadePopupListener(this)));
	}

	@Override
	protected void uninstallListeners()
	{
		if (!isMenuOpaque() && fadeListener != null)
		{
			popupMenu.removePopupMenuListener(fadeListener);
			fadeListener = null;
		}

		super.uninstallListeners();
	}

	@Override
	public Popup getPopup(JPopupMenu popupMenu, int x, int y)
	{
		Popup popup = super.getPopup(popupMenu, x, y);

		if (!isMenuOpaque())
		{
			try {

				Dimension size = popupMenu.getPreferredSize();

				if (size.width > 0 && size.height > 0)
				{
					Rectangle screenRect = new Rectangle(x, y, size.width, size.height);
					screenImage = robot.createScreenCapture(screenRect);
				}

				for (Component component : popupMenu.getComponents())
					if (component instanceof JPanel)
						((JPanel) component).setOpaque(true);

			} catch (Exception e) {
				screenImage = null;
			}
		}

		return popup;
	}

	@Override
	public void update(Graphics g, JComponent c)
	{
		if (screenImage != null)
			g.drawImage(screenImage, 0, 0, null);
		else
		{
			g.setColor(MenuColors().getBackground());
			g.fillRect(0, 0, c.getWidth(), c.getHeight());
		}
	}

	private boolean isMenuOpaque()
	{
		return robot != null;
	}

	private void resetScreenImage()
	{
		screenImage = null;
	}

	public static class FadePopupListener implements PopupMenuListener
	{
		private SimplePopupMenuUI popupMenuUI;
	
		public FadePopupListener(SimplePopupMenuUI popupMenuUI)
		{
			this.popupMenuUI = popupMenuUI;
		}

		@Override
		public void popupMenuWillBecomeVisible(PopupMenuEvent e)
		{
			
		}

		@Override
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
		{
			if (popupMenuUI.screenImage != null)
			{
				JPopupMenu popup = (JPopupMenu) e.getSource();

				if (popup.isShowing() && popup.getRootPane().isShowing())
				{
					Point pointPopup = popup.getLocationOnScreen();
					Point pointRoot = popup.getRootPane().getLocationOnScreen();

					Graphics g = popup.getRootPane().getGraphics();
					g.drawImage(popupMenuUI.screenImage, pointPopup.x - pointRoot.x, pointPopup.y - pointRoot.y, null);
					popupMenuUI.resetScreenImage();
				}
			}
		}

		@Override
		public void popupMenuCanceled(PopupMenuEvent e)
		{
			// TODO Auto-generated method stub
			
		}
	}
}
