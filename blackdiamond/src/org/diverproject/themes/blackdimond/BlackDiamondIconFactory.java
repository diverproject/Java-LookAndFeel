package org.diverproject.themes.blackdimond;

import org.diverproject.themes.DefaultIconFactory;

public class BlackDiamondIconFactory extends DefaultIconFactory
{
	private static final BlackDiamondIconFactory INSTANCE = new BlackDiamondIconFactory();

	private BlackDiamondIconFactory()
	{
		
	}

	public static BlackDiamondIconFactory getInstance()
	{
		return INSTANCE;
	}
}
