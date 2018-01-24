package org.diverproject.themes;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import org.diverproject.themes.border.SimpleMenuBorder;
import org.diverproject.themes.border.SimpleMenuItemBorder;
import org.diverproject.themes.border.SimpleRootPaneBorder;

public class DefaultBorderFactory implements AbstractBorderFactory
{
	@Override
	public SimpleRootPaneBorder getRootPaneBorder()
	{
		return new SimpleRootPaneBorder();
	}

	@Override
	public Border getMenuBarBorder()
	{
		return BorderFactory.createEmptyBorder(1, 1, 1, 1);
	}

	@Override
	public SimpleMenuBorder getMenuBorder()
	{
		return new SimpleMenuBorder();
	}

	@Override
	public Border getPopupMenuBorder()
	{
		return BorderFactory.createEmptyBorder(1, 1, 1, 1);
	}

	@Override
	public SimpleMenuItemBorder getMenuItemBorder()
	{
		return new SimpleMenuItemBorder();
	}

	@Override
	public Border getPopupMenuItemBorder()
	{
		return BorderFactory.createEmptyBorder(1, 1, 1, 1);
	}
}
