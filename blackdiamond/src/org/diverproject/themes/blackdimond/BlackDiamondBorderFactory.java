package org.diverproject.themes.blackdimond;

import org.diverproject.themes.DefaultBorderFactory;

public class BlackDiamondBorderFactory extends DefaultBorderFactory
{
	private static final BlackDiamondBorderFactory INSTANCE = new BlackDiamondBorderFactory();

	private BlackDiamondBorderFactory()
	{
		
	}

	public static BlackDiamondBorderFactory getInstance()
	{
		return INSTANCE;
	}
}
