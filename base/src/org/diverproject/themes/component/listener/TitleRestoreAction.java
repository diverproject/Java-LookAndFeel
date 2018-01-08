package org.diverproject.themes.component.listener;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.UIManager;

import org.diverproject.themes.component.SimpleTitlePane;

public class TitleRestoreAction extends AbstractAction
{
	private static final long serialVersionUID = -3460969849279551752L;

	private SimpleTitlePane titlePane;

	public TitleRestoreAction(SimpleTitlePane titlePane)
	{
		super(UIManager.getString("MetalTitlePane.restoreTitle"));

		this.titlePane = titlePane;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		titlePane.restore();
	}
}
