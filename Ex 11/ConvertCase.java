import javax.swing.*;
import java.io.*;

public class ConvertCase {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            int outputResult = fileChooser.showSaveDialog(null);
            if (outputResult == JFileChooser.APPROVE_OPTION) {
                File outputFile = fileChooser.getSelectedFile();
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String convertedLine = line.toUpperCase();
                        writer.write(convertedLine);
                        writer.newLine();
                    }
                    
                    JOptionPane.showMessageDialog(null, "File conversion completed successfully!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
