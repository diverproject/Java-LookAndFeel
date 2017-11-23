package org.diverproject.themes;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import org.diverproject.themes.border.SimpleMenuBorder;

public class DefaultBorderFactory implements AbstractBorderFactory
{
	@Override
	public Border getMenuBarBorder()
	{
		return BorderFactory.createEmptyBorder(1, 1, 1, 1);
	}

	public SimpleMenuBorder getMenuBorder()
	{
		return new SimpleMenuBorder();
	}

	@Override
	public Border getPopupMenuBorder()
	{
		return BorderFactory.createEmptyBorder(1, 1, 1, 1);
	}
}
