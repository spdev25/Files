import javax.swing.*;
import java.io.*;

public class ExchangeFiles {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int firstResult = fileChooser.showOpenDialog(null);
        if (firstResult == JFileChooser.APPROVE_OPTION) {
            File firstFile = fileChooser.getSelectedFile();
            int secondResult = fileChooser.showOpenDialog(null);
            if (secondResult == JFileChooser.APPROVE_OPTION) {
                File secondFile = fileChooser.getSelectedFile();
                try (BufferedReader reader1 = new BufferedReader(new FileReader(firstFile));
                     BufferedReader reader2 = new BufferedReader(new FileReader(secondFile));
                     BufferedWriter writer1 = new BufferedWriter(new FileWriter(firstFile));
                     BufferedWriter writer2 = new BufferedWriter(new FileWriter(secondFile))) {
                    String line;

                    while ((line = reader1.readLine()) != null) {
                        writer2.write(line);
                        writer2.newLine();
                    }
        
                    while ((line = reader2.readLine()) != null) {
                        writer1.write(line);
                        writer1.newLine();
                    }
                    
                    JOptionPane.showMessageDialog(null, "Contents exchanged between files!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
