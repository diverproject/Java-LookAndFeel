package org.diverproject.themes;

import org.diverproject.util.UtilException;

public class ThemeException extends UtilException
{
	private static final long serialVersionUID = -176694914670748424L;

	public ThemeException(String message)
	{
		super(message);
	}

	public ThemeException(String format, Object... args)
	{
		super(format, args);
	}

	public ThemeException(Exception e)
	{
		super(e);
	}

	public ThemeException(Exception e, String format, Object... args)
	{
		super(e, format, args);
	}
}
