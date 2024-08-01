package mypackage;
import java.util.*;
import java.io.Serializable;

public class Product implements Item, Serializable
{
  private int id;
  private String name;
  private double price;
  private String type;
  private String description;
  private double offer;
  private int stockLeft;
  private int stockSold;
  private double rating;

  public Product(int i, String n, double p, String t, String des, double o, int sl)
  {
    id = i;
    name = n;
    price = p;
    type = t;
    description = des;
    offer = o;
    stockLeft = sl;
    stockSold = 0;
    rating = 0;
  }

  public int getID()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public double getPrice()
  {
    return price;
  }

  public double getOffer()
  {
    return offer;
  }
    public String getType()
    {
        return type;
    }
  public int getSL()
  {
    return stockLeft;
  }
  public int getSS()
  {
    return stockSold;
  }
  public void addStock(int a)
  {
    stockLeft += a;
  }
  public void updateRating(double r)
  {
    rating = ((rating * stockSold-1)+r)/stockSold;
  }
  public void order()
  {
    stockSold++;
  }
  public void editOffer(double o)
  {
    offer = o;
  }
    public void details()
    {
        System.out.println(getName()+"\nPrice : "+getPrice()+"\nID : "+getID()+"\n"+description);
    }
}


