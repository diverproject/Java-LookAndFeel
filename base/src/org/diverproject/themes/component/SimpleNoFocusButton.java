package org.diverproject.themes.component;

import javax.swing.Icon;
import javax.swing.JButton;

public class SimpleNoFocusButton extends JButton
{
	private static final long serialVersionUID = 3952822422630875565L;

	public SimpleNoFocusButton()
	{
		init();
	}

	public SimpleNoFocusButton(Icon icon)
	{
		super(icon);
		init();
	}

	private void init()
	{
		setFocusPainted(false);
		setRolloverEnabled(true);
		setFocusable(false);
	}

	@Override
	public boolean isFocusTraversable()
	{
		return false;
	}

	@Override
	@Deprecated
	public void requestFocus()
	{

	}
}
