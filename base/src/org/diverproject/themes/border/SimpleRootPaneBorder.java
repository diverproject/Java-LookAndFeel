package org.diverproject.themes.border;

import static org.diverproject.themes.component.ThemesUIFunctions.RootPaneColors;

import org.diverproject.themes.colors.IBoderLinedColors;

public class SimpleRootPaneBorder extends SimpleBorderLined
{
	private static final long serialVersionUID = -3933337819883814374L;
	private static final int BORDER_SIZE = 3;
	private static final int LINE_SIZE = 2;
	private static final IBoderLinedColors COLORS = RootPaneColors();

	public SimpleRootPaneBorder()
	{
		super(COLORS, BORDER_SIZE, LINE_SIZE);
	}
}
