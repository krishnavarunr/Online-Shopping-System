package mypackage;
import java.util.*;
import java.io.Serializable;

public class Accounts implements Serializable
{
    public String name,mobile,password;
    public ArrayList<Order> activeOrders, completedOrders;
    public int prime;


    public Accounts(String name,String mobile,String password, int prime)
    {
        this.name = name;
        this.mobile = mobile ;
        this.password  = password;
        /*this.activeOrder = activeOrder;
        this.completeOrder = completeOrder;*/
        this.prime = prime;
        activeOrders = new ArrayList<>();
        //completedOrders = new ArrayList<>();
    }

    public void upgradePrime()
    {
        prime =1;
    }

    public void cancelPrime()
    {
        prime =0;
    }

    public String getName()
  {
        return name;
    }

     public String getMobile()
  {
        return mobile;
    }

     public String getPassword()
  {
       return password;
    }

    public int getPrime()
    {
        return prime;
    }

    public void placeOrder(Order o)
    {
        activeOrders.add(o);
        o.updateSL();
        //o.details();
    }

    public void returnOrder(int id)
    {

    int a =0;
    for(Order o : activeOrders)
    {
      if(o.status == 0 && o.deliveryTime.compareTo(new java.util.Date())<=0)
      {
      //System.out.println("Status : Delivered not rated");
        o.status=1;
      }
      if(o.getID()==id && o.status!=0)
      {
        o.changeStatus(3);
        a=1;
        System.out.println("Order is returned");
        o.returnSL();
      }
    }
    if(a==0)
    {
      System.out.println("Not found");
    }
    }

  public void cancelOrder(int id) {
        int a = 0;
        for (Order o : activeOrders) {
            if (o.status == 0 && o.deliveryTime.compareTo(new java.util.Date()) <= 0) {
                o.status = 1;
            }
            if (o.getID() == id && o.status == 0) {
                o.changeStatus(4);
                System.out.println("Order is cancelled");
                a = 1;
                o.returnSL();
            }
        }
        if (a == 0) {
            System.out.println("Order not found");
        }
    }

    public void viewOrder(int id) {
        int found = 0;
        System.out.println(" Your order is ");
        for (Order o : activeOrders) {
            if (o.status == 0 && o.deliveryTime.compareTo(new java.util.Date()) <= 0) {
                o.status = 1;
            }
            if (o.getID() == id) {
                o.details();
                found = 1;
            }
        }
        if (found == 0) {
            System.out.println("Order not found");
        }
    }

    public void viewActive() {
        int found = 0;
        System.out.println("Active orders are ");
        for (Order o : activeOrders) {
            if (o.status == 0 && o.deliveryTime.compareTo(new java.util.Date()) <= 0) {
                o.status = 1;
            }
            if (o.status == 0) {
                o.details();
                found = 1;
            }
        }
        if (found == 0) {
            System.out.println("No active orders found");
        }
    }

    public void viewCompleted() {
        int found = 0;
        System.out.println("Completed orders are ");
        for (Order o : activeOrders) {
            if (o.status == 0 && o.deliveryTime.compareTo(new java.util.Date()) <= 0) {
                o.status = 1;
            }
            if (o.status != 0) {
                o.details();
                found = 1;
            }
        }
        if (found == 0) {
            System.out.println("No completed orders found");
        }
    }

    public void rateOrders(int id) {
        int found = 0;
        for (Order o : activeOrders) {
            if (o.status == 0 && o.deliveryTime.compareTo(new java.util.Date()) <= 0) {
                o.status = 1;
            }
            if (o.status == 1) {
                o.rating();
                o.changeStatus(2);
                found = 1;
            }
        }
        if (found == 0) {
            System.out.println("No orders to rate");
        }
    }
}