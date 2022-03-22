package ECOnnect.UI.MainMenu;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import ECOnnect.UI.Company.CompanyScreen;
import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.*;
import ECOnnect.UI.ProductTypes.ProductTypesScreen;

public class MainMenuView extends View {
    
    private final MainMenuController _ctrl;
    private final JTabbedPane _tabbedPane = new JTabbedPane();
    
    // Todo: add appropiate tabs
    private final Screen[] TAB_SCREENS = {
        new ProductTypesScreen(),
        new CompanyScreen()
    };
    
    public MainMenuView(MainMenuController ctrl) {
        this._ctrl = ctrl;
        setUp();
    }
    
    // Build the GUI
    private void setUp() {
        _tabbedPane.setUI(new CustomTabbedPaneUI());
        
        // Create tabs
        for (Screen screen : TAB_SCREENS) {
            _tabbedPane.addTab(screen.getTitle(), screen.getPanel());
        }
        _tabbedPane.addTab("Logout", null);
        _tabbedPane.addChangeListener(_ctrl.tabListener());
        panel.add(_tabbedPane);
    }
    
    @Override
    public void postInit() {
        super.postInit();
        
        int tabIndex = _ctrl.getCurrentTabIndex();
        
        // Set the title to the first tab
        ScreenManager.getInstance().updateTitle(TAB_SCREENS[tabIndex].getTitle());
        
        // Call the postInit method of all tabs
        for (Screen screen : TAB_SCREENS) {
            screen.postInit();
        }
        
        // Navigate to the correct tab
        _tabbedPane.setSelectedIndex(tabIndex);
    }
    
    // Custom TabbedPaneUI that moves the Logout tab to the right
    private class CustomTabbedPaneUI extends BasicTabbedPaneUI {
        @Override
        protected java.awt.LayoutManager createLayoutManager() {
            return new TabbedPaneLayout() {
                @Override
                protected void calculateTabRects(int tabPlacement, int tabCount) {
                    if (tabCount == 0) return;
                    // Compute coordinates for each tab
                    super.calculateTabRects(tabPlacement,tabCount);
                    // Override the last tab's coordinates (move it to the right)
                    rects[rects.length-1].x = panel.getWidth() - rects[rects.length-1].width - 10;
                }
            };
        }
    }
}
