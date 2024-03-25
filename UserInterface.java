package Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UserInterface {
    public void displayMenu() {

        setupLookAndFeel();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        JFrame frame = new JFrame("File Encryptor");
        frame.setSize(400, 290);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("File Encryptor");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(120, 10, 200, 30);
        panel.add(titleLabel);

        JLabel createdByLabel = new JLabel("Made by Het Joshi");
        createdByLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        createdByLabel.setBounds(130, 40, 150, 20);
        panel.add(createdByLabel);

        JLabel inputFileLabel = new JLabel("Input File:");
        inputFileLabel.setBounds(20, 80, 80, 25);
        panel.add(inputFileLabel);

        JTextField inputFileText = new JTextField();
        inputFileText.setBounds(100, 80, 200, 25);
        panel.add(inputFileText);

        JButton browseInputButton = new JButton("Browse");
        browseInputButton.setBounds(310, 80, 70, 25);
        browseInputButton.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                inputFileText.setText(selectedFile.getAbsolutePath());
            }
        });
        panel.add(browseInputButton);

        JLabel outputFileLabel = new JLabel("Output File:");
        outputFileLabel.setBounds(20, 120, 80, 25);
        panel.add(outputFileLabel);

        JTextField outputFileText = new JTextField();
        outputFileText.setBounds(100, 120, 200, 25);
        panel.add(outputFileText);

        JButton browseOutputButton = new JButton("Browse");
        browseOutputButton.setBounds(310, 120, 70, 25);
        browseOutputButton.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                outputFileText.setText(selectedFile.getAbsolutePath());
            }
        });
        panel.add(browseOutputButton);

        JLabel keyLabel = new JLabel("Key:");
        keyLabel.setBounds(20, 160, 80, 25);
        panel.add(keyLabel);

        JTextField keyInputText = new JTextField();
        keyInputText.setBounds(100, 160, 200, 25);
        keyInputText.setText("yourEncryptionKey");
        FileEncryptor.generateKey("yourEncryptionKey");
        panel.add(keyInputText);

        JButton setKeyButton = new JButton("Set Key");
        setKeyButton.setBounds(310, 160, 70, 25);
        setKeyButton.addActionListener(e -> {
            if (keyInputText.getText().isEmpty())
                return;

            FileEncryptor.generateKey(keyInputText.getText());
        });
        panel.add(setKeyButton);

        JButton encryptButton = new JButton("Encrypt");
        encryptButton.setBounds(150, 200, 100, 30);
        encryptButton.addActionListener(e -> {
            if (inputFileText.getText().isEmpty() || outputFileText.getText().isEmpty() ||
                    keyInputText.getText().isEmpty())
                return;

            try {
                String inputFilePath = inputFileText.getText();
                String outputFilePath = outputFileText.getText();
                FileEncryptor.EncryptFile(inputFilePath, outputFilePath);
                JOptionPane.showMessageDialog(null, "File encrypted successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
        panel.add(encryptButton);

        frame.setVisible(true);
    }

    private void setupLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}