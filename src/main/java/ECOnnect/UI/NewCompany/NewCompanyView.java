package ECOnnect.UI.NewCompany;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;

import javax.swing.*;
import java.awt.*;

public class NewCompanyView extends View {
    private NewCompanyController ctrl;

    private JTextField nameTxt = new JTextField(20);
    private JTextField locationTxt = new JTextField(20);
    private JTextField imageUrl = new JTextField(20);

    // COMPONENTS

    public NewCompanyView(NewCompanyController ctrl){
        this.ctrl=ctrl;
        setUp();
    }

    private void setUp(){
        panel.add(Box.createVerticalGlue());

        nameTxt.setMaximumSize(new Dimension(100, 30));
        panel.add(HorizontalBox.create(new JLabel("Company Name:     "), nameTxt));

        panel.add(Box.createVerticalStrut(10));

        locationTxt.setMaximumSize(new Dimension(100,30));
        panel.add(HorizontalBox.create(new JLabel("Company Location:"), locationTxt));

        panel.add(Box.createVerticalStrut(10));

        imageUrl.setMaximumSize(new Dimension(100, 30));
        panel.add(HorizontalBox.create(new JLabel("Image URL:              "), imageUrl));

        panel.add(Box.createVerticalStrut(10));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(ctrl.saveButton());
        panel.add(saveButton);

        panel.add(Box.createVerticalStrut(10));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(ctrl.cancelButton());
        panel.add(cancelButton);

        panel.add(Box.createVerticalGlue());
    }

    public String getNameText() {
        return nameTxt.getText();
    }

    public String getLocationText() {
        return locationTxt.getText();
    }

    public String getImageTxt() {
        return imageUrl.getText();
    }
}
