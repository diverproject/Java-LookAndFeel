package org.diverproject.themes.border;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.border.AbstractBorder;

import org.diverproject.themes.LookAndFeelUtil;
import org.diverproject.themes.colors.IBoderLinedColors;

public class SimpleBorderLined extends AbstractBorder
{
	private static final long serialVersionUID = -3545831804653301582L;

	private int borderSize;
	private int lineSize;
	private Insets insets;
	private IBoderLinedColors colors;

	public SimpleBorderLined(IBoderLinedColors colors, int borderSize, int lineSize)
	{
		int fullSize = borderSize + lineSize;

		this.colors = colors;
		this.lineSize = lineSize;
		this.borderSize = borderSize;
		this.insets = new Insets(fullSize, fullSize, fullSize, fullSize);
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
	{
		boolean active = c.isFocusOwner();
		int margin = borderSize / 2;

		if (c instanceof JComponent)
			active = LookAndFeelUtil.isActive((JComponent) c);

		Rectangle bounds = g.getClipBounds();
		g.setColor(colors.getBackground());
		g.fillRect(0, 0, bounds.width - bounds.x, bounds.height - bounds.y);
		g.setColor(active ? colors.getBorder() : colors.getDisabledBorder());

		for (int i = 1, adjMargin = margin; i <= lineSize; i++, adjMargin++)
			g.drawRoundRect(adjMargin, adjMargin, bounds.width - bounds.x - (adjMargin * 2) - 1, bounds.height - bounds.y - (adjMargin * 2) - 1, lineSize, lineSize);
	}

	@Override
	public Insets getBorderInsets(Component c)
	{
		return new Insets(insets.top, insets.left, insets.bottom, insets.right);
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets)
	{
		insets.left = insets.left;
		insets.top = insets.top;
		insets.right = insets.right;
		insets.bottom = insets.bottom;

		return insets;
	}
}
