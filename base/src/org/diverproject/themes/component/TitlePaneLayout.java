package org.diverproject.themes.component;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JMenuBar;

import org.diverproject.themes.LookAndFeelUtil;
import org.diverproject.util.lang.IntUtil;

public class TitlePaneLayout implements LayoutManager
{
	private static final int COMPONENT_SPACING = 5;
	private static final int HORIZONTAL_SPACING = COMPONENT_SPACING;
	private static final int VERTICAL_SPACING = COMPONENT_SPACING;

	private int buttonsOffset;
	private SimpleTitlePane titlePane;

	public static final int MIN_PANE_HEIGHT = 24;

	public TitlePaneLayout(SimpleTitlePane titlePane)
	{
		this.titlePane = titlePane;
	}

	@Override
	@Deprecated
	public void addLayoutComponent(String name, Component comp)
	{

	}

	@Override
	@Deprecated
	public void removeLayoutComponent(Component comp)
	{

	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		int height = computeHeight();

		return new Dimension(height, height);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		return preferredLayoutSize(parent);
	}

	@Override
	public void layoutContainer(Container parent)
	{
		if (titlePane.getJMenuBar() != null)
		{
			JMenuBar menuBar = titlePane.getJMenuBar();
			Dimension menuDimension = menuBar.getPreferredSize();

			menuBar.setBounds(2, (getHeight() - menuDimension.height), menuDimension.width, menuDimension.height);
		}

		int btnSize = getHeight() - VERTICAL_SPACING;
		int padding = HORIZONTAL_SPACING;
		int xOffset = getWidth() - padding;
		int yOffset = IntUtil.major(0, ((getHeight() - btnSize) / 2));

		xOffset = prepareButton(titlePane.getCloseButton(), xOffset, yOffset, btnSize);
		if (LookAndFeelUtil.isFrameStateSupported(Toolkit.getDefaultToolkit(), Frame.MAXIMIZED_BOTH))
		xOffset = prepareButton(titlePane.getMaximizeButton(), xOffset, yOffset, btnSize);
		xOffset = prepareButton(titlePane.getMinimizeButton(), xOffset, yOffset, btnSize);

		buttonsOffset = getWidth() - xOffset - 100;
	}

	private int prepareButton(JButton button, int xOffset, int yOffset, int btnSize)
	{
		if (button != null)
		{
			xOffset += -btnSize;
			button.setBounds(xOffset, yOffset, btnSize, btnSize);
		}

		return xOffset;
	}

	private int computeHeight()
	{
		FontMetrics metrics = LookAndFeelUtil.getFontMetrics(titlePane, null, titlePane.getFont());
		int minHeight = metrics.getHeight() + 6;

		return minHeight > MIN_PANE_HEIGHT ? minHeight : MIN_PANE_HEIGHT;
	}

	private int getWidth()
	{
		return titlePane.getWidth();
	}

	private int getHeight()
	{
		return titlePane.getHeight();
	}

	public int getButtonsOffset()
	{
		return buttonsOffset;
	}
}
