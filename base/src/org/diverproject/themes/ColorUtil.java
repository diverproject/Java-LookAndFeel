
package org.diverproject.themes;

import java.awt.Color;

public class ColorUtil
{
	public static Color brighter(Color c, double p)
	{
		if (c == null)
			return null;

		double r = c.getRed();
		double g = c.getGreen();
		double b = c.getBlue();

		double rd = 255.0 - r;
		double gd = 255.0 - g;
		double bd = 255.0 - b;

		r += (rd * p) / 100.0;
		g += (gd * p) / 100.0;
		b += (bd * p) / 100.0;

		return createColor((int) r, (int) g, (int) b);
	}

	public static Color darker(Color c, double p)
	{
		if (c == null)
			return null;

		double r = c.getRed();
		double g = c.getGreen();
		double b = c.getBlue();

		r -= (r * p) / 100.0;
		g -= (g * p) / 100.0;
		b -= (b * p) / 100.0;

		return createColor((int) r, (int) g, (int) b);
	}

	public static Color createColor(int r, int g, int b)
	{
		return new Color(((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF)));
	}
}
