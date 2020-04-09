package ui;

import Model.item;
import Model.takeOrderModel;

import java.util.ArrayList;
import java.util.List;

public class orderUI {


    public static void main(String[] args) {
         List<item> fullMeal = new ArrayList<>();

        List<item> halfMeal = new ArrayList<>();
        item Burger = new item("Burger", 10, 5);
        item cokeFloat = new item("Float", 10, 4);
        item Fries = new item("Fries", 5, 10);
        fullMeal.add(Burger);
        //fullMeal.add(cokeFloat);
        //fullMeal.add(Fries);
        //fullMeal.add(Burger);
        halfMeal.add(Burger);
        halfMeal.add(cokeFloat);
        takeOrderModel orderMachine = new takeOrderModel();

        orderMachine.takeNewOrder(fullMeal, "Rahul Roy");
        orderMachine.takeNewOrder(halfMeal, "Bacha");
        daemonThread backThread = new daemonThread(orderMachine);
        backThread.setDaemon(true);
        backThread.assignCook();
        orderMachine.takeNewOrder(halfMeal, "Bacha2");
    }
}
