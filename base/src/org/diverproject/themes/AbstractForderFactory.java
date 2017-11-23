
package org.diverproject.themes;

import javax.swing.border.Border;

public interface AbstractForderFactory
{
	public Border getFocusFrameBorder();
	public Border getButtonBorder();
	public Border getToggleButtonBorder();
	public Border getTextBorder();
	public Border getSpinnerBorder();
	public Border getTextFieldBorder();
	public Border getComboBoxBorder();
	public Border getTableHeaderBorder();
	public Border getTableScrollPaneBorder();
	public Border getScrollPaneBorder();
	public Border getTabbedPaneBorder();
	public Border getMenuBarBorder();
	public Border getMenuItemBorder();
	public Border getPopupMenuBorder();
	public Border getInternalFrameBorder();
	public Border getPaletteBorder();
	public Border getToolBarBorder();
	public Border getDesktopIconBorder();
	public Border getProgressBarBorder();
}
