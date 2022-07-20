package com.coreyd97.burpcustomizer;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.IntelliJTheme;

import javax.swing.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class CustomTheme extends IntelliJTheme.ThemeLaf {

    Class burpLaf, burpDark, burpLight;

    public CustomTheme(IntelliJTheme.ThemeLaf base) {
        super(base.getTheme());
        try {
            this.burpLaf = ClassLoader.getSystemClassLoader().loadClass("burp.theme.BurpLaf");
            this.burpDark = ClassLoader.getSystemClassLoader().loadClass("burp.theme.BurpDarkLaf");
            this.burpLight = ClassLoader.getSystemClassLoader().loadClass("burp.theme.BurpLightLaf");
        }catch (Exception e){
            throw new RuntimeException("Cannot find required Burp themes. " +
                    "This shouldn't happen as we shouldn't try to switch the theme if it's not supported.");
        }
    }

    @Override
    public UIDefaults getDefaults() {
        UIDefaults defaults;
        FlatLaf burpBase;
        try {
            if (isDark()) {
                burpBase = (FlatLaf) burpDark.getConstructor().newInstance();
            }else{
                burpBase = (FlatLaf) burpLight.getConstructor().newInstance();
            }
            defaults = burpBase.getDefaults();

        }catch (Exception e){
            defaults = super.getDefaults();
            BurpCustomizer.callbacks.printError("Could not get Burp base theme! - " + e.getMessage());
        }

        UIDefaults superDefaults = super.getDefaults();
        superDefaults.remove("SplitPaneUI"); //Do not remove Burp's UI delegates.
        superDefaults.remove("TabbedPaneUI");
        defaults.putAll(superDefaults);

        //For some reason, using lazy loading in getAdditionalDefaults for this property causes issues...
        defaults.put("TabbedPane.selectedBackground", defaults.get("TabbedPane.background"));
        return defaults;
    }



    @Override
    protected Properties getAdditionalDefaults() {
        //Add Additional Overrides Here
        Properties defaults = new Properties();
        defaults.put("TabbedPane.tabInsets", "2,15,4,15");
        defaults.put("TabbedPane.tabHeight", "20");
        defaults.put("Burp.selectionBackground", "lazy(Table.selectionBackground)");
        defaults.put("Burp.selectionForeground", "lazy(Table.selectionForeground)");
        defaults.put("Burp.burpOrange", "#FABD2F");
        defaults.put("Burp.burpTitle", "lazy(TabbedPane.underlineColor)"); //Request response titles
        defaults.put("Burp.burpError", "lazy(TabbedPane.underlineColor)");
        // defaults.put("Burp.currentLineBackground", "lazy(TextArea.selectionBackground)");
        defaults.put("Burp.selectionBorder", "lazy(Tree.selectionBackground)");
//        defaults.put("Burp.solidForeground", "#FF00FF");
        defaults.put("Burp.tabFlashColour", "lazy(TabbedPane.underlineColor)");
        defaults.put("Burp.border", "lazy(Component.borderColor)");
        defaults.put("Burp.expandableConfigPanelBorder", "lazy(Component.borderColor)");
        defaults.put("Burp.highlightPanelBackground", "lazy(TabbedPane.hoverColor)");
        defaults.put("Burp.appLoginWarning", "lazy(TabbedPane.underlineColor)");
        defaults.put("Table.alternateRowColor", "lighten(Table.background,5%,lazy)");

        defaults.put("Burp.suiteTabbedPaneBackground", "lazy(TabbedPane.background)");
        defaults.put("Burp.inspectorBackground", "lazy(Panel.background)");
        defaults.put("Burp.inspectorCollapsedBackground", "lazy(Panel.background)");
        defaults.put("Burp.inspectorBorder", "lazy(Component.borderColor)");
        defaults.put("Burp.inspectorTableBackground", "lazy(Table.background)");
        defaults.put("Burp.inspectorTableHeadingBackground", "lazy(TableHeader.background)");
        defaults.put("Burp.inspectorTableRowHighlightBackground", "lazy(Table.selectionBackground)");
        defaults.put("Burp.inspectorTableRowHighlightActionBackground", "lazy(Table.dropCellBackground)");
        defaults.put("Burp.inspectorTableEntryNameForeground", "lazy(Table.foreground)");
        defaults.put("Burp.inspectorTableEntryValueForeground", "lazy(Table.focusCellForeground)");
        defaults.put("Burp.inspectorTableEditableFieldBackground", "lazy(TextField.background)"); //TOFIX
        defaults.put("Burp.inspectorEmptyCollapsedViewLabelForeground", "lazy(Label.foreground)");
//        defaults.put("Burp.inspectorSeeMoreHoverBackground", "#7DFF15FF");
//        defaults.put("Burp.inspectorSeeMorePressedBackground", "#1DB485FF");
        defaults.put("TabbedPane.selectedBackground", "lazy(TabbedPane.background)");

        defaults.put("Burp.filterBarForeground", "lazy(TextField.foreground)");
        defaults.put("Burp.filterBarBackground", "lazy(TextField.background)");
        defaults.put("Burp.clueTextForeground", "lazy(TextField.placeholderForeground)");
//        defaults.put("Burp.healthcheckSuccess", new Color(7, 8, 126));
//        defaults.put("Burp.healthcheckWarning", new Color(129, 38, 81));
//        defaults.put("Burp.healthcheckFail", new Color(120, 133, 252));
        defaults.put("Burp.searchHighlightColour", "lazy(SearchMatch.startBackground)");
        defaults.put("Burp.alertHighlightColour", "lazy(Component.focusColor)");
        defaults.put("Burp.defaultFixedHighlightColour", "lazy(Component.error.focusedBorderColor)"); //Issues Panel Highlight
       defaults.put("Burp.intruderHighlight", "#9396C0");
//        defaults.put("Burp.mapNodeEmpty", new Color(53, 214, 237));
//        defaults.put("Burp.mapNodeError", new Color(71, 133, 70));
//        defaults.put("Burp.mapNodeRequested", new Color(43, 127, 51));
//        defaults.put("Burp.mapNodeNotRequested", new Color(111, 250, 64));
        defaults.put("Burp.primaryButtonForeground", "lazy(Button.default.foreground)");
        defaults.put("Burp.primaryButtonBackground", "lazy(Button.default.startBackground)");
        defaults.put("Burp.actionPanelBackground", "lazy(Button.startBackground)");
        defaults.put("Burp.actionPanelHoverBackground", "lazy(Button.hoverBackground)");
        defaults.put("Burp.actionPanelBorder", "lazy(Button.borderColor)");
        defaults.put("Burp.standoutPanelBackground", "lazy(Button.startBackground)");
        defaults.put("Burp.standoutPanelHoverBackground", "lazy(Button.hoverBackground)");
        defaults.put("Burp.proUpsellForeground", "lazy(Button.foreground)");
        defaults.put("Burp.proUpsellBackground", "lazy(Button.background)");

        //Repeater pretty / raw buttons
        defaults.put("Burp.radioBarActiveForeground", "lazy(ToggleButton.foreground)");
        defaults.put("Burp.radioBarActiveBackground", "lazy(ToggleButton.pressedBackground)");
        defaults.put("Burp.radioBarHoverForeground", "lazy(ToggleButton.selectedForeground)");
        defaults.put("Burp.radioBarHoverBackground", "lazy(ToggleButton.toolbar.hoverBackground)");
        defaults.put("Burp.radioBarInactiveForeground", "lazy(ToggleButton.foreground)");
        defaults.put("Burp.radioBarInactiveBackground", "lazy(ToggleButton.startBackground)");
        defaults.put("Burp.radioBarDisabledForeground", "lazy(ToggleButton.disabledText)");
        defaults.put("Burp.radioBarDivider", "lazy(Component.borderColor)");

        defaults.put("Burp.requestResponseTabBorder", "lazy(TabbedPane.underlineColor)");
        defaults.put("Burp.requestResponseTabInactiveForeground", "lazy(TabbedPane.foreground)");
        defaults.put("Burp.requestResponseTabInactiveBackground", "lazy(TabbedPane.background)");
        defaults.put("Burp.requestResponseTabHoverBackground", "lazy(TabbedPane.buttonHoverBackground)");
        defaults.put("Burp.ribbonPanelBorder", "lazy(Component.borderColor)");
        defaults.put("Burp.ribbonButtonForeground", "lazy(TabbedPane.foreground)");
        defaults.put("Burp.ribbonButtonHoverForeground", "lazy(TabbedPane.foreground)");
        defaults.put("Burp.ribbonButtonSelectedForeground", "lazy(TabbedPane.foreground)");
        defaults.put("Burp.ribbonButtonInactiveForeground", "lazy(TabbedPane.foreground)");
        defaults.put("Burp.ribbonButtonBackground", "lazy(TabbedPane.background)");
        defaults.put("Burp.ribbonButtonSelectedBackground", "lazy(TabbedPane.buttonPressedBackground)");
        defaults.put("Burp.ribbonButtonHoverBackground", "lazy(TabbedPane.buttonHoverBackground)");
        defaults.put("Burp.ribbonButtonSelectedHoverBackground", "lazy(TabbedPane.background)");
       defaults.put("Burp.scanPhaseInactiveForeground", "lazy(TabbedPane.foreground)");
       defaults.put("Burp.scanPhaseInactiveBackground", "lazy(TabbedPane.background)");
        defaults.put("Burp.htmlLinkForeground", "lazy(Component.linkColor)");
       defaults.put("Burp.severityHigh", "#FB4934");
       defaults.put("Burp.severityMedium", "#fe8019");
       defaults.put("Burp.severityLow", "#689d6a");
       defaults.put("Burp.severityInfo", "#83A598");
        // defaults.put("Burp.actionNormal", "lazy(Button.startBackground)");
        defaults.put("Burp.actionHover", "lazy(Button.default.focusColor)");
        // defaults.put("Burp.actionPressed", "lazy(Button.endBackground)");
        // defaults.put("Burp.taskActionNormal", "lazy(Button.foreground)");
        defaults.put("Burp.taskActionHover", "lazy(Button.default.focusColor)");
        // defaults.put("Burp.taskActionPressed", "lazy(Button.default.focusColor)");
        // defaults.put("Burp.taskListHeaderBackground", "lazy(TextField.background)");
        
        //TextEditor
        defaults.put("Burp.textEditorText", "#FBF1C7");
        defaults.put("Burp.textEditorReservedWord", "#FABD2F");
       defaults.put("Burp.textEditorReservedWord2", "#cc241d");

       defaults.put("Burp.textEditorAnnotation", "#FABD2F");
       defaults.put("Burp.textEditorComment", "#928374");
       defaults.put("Burp.textEditorDataType", "#B8BB26");
       defaults.put("Burp.textEditorFunction", "#B8BB26");
       defaults.put("Burp.textEditorLiteralBoolean", "#FB4934");
       defaults.put("Burp.textEditorLiteralNumber", "#D3869B");
       defaults.put("Burp.textEditorLiteralQuote", "#9396C0");
       defaults.put("Burp.textEditorLiteralString", "#A5C261");
       defaults.put("Burp.textEditorTagDelimiter", "#E8BF6A");
       defaults.put("Burp.textEditorTagName", "#83A598");
       defaults.put("Burp.textEditorProcessingInstruction", "#9396C0");
       defaults.put("Burp.textEditorCdataDelimiter", "#BABABA");
       defaults.put("Burp.textEditorCdata", "#E8BF6A");
       defaults.put("Burp.textEditorEntityReference", "#83A598");
       defaults.put("Burp.textEditorOperator", "#E8DDBB2");
       defaults.put("Burp.textEditorPreProcessor", "#8EC07C");
       defaults.put("Burp.textEditorRegex", "#CC7832");
       defaults.put("Burp.textEditorSeparator", "#EBDBB2");
       defaults.put("Burp.textEditorVariable", "#83A598");

       defaults.put("Burp.textEditorHttpFirstLine", "#FBF1C7");
       defaults.put("Burp.textEditorHeaderName", "#8EC07C");
       defaults.put("Burp.textEditorHeaderValue", "#FBF1C7");
       defaults.put("Burp.textEditorParamName", "#83A598");
       defaults.put("Burp.textEditorParamValue", "#B8BB26");
       defaults.put("Burp.textEditorCookieName", "#83A598");
       defaults.put("Burp.textEditorCookieValue", "#FABD2F");

        defaults.put("Burp.textEditorBackground", "lazy(TextArea.background)");
        defaults.put("Burp.textEditorCurrentLineBackground", "lazy(EditorPane.inactiveBackground)");
        defaults.put("Burp.textEditorSelectionBackground", "lazy(TextArea.selectionBackground)");
        defaults.put("Burp.textEditorSelectionForeground", "lazy(TextArea.selectionForeground)");
        defaults.put("Burp.textEditorGutterBorder", "lazy(Component.borderColor)");
        defaults.put("Burp.textEditorLineNumbers", "lazy(TextField.placeholderForeground)");
        defaults.put("Burp.textEditorLozengeBackground", "lazy(Button.default.endBackground)"); //Newline indicators
        defaults.put("Burp.textEditorLozengeText", "lazy(Button.default.foreground)");       //Newline indicators
//        defaults.put("Burp.warningBarForeground", "#E29408FF");
//        defaults.put("Burp.warningBarBackground", "#1255C6FF");
        return defaults;
    }
}
