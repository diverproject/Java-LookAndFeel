
package org.diverproject.themes;

import javax.swing.border.Border;

public interface AbstractBorderFactory
{
	public Border getMenuBarBorder();
	public Border getMenuBorder();
	public Border getPopupMenuBorder();
	public Border getMenuItemBorder();
	public Border getPopupMenuItemBorder();
}
