package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class order {
    public String id;
    public List<item> orderItems = new ArrayList<item>();
    public String customerName;
    public String orderTime;
    public float cost;
    public float timeToComplete;


    public order(List<item> allitems, String customerName){
        this.id = getUniqueId();
        this.orderItems = allitems;
        this.customerName = customerName;
        this.orderTime = getCurrentTime();
        this.cost = calculateCost(allitems);
        this.timeToComplete = calculateTimeToComplete(allitems);
    }



    //TODO: write this function
    //MODIFIES this
    //EFFECTS returns the total cost of the item in order
    private float calculateCost(List<item> totalitems){
        float totalCost=0;
        for (int i=0;i<totalitems.size(); i++){
            totalCost+=totalitems.get(i).cost;
        }
        return totalCost;
    }

    //TODO: write this function
    //MODIFIES this
    //EFFECTS returns the time it will take to complete the oder
    private float calculateTimeToComplete(List<item> totalitems){
        float totalTime=0;
        for (int i=0;i<totalitems.size(); i++){
            totalTime+=totalitems.get(i).preprationTime;
        }
        return totalTime;
    }

    //TODO: write a function which returns a unique id of given length
    //MODIFIES this
    //EFFECST returns a unique id of a specified length
    public String getUniqueId(){
        char[] characters = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','1','2','3','4','5'};
        String unid="";
        int length = characters.length;
        int i = 5;
        while (i>0){
            int randNum = ThreadLocalRandom.current().nextInt(0, length);
            unid+=characters[randNum];
            i--;
        }
        return unid;
    }

    //TODO: write a function which returns the current time
    //MODIFIES this
    //EFFECTS returns the current time in order date hour min (24hr clock)
    public String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }
}
