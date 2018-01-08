package org.diverproject.themes.blackdimond;

import org.diverproject.themes.AbstractLookAndFeel;
import org.diverproject.themes.ComponentProperties;
import org.diverproject.themes.component.SimpleRootPaneUI;

public class BlackDiamondLookAndFeel extends AbstractLookAndFeel
{
	private static final long serialVersionUID = 8809429476998940167L;
	private static final BlackDiamondLookAndFeel INSTANCE = new BlackDiamondLookAndFeel();

	private BlackDiamondTheme theme;

	@Override
	protected void createDefaultTheme()
	{
		theme = new BlackDiamondTheme();

		SimpleRootPaneUI.setDecorateWindows(theme.isDecorateWindows());
	}

	public BlackDiamondTheme getTheme()
	{
		return theme;
	}

	@Override
	public BlackDiamondBorderFactory getBorderFactory()
	{
		return BlackDiamondBorderFactory.getInstance();
	}

	@Override
	public BlackDiamondIconFactory getIconFactory()
	{
		return BlackDiamondIconFactory.getInstance();
	}

	@Override
	public String getDescription()
	{
		return "Black Dimond Java Desktop Theme";
	}

	@Override
	public String getID()
	{
		return "BlackDimond";
	}

	@Override
	public String getName()
	{
		return "Black Dimond";
	}

	@Override
	public boolean isNativeLookAndFeel()
	{
		return false;
	}

	@Override
	public boolean isSupportedLookAndFeel()
	{
		return true;
	}

	@Override
	protected void initClassDefaults(ComponentProperties properties)
	{
		super.initClassDefaults(properties);
	}

	@Override
	protected void initFontsDefaults(ComponentProperties properties)
	{
		super.initFontsDefaults(properties);
	}

	@Override
	protected void initBordersDefaults(ComponentProperties properties)
	{
		super.initBordersDefaults(properties);
	}

	public static BlackDiamondLookAndFeel getInstance()
	{
		return INSTANCE;
	}
}
