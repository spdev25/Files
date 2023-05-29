import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FileEncryptionGUI extends JFrame {
    private JTextField inputFileField;
    private JTextField outputFileField;
    private JTextField keyField;

    public FileEncryptionGUI() {
        setTitle("File Encryption");
        setSize(400, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel inputFileLabel = new JLabel("Input File:");
        inputFileLabel.setBounds(20, 20, 80, 25);
        add(inputFileLabel);

        inputFileField = new JTextField();
        inputFileField.setBounds(110, 20, 200, 25);
        add(inputFileField);

        JLabel outputFileLabel = new JLabel("Output File:");
        outputFileLabel.setBounds(20, 50, 80, 25);
        add(outputFileLabel);

        outputFileField = new JTextField();
        outputFileField.setBounds(110, 50, 200, 25);
        add(outputFileField);

        JLabel keyLabel = new JLabel("Key:");
        keyLabel.setBounds(20, 80, 80, 25);
        add(keyLabel);

        keyField = new JTextField();
        keyField.setBounds(110, 80, 50, 25);
        add(keyField);

        JButton encryptButton = new JButton("Encrypt");
        encryptButton.setBounds(110, 110, 90, 25);
        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                encryptFile();
            }
        });
        add(encryptButton);

        JButton decryptButton = new JButton("Decrypt");
        decryptButton.setBounds(220, 110, 90, 25);
        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decryptFile();
            }
        });
        add(decryptButton);
    }

    private void encryptFile() {
        String inputFileName = inputFileField.getText();
        String outputFileName = outputFileField.getText();
        int key = Integer.parseInt(keyField.getText());

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            int c;
            while ((c = reader.read()) != -1) {
                char character = (char) c;
                if (Character.isLetter(character)) {
                    char encryptedChar = encryptChar(character, key);
                    writer.write(encryptedChar);
                } else {
                    writer.write(character);
                }
            }

            JOptionPane.showMessageDialog(this, "File encrypted successfully!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error encrypting file: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void decryptFile() {
        String inputFileName = inputFileField.getText();
        String outputFileName = outputFileField.getText();
        int key = Integer.parseInt(keyField.getText());

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            int c;
            while ((c = reader.read()) != -1) {
                char character = (char) c;
                if (Character.isLetter(character)) {
                    char decryptedChar = decryptChar(character, key);
                    writer.write(decryptedChar);
                } else {
                    writer.write(character);
                }
            }

            JOptionPane.showMessageDialog(this, "File decrypted successfully!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error decrypting file: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private char encryptChar(char c, int key) {
        if (Character.isUpperCase(c)) {
            return (char) ((c - 'A' + key) % 26 + 'A');
        } else if (Character.isLowerCase(c)) {
            return (char) ((c - 'a' + key) % 26 + 'a');
        }
        return c;
    }

    private char decryptChar(char c, int key) {
        if (Character.isUpperCase(c)) {
            return (char) ((c - 'A' - key + 26) % 26 + 'A');
        } else if (Character.isLowerCase(c)) {
            return (char) ((c - 'a' - key + 26) % 26 + 'a');
        }
        return c;
    }

    public static void main(String[] args) {
        FileEncryptionGUI gui = new FileEncryptionGUI();
        gui.setVisible(true);
    }
}