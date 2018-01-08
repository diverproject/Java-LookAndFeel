package org.diverproject.themes.component.listener;

import static javax.swing.JRootPane.NONE;
import static org.diverproject.themes.component.SimpleRootPaneUI.BORDER_DRAG_THICKNESS;
import static org.diverproject.themes.component.SimpleRootPaneUI.MINIMUM_SIZE;
import static org.diverproject.themes.component.SimpleRootPaneUI.getExtendedState;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import org.diverproject.themes.component.TitlePaneActions;
import org.diverproject.themes.component.SimpleRootPaneUI;

public class MouseInputWindowResize implements MouseInputListener
{
	private SimpleRootPaneUI ui;
	private boolean movingWindow;
	private int dragOffsetX;
	private int dragOffsetY;
	private int dragWidth;
	private int dragHeight;
	private int dragCursor;

	public MouseInputWindowResize(SimpleRootPaneUI ui)
	{
		this.ui = ui;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (!(e.getSource() instanceof Frame))
			return;

		Frame frame = (Frame) e.getSource();
		Point convertedPoint = SwingUtilities.convertPoint(frame, e.getPoint(), ui.getJRootPane());

		if (ui.getTitlePane() != null && ui.getTitlePane().contains(convertedPoint) && frame.isResizable())
			if (e.getClickCount() % 2 == 0 && (e.getModifiers() & InputEvent.BUTTON1_MASK) != 0)
				if ((getExtendedState(frame) & Frame.MAXIMIZED_BOTH) != 0)
					((TitlePaneActions) ui.getTitlePane()).restore();
				else
					((TitlePaneActions) ui.getTitlePane()).maximize();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if (!(e.getSource() instanceof Window) || ui.getJRootPane().getWindowDecorationStyle() == NONE)
			return;	

		Window window = (Window) e.getSource();
		window.toFront();

		Point dragOffset = e.getPoint();
		Frame frame = window instanceof Frame ? (Frame) window : null;
		Dialog dialog = window instanceof Dialog ? (Dialog) window : null;

		int state = frame != null ? getExtendedState(frame) : 0;

		if (ui.getTitlePane() != null)
		{
			Point dragConvertedOffset = SwingUtilities.convertPoint(window, dragOffset, ui.getTitlePane());

			if (ui.getTitlePane().contains(dragConvertedOffset))
			{
				if ((frame != null && ui.getTitlePane().contains(dragConvertedOffset) &&
						(state & Frame.MAXIMIZED_BOTH) == 0 || dialog != null) &&
						dragOffset.y >= BORDER_DRAG_THICKNESS &&
						dragOffset.x >= BORDER_DRAG_THICKNESS &&
						dragOffset.x < window.getWidth() - BORDER_DRAG_THICKNESS)
					{
						movingWindow = true;
						dragOffsetX = dragOffset.x;
						dragOffsetY = dragOffset.y;

						for (PropertyChangeListener listener : window.getPropertyChangeListeners())
							listener.propertyChange(new PropertyChangeEvent(window, "windowMoving", Boolean.FALSE, Boolean.FALSE));
					}
			}
		}

		if (!movingWindow && (frame != null && frame.isResizable() && (state & Frame.MAXIMIZED_BOTH) == 0) || (dialog != null && dialog.isResizable()))
		{
			dragOffsetX = dragOffset.x;
			dragOffsetY = dragOffset.y;
			dragWidth = window.getWidth();
			dragHeight = window.getHeight();
			dragCursor = parseCursorCornor(window, dragOffset.x, dragOffset.y);

			for (PropertyChangeListener listener : window.getPropertyChangeListeners())
				listener.propertyChange(new PropertyChangeEvent(window, "windowResizing", Boolean.FALSE, Boolean.FALSE));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (!(e.getSource() instanceof Window))
			return;

		Window window = (Window) e.getSource();

		if (window != null && ui.getJRootPane() != null)
		{
			if (dragCursor != 0 && !window.isValid())
			{
				window.validate();
				ui.getJRootPane().repaint();
			}

			for (PropertyChangeListener listener : window.getPropertyChangeListeners())
			{
				if (movingWindow)
					listener.propertyChange(new PropertyChangeEvent(window, "windowMoved", Boolean.FALSE, Boolean.FALSE));
				else
					listener.propertyChange(new PropertyChangeEvent(window, "windowResized", Boolean.FALSE, Boolean.FALSE));
			}
		}

		movingWindow = false;
		dragCursor = 0;
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		mouseMoved(e);
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		if (e.getSource() instanceof Window)
			((Window) e.getSource()).setCursor(ui.getOldCursor());
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		if (!(e.getSource() instanceof Window))
			return;

		Window window = (Window) e.getSource();
		int minScreenY = getMinScreenY();

		if (movingWindow)
		{
			Point location = e.getLocationOnScreen();
			location.x -= dragOffsetX;
			location.y = Math.max(minScreenY, location.y - dragOffsetY);
			window.setLocation(location);
		}

		else if (dragCursor != 0)
		{
			Point point = e.getPoint();
			Rectangle bounds = window.getBounds();
			Rectangle oldBounds = new Rectangle(bounds);
			Rectangle resizeBounds = new Rectangle();

			switch (dragCursor)
			{
				case Cursor.E_RESIZE_CURSOR:
					resizeBounds.width = point.x + (dragWidth - dragOffsetX) - bounds.width;
					break;

				case Cursor.S_RESIZE_CURSOR:
					resizeBounds.height = point.y + (dragHeight - dragOffsetY) - bounds.height;
					break;

				case Cursor.N_RESIZE_CURSOR:
					resizeBounds.y = point.y - dragOffsetY;
					resizeBounds.height = -(point.y - dragOffsetY);
					break;

				case Cursor.W_RESIZE_CURSOR:
					resizeBounds.x = point.x - dragOffsetX;
					resizeBounds.width = -(point.x - dragOffsetX);
					break;

				case Cursor.NE_RESIZE_CURSOR:
					resizeBounds.y = point.y - dragOffsetY;
					resizeBounds.width = point.x + (dragWidth - dragOffsetX) - bounds.width;
					resizeBounds.height = -(point.y - dragOffsetY);
					break;

				case Cursor.SE_RESIZE_CURSOR:
					resizeBounds.width = point.x + (dragWidth - dragOffsetX) - bounds.width;
					resizeBounds.height = point.y + (dragHeight - dragOffsetY) - bounds.height;
					break;

				case Cursor.NW_RESIZE_CURSOR:
					resizeBounds.x = point.x - dragOffsetX;
					resizeBounds.y = point.y - dragOffsetY;
					resizeBounds.width = -(point.x - dragOffsetX);
					resizeBounds.height = -(point.y - dragOffsetY);
					break;

				case Cursor.SW_RESIZE_CURSOR:
					resizeBounds.x = point.x - dragOffsetX;
					resizeBounds.y = 0;
					resizeBounds.width = -(point.x - dragOffsetX);
					resizeBounds.height = point.y + (dragHeight - dragOffsetY) - bounds.height;
					break;
			}

			resize(bounds, MINIMUM_SIZE, resizeBounds);

			if (!bounds.equals(oldBounds))
			{
				if (bounds.y < minScreenY)
				{
					bounds.y = minScreenY;
					bounds.height -= minScreenY - bounds.y;
				}

				window.setBounds(bounds);
				window.validate();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		if (!(e.getSource() instanceof Window) || ui.getJRootPane().getWindowDecorationStyle() == NONE)
			return;

		Window window = (Window) e.getSource();
		int cursor = parseCursorCornor(window, e.getX(), e.getY());

		if (cursor == 0)
			window.setCursor(ui.getOldCursor());
		else
		{
			Frame frame = window instanceof Frame ? (Frame) window : null;
			Dialog dialog = window instanceof Dialog ? (Dialog) window : null;

			if ((frame != null && (frame.isResizable() && (getExtendedState(frame) & Frame.MAXIMIZED_BOTH) == 0)) ||
				dialog != null && dialog.isResizable())
				window.setCursor(Cursor.getPredefinedCursor(cursor));
			else
				window.setCursor(ui.getOldCursor());
		}
	}

	private int parseCursorCornor(Component c, int x, int y)
	{
		if (x <= BORDER_DRAG_THICKNESS)
		{
			if (y <= BORDER_DRAG_THICKNESS)
				return Cursor.NW_RESIZE_CURSOR;

			if (y >= c.getHeight() - BORDER_DRAG_THICKNESS)
				return Cursor.SW_RESIZE_CURSOR;

			return Cursor.W_RESIZE_CURSOR;
		}

		if (x >= c.getWidth() - BORDER_DRAG_THICKNESS)
		{
			if (y <= BORDER_DRAG_THICKNESS)
				return Cursor.NE_RESIZE_CURSOR;

			if (y >= c.getHeight() - BORDER_DRAG_THICKNESS)
				return Cursor.SE_RESIZE_CURSOR;

			return Cursor.E_RESIZE_CURSOR;
		}

		if (y <= BORDER_DRAG_THICKNESS)
			return Cursor.N_RESIZE_CURSOR;

		if (y >= c.getHeight() - BORDER_DRAG_THICKNESS)
			return Cursor.S_RESIZE_CURSOR;

		if (x <= BORDER_DRAG_THICKNESS)
			return Cursor.W_RESIZE_CURSOR;

		if (x >= c.getWidth() - BORDER_DRAG_THICKNESS)
			return Cursor.E_RESIZE_CURSOR;

		return Cursor.DEFAULT_CURSOR;
	}

	private int getMinScreenY()
	{
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		int minScreenY = 0;

		for (GraphicsDevice device : environment.getScreenDevices())
		{
			GraphicsConfiguration configuration = device.getDefaultConfiguration();
			int minY = configuration.getBounds().y + Toolkit.getDefaultToolkit().getScreenInsets(configuration).top;
			minScreenY = minScreenY == 0 ? minY : Math.min(minScreenY, minY);
		}

		return minScreenY;
	}

	private void resize(Rectangle bounds, Dimension min, Rectangle resized)
	{
		bounds.x += resized.x;
		bounds.y += resized.y;
		bounds.width += resized.width;
		bounds.height += resized.height;

		if (min != null)
		{
			if (bounds.width < min.width)
			{
				bounds.x -= (resized.x != 0) ? min.width - bounds.width : 0;
				bounds.width = min.width;
			}

			if (bounds.height < min.height)
			{
				bounds.y -= (resized.y != 0) ? min.height - bounds.height : 0;
				bounds.height = min.height;
			}
		}

		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice devices[] = environment.getScreenDevices();
		GraphicsDevice device = devices[0]; // TODO verificar qual tela está sendo usada?

		if (bounds.x + bounds.width > device.getDisplayMode().getWidth())
			bounds.width = device.getDisplayMode().getWidth() - bounds.x;

		if (bounds.y + bounds.height > device.getDisplayMode().getHeight())
			bounds.height = device.getDisplayMode().getHeight() - bounds.y;
	}
}
