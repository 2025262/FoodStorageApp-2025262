/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package foodstorageapp;

/**
 *
 * @author Gabriela Richardz Nunes - 2025262
 */

import java.time.LocalDate;
import java.time.LocalDateTime;

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
}

//add food method
    public void addFood(Food food) {
        if (useOppositeDoor) {
            if (rear == CAPACITY - 1) {
                System.out.println("❌ Storage is full. Cannot add more foods.");
                return;
            }
            if (front == -1 && rear == -1) {
                front = 0;
                rear = 0;
            } else {
                rear++;
            }
            foods[rear] = food;
            System.out.println("✅ Added to storage (FIFO): " + food);

//alert when reach the maximum capacity
            if (rear == CAPACITY - 1) {
                System.out.println("⚠️ Warning: Storage is now FULL (" + CAPACITY + "/" + CAPACITY + " items).");
            }

        } else {
            if (top == CAPACITY - 1) {
                System.out.println("❌ Storage is full. Cannot add more foods.");
                return;
            }
            foods[++top] = food;
            System.out.println("✅ Added to storage (LIFO): " + food);

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

public class FoodStorageApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    
}
