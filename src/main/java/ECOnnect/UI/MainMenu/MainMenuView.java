package ECOnnect.UI.MainMenu;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import ECOnnect.UI.Company.CompanyScreen;
import ECOnnect.UI.NewProduct.NewProductScreen;
import ECOnnect.UI.Product.ProductScreen;
import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.*;
import ECOnnect.UI.Login.LoginScreen;
import ECOnnect.UI.ProductTypes.ProductTypesScreen;

public class MainMenuView extends View {
    
    private MainMenuController ctrl;
    
    // Todo: add appropiate tabs
    private final Screen[] TAB_SCREENS = {
        new ProductTypesScreen(),
        new CompanyScreen()
    };
    
    public MainMenuView(MainMenuController ctrl) {
        this.ctrl = ctrl;
        setUp();
    }
    
    // Build the GUI
    private void setUp() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setUI(new CustomTabbedPaneUI());
        
        // Create tabs
        for (Screen screen : TAB_SCREENS) {
            tabbedPane.addTab(screen.getTitle(), screen.getPanel());
        }
        tabbedPane.addTab("Logout", null);
        tabbedPane.addChangeListener(ctrl.tabListener());
        panel.add(tabbedPane);
    }
    
    @Override
    public void postInit() {
        super.postInit();
        // Set the title to the first tab
        ScreenManager.getInstance().updateTitle(TAB_SCREENS[0].getTitle());
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
