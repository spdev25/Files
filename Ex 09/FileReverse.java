import javax.swing.*;
import java.io.*;

public class FileReverse {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int inputResult = fileChooser.showOpenDialog(null);
        if (inputResult == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            int outputResult = fileChooser.showSaveDialog(null);
            if (outputResult == JFileChooser.APPROVE_OPTION) {
                File outputFile = fileChooser.getSelectedFile();
                reverseFile(inputFile, outputFile);
                JOptionPane.showMessageDialog(null, "File reversed successfully!");
            }
        }
    }

    private static void reverseFile(File inputFile, File outputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            StringBuilder reversedContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                reversedContent.append(new StringBuilder(line).reverse().toString()).append("\n");
            }
            writer.write(reversedContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
