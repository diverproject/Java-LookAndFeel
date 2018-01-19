package org.diverproject.themes.component;

import static org.diverproject.themes.component.ThemesUIFunctions.IconFactory;
import static org.diverproject.themes.component.ThemesUIFunctions.Theme;
import static org.diverproject.themes.component.ThemesUIFunctions.TitleColors;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.UIResource;

import org.diverproject.themes.component.listener.TitleMinimizeAction;
import org.diverproject.themes.component.listener.TitleMaximizeAction;
import org.diverproject.themes.component.listener.TitleRestoreAction;
import org.diverproject.themes.component.listener.WindowMaximize;
import org.diverproject.util.ImageUtil;
import org.diverproject.util.PaintUtil;
import org.diverproject.themes.DefaultIcon;
import org.diverproject.themes.LookAndFeelUtil;
import org.diverproject.themes.component.listener.TitleChangeListener;
import org.diverproject.themes.component.listener.TitleCloseAction;

public class SimpleTitlePane extends JComponent implements TitlePaneActions
{
	private static final long serialVersionUID = 3852132413501441885L;

	public static final String PAINT_ACTIVE = "paintActive";

	private static DefaultIcon iconifyIcons[];
	private static DefaultIcon maximizeIcons[];
	private static DefaultIcon minimizeIcons[];
	private static DefaultIcon closeIcons[];

	private Window window;
	private Action closeAction;
	private Action minimizeAction;
	private Action restoreAction;
	private Action maximizeAction;
	private boolean wasMaximized;

	private JRootPane rootPane;
	private SimpleRootPaneUI rootPaneUI;
	private SimpleTitleMenuBar menuBar;
	private JPanel customTitlePanel;
	private SimpleTitleButton minimizeButton;
	private SimpleTitleButton maximizeButton;
	private SimpleTitleButton closeButton;

	private int state;
	private boolean noMaximized;
	private float opacity;
	private BufferedImage background;
	private TitlePaneLayout layout;

	private WindowListener windowListener;
	private PropertyChangeListener propertyChangeListener;

	public SimpleTitlePane(JRootPane rootPane, SimpleRootPaneUI rootPaneUI)
	{
		this.rootPane = rootPane;
		this.rootPaneUI = rootPaneUI;

		setIcons();
		installDependences();
		installDefaults();
		setLayout((layout = new TitlePaneLayout(this)));
	}

	@Override
	public void addNotify()
	{
		super.addNotify();

		uninstallListeners();
		window = SwingUtilities.getWindowAncestor(this);

		if (window != null)
		{
			setState(window instanceof Frame ? SimpleRootPaneUI.getExtendedState((Frame) window) : 0);
			setActive(LookAndFeelUtil.isWindowActive(window));
			installListeners();
		}
	}

	@Override
	public void removeNotify()
	{
		super.removeNotify();

		uninstallListeners();
		window = null;
	}

	@Override
	public void iconify()
	{
		Frame frame = getFrame();

		if (frame != null)
			SimpleRootPaneUI.setExtendedState(frame, state | Frame.ICONIFIED);
	}

	@Override
	public void maximize()
	{
		Frame frame = getFrame();

		if (frame == null)
			return;

		validateMaximizedBounds();

		for (PropertyChangeListener listener : frame.getPropertyChangeListeners())
			listener.propertyChange(new PropertyChangeEvent(this, "windowMaximize", false, false));

		SimpleRootPaneUI.setExtendedState(frame, state | Frame.MAXIMIZED_BOTH);

		for (PropertyChangeListener listener : frame.getPropertyChangeListeners())
			listener.propertyChange(new PropertyChangeEvent(this, "windowMaximized", false, false));
	}

	@Override
	public void restore()
	{
		Frame frame = getFrame();

		if (frame == null)
			return;

		noMaximized = false;

		for (PropertyChangeListener listener : frame.getPropertyChangeListeners())
			listener.propertyChange(new PropertyChangeEvent(this, "windowRestore", false, false));

		if ((state & Frame.ICONIFIED) != 0)
			SimpleRootPaneUI.setExtendedState(frame, state & ~Frame.ICONIFIED);
		else
			SimpleRootPaneUI.setExtendedState(frame, state & ~Frame.MAXIMIZED_BOTH);

		for (PropertyChangeListener listener : frame.getPropertyChangeListeners())
			listener.propertyChange(new PropertyChangeEvent(this, "windowRestored", false, false));
	}

	@Override
	public void close()
	{
		if (window != null)
			window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		if (getFrame() != null)
			setState(SimpleRootPaneUI.getExtendedState(getFrame()));

		paintBackground(g);
		g.setFont(getFont());

		FontMetrics metrics = LookAndFeelUtil.getFontMetrics(this, g, getFont());
		Rectangle padding = new Rectangle(0, 0, getWidth(), getHeight());
		padding.y = ((padding.height - metrics.getHeight()) / 2) + metrics.getAscent();
		padding.x = 24; // FIXME como remover o ícone do java?

		int titleWidth = padding.width - layout.getButtonsOffset() -  4;
		String title = LookAndFeelUtil.clipText(getTitle(), metrics, titleWidth);

		int iconWidth = paintIcon(g, padding.x);
		titleWidth -= iconWidth + 4;
		padding.x += iconWidth + 4;

		titleWidth -= iconWidth + 4;

		if (Theme().isCenterWindowTitle())
		{
			int titleLength = metrics.stringWidth(title);
			padding.x += (titleWidth - titleLength) / 2;
		}

		paintText(g, padding.x, padding.y, title);
	}

	private int paintIcon(Graphics g, int x)
	{
		Image image = getFrameIconImage();

		if (image == null)
			return 0;

		Graphics2D g2d = (Graphics2D) g;
		Object oldHint = g2d.getRenderingHint(RenderingHints.KEY_INTERPOLATION);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		int height = getHeight() - 2;
		Dimension dimension = new Dimension(image.getWidth(null), image.getHeight(null));

		if (dimension.height <= height)
			g2d.drawImage(image, x, (height - dimension.height) / 2, dimension.width, dimension.height, null);
		else
		{
			double fac = dimension.getWidth() / dimension.getHeight();
			dimension.height = height;
			dimension.width = (int) (fac * dimension.getHeight());

			g2d.drawImage(image, x, 0, dimension.width, dimension.height, null);
		}

		if (oldHint != null)
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, oldHint);

		return dimension.width;
	}

	protected Image getFrameIconImage()
	{
		List<Image> icons = getWindow().getIconImages();

		if (icons != null && !icons.isEmpty())
			return icons.get(0);

		Window owner = getWindow().getOwner();

		if (owner != null)
			do {

				if ((icons = owner.getIconImages()) != null && !icons.isEmpty())
					return icons.get(0);

			} while ((owner = owner.getOwner()) != null);

		return ImageUtil.toImage(IconFactory().getApplicationIcon());
	}

	private void paintBackground(Graphics g)
	{
		if (!isActive())
			PaintUtil.background(g, TitleColors().getInactiveBackground(), 0, 0, getWidth(), getHeight());
		else
		{
			Graphics2D g2d = (Graphics2D) g;
			Composite oldComposite = g2d.getComposite();

			if (background != null)
			{
				g.drawImage(background, 0, 0, null);
				AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
				g2d.setComposite(alpha);
			}

			PaintUtil.background(g, TitleColors().getActiveBackground(), 0, 0, getWidth(), getHeight());
			g2d.setComposite(oldComposite);
		}
	}

	public void paintText(Graphics g, int x, int y, String title)
	{
		g.setColor(isActive() ? TitleColors().getActiveForeground() : TitleColors().getInactiveForeground());
		PaintUtil.drawString(rootPane, g, title, x, y);
	}

	protected void installListeners()
	{
		if (window == null)
			return;

		window.addWindowListener((windowListener = new WindowMaximize(this)));
		window.addPropertyChangeListener((propertyChangeListener = new TitleChangeListener(this)));
	}

	protected void uninstallListeners()
	{
		if (window == null)
			return;

		window.removeWindowListener(windowListener);
		window.removePropertyChangeListener(propertyChangeListener);
	}

	protected void installDependences()
	{
		newActions();
		newButtons();

		if (rootPane.getWindowDecorationStyle() == JRootPane.FRAME)
		{
			newMenuBar();
			add(menuBar);
			add(minimizeButton);
			add(maximizeButton);
		}

		add(closeButton);
	}

	protected void installDefaults()
	{
		setFont(UIManager.getFont("InternalFrame.titleFont"));

		if (rootPane.getClientProperty("customTitlePanel") instanceof JPanel)
			setCustomizedTitlePanel((JPanel) rootPane.getClientProperty("customTitlePanel"));
	}

	public void setCustomizedTitlePanel(JPanel panel)
	{
		if (customTitlePanel != null)
		{
			remove(customTitlePanel);
			customTitlePanel = null;
		}

		if (panel != null)
			add((customTitlePanel = panel));

		rootPane.putClientProperty("customTitlePanel", customTitlePanel);
		revalidate();
		repaint();
	}

	private void newActions()
	{
		closeAction = new TitleCloseAction(this);
		minimizeAction = new TitleMinimizeAction(this);
		restoreAction = new TitleRestoreAction(this);
		maximizeAction = new TitleMaximizeAction(this);
	}

	private void newButtons()
	{
		minimizeButton = new SimpleTitleButton(minimizeAction, SimpleTitleButton.ICONIFY, minimizeIcons);
		maximizeButton = new SimpleTitleButton(maximizeAction, SimpleTitleButton.MAXIMIZE, maximizeIcons);
		closeButton = new SimpleTitleButton(closeAction, SimpleTitleButton.CLOSE, closeIcons);
	}

	private void newMenuBar()
	{
		menuBar = new SimpleTitleMenuBar(this);

		if (rootPane.getWindowDecorationStyle() == JRootPane.FRAME)
		{
			JMenu menu = new JMenu("   ");

			menu.add(restoreAction).setMnemonic(getInt("MetalTitlePane.restoreMnemonic"));
			menu.add(minimizeAction).setMnemonic(getInt("MetalTitlePane.iconifyMnemonic"));

			if (LookAndFeelUtil.isFrameStateSupported())
				menu.add(maximizeAction).setMnemonic(getInt("MetalTitlePane.maximizeMnemonic"));

			menu.addSeparator();
			menu.add(closeAction).setMnemonic(getInt("MetalTitlePane.closeMnemonic"));

			menuBar.add(menu);
		}
	}

	private void validateMaximizedBounds()
	{
		Frame frame = getFrame();

		if (frame != null && !noMaximized)
		{
			GraphicsConfiguration gc = frame.getGraphicsConfiguration();
			Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
			Rectangle bounds = gc.getBounds();
			bounds.x = Math.max(0, insets.left);
			bounds.y = Math.max(0, insets.top);
			bounds.width -= (insets.left + insets.right);
			bounds.height -= (insets.top + insets.bottom);
			frame.setMaximizedBounds(bounds);
		}
	}

	public boolean wasMaximized()
	{
		return wasMaximized;
	}

	public void setWasMaximized(boolean wasMaximized)
	{
		this.wasMaximized = wasMaximized;
	}

	public void setState(int state)
	{
		setState(state, false);
	}

	protected void setState(int state, boolean updateRegardless)
	{
		if (window == null || rootPane.getWindowDecorationStyle() != JRootPane.FRAME || (this.state == state && !updateRegardless))
			return;

		Frame frame = getFrame();

		if (frame == null)
			setStateNoFrame();
		else
			setStateFrame(frame, state);

		closeAction.setEnabled(true);
		this.state = state;
	}

	private void setStateNoFrame()
	{
		minimizeAction.setEnabled(false);
		maximizeAction.setEnabled(false);
		restoreAction.setEnabled(false);

		remove(maximizeButton);
		remove(minimizeButton);
		revalidate();
		repaint();		
	}

	private void setStateFrame(Frame frame, int state)
	{
		if ((state & Frame.MAXIMIZED_BOTH) != 0 && (rootPane.getBorder() == null || rootPane.getBorder() instanceof UIResource) && frame.isShowing())
			rootPane.setBorder(null);
		else
			rootPaneUI.installBorder(rootPane);

		if (!frame.isResizable())
		{
			maximizeAction.setEnabled(false);
			restoreAction.setEnabled(false);

			if (maximizeButton.getParent() != null)
			{
				remove(maximizeButton);
				revalidate();
				repaint();
			}
		}

		else
		{
			if ((state & Frame.MAXIMIZED_BOTH) != 0)
			{
				updateMaximizeButton(restoreAction, iconifyIcons);
				maximizeAction.setEnabled(false);
				restoreAction.setEnabled(true);
			}

			else
			{
				updateMaximizeButton(maximizeAction, maximizeIcons);
				maximizeAction.setEnabled(true);
				restoreAction.setEnabled(false);
			}

			if (maximizeButton.getParent() == null || minimizeButton.getParent() == null)
			{
				add(maximizeButton);
				add(minimizeButton);
				revalidate();
				repaint();
			}

			maximizeButton.setText(null);
		}
	}

	private void updateMaximizeButton(Action action, DefaultIcon icons[])
	{
		maximizeButton.setAction(action);
		maximizeButton.setIcons(icons);
	}

	public boolean isActive()
	{
		return (window == null) ? true : LookAndFeelUtil.isWindowActive(window);
	}

	public void setActive(boolean active)
	{
		if (rootPane.getWindowDecorationStyle() == JRootPane.FRAME)
		{
			minimizeButton.putClientProperty(PAINT_ACTIVE, active);
			maximizeButton.putClientProperty(PAINT_ACTIVE, active);
			closeButton.putClientProperty(PAINT_ACTIVE, active);
		}

		getRootPane().repaint();
	}

	public void setBackground(BufferedImage background)
	{
		this.background = background;
	}

	public void setOpacity(float opacity)
	{
		this.opacity = opacity;
	}

	public Window getWindow()
	{
		return window == null ? (window = SwingUtilities.getWindowAncestor(this)) : window;
	}

	public Frame getFrame()
	{
		return (Frame) getWindow();
	}

	public String getTitle()
	{
		if (window instanceof Frame)
			return ((Frame) window).getTitle();

		if (window instanceof Dialog)
			return ((Dialog) window).getTitle();

		return null;
	}

	public JMenuBar getJMenuBar()
	{
		return menuBar;
	}

	public SimpleTitleButton getMinimizeButton()
	{
		return minimizeButton;
	}

	public SimpleTitleButton getMaximizeButton()
	{
		return maximizeButton;
	}

	public SimpleTitleButton getCloseButton()
	{
		return closeButton;
	}

	public static void setIcons()
	{
		iconifyIcons = new DefaultIcon[]	{ IconFactory().getIconifyIcon(),	IconFactory().getIconifyHoverIcon(),	IconFactory().getIconifyActiveIcon() };
		maximizeIcons = new DefaultIcon[]	{ IconFactory().getMaximizeIcon(),	IconFactory().getMaximizeHoverIcon(),	IconFactory().getMaximizeActiveIcon() };
		minimizeIcons = new DefaultIcon[]	{ IconFactory().getMinimizeIcon(),	IconFactory().getMinimizeHoverIcon(),	IconFactory().getMinimizeActiveIcon() };
		closeIcons = new DefaultIcon[]		{ IconFactory().getCloseIcon(),		IconFactory().getCloseHoverIcon(),		IconFactory().getCloseActiveIcon() };
	}

	private static int getInt(String key)
	{
		return getInt(key, -1);
	}

	private static int getInt(String key, int noValue)
	{
		Object value = UIManager.get(key);

		if (value instanceof Integer)
			return ((Integer) value).intValue();

		if (value instanceof String)
			try {
				return Integer.parseInt((String) value);
			} catch (NumberFormatException nfe) {
			}

		return noValue;
	}
}
