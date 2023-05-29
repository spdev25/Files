import javax.swing.*;
import java.io.*;

public class CopyFile {
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
                        String replacedLine = line.replace(".", ",");
                        writer.write(replacedLine);
                        writer.newLine();
                    }
                    
                    JOptionPane.showMessageDialog(null, "File copied with replacements!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
