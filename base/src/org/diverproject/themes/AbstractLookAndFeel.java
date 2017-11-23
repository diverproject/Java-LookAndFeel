package org.diverproject.themes;

import javax.swing.UIDefaults;
import javax.swing.plaf.metal.MetalLookAndFeel;

import org.diverproject.themes.component.SimpleMenuBarUI;
import org.diverproject.themes.component.SimpleMenuUI;
import org.diverproject.themes.component.SimplePopupMenuUI;

public abstract class AbstractLookAndFeel extends MetalLookAndFeel
{
	private static final long serialVersionUID = -3225208352514175597L;

	protected ComponentProperties componentProperties;

	public abstract AbstractBorderFactory getBorderFactory();
    public abstract AbstractIconFactory getIconFactory();
	public abstract AbstractTheme getTheme();

	public AbstractLookAndFeel()
	{
		componentProperties = new ComponentProperties();
	}

	@Override
	protected void initClassDefaults(UIDefaults table)
	{
		super.initClassDefaults(table);

		componentProperties.setUIDefaults(table);

		initClassDefaults(componentProperties);
		initFontsDefaults(componentProperties);
		initBordersDefaults(componentProperties);
	}

	protected void initClassDefaults(ComponentProperties properties)
	{
		properties.getMenuBar().setUI(SimpleMenuBarUI.class);
		properties.getMenu().setUI(SimpleMenuUI.class);
		properties.getPopupMenu().setUI(SimplePopupMenuUI.class);

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
	}

	protected void initFontsDefaults(ComponentProperties properties)
	{
		properties.getMenuBar().setFont(getTheme().getMenuFont());
		properties.getMenu().setFont(getTheme().getMenuFont());
		properties.getPopupMenu().setFont(getTheme().getMenuFont());
	}

	protected void initBordersDefaults(ComponentProperties properties)
	{
		properties.getMenuBar().setBorder(getBorderFactory().getMenuBarBorder());
		properties.getMenu().setBorder(getBorderFactory().getMenuBorder());
		properties.getPopupMenu().setBorder(getBorderFactory().getPopupMenuBorder());
	}
}
