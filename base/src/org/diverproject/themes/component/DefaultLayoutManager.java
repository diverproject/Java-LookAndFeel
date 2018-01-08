package org.diverproject.themes.component;

import static javax.swing.JRootPane.NONE;
import static org.diverproject.themes.component.SimpleRootPaneUI.MAXIMUM_SIZE;
import static org.diverproject.themes.component.SimpleRootPaneUI.MINIMUM_SIZE;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JRootPane;

import org.diverproject.themes.component.listener.LayoutManagerAdapter;

public class DefaultLayoutManager extends LayoutManagerAdapter
{
	@Override
	public float getLayoutAlignmentX(Container target)
	{
		return 0.0f;
	}

	@Override
	public float getLayoutAlignmentY(Container target)
	{
		return 0.0f;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		return MINIMUM_SIZE;
	}

	@Override
	public Dimension maximumLayoutSize(Container target)
	{
		return MAXIMUM_SIZE;
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		Insets insets = parent.getInsets();
		JRootPane rootPane = (JRootPane) parent;

		Dimension paneDimension = rootPane.getContentPane() != null ? rootPane.getContentPane().getPreferredSize() : rootPane.getSize();
		Dimension menuDimension = rootPane.getJMenuBar().getPreferredSize();
		Dimension titleDimension = null;

		if (rootPane.getWindowDecorationStyle() != NONE)
		{
			JComponent titlePane = ((SimpleRootPaneUI) rootPane.getUI()).getTitlePane();

			if (titlePane != null)
				titleDimension = titlePane.getPreferredSize();
		}

		if (paneDimension == null)
			paneDimension = new Dimension(0, 0);

		if (menuDimension == null)
			menuDimension = new Dimension(0, 0);

		if (titleDimension == null)
			titleDimension = new Dimension(0, 0);

		int width = Math.max(Math.max(paneDimension.width, menuDimension.width), titleDimension.width) + insets.left + insets.right;
		int height = paneDimension.height + menuDimension.height + titleDimension.width + insets.top + insets.bottom;

		return new Dimension(width, height);
	}

	@Override
	public void layoutContainer(Container parent)
	{
		JRootPane rootPane = (JRootPane) parent;
		Rectangle bounds = rootPane.getBounds();
		Insets insets = rootPane.getInsets();

		int width = bounds.width - insets.right - insets.left;
		int height = bounds.height - insets.top - insets.bottom;
		int yOffset = 0;

		if (rootPane.getLayeredPane() != null)
			rootPane.getLayeredPane().setBounds(insets.left, insets.top, width, height);

		if (rootPane.getWindowDecorationStyle() != NONE && rootPane.getUI() instanceof SimpleRootPaneUI)
		{
			JComponent titlePane = ((SimpleRootPaneUI) rootPane.getUI()).getTitlePane();

			if (rootPane.getGlassPane() != null)
			{
				int titleHeight = titlePane != null ? titlePane.getPreferredSize().height : 0;
				rootPane.getGlassPane().setBounds(insets.left, insets.top + titleHeight, width, height - titleHeight);
			}

			if (titlePane != null)
			{
				Dimension titleDimension = titlePane.getPreferredSize();

				if (titleDimension != null)
				{
					int titleHeight = titlePane.getPreferredSize().height;
					titlePane.setBounds(0, 0, width, titleHeight);
					yOffset += titleHeight;
				}
			}
		}

		if (rootPane.getJMenuBar() != null)
		{
			Dimension menuDimension = rootPane.getJMenuBar().getPreferredSize();
			rootPane.getJMenuBar().setBounds(0, yOffset, width, menuDimension.height);
			yOffset += menuDimension.height;
		}

		if (rootPane.getContentPane() != null)
			rootPane.getContentPane().setBounds(0, yOffset, width, height < yOffset ? 0 : height - yOffset);
	}
}
