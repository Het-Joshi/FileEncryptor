package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {
    public void displayMenu() {
        JFrame frame = new JFrame("File Encryptor");
        frame.setSize(400, 250);
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

        JLabel outputFileLabel = new JLabel("Output File:");
        outputFileLabel.setBounds(20, 120, 80, 25);
        panel.add(outputFileLabel);

        JTextField outputFileText = new JTextField();
        outputFileText.setBounds(100, 120, 200, 25);
        panel.add(outputFileText);

        JButton encryptButton = new JButton("Encrypt");
        encryptButton.setBounds(150, 160, 100, 30);
        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputFilePath = inputFileText.getText();
                    String outputFilePath = outputFileText.getText();
                    FileEncryptor.EncryptFile(inputFilePath, outputFilePath);
                    JOptionPane.showMessageDialog(null, "File encrypted successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        panel.add(encryptButton);

        frame.setVisible(true);
    }
}