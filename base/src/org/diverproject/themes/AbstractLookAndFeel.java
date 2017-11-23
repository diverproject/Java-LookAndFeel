package org.diverproject.themes;

import javax.swing.plaf.metal.MetalLookAndFeel;

public abstract class AbstractLookAndFeel extends MetalLookAndFeel
{
	private static final long serialVersionUID = -3225208352514175597L;

	public abstract AbstractForderFactory getBorderFactory();
    public abstract AbstractIconFactory getIconFactory();
	public abstract AbstractTheme getTheme();
}
