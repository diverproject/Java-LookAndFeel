package org.diverproject.themes;

import org.diverproject.util.UtilRuntimeException;

public class ThemeRuntimeException extends UtilRuntimeException
{
	private static final long serialVersionUID = -176694914670748424L;

	public ThemeRuntimeException(String message)
	{
		super(message);
	}

	public ThemeRuntimeException(String format, Object... args)
	{
		super(format, args);
	}

	public ThemeRuntimeException(Exception e)
	{
		super(e);
	}

	public ThemeRuntimeException(Exception e, String format, Object... args)
	{
		super(e, format, args);
	}
}