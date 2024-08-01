import java.util.*;

interface Item
{
	public double getPrice();
	public void order();
	public int getID();
	public String getName();
	public double getOffer();
	public void updateRating(double r);
	public int getSL();	
}

class Product implements Item
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


abstract class Pack
{
	Product a;
	int quantity;

	public abstract double cal();
}

class Normal extends Pack
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

class Prime extends Pack
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

class Cart
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

class Order {
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
        deliveryTime = new Date(orderTime.getTime() + 14 * 24 * 60 * 60 * 1000);
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
		System.out.println("Address :\n"+ address);
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
}

class Accounts{
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
class Database{
     public ArrayList<Accounts> accounts;
     public ArrayList<Product> products;
    
    public Database(ArrayList<Accounts> accounts,ArrayList<Product> products){
        this.accounts = accounts;
        this.products = products ;
      
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
            }
        }
    }

    public void user(Accounts a)
    {
        Scanner obj = new Scanner(System.in);
        while(true)
        {
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
                    System.out.println("Press 1 to add to cart\nPress 2 to view cart\nPress 3 to remove from cart\nPress any other number to Finalise");
                    int cc = obj.nextInt();
                    obj.nextLine();
                    if(cc==1)
                    { 
                        System.out.println("Press 1 to search by name\nPress 2 to search by type\nPress 3 to order\nPress 4 to exit");
                        int c1 = obj.nextInt();
                        Pack p = null;
                        obj.nextLine();
                        if(c1 == 1)
                        {
                            System.out.print("Enter the name : ");
                            String n = obj.nextLine();
                            searchbyname(n);
                        } 
                        else if(c1==2)
                        {
                            System.out.print("Enter the type : ");
                            String t = obj.nextLine();
                            searchbytype(t);
                        }
                        else if(c1==3)
                        {
                            int flag =0;
                            System.out.println("Enter the ID of the product : ");
                            int i = obj.nextInt();
                            obj.nextLine();
                            for(Product pr : products)
                            {
                                if(pr.getID()==i)
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
                            }
                            if(flag==0 || p == null)
                            {
                                System.out.println("No such product exist");
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
                        System.out.println("Exit");
                    }
                }
            }
            else
            {
                System.out.println("Logging out");
                break;
            }
        }
    }   
}

/*class Admin {
    public static void viewAllOrders(ArrayList<Accounts> accounts) {
        for (Accounts account : accounts) {
            System.out.println("Orders for " + account.getName() + " (" + account.getMobile() + "):");
            account.viewActive();
            account.viewCompleted();
        }
    }

    public static void markOrderCompleted(ArrayList<Accounts> accounts, int orderId) {
        for (Accounts account : accounts) {
            for (Order order : account.activeOrders) {
                if (order.getID() == orderId && order.status == 0) {
                    order.changeStatus(1);
                    System.out.println("Order ID " + orderId + " marked as completed.");
                    return;
                }
            }
        }
        System.out.println("Order ID " + orderId + " not found or already completed.");
    }
    public static void addProduct(ArrayList<Product> products) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter product type: ");
        String type = scanner.nextLine();

        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        System.out.print("Enter product offer percentage: ");
        double offer = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter initial stock quantity: ");
        int stockLeft = scanner.nextInt();
        scanner.nextLine();

        products.add(new Product(id, name, price, type, description, offer, stockLeft));
        System.out.println("Product added successfully.");
    }

    public static void editProduct(ArrayList<Product> products) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID to edit: ");
        int idToEdit = scanner.nextInt();
        scanner.nextLine();

        for (Product product : products) {
            if (product.getID() == idToEdit) {
                System.out.println("Enter new details for product ID " + idToEdit + ":");
                
                System.out.print("Enter new product name: ");
                String newName = scanner.nextLine();
                product.name = newName;

                System.out.print("Enter new product price: ");
                double newPrice = scanner.nextDouble();
                product.price = newPrice;
                scanner.nextLine();

                System.out.print("Enter new product type: ");
                String newType = scanner.nextLine();
                product.type = newType;

                System.out.print("Enter new product description: ");
                String newDescription = scanner.nextLine();
                product.description = newDescription;

                System.out.print("Enter new product offer percentage: ");
                double newOffer = scanner.nextDouble();
                product.offer = newOffer;
                scanner.nextLine();

                System.out.print("Enter new stock quantity: ");
                int newStockLeft = scanner.nextInt();
                product.stockLeft = newStockLeft;
                scanner.nextLine();

                System.out.println("Product edited successfully.");
                return;
            }
        }

        System.out.println("Product ID " + idToEdit + " not found.");
    }
}*/

class Main 
{
    public static void main(String args[])
    {
        ArrayList<Accounts> a = new ArrayList<>();
        ArrayList<Product> p = new ArrayList<>();
        a.add(new Accounts("Walter White", "9865877516", "ww", 1));
        a.add(new Accounts("Jesse Pinkman", "9865877510", "jp", 0));
        a.add(new Accounts("Saul Goodman", "9865877511", "sg", 1));
        a.add(new Accounts("Tony Soprano", "9865877512", "ts", 1));
        a.add(new Accounts("Gustavo Fring", "9865877513", "gf", 0));
        a.add(new Accounts("Christopher Waltz", "9865877514", "cw", 0));
        a.add(new Accounts("Mark Grayson", "9865877515", "mg", 0));
        a.add(new Accounts("Skyler White", "9865877517", "sw", 1));
        a.add(new Accounts("Hank Schrader", "9865877518", "hs", 0));

        p.add(new Product(1, "Samsung M31", 16000.0, "Mobile", "RAM : 6GB, ROM : 128GB", 15.0, 400));
        p.add(new Product(2, "Apple iPhone 12", 79999.0, "Mobile", "RAM : 6GB, ROM : 256GB", 10.0, 200));
        p.add(new Product(3, "Dell XPS 13", 99999.0, "Laptop", "Intel Core i7, 16GB RAM, 512GB SSD", 12.0, 150));
        p.add(new Product(4, "Sony Bravia 55-inch", 54999.0, "TV", "4K UHD, Smart TV", 8.0, 100));
        p.add(new Product(5, "Nike Air Max", 6999.0, "Footwear", "Running Shoes", 20.0, 300));
        p.add(new Product(6, "Canon EOS Rebel T7i", 64999.0, "Camera", "24.2MP DSLR", 5.0, 50));
        Database database = new Database(a, p);
        Scanner obj = new Scanner(System.in);
        int flag=0;
        Accounts account = null;
        int login = 0;
        while(true)
        {
            System.out.println("Press 1 for Login\nPress 2 for signup\nPress 3 for login as admin\nAny other number to exit");
            int c = obj.nextInt();
            obj.nextLine();
            if(c==1)
            {
                while(true)
                {
                    System.out.print("Enter your mobile number : ");
                    String n = obj.nextLine();
                    flag = 0;
                    for(Accounts q : a)
                    {
                        if(n.equals(q.getMobile()))
                        {
                            flag = 1;
                            account = q;
                        }
                    }
                    if(flag == 0)
                    {
                        System.out.println("No such account exist try sign up to add your account");
                        break;
                    }
                    else
                    {
                        System.out.print("Enter Password : ");
                        String pa = obj.nextLine();
                        if(account.getPassword().equals(pa))
                        {
                            database.user(account);
                            System.out.println("Logging out\n..........");
                            break;
                        }
                        else
                        {
                            System.out.println("Password is wrong Try again");
                            continue;
                        }
                    }
                }
            }
            if(c==2)
            {
                System.out.print("Enter your Mobile number : ");
                String mobile = obj.nextLine();
                System.out.print("Enter your name : ");
                String name = obj.nextLine();
                System.out.print("Create Password : ");
                String pass = obj.nextLine();
                database.addAccounts(new Accounts(name, mobile, pass, c));
                System.out.println("Account created successfully Login to continue");
            }
            if(c==3)
            {
                //admin part
                /*while (true) {
                    System.out.println("Press 1 to view all orders\nPress 2 to mark an order as completed\nPress 3 to add a product\nPress 4 to edit a product\nAny other number to exit");
                    int adminChoice = obj.nextInt();
                    obj.nextLine();
                    if (adminChoice == 1) {
                        admin.viewAllOrders(a);
                    } else if (adminChoice == 2) {
                        System.out.print("Enter the order ID to mark as completed: ");
                        int orderId = obj.nextInt();
                        obj.nextLine();
                        admin.markOrderCompleted(a, orderId);
                    } else if (adminChoice == 3) {
                        admin.addProduct(p);
                    } else if (adminChoice == 4) {
                        admin.editProduct(p);
                    } else {
                        System.out.println("Exiting Admin Panel.");
                        break;
                    }
                }*/
            }
            else
            {
                System.out.println("Program ended.....");
                break;
            }
        } 
    }
}
