package org.diverproject.themes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalTheme;

import org.diverproject.themes.colors.MenuColors;

public abstract class AbstractTheme extends MetalTheme
{
	public static final String BASE_FONT_NAME = "Verdana";
	public static final int BASE_FONT_STYLE = Font.PLAIN;
	public static final int BASE_FONT_SIZE = 12;

    public static final ColorUIResource WHITE = new ColorUIResource(Color.WHITE);
    public static final ColorUIResource LIGHT_GRAY = new ColorUIResource(Color.LIGHT_GRAY);
    public static final ColorUIResource GRAY = new ColorUIResource(Color.GRAY);
    public static final ColorUIResource DARK_GRAY = new ColorUIResource(Color.DARK_GRAY);
    public static final ColorUIResource BLACK = new ColorUIResource(Color.BLACK);
    public static final ColorUIResource RED = new ColorUIResource(Color.RED);
    public static final ColorUIResource PINK = new ColorUIResource(Color.PINK);
    public static final ColorUIResource ORANGE = new ColorUIResource(Color.ORANGE);
    public static final ColorUIResource YELLOW = new ColorUIResource(Color.YELLOW);
    public static final ColorUIResource GREEN = new ColorUIResource(Color.GREEN);
    public static final ColorUIResource MAGENTA = new ColorUIResource(Color.MAGENTA);
    public static final ColorUIResource CYAN = new ColorUIResource(Color.CYAN);
    public static final ColorUIResource BLUE = new ColorUIResource(Color.BLUE);

	public static final ColorUIResource PRIMARY_COLOR_1 = new ColorUIResource(0, 0, 0);
	public static final ColorUIResource PRIMARY_COLOR_2 = new ColorUIResource(255, 0, 0);
	public static final ColorUIResource PRIMARY_COLOR_3 = new ColorUIResource(255, 255, 255);
	public static final ColorUIResource SECONDARY_COLOR_1 = new ColorUIResource(0, 0, 0);
	public static final ColorUIResource SECONDARY_COLOR_2 = new ColorUIResource(255, 0, 0);
	public static final ColorUIResource SECONDARY_COLOR_3 = new ColorUIResource(255, 255, 255);

	public static final FontUIResource CONTROL_TEXT_FONT = new FontUIResource(BASE_FONT_NAME, BASE_FONT_STYLE, BASE_FONT_SIZE);
	public static final FontUIResource SYSTEM_TEXT_FONT = new FontUIResource(BASE_FONT_NAME, BASE_FONT_STYLE, BASE_FONT_SIZE);
	public static final FontUIResource USER_TEXT_FONT = new FontUIResource(BASE_FONT_NAME, BASE_FONT_STYLE, BASE_FONT_SIZE);
	public static final FontUIResource MENU_TEXT_FONT = new FontUIResource(BASE_FONT_NAME, BASE_FONT_STYLE, BASE_FONT_SIZE);
	public static final FontUIResource WINDOW_TITLE_FONT = new FontUIResource(BASE_FONT_NAME, BASE_FONT_STYLE, BASE_FONT_SIZE + 2);
	public static final FontUIResource SUB_TEXT_FONT = new FontUIResource(BASE_FONT_NAME, BASE_FONT_STYLE, BASE_FONT_SIZE - 2);

	protected ColorUIResource foregroundColor = PRIMARY_COLOR_1;
	protected ColorUIResource desktopColor = PRIMARY_COLOR_2;
	protected ColorUIResource selectionBackgroundColor = PRIMARY_COLOR_3;
	protected ColorUIResource frameColor = SECONDARY_COLOR_1;
	protected ColorUIResource controlBackgroundColor = SECONDARY_COLOR_2;
	protected ColorUIResource backgroundColor = SECONDARY_COLOR_3;

	protected FontUIResource controlTextoFont = CONTROL_TEXT_FONT;
	protected FontUIResource systemTextFont = SYSTEM_TEXT_FONT;
	protected FontUIResource userTextFont = USER_TEXT_FONT;
	protected FontUIResource menuTextFont = MENU_TEXT_FONT;
	protected FontUIResource windowTitleFont = WINDOW_TITLE_FONT;
	protected FontUIResource subTextFont = SUB_TEXT_FONT;

	protected FontUIResource menuFont;
	protected FontUIResource menuItemFont;

	protected MenuColors menuColors;

	public AbstractTheme()
	{
		menuColors = new MenuColors(WHITE, DARK_GRAY);
		menuFont = menuTextFont;
		menuItemFont = menuTextFont;
	}

	protected void setMenuFont(String name, int style, int size)
	{
		setMenuFont(name, style, size, size);
	}

	protected void setMenuFont(String name, int style, int barSize, int itemSize)
	{
		menuFont = new FontUIResource(name, style, barSize);
		menuItemFont = new FontUIResource(name, style, itemSize);
	}

	@Override
	protected ColorUIResource getPrimary1()
	{
		return foregroundColor;
	}

	@Override
	protected ColorUIResource getPrimary2()
	{
		return desktopColor;
	}

	@Override
	protected ColorUIResource getPrimary3()
	{
		return selectionBackgroundColor;
	}

	@Override
	protected ColorUIResource getSecondary1()
	{
		return frameColor;
	}

	@Override
	protected ColorUIResource getSecondary2()
	{
		return controlBackgroundColor;
	}

	@Override
	protected ColorUIResource getSecondary3()
	{
		return backgroundColor;
	}

	@Override
	public FontUIResource getControlTextFont()
	{
		return controlTextoFont;
	}

	@Override
	public FontUIResource getSystemTextFont()
	{
		return systemTextFont;
	}

	@Override
	public FontUIResource getUserTextFont()
	{
		return userTextFont;
	}

	@Override
	public FontUIResource getMenuTextFont()
	{
		return menuTextFont;
	}

	@Override
	public FontUIResource getWindowTitleFont()
	{
		return windowTitleFont;
	}

	@Override
	public FontUIResource getSubTextFont()
	{
		return subTextFont;
	}

	@Override
	public ColorUIResource getMenuBackground()
	{
		return menuColors.getBackground();
	}

	@Override
	public ColorUIResource getMenuForeground()
	{
		return menuColors.getForeground();
	}

	@Override
	public ColorUIResource getMenuSelectedBackground()
	{
		return menuColors.getSelectedBackground();
	}

	@Override
	public ColorUIResource getMenuSelectedForeground()
	{
		return menuColors.getSelectedForeground();
	}

	@Override
	public ColorUIResource getMenuDisabledForeground()
	{
		return menuColors.getDisabledForeground();
	}

	public MenuColors getMenuColors()
	{
		return menuColors;
	}

	public FontUIResource getMenuFont()
	{
		return menuFont;
	}

    public FontUIResource getMenuItemFont()
	{
		return menuItemFont;
	}
}
