package org.diverproject.themes;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DiverProjectTheme
{
	private static AbstractLookAndFeel currentLookAndFeel;

	public static AbstractLookAndFeel getCurrentLookAndFeel()
	{
		return currentLookAndFeel;
	}

	public static void setLookAndFeel(AbstractLookAndFeel lookAndFeel) throws UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel((currentLookAndFeel = lookAndFeel));
	}
}
