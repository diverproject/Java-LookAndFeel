package org.diverproject.themes;

import static org.diverproject.log.LogSystem.logError;
import static org.diverproject.util.Util.format;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.diverproject.util.ObjectDescription;

public class DefaultIcon implements Icon
{
	public static final int DEFAULT_SIZE = 16;
	public static final int DEFAULT_WIDTH = DEFAULT_SIZE;
	public static final int DEFAULT_HEIGHT = DEFAULT_SIZE;

	private String namepath;
	private ImageIcon icon;

	public DefaultIcon(String classPath, String name)
	{
		this(classPath, name, "png");
	}

	public DefaultIcon(String classPath, String name, String extension)
	{
		namepath = format("/%s/%s.%s", classPath, name, extension);
	}

	private Icon getIcon()
	{
		if (icon == null)
			try {
				icon = new ImageIcon(DefaultIcon.class.getResource(namepath));
			} catch (RuntimeException ase) {
				logError("'%s' não encontrado.", getNamePath());
			}

		return icon;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y)
	{
		if (getIcon() != null)
			icon.paintIcon(c, g, x, y);
		else
		{
            g.setColor(Color.red);
            g.fillRect(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            g.setColor(Color.white);
            g.drawLine(x, y, x + DEFAULT_WIDTH - 1, y + DEFAULT_HEIGHT - 1);
            g.drawLine(x + DEFAULT_WIDTH - 1, y, x, y + DEFAULT_HEIGHT - 1);
		}
	}

	@Override
	public int getIconWidth()
	{
		return icon != null ? icon.getIconWidth() : DEFAULT_WIDTH;
	}

	@Override
	public int getIconHeight()
	{
		return icon != null ? icon.getIconHeight() : DEFAULT_HEIGHT;
	}

	public String getNamePath()
	{
		return namepath;
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		description.append(getNamePath());

		return description.toString();
	}
}
