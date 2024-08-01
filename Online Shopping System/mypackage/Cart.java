package mypackage;
import java.util.*;
import java.io.Serializable;

public class Cart implements Serializable
{
  ArrayList<Pack> list;
    Cart()
    {
        list = new ArrayList<>();
    }
    public boolean isempty()
    {
        if(list.isEmpty())
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
  public void addItem(Pack a)
  {
    list.add(a);
  }
  public void removeItem(Pack a)
  {
    list.remove(a);
  }
  public double caltotal()
  {
    double d = 0;
    for(Pack i:list)
    {
      d += i.cal();
    }
    return d;
  }
  public void view()
  {
    for(Pack i:list)
    {
      System.out.println(i.a.getName()+" "+i.a.getPrice()+"*"+i.quantity+" "+i.cal());
    }
    System.out.println("Total : " + caltotal());
  }
}