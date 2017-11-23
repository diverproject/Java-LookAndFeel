package org.diverproject.themes.blackdimond;

import javax.swing.UIDefaults;

import org.diverproject.themes.AbstractLookAndFeel;
import org.diverproject.themes.component.SimpleMenuBarUI;
import org.diverproject.themes.component.SimpleMenuUI;
import org.diverproject.themes.component.SimplePopupMenuUI;

public class BlackDiamondLookAndFeel extends AbstractLookAndFeel
{
	private static final long serialVersionUID = 8809429476998940167L;
	private static final BlackDiamondLookAndFeel INSTANCE = new BlackDiamondLookAndFeel();

	private final BlackDiamondTheme theme = new BlackDiamondTheme();

	public BlackDiamondTheme getTheme()
	{
		return theme;
	}

	@Override
	public BlackDiamondBorderFactory getBorderFactory()
	{
		return BlackDiamondBorderFactory.getInstance();
	}

	@Override
	public BlackDiamondIconFactory getIconFactory()
	{
		return BlackDiamondIconFactory.getInstance();
	}

	@Override
	public String getDescription()
	{
		return "Black Dimond Java Desktop Theme";
	}

	@Override
	public String getID()
	{
		return "BlackDimond";
	}

	@Override
	public String getName()
	{
		return "Black Dimond";
	}

	@Override
	public boolean isNativeLookAndFeel()
	{
		return false;
	}

	@Override
	public boolean isSupportedLookAndFeel()
	{
		return true;
	}

	@Override
	protected void initClassDefaults(UIDefaults table)
	{
		super.initClassDefaults(table);

		table.putDefaults(new Object[]
		{
			           "MenuBarUI", SimpleMenuBarUI.class.getName(),
			              "MenuUI", SimpleMenuUI.class.getName(),
			         "PopupMenuUI", SimplePopupMenuUI.class.getName(),
			/*
			          "MenuItemUI", SimpleMenuItemUI.class.getName(),
			"PopupMenuSeparatorUI", SimplePopupMenuSeparatorUI.class.getName(),
			          "CheckBoxUI", SimpleCheckBoxUI.class.getName(),
			          "ComboBoxUI", SimpleComboBoxUI.class.getName(),
			       "DesktopIconUI", SimpleDesktopIconUI.class.getName(),
			       "FileChooserUI", SimpleFileChooserUI.class.getName(),
			     "InternalFrameUI", SimpleInternalFrameUI.class.getName(),
			             "LabelUI", SimpleLabelUI.class.getName(),
			"PopupMenuSeparatorUI", SimplePopupMenuSeparatorUI.class.getName(),
			       "ProgressBarUI", SimpleProgressBarUI.class.getName(),
			       "RadioButtonUI", SimpleRadioButtonUI.class.getName(),
			         "ScrollBarUI", SimpleScrollBarUI.class.getName(),
			        "ScrollPaneUI", SimpleScrollPaneUI.class.getName(),
			         "SeparatorUI", SimpleSeparatorUI.class.getName(),
			            "SliderUI", SimpleSliderUI.class.getName(),
			         "SplitPaneUI", SimpleSplitPaneUI.class.getName(),
			        "TabbedPaneUI", SimpleTabbedPaneUI.class.getName(),
			         "TextFieldUI", SimpleTextFieldUI.class.getName(),
			      "ToggleButtonUI", SimpleToggleButtonUI.class.getName(),
			           "ToolBarUI", SimpleToolBarUI.class.getName(),
			           "ToolTipUI", SimpleToolTipUI.class.getName(),
			              "TreeUI", SimpleTreeUI.class.getName(),
			          "RootPaneUI", SimpleRootPaneUI.class.getName(),
			*/
		});
	}

	@Override
	protected void initComponentDefaults(UIDefaults table)
	{
		super.initComponentDefaults(table);

		// FIXME por que caralhos não funfa?
		table.putDefaults(new Object[]
		{
			           "MenuBar.font", theme.getMenuFont(),
			              "Menu.font", theme.getMenuFont(),
			         "PopupMenu.font", theme.getMenuFont(),

			         "MenuBar.border", getBorderFactory().getMenuBarBorder(),
			            "Menu.border", getBorderFactory().getMenuBorder(),
			       "PopupMenu.border", getBorderFactory().getPopupMenuBorder(),
		});
	}

	public static BlackDiamondLookAndFeel getInstance()
	{
		return INSTANCE;
	}
}
