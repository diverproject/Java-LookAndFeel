package org.diverproject.themes.component.listener;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.UIManager;

import org.diverproject.themes.component.SimpleTitlePane;

public class TitleCloseAction extends AbstractAction
{
	private static final long serialVersionUID = 1355007769147042998L;

	private SimpleTitlePane titlePane;

	public TitleCloseAction(SimpleTitlePane titlePane)
	{
		super(UIManager.getString("MetalTitlePane.closeTitle"));

		this.titlePane = titlePane;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		titlePane.close();
	}
}
