import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFileChooser;

public class TextComponentFrame extends JFrame {

    public static final int TEXTAREA_ROWS = 8;
    public static final int TEXTAREA_COLUMNS = 20;

    public TextComponentFrame() {
        setTitle("UserTextGenerator"); // Set window title

        JTextField textField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 2));
        northPanel.add(new JLabel("User name: ", SwingConstants.RIGHT));
        northPanel.add(textField);
        northPanel.add(new JLabel("Password: ", SwingConstants.RIGHT));
        northPanel.add(passwordField);
        add(northPanel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // SOUTH PANEL with buttons and footer label
        JPanel southPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton insertButton = new JButton("Insert");
        JButton saveButton = new JButton("Save");
        buttonPanel.add(insertButton);
        buttonPanel.add(saveButton);

        // Insert button: append username + password to text area
        insertButton.addActionListener(event -> {
            char[] pwd = passwordField.getPassword();
            textArea.append("User name: " + textField.getText() + " Password: " + String.valueOf(pwd) + "\n");
        });

        // Save button: ask for folder, then save into userdata.txt inside it
        saveButton.addActionListener((ActionEvent e) -> {
            JFileChooser folderChooser = new JFileChooser();
            folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = folderChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File folder = folderChooser.getSelectedFile();
                File file = new File(folder, "userdata.txt");
                try (FileWriter writer = new FileWriter(file, true)) { // true = append mode
                    writer.write(textArea.getText() + System.lineSeparator());
                    textArea.setText(""); // clear text area after saving
                    System.out.println("Saved to: " + file.getAbsolutePath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JLabel footerLabel = new JLabel("App Made by SanjayKumaran", SwingConstants.RIGHT);
        southPanel.add(buttonPanel, BorderLayout.WEST);
        southPanel.add(footerLabel, BorderLayout.EAST);

        add(southPanel, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    public static void main(String[] args) {
        new TextComponentFrame();
    }
}