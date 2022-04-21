package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import static Login.GetLoginInfo.calculatePasswordStrength;

public class ResetPassword
{
    ResetPassword()
    {
    // Create a new frame
    JFrame f =new JFrame("Login Details");
        f.getContentPane().setBackground(new Color(230,230,0));
        f.setBackground(Color.cyan);

    // Create a new Label for the Title
    JLabel title = new JLabel("Reset Password",JLabel.CENTER);
        title.setBounds(500,10,400,50);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        title.setForeground(Color.BLUE);
        f.add(title);

    // Declaring all the labels,text-fields and password fields
    JLabel username, password, password_confirm,oldpassword;
    JTextField username_field;
    JPasswordField passwordField, passwordField1,oldpassword_field;

    // Create a JLabel with the text "The Username should be atleast 6 characters long and should not be a email address"
    JLabel username_label = new JLabel("     The Username should be atleast 6 characters long and at maximum 15 characters long. " +
            "It should not be a email address format");
        username_label.setBounds(80,70,1200,50);
        username_label.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        username_label.setForeground(new Color(2, 138, 15));
        username_label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        username_label.setVisible(false);
        f.add(username_label);

    username = new JLabel("Enter Username: *");
        username.setBounds(400, 150, 170, 80);
        username.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(username);
        username_field = new JTextField();
        username_field.setBounds(600, 173, 200, 25);

    // KeyListener for checking if username is entered on pressing the check button
        username_field.addKeyListener(new KeyAdapter() {
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (username_field.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Please enter a username");
            }
        }
    }});

    // Listener for the username field to allow only 10 characters
        username_field.addKeyListener(new KeyAdapter()
{
    public void keyTyped(KeyEvent e)
    {
        if (username_field.getText().length() >= 15)
        {
            e.consume();
        }
    }
});
        f.add(username_field);

    // Create label with "enter Strong Password Constraints" left side of the password label
    JLabel password_constraints = new JLabel("  Use a variety of characters including letters, numbers, symbols, and upper and lower case. ");
        password_constraints.setBounds(100, 70, 800, 80);
        password_constraints.setFont(new Font("Calibri", Font.BOLD, 20));
    // Add colour to the label to #028A0F
        password_constraints.setForeground(new Color(2, 138, 15));
    // Add surrounding border to the label only sourrounding the text
        password_constraints.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        password_constraints.setVisible(false);
        f.add(password_constraints);


    password = new JLabel("Enter New Password: *");
        password.setBounds(400, 250, 200, 80);
        password.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(password);

    passwordField = new JPasswordField();
    // Set the text colour to #ec3b83
        passwordField.setForeground(new Color(236, 59, 131));
        passwordField.setBounds(600, 273, 200, 25);
    // Display the dialog box if the length of the password is less than 10
        passwordField.addKeyListener(new KeyAdapter() {
    public void keyTyped(KeyEvent e) {
        if (String.valueOf(passwordField.getPassword()).length() >= 30)
            e.consume();
    }
});

    // Code to display the password strength on a ProgressBar
    JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(200, 225, 100, 20);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(2, 138, 15));
        progressBar.setValue(0);
        progressBar.setString("");
        progressBar.setVisible(false);
        f.add(progressBar);

    //JLabel to display Password Strength
    JLabel password_strength = new JLabel("Password Strength: ");
        password_strength.setBounds(20, 225, 150, 20);
        password_strength.setFont(new Font("Calibri", Font.ITALIC, 18));
        password_strength.setVisible(false);
        f.add(password_strength);

    // Call the method to check the strength of the password
        passwordField.addKeyListener(new KeyAdapter() {
    public void keyTyped(KeyEvent e) {
        progressBar.setVisible(true);
        password_strength.setVisible(true);
        calculatePasswordStrength(String.valueOf(passwordField.getPassword()), progressBar);
    }
});

    // When the user focuses on the password field, display the password_constraints label
        passwordField.addFocusListener(new FocusAdapter() {
    public void focusGained(FocusEvent e) {
        password_constraints.setVisible(true);
    }
    public void focusLost(FocusEvent e) {
        password_constraints.setVisible(false);
    }
});
        f.add(passwordField);

    password_confirm= new JLabel("Confirm New Password: *");
        password_confirm.setBounds(400,300,200,80);
        password_confirm.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(password_confirm);
    passwordField1 = new JPasswordField();
        passwordField1.setBounds(600,323,200,25);
        passwordField1.addKeyListener(new KeyAdapter() {
    public void keyTyped(KeyEvent e) {
        if (String.valueOf(passwordField1.getPassword()).length() >= 30)
            e.consume();
        // if password field is in focus
        if (passwordField1.isFocusOwner()) {
            progressBar.setVisible(false);
            password_strength.setVisible(false);
        }
    }
});

    // Create a label for the error message that passwords doesn't match
    JLabel error = new JLabel("Password fields do not match",JLabel.CENTER);
        error.setBounds(800,262,400,50);
        error.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        error.setForeground(Color.RED);
        error.setVisible(false);
        f.add(error);

    // KeyListener to check if the password and confirm password are same
        passwordField1.addKeyListener(new KeyAdapter() {
    public void keyReleased(KeyEvent e) {
        // Check if the focus is on the confirm password field
        if (passwordField1.isFocusOwner()) {
            // Check if the password and confirm password are same
            if (String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordField1.getPassword())))
                // If they are same, then dont display the error message
                error.setVisible(false);
            else
                // If they are not same, then display the error message
                error.setVisible(true);
        }
    }
});
        f.add(passwordField1);

    // When the userfield is on focus , then display the username_label
        username_field.addFocusListener(new FocusAdapter() {
    public void focusGained(FocusEvent e) {
        passwordField.setText("");
        passwordField1.setText("");
        username_label.setVisible(true);
    }
    public void focusLost(FocusEvent e) {
        username_label.setVisible(false);
    }
});

    JLabel RoleID = new JLabel("Old Password: *");
        RoleID.setBounds(400,200,200,80);
        RoleID.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(RoleID);

    oldpassword_field = new JPasswordField();
    oldpassword_field.setBounds(600,223,200,25);
    f.add(oldpassword_field);

    // To add a button to display password when the user clicks on it
    JButton show_password = new JButton("Show Password");
        show_password.setBounds(850, 223, 150, 25);
    // When the user clicks on the button, then display the password
        show_password.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        if(String.valueOf(oldpassword_field.getPassword()).length()==0)
        {
            JOptionPane.showMessageDialog(null, "Please enter the password");
        }
        passwordField.setEchoChar((char)0);
        passwordField1.setEchoChar((char)0);
        oldpassword_field.setEchoChar((char)0);
    }
});
        f.add(show_password);

        // By Default its false
        passwordField.setVisible(false);
        passwordField1.setVisible(false);
        password.setVisible(false);
        password_confirm.setVisible(false);

    JButton Check = new JButton("Verify Account");
        Check.setBounds(850,168,170,25);
        f.add(Check);
    // Add a KeyListener to the button to check if the username and his old password are correct
        Check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // if the username is empty then display the error message on dialog box
                if (username_field.getText().equals("") || (String.valueOf(oldpassword_field.getPassword()).equals("")))
                {
                    JOptionPane.showMessageDialog(f, "One or more fields is empty !", "Error", JOptionPane.ERROR_MESSAGE);
                    if(username_field.getText().equals(""))
                        username_field.requestFocus();
                    else
                        oldpassword_field.requestFocus();
                }
                else
                {
                // Connect with the database and check if the username and password is correct
                try {
                    // Connect to the ORACLE database
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                    String sql = "SELECT * FROM TBLLOGIN WHERE USER_NAME = ? AND PASSWORD = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, username_field.getText());
                    ps.setString(2, String.valueOf(oldpassword_field.getPassword()));
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        // If the username and password are correct, then display the  dialog box
                        JOptionPane.showMessageDialog(null, "Account Verified Successfully");
                        // Set the focus on the new password field
                        passwordField.requestFocus();
                        //Set the username field to green border
                        username_field.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                        passwordField.setVisible(true);
                        passwordField1.setVisible(true);
                        password.setVisible(true);
                        password_confirm.setVisible(true);

                        // Focus on the new password field
                        passwordField.requestFocus();
                    }
                    else {
                        // If the username and password are not correct, then display the error message
                        JOptionPane.showMessageDialog(null, "Account Credentials Wrong! Try Again", "Error", JOptionPane.ERROR_MESSAGE);
                        username_field.requestFocus();
                        username_field.setBorder(BorderFactory.createLineBorder(Color.RED));
                        username_field.setText("");
                        oldpassword_field.setText("");
                        passwordField.setVisible(false);
                        passwordField1.setVisible(false);
                        password.setVisible(false);
                        password_confirm.setVisible(false);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
    }});

    JButton Register = new JButton("Change Password");
        Register.setBounds(500,390,200,25);
        f.add(Register);
        Register.addActionListener(e ->
    {
        // Create a method to add all the data from the fields to the table TBLLOGIN in the database
        String Username = username_field.getText();
        String passText = new String(passwordField.getPassword());
        String passText_confirm = new String(passwordField1.getPassword());
        String oldpassword1 = new String(oldpassword_field.getPassword());

        if (Username.trim().equals("") || passText.trim().equals("") || passText_confirm.trim().equals("") || oldpassword1.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);

            // If the fields are filled then check if the password and confirm password are the same
            if (!passText.equals(passText_confirm))
                JOptionPane.showMessageDialog(null, "Password Doesn't Match", "Confirm Password", 2);

            // Check if the new password is the same as the old password
            if(oldpassword1.equals(passText))
                JOptionPane.showMessageDialog(null, "Old Password and New Password Cannot be the same", "Old Password", 2);
        }

        else
        {
            // Connect to the database and update the password
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
                PreparedStatement pst;
                pst = connection.prepareStatement("UPDATE TBLLOGIN SET PASSWORD = ? WHERE USER_NAME = ?");
                pst.setString(1, passText);
                pst.setString(2, Username);
                pst.executeUpdate();
                if (pst.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null, "Password Changed Successfully", "Password Changed", 1);
                    Register.setVisible(false);
                }
                else
                    JOptionPane.showMessageDialog(null, "Password Change Unsuccessful", "Password Change", 2);
                connection.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }});

    JButton cancel = new JButton("Exit");
        cancel.setBounds(740,390,100,30);
    // To create a dialog box with yes or no options for the cancel button
        cancel.addActionListener(e -> {
    int dialogButton = JOptionPane.YES_NO_OPTION;
    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit from the application ?", "Warning", dialogButton);
    if (dialogResult == JOptionPane.YES_OPTION) {
        f.dispose();
    }
});
    // Change cancel button style
        cancel.setBackground(Color.WHITE);
        cancel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        cancel.setFont(new Font("Arial", Font.BOLD, 12));
    // Submit Button Text Color
        cancel.setForeground(Color.blue);
        f.add(cancel);

    // Creating the button to go back to the previous frame
    JButton back = new JButton("Back");
        back.setBounds(320,390,100,30);
    // To move to previous page
        back.addActionListener(e -> {
    f.dispose();
    new Login();
    Login.main(null);
}
        );
    // Change back button style
        back.setBackground(Color.WHITE);
        back.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        back.setFont(new Font("Arial", Font.BOLD, 12));
    // Back Button Text Color
        back.setForeground(Color.blue);
        f.add(back);


    JLabel label = new JLabel();
        f.add(label);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600, 800);
        f.setLayout(null);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new ResetPassword();
    }
}

