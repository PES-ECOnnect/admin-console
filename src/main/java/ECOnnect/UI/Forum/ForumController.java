package ECOnnect.UI.Forum;

import java.awt.event.*;

import ECOnnect.UI.Interfaces.*;
import ECOnnect.API.ForumService.Post;

public class ForumController extends Controller {

    private final ForumView _view = new ForumView(this);
    private final ForumModel _model = new ForumModel();
    
    
    private void refreshList() {
        // Clear the list
        _view.clearList();
        
        // Get posts from model
        Post[] posts = null;
        try {
            posts = _model.getPosts();
        }
        catch (Exception e) {
            _view.displayError("Could not fetch posts: " + e.getMessage());
            return;
        }
        
        // Convert to forum post items
        ForumPostItem[] postItems = new ForumPostItem[posts.length];
        
        for (int i = 0; i < posts.length; ++i) {
            Post p = posts[i];
            postItems[i] = new ForumPostItem(p.postid, p.username, p.text, p.imageurl, p.likes, p.dislikes);
        }
        
        _view.addItems(postItems);
    }
    
    ActionListener refreshButton(){
        return (ActionEvent e) -> {
            refreshList();
        };
    }
    
    ActionListener deleteButton(){
        return (ActionEvent e) -> {
            for (ForumPostItem p : _view.getSelected()) {
                try {
                    _model.deletePost(p.getId());
                }
                catch (Exception ex) {
                    _view.displayError("Could not delete post: " + ex.getMessage());
                    refreshList();
                    return;
                }
            }
            refreshList();
        };
    } 
    

    public View getView() {
        return _view;
    }
    
    @Override
    public void postInit(Object[] args) {
        refreshList();
    }
}
