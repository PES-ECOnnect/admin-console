package ECOnnect.UI.Forum;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Product.ImageDetail.ImageDetailScreen;
import ECOnnect.UI.Utilities.ImageLoader;
import ECOnnect.UI.Utilities.ItemListElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ForumPostItem extends ItemListElement {
    private final int _id;
    private final String _author;
    private final String _text;
    private final String _imageUrl;
    private final String _likeDislike;
    private final JCheckBox _deleteCheckbox = new JCheckBox();

    public ForumPostItem(int id, String author, String text, String imageUrl, int likes, int dislikes) {
        _id = id;
        _author = author;
        _text = text;
        _imageUrl = imageUrl;
        _likeDislike = likes + " / " + dislikes;
        
        super.init();
    }
    
    public int getId() {
        return _id;
    }

    public static String[] getHeaderNames(){
        return new String[] {"Author", "Text", "Thumbnail", "Full image", "Likes / Dislikes", "Ban author", "Select for delete"};
    }

    protected Component[] getRowComponents(){
        JTextField authorField = new JTextField(_author);
        authorField.setEditable(false);
        
        JTextField textField = new JTextField(_text);
        textField.setEditable(false);
        
        JLabel thumbnail = new JLabel();
        thumbnail.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton imageButton = new JButton("View");
        imageButton.addActionListener(imageButtonListener());
        
        JTextField likeDislikeField = new JTextField(_likeDislike);
        likeDislikeField.setEditable(false);
        
        JButton banButton = new JButton("Ban user");
        banButton.addActionListener(banButtonListener());
        
        // Callback for image loading
        ImageLoader.loadAsync(_imageUrl, new ImageLoader.ImageLoaderCallback() {
            @Override
            public void imageLoaded(ImageIcon image) {
                ImageIcon scaledImage = ImageLoader.scale(image, -1, DEFAULT_SIZE.height);
                thumbnail.setIcon(scaledImage);
            }
            @Override
            public void couldNotLoad() {
                thumbnail.setText(_imageUrl);
            }
        });
        
        return new Component[] {
            authorField,
            textField,
            thumbnail,
            imageButton,
            likeDislikeField,
            banButton,
            _deleteCheckbox
        };
    }
    
    private ActionListener imageButtonListener() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(ImageDetailScreen.class, _imageUrl, ScreenManager.MAIN_MENU_SCREEN);
        };
    }
    
    private ActionListener banButtonListener() {
        return (ActionEvent e) -> {
            System.out.println("Banning user " + _author);
        };
    }

    @Override
    public boolean isSelected() {
        return _deleteCheckbox.isSelected();
    }

    @Override
    public void uncheck() {
        _deleteCheckbox.setSelected(false);
    }
}
