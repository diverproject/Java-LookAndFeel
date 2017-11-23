package org.diverproject.themes.border;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JMenuItem;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.UIResource;

import org.diverproject.themes.AbstractLookAndFeel;
import org.diverproject.themes.AbstractTheme;
import org.diverproject.themes.DiverProjectTheme;
import org.diverproject.themes.colors.MenuColors;

public class SimpleMenuBorder extends AbstractBorder implements UIResource
{
	private static final long serialVersionUID = 3733125383745956657L;
	private static final Insets INSETS = new Insets(2, 2, 2, 2);
	private static final Insets OFFSET = new Insets(2, 2, 2, 2);

	public AbstractLookAndFeel LookAndFeel()
	{
		return DiverProjectTheme.getCurrentLookAndFeel();
	}

	public AbstractTheme Theme()
	{
		return DiverProjectTheme.getCurrentLookAndFeel().getTheme();
	}

	public MenuColors Colors()
	{
		return DiverProjectTheme.getCurrentLookAndFeel().getTheme().getMenuColors();
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
	{
		JMenuItem mi = (JMenuItem) c;

		// Borda visível apenas se o menu estiver ativo
		if (mi.getModel().isArmed() || mi.getModel().isSelected())
		{
			// Preenchimento do espaço fora da borda (Top, Right, Bottom, Left
			{
				g.setColor(Colors().getBackground());

				for (int by = 0; by < OFFSET.top; by++)
					g.drawLine(0, by, width, by);

				for (int bx = 0; bx <= OFFSET.right; bx++)
					g.drawLine(width - bx - 1, 0, width - bx - 1, height);

				for (int by = 0; by <= OFFSET.top; by++)
					g.drawLine(0, height - by - 1, width, height - by - 1);

				for (int bx = 0; bx <= OFFSET.left; bx++)
					g.drawLine(bx, 0, bx, height);
			}

			// Desenho das bordas
			{
				int tx = x + OFFSET.left;
				int ty = y + OFFSET.top;
				int twidth = width - OFFSET.right;
				int theight = height - OFFSET.bottom;

				// Bordas mais claras (Top, Bottom)
				g.setColor(Colors().getBorderBrighter());
				g.drawLine(tx + 2    , ty + 1     , twidth - 3, ty + 1);

				// Bordas mais escuras (Top, Right, Bottom, Left)
				g.setColor(Colors().getBorderDarker());
				g.drawLine(tx + 2    , ty + 0     , twidth - 2, ty + 0);
				g.drawLine(twidth - 2, ty + 1     , twidth - 2, theight - 3);
				g.drawLine(tx + 2    , theight - 2, twidth - 2, theight - 2);
				g.drawLine(tx + 1    , ty + 1     , tx + 1    , theight - 3);
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
