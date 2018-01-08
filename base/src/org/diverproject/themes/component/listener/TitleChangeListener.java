package org.diverproject.themes.component.listener;

import java.awt.Frame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.diverproject.themes.component.SimpleRootPaneUI;
import org.diverproject.themes.component.SimpleTitlePane;

public class TitleChangeListener implements PropertyChangeListener
{
	private SimpleTitlePane titlePane;

	public TitleChangeListener(SimpleTitlePane titlePane)
	{
		this.titlePane = titlePane;
	}

	@Override
	public void propertyChange(PropertyChangeEvent e)
	{
		switch (e.getPropertyName())
		{
			case "resizable":
			case "state":
				Frame frame = titlePane.getFrame();
				if (frame != null)
					titlePane.setState(SimpleRootPaneUI.getExtendedState(frame));
				if (e.getPropertyName().equals("resizeable"))
					titlePane.repaint();
				break;

			case "title":
				titlePane.repaint();
				break;

			case "componentOrientation":
				titlePane.revalidate();
				titlePane.repaint();
				break;

			case "windowRestored":
				titlePane.setWasMaximized(false);
				break;

			case "windowMaximized":
				titlePane.setWasMaximized(true);
				break;
		}
	}

}
