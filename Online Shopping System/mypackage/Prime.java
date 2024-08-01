package mypackage;
import java.util.*;
public class Prime extends Pack
{
  Prime(Product i, int q)
  {
    a = i;
    quantity = q;
  }
  public double cal()
  {
    return (a.getPrice()-(a.getPrice()*a.getOffer()/100))*quantity;
  }
}