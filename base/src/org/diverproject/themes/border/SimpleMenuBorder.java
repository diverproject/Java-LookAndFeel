package org.diverproject.themes.border;

import static org.diverproject.themes.component.ThemesUIFunctions.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JMenuItem;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.UIResource;

import org.diverproject.util.lang.IntUtil;

public class SimpleMenuBorder extends AbstractBorder implements UIResource
{
	private static final long serialVersionUID = 3733125383745956657L;

	public static final int SIZE_INSETS = 3;
	private static final Insets INSETS = new Insets(SIZE_INSETS, SIZE_INSETS, SIZE_INSETS, SIZE_INSETS);

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
	{
		JMenuItem mi = (JMenuItem) c;

		if (mi.getModel().isArmed() || mi.getModel().isSelected())
		{
			g.setColor(MenuColors().getBorderBrighter());
			g.drawRoundRect(SIZE_INSETS, SIZE_INSETS, width - (SIZE_INSETS * 2), height - (SIZE_INSETS * 2), SIZE_INSETS, SIZE_INSETS);
		}

		else if (mi.getModel().isRollover())
		{
			int offset = SIZE_INSETS + 1;
			int heightEffect = height / 3;

			for (int i = 0; i < heightEffect; i++)
			{
				int heightOffset = offset + i;
				int alphaDegree = (int) (64 * (IntUtil.toPorcent(i, heightEffect) / 100f) + 1);
				Color whiteAlpha = new Color(255, 255, 255, 64 - alphaDegree);

				g.setColor(whiteAlpha);
				g.drawLine(offset, heightOffset, width - offset - 1, heightOffset);
			}
		}
	}

	@Override
	public Insets getBorderInsets(Component c)
	{
		return new Insets(INSETS.top, INSETS.left, INSETS.bottom, INSETS.right);
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets)
	{
		insets.left = INSETS.left;
		insets.top = INSETS.top;
		insets.right = INSETS.right;
		insets.bottom = INSETS.bottom;

		return insets;
	}
}
