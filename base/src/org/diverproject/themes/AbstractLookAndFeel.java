package org.diverproject.themes;

import javax.swing.UIDefaults;
import javax.swing.plaf.metal.MetalLookAndFeel;

import org.diverproject.themes.component.SimpleMenuBarUI;
import org.diverproject.themes.component.SimpleMenuItemUI;
import org.diverproject.themes.component.SimpleMenuUI;
import org.diverproject.themes.component.SimplePopupMenuItemUI;
import org.diverproject.themes.component.SimplePopupMenuUI;
import org.diverproject.themes.component.SimpleRootPaneUI;

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
	}

	@Override
	protected void initComponentDefaults(UIDefaults table)
	{
		super.initComponentDefaults(table);

		initFontsDefaults(componentProperties);
		initBordersDefaults(componentProperties);
	}

	@Override
	protected void initSystemColorDefaults(UIDefaults table)
	{
		super.initSystemColorDefaults(table);

		initColorsDefaults(componentProperties);
	}

	protected void initClassDefaults(ComponentProperties properties)
	{
		properties.getRootPane().setUI(SimpleRootPaneUI.class);
		properties.getMenuBar().setUI(SimpleMenuBarUI.class);
		properties.getMenu().setUI(SimpleMenuUI.class);
		properties.getPopupMenu().setUI(SimplePopupMenuUI.class);
		properties.getMenuItem().setUI(SimpleMenuItemUI.class);
		properties.getPopupMenuItem().setUI(SimplePopupMenuItemUI.class);

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
		properties.getMenuItem().setFont(getTheme().getMenuItemFont());
		properties.getPopupMenuItem().setFont(getTheme().getMenuItemFont());
	}

	protected void initBordersDefaults(ComponentProperties properties)
	{
		properties.getRootPane().setCustom("frameBorder", getBorderFactory().getRootPaneBorder());
		properties.getMenuBar().setBorder(getBorderFactory().getMenuBarBorder());
		properties.getMenu().setBorder(getBorderFactory().getMenuBorder());
		properties.getPopupMenu().setBorder(getBorderFactory().getPopupMenuBorder());
		properties.getMenuItem().setBorder(getBorderFactory().getMenuItemBorder());
		properties.getPopupMenuItem().setBorder(getBorderFactory().getPopupMenuItemBorder());
	}

	protected void initColorsDefaults(ComponentProperties properties)
	{
		properties.getDesktop().setColor(getTheme().getCaptionColors().getBackground());
		properties.getActiveCaption().setColor(getTheme().getCaptionColors().getBackground());
		properties.getActiveCaptionText().setColor(getTheme().getCaptionColors().getForeground());
		properties.getActiveCaptionBorder().setColor(getTheme().getCaptionColors().getBorder());
		properties.getActiveCaption().setColor(getTheme().getCaptionColors().getInactiveBackground());
		properties.getActiveCaptionText().setColor(getTheme().getCaptionColors().getInactiveForeground());
		properties.getActiveCaptionBorder().setColor(getTheme().getCaptionColors().getInactiveBorder());

		properties.getWindow().setColor(getTheme().getWindowColors().getBackground());
		properties.getWindowBorder().setColor(getTheme().getWindowColors().getBorder());
		properties.getWindowText().setColor(getTheme().getWindowColors().getForeground());

		properties.getText().setColor(getTheme().getTextColors().getBackground());
		properties.getTextText().setColor(getTheme().getTextColors().getForeground());
		properties.getTextHighlight().setColor(getTheme().getTextColors().getHightlightBackground());
		properties.getTextHighlightText().setColor(getTheme().getTextColors().getHightlightForeground());
		properties.getTextInactiveText().setColor(getTheme().getTextColors().getDisabledForeground());

		properties.getMenu().setColor(getTheme().getMenuColors().getBackground());
		properties.getMenu().setColor(getTheme().getMenuColors().getForeground());
		properties.getMenuBar().setRolloverEnabled(true);
	}
}
