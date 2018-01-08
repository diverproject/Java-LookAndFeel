package org.diverproject.themes.blackdimond;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import org.diverproject.themes.AbstractTheme;

public class BlackDiamondTheme extends AbstractTheme
{
	public BlackDiamondTheme()
	{
		captionColors.setBackground(new ColorUIResource(83, 83, 83));
		captionColors.setForeground(new ColorUIResource(255, 255, 255));
		captionColors.setBorder(new ColorUIResource(83, 83, 83));
		captionColors.setInactiveBackground(new ColorUIResource(83, 83, 83));
		captionColors.setInactiveForeground(new ColorUIResource(109, 109, 109));
		captionColors.setInactiveBorder(new ColorUIResource(83, 83, 83));

		windowColors.setBackground(new ColorUIResource(83, 83, 83));
		windowColors.setForeground(new ColorUIResource(255, 255, 255));
		windowColors.setBorder(new ColorUIResource(83, 83, 83));

		textColors.setBackground(new ColorUIResource(83, 83, 83));
		textColors.setForeground(new ColorUIResource(255, 255, 255));
		textColors.setHightlightBackground(new ColorUIResource(83, 83, 83));
		textColors.setHightlightForeground(new ColorUIResource(208, 208, 208));
		textColors.setDisabledForeground(new ColorUIResource(109, 109, 109));

		menuColors.setBackground(new ColorUIResource(83, 83, 83));
		menuColors.setForeground(new ColorUIResource(255, 255, 255));
		menuColors.setSelectedBackground(new ColorUIResource(57, 57, 57));
		menuColors.setSelectedForeground(new ColorUIResource(255, 255, 255));
		menuColors.setDisabledBackground(new ColorUIResource(menuColors.getBackground()));
		menuColors.setDisabledForeground(new ColorUIResource(menuColors.getDisabledBackground().brighter()));
		menuColors.setBorderBrighter(new ColorUIResource(107, 107, 107));
		menuColors.setBorderDarker(new ColorUIResource(23, 23, 23));

		menuItemColors.setBackground(new ColorUIResource(83, 83, 83));
		menuItemColors.setForeground(new ColorUIResource(255, 255, 255));
		menuItemColors.setSelectedBackground(new ColorUIResource(57, 57, 57));
		menuItemColors.setSelectedForeground(new ColorUIResource(255, 255, 255));
		menuItemColors.setDisabledBackground(new ColorUIResource(menuColors.getBackground()));
		menuItemColors.setDisabledForeground(new ColorUIResource(menuColors.getDisabledBackground().brighter()));
		menuItemColors.setBorderBrighter(new ColorUIResource(107, 107, 107));
		menuItemColors.setBorderDarker(new ColorUIResource(23, 23, 23));

		setMenuFont("Dialog", FontUIResource.PLAIN, 12, 12);
		setDecorateWindows(true);
		setCenterWindowTitle(true);
	}

	@Override
	public String getName()
	{
		return "Black Diamond Theme";
	}
}
