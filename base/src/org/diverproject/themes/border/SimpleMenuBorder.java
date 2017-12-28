package org.diverproject.themes.border;

import static org.diverproject.themes.component.ThemesUIFunctions.*;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JMenuItem;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.UIResource;

public class SimpleMenuBorder extends AbstractBorder implements UIResource
{
	private static final long serialVersionUID = 3733125383745956657L;
	private static final Insets INSETS = new Insets(2, 2, 2, 2);

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
	{
		JMenuItem mi = (JMenuItem) c;

		// Borda visível apenas se o menu estiver ativo
		if (mi.getModel().isArmed() || mi.getModel().isSelected())
		{
			Graphics2D g2d = (Graphics2D) g;
			int offset = 3;

			// Preenchimento do espaço fora da borda (Top, Right, Bottom, Left
			g2d.setColor(MenuColors().getBackground());
			g2d.setStroke(new BasicStroke(offset * 2)); // Stroke considera width a soma dos dois lados
			g2d.drawRect(0, 0, width - 1, height - 1);

			// Desenho das bordas
			int tx = x + offset;
			int ty = y + offset;
			int twidth = width - offset;
			int theight = height - offset;

			// Bordas mais escuras (Top, Right, Bottom, Left)
			g2d.setStroke(new BasicStroke(1));

			g2d.setColor(MenuColors().getBorderDarker());
			g2d.drawLine(tx + 2    , ty + 0     , twidth - 4, ty + 0);
			g2d.drawLine(twidth - 2, ty + 1     , twidth - 2, theight - 3);
			g2d.drawLine(tx + 2    , theight - 2, twidth - 4, theight - 2);
			g2d.drawLine(tx + 0    , ty + 1     , tx + 0    , theight - 3);

			// Bordas mais claras (Top, Bottom)
			g2d.setColor(MenuColors().getBorderBrighter().darker());
			g2d.drawLine(tx + 2    , ty + 1     , twidth - 4, ty + 1);
			g2d.setColor(MenuColors().getBorderBrighter());
			g2d.drawLine(tx + 3    , ty + 2     , twidth - 5, ty + 2);
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
