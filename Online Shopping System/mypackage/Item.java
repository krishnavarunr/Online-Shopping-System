package mypackage;
import java.util.*;
public interface Item
{
  public double getPrice();
  public void order();
  public int getID();
  public String getName();
  public double getOffer();
  public void updateRating(double r);
  public int getSL();	
}