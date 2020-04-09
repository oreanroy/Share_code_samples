package ui;


import Model.takeOrderModel;

public class daemonThread extends Thread{

    //call this method in the main method of driving fucntion

    private  takeOrderModel orderModel;
    daemonThread(takeOrderModel orderModel){
        this.orderModel = orderModel;
    }

    public void assignCook(){
        while(true){

            int toComplete = orderModel.toCompleteOrders.size();
            if ( !orderModel.cookBusy && toComplete>0 ) orderModel.prepareOrder();

        }
    }
}
