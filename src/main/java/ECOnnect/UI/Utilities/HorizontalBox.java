package ECOnnect.UI.Utilities;

import java.awt.*;
import javax.swing.*;

public class HorizontalBox {
    
    public static Box create(Component ... comps) {
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        boolean first = true;
        
        for (Component comp : comps) {
            if (first) first = false;
            else box.add(Box.createHorizontalStrut(30));
            box.add(comp);
        }
        
        box.add(Box.createHorizontalGlue());
        box.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        return box;
    }
}

