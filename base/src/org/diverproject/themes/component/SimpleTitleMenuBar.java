package org.diverproject.themes.component;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Window;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JMenuBar;
import javax.swing.UIManager;

import org.diverproject.themes.LookAndFeelUtil;
import org.diverproject.util.lang.IntUtil;

public class SimpleTitleMenuBar extends JMenuBar
{
	private static final long serialVersionUID = -3083366813653323732L;

	private SimpleTitlePane titlePane;

	public SimpleTitleMenuBar(SimpleTitlePane titlePane)
	{
		setOpaque(false);

		this.titlePane = titlePane;
	}

	@Override
	public Dimension getMinimumSize()
	{
		return getPreferredSize();
	}

	@Override
	public Dimension getPreferredSize()
	{
		Dimension size = super.getPreferredSize();
		Image icon = getIcon();

		if (icon == null)
			return size;

		Dimension preferred = new Dimension(icon.getWidth(null), icon.getHeight(null));
		int computeHeight = getComputeHeight();

		if (preferred.getHeight() > computeHeight)
		{
			double scale = (double) computeHeight / (double) preferred.height;
			preferred.width *= scale;
			preferred.height *= scale;
		}

		return new Dimension(IntUtil.major(preferred.width, size.width), IntUtil.major(preferred.height, size.height));
	}

	@Override
	public void paint(Graphics g)
	{
		Image icon = getIcon();

		if (icon == null)
		{
			Icon baseIcon = UIManager.getIcon("InternalFrame.icon");

			if (baseIcon != null)
				baseIcon.paintIcon(this, g, 2, 2);

			return;
		}

		Graphics2D g2d = (Graphics2D) g;
		Object oldHint = g2d.getRenderingHint(RenderingHints.KEY_INTERPOLATION);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		Rectangle bounds = new Rectangle(0, 0, icon.getWidth(null), icon.getHeight(null));

		if (bounds.height > getHeight())
		{
			double scale = (double) (getHeight() - 2) / bounds.getHeight();
			bounds.width *= scale;
			bounds.height *= scale;
		}

		else
			bounds.y = (getHeight() - bounds.height) / 2;

		g2d.drawImage(icon, bounds.x, bounds.y, bounds.width, bounds.height, null);

		if (oldHint != null)
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, oldHint);
	}

	private Image getIcon()
	{
		Window window = titlePane.getWindow();

		if (window == null)
			return null;

		if (window instanceof Frame)
			return ((Frame) window).getIconImage();

		List<Image> icons = window.getIconImages();

		if (icons != null && !icons.isEmpty())
			return icons.get(0);

		Window ownerWindow = window.getOwner();

		if (ownerWindow != null)
			do {

				icons = ownerWindow.getIconImages();

				if (icons != null && !icons.isEmpty())
					return icons.get(0);

			} while ((ownerWindow = window.getOwner()) != null);

		if (icons == null || icons.isEmpty())
			if (Frame.getFrames() != null && Frame.getFrames().length > 0)
				return Frame.getFrames()[0].getIconImage();

		return null;
	}

	private int getComputeHeight()
	{
		FontMetrics metrics = LookAndFeelUtil.getFontMetrics(this, null, getFont());

		return metrics.getHeight() + 6;
	}
}
