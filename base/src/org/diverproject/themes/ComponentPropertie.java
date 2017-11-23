package org.diverproject.themes;

import static org.diverproject.util.Util.format;

import javax.swing.UIDefaults;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.FontUIResource;

public class ComponentPropertie
{
	UIDefaults uiDefaults;
	private String prefix;

	public ComponentPropertie(String prefix)
	{
		this.prefix = prefix;
	}

	public void setUI(Class<? extends ComponentUI> cls)
	{
		if (uiDefaults != null && cls != null)
			uiDefaults.putDefaults(new Object[] { format("%sUI", prefix), cls.getName() });
	}

	public void setFont(FontUIResource font)
	{
		if (uiDefaults != null && font != null)
			uiDefaults.putDefaults(new Object[] { format("%s.font", prefix), font });
	}

	public void setBorder(Border border)
	{
		if (uiDefaults != null && border != null)
			uiDefaults.putDefaults(new Object[] { format("%s.border", prefix), border });
	}
}
