package org.diverproject.themes.blackdimond;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import org.diverproject.themes.AbstractTheme;

public class BlackDiamondTheme extends AbstractTheme
{
	public BlackDiamondTheme()
	{
		menuColors.setBackground(new ColorUIResource(83, 83, 83));
		menuColors.setForeground(new ColorUIResource(255, 255, 255));
		menuColors.setSelectedBackground(new ColorUIResource(57, 57, 57));
		menuColors.setSelectedForeground(new ColorUIResource(255, 255, 255));
		menuColors.setDisabledBackground(new ColorUIResource(menuColors.getBackground()));
		menuColors.setDisabledForeground(new ColorUIResource(menuColors.getDisabledBackground().brighter()));
		menuColors.setBorderBrighter(new ColorUIResource(107, 107, 107));
		menuColors.setBorderDarker(new ColorUIResource(23, 23, 23));

		setMenuFont("Tahoma", FontUIResource.PLAIN, 14, 14);
	}

	@Override
	public String getName()
	{
		return "Black Diamond Theme";
	}
}
