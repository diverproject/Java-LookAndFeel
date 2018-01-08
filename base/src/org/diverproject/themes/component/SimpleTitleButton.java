package org.diverproject.themes.component;

import javax.swing.Action;
import javax.swing.Icon;

import org.diverproject.themes.DefaultIcon;

public class SimpleTitleButton extends SimpleNoFocusButton
{
	private static final long serialVersionUID = 8800147374944068674L;

	public static final String ICONIFY = "Minimize";
	public static final String MAXIMIZE = "Maximize";
	public static final String CLOSE = "Close";

	public SimpleTitleButton(Action action, String name, DefaultIcon icon)
	{
		setContentAreaFilled(false);
		setBorderPainted(false);
		setAction(action);
		setText(null);
		setIcon(icon);
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
}
