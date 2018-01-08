package org.diverproject.themes.component.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.diverproject.themes.component.SimpleTitlePane;

public class WindowMaximize extends WindowAdapter
{
	private SimpleTitlePane titlePane;

	public WindowMaximize(SimpleTitlePane titlePane)
	{
		this.titlePane = titlePane;
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{
		titlePane.setActive(true);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		titlePane.setActive(false);
	}
}
