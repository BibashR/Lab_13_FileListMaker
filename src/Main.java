import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        boolean done = false;
        boolean needsToBeSaved = false;
        String currentFileName = null;

        while (!done) {
            displayMenu();
            String cmd = in.nextLine().trim().toUpperCase();

            try {
                switch (cmd) {
                    case "A":
                        System.out.print("Enter item to add: ");
                        String addItem = in.nextLine();
                        list.add(addItem);
                        needsToBeSaved = true;
                        break;

                    case "D":
                        viewList(list);
                        int delIndex = SafeInput.getRangedInt(in, "Enter index to delete", 0, list.size() - 1);
                        list.remove(delIndex);
                        needsToBeSaved = true;
                        break;

                    case "I":
                        viewList(list);
                        int insIndex = SafeInput.getRangedInt(in, "Enter index to insert at", 0, list.size());
                        System.out.print("Enter item to insert: ");
                        String insItem = in.nextLine();
                        list.add(insIndex, insItem);
                        needsToBeSaved = true;
                        break;

                    case "M":
                        viewList(list);
                        int fromIndex = SafeInput.getRangedInt(in, "Move which index?", 0, list.size() - 1);
                        int toIndex = SafeInput.getRangedInt(in, "To what index?", 0, list.size() - 1);
                        String itemToMove = list.remove(fromIndex);
                        list.add(toIndex, itemToMove);
                        needsToBeSaved = true;
                        break;

                    case "C":
                        if (SafeInput.getYNConfirm(in, "Clear the entire list?")) {
                            list.clear();
                            needsToBeSaved = true;
                        }
                        break;

                    case "V":
                        viewList(list);
                        break;

                    case "S":
                        if (currentFileName == null) {
                            System.out.print("Enter filename to save as (without .txt): ");
                            currentFileName = in.nextLine();
                        }
                        saveFile(list, currentFileName);
                        needsToBeSaved = false;
                        break;

                    case "O":
                        if (needsToBeSaved) {
                            if (SafeInput.getYNConfirm(in, "Unsaved changes. Save first?")) {
                                if (currentFileName == null) {
                                    System.out.print("Enter filename to save as (without .txt): ");
                                    currentFileName = in.nextLine();
                                }
                                saveFile(list, currentFileName);
                                needsToBeSaved = false;
                            }
                        }
                        System.out.print("Enter filename to open (without .txt): ");
                        currentFileName = in.nextLine();
                        loadFile(list, currentFileName);
                        needsToBeSaved = false;
                        break;

                    case "Q":
                        if (needsToBeSaved) {
                            if (SafeInput.getYNConfirm(in, "You have unsaved changes. Save before quitting?")) {
                                if (currentFileName == null) {
                                    System.out.print("Enter filename to save as (without .txt): ");
                                    currentFileName = in.nextLine();
                                }
                                saveFile(list, currentFileName);
                            }
                        }
                        done = true;
                        break;

                    default:
                        System.out.println("Invalid command.");
                }
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            }
        }

        System.out.println("Program ended.");
    }

    private static void displayMenu() {
        System.out.println("\n=== FILE LIST MAKER ===");
        System.out.println("A – Add");
        System.out.println("D – Delete");
        System.out.println("I – Insert");
        System.out.println("M – Move");
        System.out.println("C – Clear");
        System.out.println("V – View");
        System.out.println("S – Save");
        System.out.println("O – Open");
        System.out.println("Q – Quit");
        System.out.print("Enter command: ");
    }

    private static void viewList(ArrayList<String> list) {
        System.out.println("\nCurrent List:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d: %s%n", i, list.get(i));
        }
    }

    public static void saveFile(ArrayList<String> list, String filename) throws IOException {
        Path file = Paths.get(filename + ".txt");
        Files.write(file, list);
        System.out.println("List saved to " + file.getFileName());
    }

    public static void loadFile(ArrayList<String> list, String filename) throws IOException {
        Path file = Paths.get(filename + ".txt");
        if (!Files.exists(file)) {
            System.out.println("File not found: " + file.getFileName());
            return;
        }

        List<String> lines = Files.readAllLines(file);
        list.clear();
        list.addAll(lines);
        System.out.println("List loaded from " + file.getFileName());
    }
}
