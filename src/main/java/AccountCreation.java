import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Created by Emily on 2017-03-30.
 */

interface UserCallback {
    void updateUser(String newName);
    void newUser(String newName);
}

public class AccountCreation extends JPanel implements UserCallback{

    private JTextField textEntry;
    private JButton submitButton;

    private accountBox box;
    private boxFeed boxName;

    // Data
    private String searchName;

    public AccountCreation(JFrame root) {
        super();

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        this.add(new JLabel("Enter a new username:"));


        // Create SearchBox instance, set layout parameter, add to 'this' JPanel
        this.box = new accountBox(this); // Takes reference to 'this' for callback
        c.gridy = 1;
        c.fill = GridBagConstraints.VERTICAL;
        this.add(this.box, c);

        this.boxName = new boxFeed(root);
        c.gridy = 2;
        this.add(this.boxName, c);

    }


    public void updateUser(String newName){
        this.searchName = newName;
    }

    public void newUser(String newName){
        this.searchName = newName;
    }


    class accountBox extends JPanel {

        private JTextField textEntry;
        private JButton submitButton;

        public accountBox(final UserCallback callback){
            // Initialize this component
            super();
            this.setLayout(new FlowLayout());
            this.textEntry = new JTextField(15);
            this.submitButton = new JButton("Create account");

            // Defines button click action to call the callback method
            this.submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String text = textEntry.getText();
                    callback.newUser(text);
                }
            });

            // Defines key enter action to call the callback method
            this.textEntry.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String text = textEntry.getText();
                    callback.updateUser(text);
                }
            });

            // Add subcomponents to this JPanel
            this.add(this.textEntry);
            this.add(this.submitButton);
        }
    }



    class boxFeed extends JTextArea {

        JFrame root;
        static final String defaultText = "\n\n\n\n\n\n\n\n\n\n";

        public void updateName(String username){
            this.setText(username);
//        this.setText(callAPI(username));
            this.root.pack();
//        this.setVisible(false);
            this.setVisible(true);
        }

        public boxFeed(JFrame root){
            super();
            this.setLayout(new GridLayout(0,1));
            this.root = root;
            this.setText(defaultText);
            this.setVisible(true);
        }

    }
}
