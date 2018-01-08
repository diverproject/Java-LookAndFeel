package org.diverproject.themes.component;

import static javax.swing.JRootPane.COLOR_CHOOSER_DIALOG;
import static javax.swing.JRootPane.ERROR_DIALOG;
import static javax.swing.JRootPane.FILE_CHOOSER_DIALOG;
import static javax.swing.JRootPane.FRAME;
import static javax.swing.JRootPane.INFORMATION_DIALOG;
import static javax.swing.JRootPane.NONE;
import static javax.swing.JRootPane.PLAIN_DIALOG;
import static javax.swing.JRootPane.QUESTION_DIALOG;
import static javax.swing.JRootPane.WARNING_DIALOG;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JRootPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicRootPaneUI;

import org.diverproject.themes.component.listener.MouseInputWindowResize;

public class SimpleRootPaneUI extends BasicRootPaneUI
{
	public static final int BORDER_DRAG_THICKNESS = 5;

	public static final Dimension MINIMUM_SIZE = new Dimension(256, 144);
	public static final Dimension MAXIMUM_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	public static SimpleRootPaneUI createUI(JComponent component)
	{
		return new SimpleRootPaneUI();
	}

	private Window window;
	private WindowListener windowListener;

	private Cursor oldCursor;
	private JRootPane rootPane;
	private SimpleTitlePane titlePane;
	private LayoutManager layoutManager;
	private LayoutManager oldLayoutManager;
	private MouseInputListener mouseInputListener;
	private PropertyChangeListener propertyChangeListener;

	@Override
	public void installUI(JComponent c)
	{
		super.installUI(c);

		rootPane = (JRootPane) c;

		if (rootPane.getWindowDecorationStyle() != NONE)
			installDecorations(rootPane);
	}

	@Override
	public void uninstallUI(JComponent c)
	{
		super.uninstallUI(c);

		uninstallDecorations(rootPane);

		rootPane = null;
        layoutManager = null;
        mouseInputListener = null;
	}

	@Override
	protected void installListeners(JRootPane root)
	{
		super.installListeners(root);

		if (root.getWindowDecorationStyle() != NONE)
			return;

		root.addPropertyChangeListener(propertyChangeListener = new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent e)
			{
				if (e.getPropertyName().equals("ancestor"))
					if (rootPane != null && rootPane.getParent() instanceof Window)
					{
						 window = (Window) rootPane.getParent();
						 window.addWindowListener(windowListener = new WindowAdapter()
						 {
							 @Override
							 public void windowActivated(WindowEvent e)
							 {
								 if (rootPane != null)
									 rootPane.repaint();
							 };

							 @Override
							 public void windowDeactivated(WindowEvent e)
							 {
								 if (rootPane != null)
									 rootPane.repaint();
							 };
						 });
					}
			}
		});
	}

	@Override
	protected void uninstallListeners(JRootPane root)
	{
		super.uninstallListeners(root);

		if (root.getWindowDecorationStyle() != NONE)
			return;

		if (window != null && windowListener != null)
			window.removeWindowListener(windowListener);

		root.removePropertyChangeListener(propertyChangeListener);
	}

	public void installDecorations(JRootPane root)
	{
		installBorder(root);

		if (titlePane == null)
			setTitlePane(root, new SimpleTitlePane(root, this));

		installWindowListeners(root, root.getParent());
		installLayout(root);

		if (window != null)
		{
			oldCursor = window.getCursor();

			root.revalidate();
			root.repaint();
		}
	}

	public void uninstallDecorations(JRootPane root)
	{
		uninstallBorder(root);
		uninstallWindowListeners(root);
		setTitlePane(root, null);
		uninstallLayout(root);

		if (root.getWindowDecorationStyle() == NONE)
		{
			root.repaint();
			root.revalidate();
		}

		if (window != null)
		{
			window.setCursor(oldCursor);
			window = null;
		}
	}

	public void installBorder(JRootPane root)
	{
		switch (root.getWindowDecorationStyle())
		{
			case NONE:
				LookAndFeel.uninstallBorder(root);
				break;

			case FRAME:
				LookAndFeel.installBorder(root, "RootPane.frameBorder");
				break;

			case PLAIN_DIALOG:
				LookAndFeel.installBorder(root, "RootPane.plainDialogBorder");
				break;

			case INFORMATION_DIALOG:
				LookAndFeel.installBorder(root, "RootPane.informationDialogBorder");
				break;

			case ERROR_DIALOG:
				LookAndFeel.installBorder(root, "RootPane.errorDialogBorder");
				break;

			case COLOR_CHOOSER_DIALOG:
				LookAndFeel.installBorder(root, "RootPane.colorChooserDialogBorder");
				break;

			case FILE_CHOOSER_DIALOG:
				LookAndFeel.installBorder(root, "RootPane.fileChooserDialogBorder");
				break;

			case QUESTION_DIALOG:
				LookAndFeel.installBorder(root, "RootPane.questionDialogBorder");
				break;

			case WARNING_DIALOG:
				LookAndFeel.installBorder(root, "RootPane.warningDialogBorder");
				break;
		}
	}

	private void uninstallBorder(JRootPane root)
	{
		LookAndFeel.uninstallBorder(root);
	}

	private void installWindowListeners(JRootPane root, Component parent)
	{
		window = (parent instanceof Window) ? (Window) parent : SwingUtilities.getWindowAncestor(parent);

		if (window instanceof Frame)
			titlePane.setState(SimpleRootPaneUI.getExtendedState((Frame) window));

		if (window != null)
		{
			if (mouseInputListener == null)
				mouseInputListener = new MouseInputWindowResize(this);

			window.addMouseListener(mouseInputListener);
			window.addMouseMotionListener(mouseInputListener);
		}
	}

	private void uninstallWindowListeners(JRootPane root)
	{
		if (window != null)
		{
			window.removeMouseListener(mouseInputListener);
			window.removeMouseMotionListener(mouseInputListener);
		}
	}

	private void installLayout(JRootPane root)
	{
		if (layoutManager == null)
			layoutManager = new DefaultLayoutManager();

		oldLayoutManager = root.getLayout();
		root.setLayout(layoutManager);
	}

	public void uninstallLayout(JRootPane root)
	{
		if (oldLayoutManager != null)
		{
			root.setLayout(oldLayoutManager);
			oldLayoutManager = null;
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent e)
	{
		super.propertyChange(e);

		JRootPane root = (JRootPane) e.getSource();

		switch (e.getPropertyName())
		{
			case "windowDecorationStyle":
				uninstallDecorations(root);
				if (root.getWindowDecorationStyle() != NONE)
					installDecorations(root);
				break;

			case "ancestor":
				uninstallWindowListeners(root);
				if (root.getWindowDecorationStyle() != NONE)
					installWindowListeners(root, root.getParent());
				break;
		}
	}

	public JRootPane getJRootPane()
	{
		return rootPane;
	}

	public SimpleTitlePane getTitlePane()
	{
		return titlePane;
	}

	private void setTitlePane(JRootPane root, SimpleTitlePane titlePane)
	{
		JComponent oldTitlePane = this.titlePane;

		if ((oldTitlePane == null && titlePane == null) || oldTitlePane != null && oldTitlePane.equals(titlePane))
			return;

		if (oldTitlePane != null)
		{
			oldTitlePane.setVisible(false);
			root.getLayeredPane().remove(oldTitlePane);
		}

		if (titlePane != null)
		{
			root.getLayeredPane().add(titlePane, JLayeredPane.FRAME_CONTENT_LAYER);
			titlePane.setVisible(true);
		}

		this.titlePane = titlePane;
	}

	public Cursor getOldCursor()
	{
		return oldCursor;
	}

	public static int getExtendedState(Frame frame)
	{
		try {

			Method method = frame.getClass().getMethod("getExtendedState");
			Integer extendedState = (Integer) method.invoke(frame);

			return extendedState.intValue();

		} catch (Exception ex) {
		}

		return 0;
	}

	public static void setExtendedState(Frame frame, int state)
	{
		try {

			Class<?> parameters[] = new Class<?>[] { Integer.TYPE };
			Method method = frame.getClass().getMethod("setExtendedState", parameters);
			method.invoke(frame, new Object[] { state });

		} catch (Exception ex) {
		}
	}

	public static void setDecorateWindows(boolean decorateWindows)
	{
		try {

			System.setProperty("sun.awt.noerasebackground", "true");
			System.setProperty("sun.awt.erasebackgroundonresize", "false");

			Class<?> paramTypes[] = new Class[] { Boolean.TYPE };
			Object paramValues[] = new Object[] { decorateWindows };

			Method jframeMethod = JFrame.class.getMethod("setDefaultLookAndFeelDecorated", paramTypes);
			jframeMethod.invoke(null, paramValues);

			Method jdialogMethod = JDialog.class.getMethod("setDefaultLookAndFeelDecorated", paramTypes);
			jdialogMethod.invoke(null, paramValues);

		} catch (Exception ex) {
		}
	}
}
