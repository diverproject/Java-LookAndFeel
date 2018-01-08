package org.diverproject.themes;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Window;
import java.lang.reflect.Method;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

public class LookAndFeelUtil
{
	private static final String ELLIPSIS = "...";

	public static boolean isFrameStateSupported()
	{
		return isFrameStateSupported(Frame.MAXIMIZED_BOTH);
	}

	public static boolean isFrameStateSupported(int state)
	{
		return isFrameStateSupported(Toolkit.getDefaultToolkit(), state);
	}

	public static boolean isFrameStateSupported(Toolkit tk, int state)
	{
		try {

			Class<?> parameters[] = { Integer.TYPE };
			Method method = tk.getClass().getMethod("isFrameStateSupported", parameters);

			return (boolean) method.invoke(tk, new Object[] { state });

		} catch (Exception ex) {
		}

		return false;
	}

    public static boolean isActive(JComponent c)
	{
		if (c == null)
			return false;

		boolean active = true;

		if (c instanceof JInternalFrame)
			active = ((JInternalFrame) c).isSelected();

		if (active)
		{
			Container parent = c.getParent();

			if (parent != null)
				do {
					if (parent instanceof JInternalFrame)
					{
						active = ((JInternalFrame) parent).isSelected();
						break;
					}
				} while ((parent = parent.getParent()) != null);

			if (active)
				active = isFrameActive(c);
		}

		return active;
	}

	private static boolean isFrameActive(Component c)
	{
		if (c == null)
			return false;

		Window window = SwingUtilities.getWindowAncestor(c);

		if (window != null)
		{
			if (window.getClass().getName().indexOf("Popup") >= 0)
				return true;

			return isWindowActive(window);
		}

		return true;
	}

	public static boolean isWindowActive(Window window)
	{
		try {

			Method method = window.getClass().getMethod("isActive", new Class<?>[0]);
			Boolean active = (Boolean) method.invoke(window, new Object[0]);

			return active;

		} catch (Exception e) {
		}

		return true;
	}

	public static FontMetrics getFontMetrics(JComponent component, Graphics graphics, Font font)
	{
		FontMetrics metrics = null;

		try {

			Class<?> swingUtilities2 = Class.forName("sun.swing.SwingUtilities2");
			Class<?> typeParameters[] = new Class<?>[] { JComponent.class, Graphics.class, Font.class };
			Method method = swingUtilities2.getMethod("getFontMetrics", typeParameters);
			metrics = (FontMetrics) method.invoke(null, new Object[] { component, graphics, font });

		} catch (Exception e) {
		}

		if (metrics == null)
		{
			if (graphics == null && component != null)
				graphics = component.getGraphics();

			if (graphics != null)
				metrics = font != null ? graphics.getFontMetrics(font) : graphics.getFontMetrics();

			else if (component != null)
				metrics = font != null ? component.getFontMetrics(font) : component.getFontMetrics(component.getFont());
		}

		return metrics;
	}

	public static String clipText(String text, FontMetrics metrics, int maxwidth)
	{
		if (text == null || text.isEmpty())
			return "";

		int width = SwingUtilities.computeStringWidth(metrics, text);

		if (width > maxwidth)
		{
			int total = SwingUtilities.computeStringWidth(metrics, ELLIPSIS);

			for (int i = 0; i < text.length(); i++)
			{
				total += metrics.charWidth(text.charAt(i));

				if (total > maxwidth)
					return text.substring(0, i) + ELLIPSIS;
			}
		}

		return text;
	}
}
