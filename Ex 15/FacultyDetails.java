import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class FacultyDetails {
    private static Map<String, String> facultyMap = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            String option = JOptionPane.showInputDialog("Menu\n1. Add new record\n2. Delete a record\n3. Update a record\n4. Display all records\n5. Display a particular record\n6. Exit");
            
            switch (option) {
                case "1":
                    addRecord();
                    break;
                case "2":
                    deleteRecord();
                    break;
                case "3":
                    updateRecord();
                    break;
                case "4":
                    displayAllRecords();
                    break;
                case "5":
                    displayRecord();
                    break;
                case "6":
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
    }
    
    private static void addRecord() {
        String name = JOptionPane.showInputDialog("Enter faculty name:");
        String details = JOptionPane.showInputDialog("Enter faculty details:");
        
        facultyMap.put(name, details);
        
        JOptionPane.showMessageDialog(null, "Record added successfully!");
    }
    
    private static void deleteRecord() {
        String name = JOptionPane.showInputDialog("Enter faculty name:");
        
        if (facultyMap.containsKey(name)) {
            facultyMap.remove(name);
            JOptionPane.showMessageDialog(null, "Record deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Faculty record not found.");
        }
    }
    
    private static void updateRecord() {
        String name = JOptionPane.showInputDialog("Enter faculty name:");
        
        if (facultyMap.containsKey(name)) {
            String newDetails = JOptionPane.showInputDialog("Enter new faculty details:");
            facultyMap.put(name, newDetails);
            JOptionPane.showMessageDialog(null, "Record updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Faculty record not found.");
        }
    }
    
    private static void displayAllRecords() {
        StringBuilder allRecords = new StringBuilder();
        
        for (Map.Entry<String, String> entry : facultyMap.entrySet()) {
            allRecords.append("Name: ").append(entry.getKey()).append("\n");
            allRecords.append("Details: ").append(entry.getValue()).append("\n\n");
        }
        
        JOptionPane.showMessageDialog(null, allRecords.toString());
    }
    
    private static void displayRecord() {
        String name = JOptionPane.showInputDialog("Enter faculty name:");
        
        if (facultyMap.containsKey(name)) {
            String details = facultyMap.get(name);
            JOptionPane.showMessageDialog(null, "Name: " + name + "\nDetails: " + details);
        } else {
            JOptionPane.showMessageDialog(null, "Faculty record not found.");
        }
    }
}