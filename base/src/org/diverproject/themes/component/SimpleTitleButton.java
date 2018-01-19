package org.diverproject.themes.component;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.Icon;

import org.diverproject.themes.DefaultIcon;
import org.diverproject.themes.ThemeRuntimeException;
import org.diverproject.themes.component.listener.MouseAdapter;
import org.diverproject.util.lang.IntUtil;

public class SimpleTitleButton extends SimpleNoFocusButton
{
	private static final long serialVersionUID = 8800147374944068674L;

	public static final int ICON_NORMAL = 0;
	public static final int ICON_HOVER = 1;
	public static final int ICON_ACTIVE = 2;
	public static final int ICONS_MIN = ICON_NORMAL;
	public static final int ICONS_MAX = ICON_ACTIVE;
	public static final int ICONS_COUNT = ICONS_MAX + 1;

	public static final String ICONIFY = "Minimize";
	public static final String MAXIMIZE = "Maximize";
	public static final String CLOSE = "Close";

	private DefaultIcon[] icons;

	private MouseListener MOUSE_LISTENER = new MouseAdapter()
	{
		@Override
		public void mouseEntered(MouseEvent e)
		{
			setIcon(ICON_HOVER);
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			setIcon(ICON_NORMAL);
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			setIcon(ICON_ACTIVE);
		}
	};

	public SimpleTitleButton(Action action, String name, DefaultIcon icons[])
	{
		setContentAreaFilled(false);
		setBorderPainted(false);
		setAction(action);
		setText(null);
		setIcons(icons);
		setIcon(ICON_NORMAL);
		addMouseListener(MOUSE_LISTENER);
		putClientProperty("paintActive", true);
		getAccessibleContext().setAccessibleName(name);
	}

	@Override
	public DefaultIcon getIcon()
	{
		return (DefaultIcon) super.getIcon();
	}

	@Override
	@Deprecated
	public void setIcon(Icon defaultIcon)
	{
		
	}

	public void setIcon(DefaultIcon defaultIcon)
	{
		super.setIcon(defaultIcon);
	}

	public void setIcon(int type)
	{
		if (IntUtil.interval(type, ICONS_MIN, ICONS_MAX));
			setIcon(icons[type]);
	}

	public void setIcons(DefaultIcon[] icons)
	{
		if (icons.length != ICONS_COUNT)
			throw new ThemeRuntimeException("definido %d icones e é necessário %d", icons.length, ICONS_COUNT);

		this.icons = icons;
	}

	@Override
	public void paint(Graphics g)
	{
		getIcon().paintIcon(this, g, 0, 0);
	}
}
