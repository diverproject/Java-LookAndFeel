package org.diverproject.themes.component.listener;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.UIManager;

import org.diverproject.themes.component.SimpleTitlePane;

public class TitleMaximizeAction extends AbstractAction
{
	private static final long serialVersionUID = 543278551466838007L;

	private SimpleTitlePane titlePane;

	public TitleMaximizeAction(SimpleTitlePane titlePane)
	{
		super(UIManager.getString("MetalTitlePane.maximizeTitle"));

		this.titlePane = titlePane;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		titlePane.maximize();
	}
}
