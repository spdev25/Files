import javax.swing.*;
import java.io.*;

public class CapitalizeAndFormat {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File outputFile = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                String data = "this is a sentence. it has some numbers like 123 and 4567.";
                StringBuilder formattedData = new StringBuilder();
                boolean capitalizeNextChar = false;
                for (char c : data.toCharArray()) {
                    if (c == '.') {
                        capitalizeNextChar = true;
                    } else if (Character.isDigit(c)) {
                        formattedData.append('(').append(c).append(')');
                    } else if (capitalizeNextChar) {
                        formattedData.append(Character.toUpperCase(c));
                        capitalizeNextChar = false;
                    } else {
                        formattedData.append(c);
                    }
                }
                
                writer.write(formattedData.toString());
                
                JOptionPane.showMessageDialog(null, "Data written to file with formatting!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}