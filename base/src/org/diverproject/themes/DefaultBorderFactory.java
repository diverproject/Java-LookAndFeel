package org.diverproject.themes;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import org.diverproject.themes.border.SimpleMenuBorder;

public class DefaultBorderFactory implements AbstractForderFactory
{
	@Override
	@Deprecated
	public Border getFocusFrameBorder()
	{
		return null;
	}

	@Override
	@Deprecated
	public Border getButtonBorder()
	{
		return null;
	}

	@Override
	@Deprecated
	public Border getToggleButtonBorder()
	{
		return null;
	}

	@Override
	@Deprecated
	public Border getTextBorder()
	{
		return null;
	}

	@Override
	@Deprecated
	public Border getSpinnerBorder()
	{
		return null;
	}

	@Override
	@Deprecated
	public Border getTextFieldBorder()
	{
		return null;
	}

	@Override
	@Deprecated
	public Border getComboBoxBorder()
	{
		return null;
	}

	@Override
	@Deprecated
	public Border getTableHeaderBorder()
	{
		return null;
	}

	@Override
	@Deprecated
	public Border getTableScrollPaneBorder()
	{
		return null;
	}

	@Override
	@Deprecated
	public Border getScrollPaneBorder()
	{
		return null;
	}

	@Override
	@Deprecated
	public Border getTabbedPaneBorder()
	{
		return null;
	}

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
	@Deprecated
	public Border getMenuItemBorder()
	{
		return null;
	}

	@Override
	public Border getPopupMenuBorder()
	{
		return BorderFactory.createEmptyBorder(1, 1, 1, 1);
	}

	@Override
	@Deprecated
	public Border getInternalFrameBorder()
	{
		return null;
	}

	@Override
	@Deprecated
	public Border getPaletteBorder()
	{
		return null;
	}

	@Override
	@Deprecated
	public Border getToolBarBorder()
	{
		return null;
	}

	@Override
	@Deprecated
	public Border getDesktopIconBorder()
	{
		return null;
	}

	@Override
	@Deprecated
	public Border getProgressBarBorder()
	{
		return null;
	}
}
