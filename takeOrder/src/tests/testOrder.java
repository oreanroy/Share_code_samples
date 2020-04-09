package tests;

import Model.item;
import Model.order;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;

public class testOrder {

    private order myOrder;

    @Before
    public void setUp(){
        List<item> testitems = new ArrayList<item>();
        item tikkiBurger = new item("Tiki Burger", 10, 5);
        item cokeFLoat = new item("cokeFloat", 12, 5);
        testitems.add(tikkiBurger);
        testitems.add(cokeFLoat);
        myOrder = new order(testitems, "ghostPc");
    }

    @Test
    public void testgetUniqueId(){
        String a = myOrder.getUniqueId();
        String b = myOrder.getUniqueId();
        assertNotEquals(a, b);
    }

    @Test
    public void testgetCurrentTime(){
        String a = myOrder.getCurrentTime();
    }
}
