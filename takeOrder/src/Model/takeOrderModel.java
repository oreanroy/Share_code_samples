package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class takeOrderModel {

    public static boolean cookBusy;
    public static List<String> toCompleteOrders = new ArrayList<>();
    private static  Map<String, order> allOrdersPlaced = new HashMap<>();
    private static  List<String> preparedOrders = new ArrayList<>();
    private static  List<String> deliveredOrders = new ArrayList<>();

    public takeOrderModel(){
        this.cookBusy = false;
    }


    //TODO: write a fucntion to take new order which returns
    //MODIFIES adds a new order to the order list
    //EFFECTS creates an oder and returns true or false as
    public boolean takeNewOrder(List<item> itemsInOrder, String customerName){
        try {
            order newOrder = new order(itemsInOrder, customerName);
            allOrdersPlaced.put(newOrder.id, newOrder);
            toCompleteOrders.add(newOrder.id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO: write a fucntion which retuns the details of the oder
    //EFFECTS returns the detail of the given order
    public String getOderderDetails(String uid){
        if (allOrdersPlaced.containsKey(uid)){
            order queryOrder = allOrdersPlaced.get(uid);
            return String.format("%s ordered. " +
                    "\n The time to complete is %s " +
                    "\n The price is %s", queryOrder.customerName,
                    queryOrder.timeToComplete, queryOrder.cost);
        }else {
            return "Please check the uid";
        }
    }

    //TODO: write the prepareOrder fcuntion
    //MODIFIES prepared list
    // EFFECTS pics up a order from the queue and puts it the prepared list after the order time
    public void prepareOrder(){
    // pick the last element from list
    if (toCompleteOrders.size() > 0){
        String nextPrepare = toCompleteOrders.get(toCompleteOrders.size()-1);
        order orderToComplete = allOrdersPlaced.get(nextPrepare);
        completeOrder(orderToComplete);
        toCompleteOrders.remove(nextPrepare);
        }
    }

    //Helper function to prepareOrder moves an order from toComplete to prepared order
    private void completeOrder(order orderToComplete){
        changeCookState();
        new java.util.Timer().schedule(
                new java.util.TimerTask(){
                    @Override
                    public void run() {
                        changeCookState();
                        preparedOrders.add(orderToComplete.id);
                        deliverOrder(orderToComplete.id);
                    }
                }, (long) (orderToComplete.timeToComplete*60)
        );
    }

    public void changeCookState(){
        this.cookBusy = !cookBusy;
    }

    // TODO
    // MODIFIES removes a order from the prepared list and puts it in delivered list
    public String deliverOrder(String completedOrder){
        preparedOrders.remove(completedOrder);
        deliveredOrders.add(completedOrder);
        System.out.println(String.format("The order of %s is here", allOrdersPlaced.get(completedOrder).customerName));
        return String.format("The order of %s is here", allOrdersPlaced.get(completedOrder).customerName);
    }


}
