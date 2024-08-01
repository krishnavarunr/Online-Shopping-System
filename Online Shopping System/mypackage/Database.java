package mypackage;
import java.util.*;
public class Database{
     public ArrayList<Accounts> accounts;
     public ArrayList<Product> products;

    public Database(ArrayList<Accounts> accounts,ArrayList<Product> products){
        this.accounts = accounts;
        this.products = products ;

    }


 public void adminFunctionality(Database database) {
    Scanner obj = new Scanner(System.in);
    try{
    System.out.print("Enter Admin Password: ");
    String adminPassword = obj.nextLine();
    if (adminPassword.equals("password")) {
   
       System.out.print("Admin Login successful.\n ");
       while (true) {
          System.out.println("Admin Functionality:");
          System.out.println("1. Add Product");
          System.out.println("2. View Products");
          System.out.println("3. View User Accounts");
          System.out.println("4. Update stock");
          System.out.println("5. Edit offers");
          System.out.println("6. Exit Admin Mode");

          int choice = obj.nextInt();
          obj.nextLine();

          switch (choice) {
                case 1:
                  // Admin adds a new product
                  System.out.print("Enter Product ID: ");
                  int productId = obj.nextInt();
                  obj.nextLine();

                  System.out.print("Enter Product Name: ");
                  String productName = obj.nextLine();

                  System.out.print("Enter Product Price: ");
                  double productPrice = obj.nextDouble();
                  obj.nextLine();

                  System.out.print("Enter Product Type: ");
                  String productType = obj.nextLine();

                  System.out.print("Enter Product Description: ");
                  String productDescription = obj.nextLine();

                  System.out.print("Enter Product Offer: ");
                  double productOffer = obj.nextDouble();
                  obj.nextLine();

                  System.out.print("Enter Initial Stock Left: ");
                  int stockLeft = obj.nextInt();
                  obj.nextLine();

                  database.addProducts(new Product(productId, productName, productPrice, productType,
                          productDescription, productOffer, stockLeft));

                  System.out.println("Product added successfully.");
                  break;

                case 2:
                  // Admin views all products
                  System.out.println("All Products:");
                  for (Product product : database.products) {
                      product.details();
                      System.out.println("---------------");
                  }
                  break;

                case 3:
                  // Admin views all user accounts
                  System.out.println("All User Accounts:");
                  for (Accounts user : database.accounts) {
                      System.out.println("Name: " + user.getName());
                      System.out.println("Mobile: " + user.getMobile());
                      System.out.println("Prime Membership: " + (user.getPrime() == 1 ? "Yes" : "No"));
                      System.out.println("---------------");
                  }
                  break;
                
                case 4:
                  System.out.println("Enter the id of the product : ");
                  int i = obj.nextInt();
                  int y = 0;
                  for(Product p : products)
                  {
                    if(p.getID()==i)
                    {  
                        p.details();
                        y=1;
                        System.out.print("Enter the stock to be added : ");
                        int x = obj.nextInt();
                        p.addStock(x);
                        System.out.println("Stock added successfully\n Updated Stock : "+ p.getSL());
                        System.out.println("---------------");
                    }
                    
                  }
                  if(y==0)
                  {
                        System.out.println("No product exist please check and try again");
                        System.out.println("---------------");
                  }
                  break;
                
                case 5:
                  System.out.println("Enter the id of the product : ");
                  int i1 = obj.nextInt();
                  int y1 = 0;
                  for(Product p : products)
                  {
                    if(p.getID()==i1)
                    {  
                        p.details();
                        y1=1;
                        System.out.print("Enter the updated offer : ");
                        double x = obj.nextDouble();
                        p.editOffer(x);
                        System.out.println("Offer updated successfully");
                        System.out.println("---------------");
                    }
                  }
                  if(y1==0)
                    {
                        System.out.println("No product exist please check and try again");
                    }
                  break;    
              
                case 6:
                  // Exit admin mode
                  System.out.println("Exiting Admin Mode.");
                  System.out.println("---------------");
                  return;

              default:
                  System.out.println("Invalid choice. Try again.");
                  break;
          }
      }
  }
  else{
      System.out.println("Admin Login Failed.\n");
      System.out.println("---------------");
  }
  }
  catch(Exception e)
  {
    e.printStackTrace();
  }
}

    public void addAccounts(Accounts a){
        accounts.add(a);
    }
    public void addProducts(Product p){
        products.add(p);
    }
    public void searchbytype(String n)
    {
        for(Product p : products)
        {
            if(p.getType().equalsIgnoreCase(n))
            {
                p.details();
                System.out.println("---------------");
            }
        }
    }
    public void searchbyname(String n)
    {
        for(Product p : products)
        {
            if(p.getName().equalsIgnoreCase(n))
            {
                p.details();
                System.out.println("---------------");
            }
        }
    }

    public void user(Accounts a)
    {
        Scanner obj = new Scanner(System.in);
        while(true)
        {
            try{
            System.out.println("Press 1 to order\nPress 2 to return your order\nPress 3 to rate your orders\nPress 4 to cancel the orders\nPress 5 to view your orders\nPress 6 to view membership\nAny other to exit");
            int c = obj.nextInt();
            obj.nextLine();
            Cart cart = new Cart();
            if(c==1)
            {
                //Cart cart = new Cart();
                while(true)
                {
                    //Cart cart = new Cart();
                    System.out.println("Press 1 to add to cart\nPress 2 to view cart\nPress 3 to remove from cart\nPress 4 to Finalise");
                    int cc = obj.nextInt();
                    obj.nextLine();
                    if(cc==1)
                    { 
                        System.out.println("Press 1 to search by name\nPress 2 to search by type\nPress 3 to order\nPress any other number to exit\n");
                        int c1 = obj.nextInt();
                        Pack p = null;
                        obj.nextLine();
                        if(c1 == 1)
                        {
                            System.out.print("Enter the name : ");
                            String n = obj.nextLine();
                            searchbyname(n);
                            System.out.println("---------------");
                        } 
                        else if(c1==2)
                        {
                            System.out.print("Enter the type : ");
                            String t = obj.nextLine();
                            searchbytype(t);
                            System.out.println("---------------");
                        }
                        else if(c1==3)
                        {
                            int flag =0;
                            System.out.println("Enter the ID of the product : ");
                            int i = obj.nextInt();
                            obj.nextLine();
                            for(Product pr : products)
                            {
                                if(pr.getID()==i&&pr.getSL()!=0)
                                {
                                    flag =1;
                                    System.out.print("Enter the quantity : ");
                                    int q = obj.nextInt();
                                    obj.nextLine();
                                    if(a.getPrime()==0)
                                    {
                                        p = new Normal(pr, q);
                                        break;
                                        //System.out.println("!");
                                    }
                                    else
                                    {
                                        p = new Prime(pr, q);
                                        break;
                                        //System.out.println("!");
                                    }
                                }
                                else if(pr.getID()==i&&pr.getSL()==0)
                                {
                                    System.out.println("Sorry, No Stock Left");
                                    flag =1;
                                    break;
                                }
                            }
                            if(flag==0)
                            {
                                System.out.println("No such product exist\n");
                            }
                            else
                            {
                                cart.addItem(p);
                                cart.view();
                                //System.out.println("%");
                            }
                        }
                    }
                    else if(cc==2)
                    {
                        if(!cart.isempty())
                        {
                            cart.view();
                            System.out.println("---------------");
                        }
                        else
                        {
                            System.out.println("Cart is empty");
                        }
                    }
                    else if(cc==3)
                    {
                        System.out.println("Enter the ID of the item to be removed");
                        int id = obj.nextInt();
                        obj.nextLine();
                        for(Pack i: cart.list)
                        {
                            if(i.a.getID()==id)
                            {
                                cart.removeItem(i);
                            }
                        }
                    }
                    else if(cc==4 && cart != null)
                    {
                        System.out.print("Enter the shipping address : ");
                        String add=obj.nextLine();
                        Order o =new Order(cart, add);
                        a.placeOrder(o);
                        System.out.println("Order placed Successfully");
                        System.out.println("---------------\n");
                        break;
                    }
                }

            }
            else if(c==2)
            {
                System.out.print("Enter the order id : ");
                int i = obj.nextInt();
                obj.nextLine();
                a.returnOrder(i);
            }
            else if(c==3)
            {
                System.out.print("Enter the order id : ");
                int i = obj.nextInt();
                obj.nextLine();
                a.rateOrders(i);
            }
            else if(c==4)
            {
                System.out.print("Enter the order id : ");
                int i = obj.nextInt();
                obj.nextLine();
                a.cancelOrder(i);
            }
            else if(c==5)
            {
                System.out.println("Enter 1 to search\nEnter 2 to view all active order\nEnter 3 to view all completed order");
                int c2 = obj.nextInt();
                obj.nextLine();
                if(c2==1)
                {
                    System.out.print("Enter the order id : ");
                    int i = obj.nextInt();
                    obj.nextLine();
                    a.viewOrder(i);
                }
                else if(c2==2)
                {
                    a.viewActive();
                }
                else if(c2==3)
                {
                    a.viewCompleted();
                }
            }
            else if(c==6)
            {
                if(a.getPrime()==0)
                {
                    System.out.println("Enter 1 to upgrade to prime with many offers with a monthy subscription : ");
                    int i = obj.nextInt();
                    if(i==1)
                    {
                        a.upgradePrime();
                        System.out.println("You are a prime member now");
                    }
                    else{
                        System.out.println("Exit");
                    }
                }
                else
                {
                    System.out.println("Enter 1 to cancel prime membership : ");
                    int i = obj.nextInt();
                    if(i==1)
                    {
                        a.cancelPrime();
                        System.out.println("Membership cancelled");
                    }
                    else{
                        System.out.println("Exit\n............");
                    }
                }
            }
            else
            {
                System.out.println("Logging out\n");
                break;
            }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }   
}