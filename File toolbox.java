import java.io.*;
import java.util.*;

public class FileToolbox {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\nFile Handling Toolbox:\n created by SanjayKumaran");
            System.out.println("1. Create File\n2. Write to File\n3.Read from File\n4.Encrypt File\n5.Decrypt File\n6.Export to CSV\n7.Scan Directory\n8.Delete File\n9.Show File Metadata\n10.Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) createFile();
            else if (choice == 2) writeFile();
            else if (choice == 3) readFile();
            else if (choice == 4) encryptFile();
            else if (choice == 5) decryptFile();
            else if (choice == 6) exportCSV();
            else if (choice == 7) scanDirectory();
            else if (choice == 8) deleteFile();
            else if (choice == 9) showMetadata();
            else if (choice == 10) {
                System.out.println("Exiting Toolbox. Goodbye! from SanjayKumaran");
                running = false;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    static void createFile() {
        System.out.print("Enter filename to create: ");
        String name = sc.nextLine();
        File file = new File(name);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + name);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void writeFile() {
        System.out.print("Enter filename to write to: ");
        String name = sc.nextLine();
        System.out.println("Enter content (type 'END' to finish):");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name))) {
            while (true) {
                String line = sc.nextLine();
                if (line.equalsIgnoreCase("END")) break;
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Content written.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void readFile() {
        System.out.print("Enter filename to read: ");
        String name = sc.nextLine();
        try (Scanner reader = new Scanner(new File(name))) {
            System.out.println("File Content:");
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    static void encryptFile() {
        System.out.print("Enter filename to encrypt: ");
        String name = sc.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(name));
             BufferedWriter writer = new BufferedWriter(new FileWriter(name + ".enc"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String encoded = Base64.getEncoder().encodeToString(line.getBytes());
                writer.write(encoded);
                writer.newLine();
            }
            System.out.println("Encrypted to: " + name + ".enc");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void decryptFile() {
        System.out.print("Enter encrypted filename: ");
        String name = sc.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(name));
             BufferedWriter writer = new BufferedWriter(new FileWriter(name + ".dec.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                byte[] decoded = Base64.getDecoder().decode(line);
                writer.write(new String(decoded));
                writer.newLine();
            }
            System.out.println("Decrypted to: " + name + ".dec.txt");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void exportCSV() {
        System.out.print("Enter filename to read: ");
        String name = sc.nextLine();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(name + ".csv"))) {
            writer.newLine();
            System.out.println("CSV exported to: " + name + ".csv");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }}
	



    static void scanDirectory() {
        System.out.print("Enter directory path: ");
        String path = sc.nextLine();
        File dir = new File(path);
        if (dir.isDirectory()) {
            System.out.println("Files in directory:");
            File[] files = dir.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    System.out.println("- " + files[i].getName());
                }
            }
        } else {
            System.out.println("Not a valid directory.");
        }
    }

    static void deleteFile() {
        System.out.print("Enter filename to delete: ");
        String name = sc.nextLine();
        File file = new File(name);
        if (file.delete()) {
            System.out.println("File deleted.");
        } else {
            System.out.println("Could not delete file.");
        }
    }

    static void showMetadata() {
        System.out.print("Enter filename for metadata: ");
        String name = sc.nextLine();
        File file = new File(name);
        if (file.exists()) {
            System.out.println("Metadata:");
            System.out.println("Name: " + file.getName());
            System.out.println("Path: " + file.getAbsolutePath());
            System.out.println("Size: " + file.length() + " bytes");
            System.out.println("Readable: " + file.canRead());
            System.out.println("Writable: " + file.canWrite());
            System.out.println("Last Modified: " + new Date(file.lastModified()));
        } else {
            System.out.println("File not found.");
        }
    }
  }
