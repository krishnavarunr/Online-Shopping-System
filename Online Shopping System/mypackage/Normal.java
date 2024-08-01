package mypackage;
import java.util.*;
public class Normal extends Pack
{
  Normal(Product i, int q)
  {
    a = i;
    quantity = q;
  }
  public double cal()
  {
    return a.getPrice()*quantity;
  } 
}