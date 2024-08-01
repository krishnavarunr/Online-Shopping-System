package mypackage;
import java.util.*;
import java.io.Serializable;

public class Order implements Serializable {
  int id;
  Cart cart;
  int otp;
  Date orderTime;
  Date deliveryTime;
  String address;
  int status;
  String complain;

  Order(Cart c, String a)
{
      cart = c;
      address = a;
      status = 0;
      orderTime = new java.util.Date();
      deliveryTime = new Date(orderTime.getTime() + 60 * 1000);
      Random random = new Random();
      id = random.nextInt(1000000);
      otp = random.nextInt(10000);
  }

  public void changeStatus(int a)
{
      status = a;
  }

  public void rating()
{
      System.out.println("Enter ratings");
      Scanner obj = new Scanner(System.in);
      for (Pack i : cart.list)
  {
          System.out.print(i.a.getName() + " : ");
    double d = obj.nextDouble();
          i.a.updateRating(d);
      }
  }
public void complain(String s)
{
  complain = s;
}
public int getID()
{
  return id;
}

public int getOTP()
{
  return otp;
}
public void details()
{
  System.out.println("ID : "+getID());
  cart.view();
  System.out.println("Ordered time : " + orderTime);
  System.out.println("Delivery time : " + deliveryTime);
  System.out.println("OTP : "+ otp);
  System.out.println("Address : "+ address);
  if(status==0)
  {
    System.out.println("Status : Not Delivered yet");
  }
  else if(status == 0 && deliveryTime.compareTo(new java.util.Date())<=0)
  {
    System.out.println("Status : Delivered not rated");
    status=1;
  }
  else if(status == 1)
  {
    System.out.println("Status : Delivered not rated");
  }
  else if(status == 2)
  {
    System.out.println("Status : Delivered and rated");
  }
  else if(status == 3)
  {
    System.out.println("Status : Returned");
  }
  else if(status == 4)
  {
    System.out.println("Status : Canceled");
  }
}
public void updateSL()
{
  for(Pack p : cart.list)
  {
      p.a.addStock(-(p.quantity));
  }
}
public void returnSL()
{
  for(Pack p : cart.list)
  {
      p.a.addStock(p.quantity);
  }
}
}