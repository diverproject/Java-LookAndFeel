package org.diverproject.themes.component.listener;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.UIManager;

import org.diverproject.themes.component.SimpleTitlePane;

public class TitleMinimizeAction extends AbstractAction
{
	private static final long serialVersionUID = -1425488351564599452L;

	private SimpleTitlePane titlePane;

	public TitleMinimizeAction(SimpleTitlePane titlePane)
	{
		super(UIManager.getString("MetalTitlePane.iconifyTitle"));

		this.titlePane = titlePane;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		titlePane.iconify();
	}
}
