/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package foodstorageapp;

/**
 *
 * @author Gabriela Richardz Nunes - 2025262
 */

/*
 * Declaration of AI Use:
 * This project received assistance from ChatGPT to review logic and structure.
 * All code was written, tested, and understood by the student.
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;


//class to declare food information
class Food {
    private String name;
    private int weight; // grams
    private LocalDate bestBefore;
    private LocalDateTime placedTime;

    public Food(String name, int weight) {
        this.name = name;
        this.weight = weight;
        this.bestBefore = LocalDate.now().plusWeeks(2);
        this.placedTime = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Food{name='" + name + "', weight=" + weight + "g, bestBefore=" + bestBefore + ", placedTime=" + placedTime + "}";
    }
}

//storage class
class Storage {
    private Food[] foods;
    private final int CAPACITY = 8;

    private int top;
    private int front;
    private int rear;
    private boolean useOppositeDoor; // true = FIFO, false = LIFO

    public Storage(boolean useOppositeDoor) {
        this.foods = new Food[CAPACITY];
        this.useOppositeDoor = useOppositeDoor;
        this.top = -1;
        this.front = -1;
        this.rear = -1;
    }

//add food method
    public void addFood(Food food) {
        if (useOppositeDoor) {
            if (rear == CAPACITY - 1) {
                System.out.println("Storage is full. Cannot add more foods.");
                return;
            }
            if (front == -1 && rear == -1) {
                front = 0;
                rear = 0;
            } else {
                rear++;
            }
            foods[rear] = food;
            System.out.println("Added to storage (FIFO): " + food);

//alert when reach the maximum capacity
            if (rear == CAPACITY - 1) {
                System.out.println("⚠️ Warning: Storage is now FULL (" + CAPACITY + "/" + CAPACITY + " items).");
            }

        } else {
            if (top == CAPACITY - 1) {
                System.out.println("Storage is full. Cannot add more foods.");
                return;
            }
            foods[++top] = food;
            System.out.println("Added to storage (LIFO): " + food);

//alert when reach the maximum capacity
            if (top == CAPACITY - 1) {
                System.out.println("⚠️ Warning: Storage is now FULL (" + CAPACITY + "/" + CAPACITY + " items).");
            }
        }
    }

//remove food method
public void removeFood() {
        if (useOppositeDoor) {
            if (front == -1 && rear == -1) {
                System.out.println("Storage is empty. Nothing to remove.");
                return;
            }
            Food removed = foods[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front++;
            }
            System.out.println("Removed from storage (FIFO): " + removed);
        } else {
            if (top == -1) {
                System.out.println("Storage is empty. Nothing to remove.");
                return;
            }
            Food removed = foods[top--];
            System.out.println("Removed from storage (LIFO): " + removed);
        }
    }

//show food method
public void showFoods() {
        if (useOppositeDoor) {
            if (front == -1 && rear == -1) {
                System.out.println("Storage is empty.");
                return;
            }
            System.out.println("Foods in storage (FIFO):");
            for (int i = front; i <= rear; i++) {
                System.out.println("  " + foods[i]);
            }
        } else {
            if (top == -1) {
                System.out.println("Storage is empty.");
                return;
            }
            System.out.println("Foods in storage (LIFO):");
            for (int i = 0; i <= top; i++) {
                System.out.println("  " + foods[i]);
            }
        }
    }

//peek food method
public void peekFood() {
        if (useOppositeDoor) {
            if (front == -1) {
                System.out.println("Storage is empty.");
            } else {
                System.out.println("Front food (FIFO): " + foods[front]);
            }
        } else {
            if (top == -1) {
                System.out.println("Storage is empty.");
            } else {
                System.out.println("Top food (LIFO): " + foods[top]);
            }
        }
    }

//search food method by name
public void searchFood(String name) {
        boolean found = false;
        System.out.println("Searching for food: '" + name + "'...");

        if (useOppositeDoor) { // FIFO
            if (front == -1 && rear == -1) {
                System.out.println("Storage is empty. Nothing to search.");
                return;
            }
            for (int i = front; i <= rear; i++) {
                if (foods[i] != null && foods[i].getName().equalsIgnoreCase(name)) {
                    System.out.println("Found (FIFO): " + foods[i]);
                    found = true;
                }
            }
        } else { // LIFO
            if (top == -1) {
                System.out.println("Storage is empty. Nothing to search.");
                return;
            }
            for (int i = 0; i <= top; i++) {
                if (foods[i] != null && foods[i].getName().equalsIgnoreCase(name)) {
                    System.out.println("Found (LIFO): " + foods[i]);
                    found = true;
                }
            }
        }
        

        if (!found) {
            System.out.println("No food found with the name: " + name);
        }
    }
}


//menu to select FIFO or LIFO
public class FoodStorageApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int modeChoice = 0;
        while (true) {
            try {
                System.out.println("Choose storage mode:");
                System.out.println("1. Use only front door (LIFO stack)");
                System.out.println("2. Use front + opposite door (FIFO queue)");
                System.out.print("Enter your choice: ");
                modeChoice = Integer.parseInt(scanner.nextLine());
                if (modeChoice == 1 || modeChoice == 2) break;
                System.out.println("Invalid option. Please enter 1 or 2.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        boolean useOppositeDoor = (modeChoice == 2);
        Storage storage = new Storage(useOppositeDoor);
        
//interactive menu - User can: add/remove/show/peek/search/exit food.
        
            while (true) {
            System.out.println("\nConfectioner Storage Menu:");
            System.out.println("1. Add Food");
            System.out.println("2. Remove Food");
            System.out.println("3. Show Foods");
            System.out.println("4. Peek Food");
            System.out.println("5. Search Food");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    String name;
                    int weight;

                    do {
                        System.out.print("Enter food name: ");
                        name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            System.out.println("Food name cannot be empty. Try again.");
                        }
                    } while (name.isEmpty());

                    while (true) {
                        System.out.print("Enter food weight (grams): ");
                        try {
                            weight = Integer.parseInt(scanner.nextLine());
                            if (weight <= 0) {
                                System.out.println("Weight must be positive. Try again.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number for weight.");
                        }
                    }

                    storage.addFood(new Food(name, weight));
                    break;

                case 2:
                    storage.removeFood();
                    break;

                case 3:
                    storage.showFoods();
                    break;

                case 4:
                    storage.peekFood();
                    break;

                case 5:
                    System.out.print("Enter food name to search: ");
                    String searchName = scanner.nextLine().trim();
                    if (!searchName.isEmpty()) {
                        storage.searchFood(searchName);
                    } else {
                        System.out.println("Food name cannot be empty.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
    
    

