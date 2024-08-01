import java.util.*;
import java.io.*;
import mypackage.*;
class Use 
{
    private static void saveObjectsToFile1(ArrayList<Accounts> object, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(object);
            System.out.println("Objects saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void saveObjectsToFile2(ArrayList<Product> object, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(object);
            System.out.println("Objects saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object loadObjectsFromFile1(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return(ArrayList<Accounts>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static Object loadObjectsFromFile2(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return(ArrayList<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void main(String args[])
    {
        /*ArrayList<Accounts> a = new ArrayList<>();
        ArrayList<Product> p = new ArrayList<>();
        a.add(new Accounts("Walter White", "9865877516", "ww", 1));
        a.add(new Accounts("Jesse Pinkman", "9865877510", "jp", 0));
        a.add(new Accounts("Saul Goodman", "9865877511", "sg", 1));
        a.add(new Accounts("Tony Soprano", "9865877512", "ts", 1));
        a.add(new Accounts("Gustavo Fring", "9865877513", "gf", 0));
        a.add(new Accounts("Christopher Moltisanti", "9865877514", "cm", 0));
        a.add(new Accounts("Mark Grayson", "9865877515", "mg", 0));
        a.add(new Accounts("Skyler White", "9865877517", "sw", 1));
        a.add(new Accounts("Hank Schrader", "9865877518", "hs", 0));

        p.add(new Product(1, "Samsung M31", 16000.0, "Mobile", "RAM : 6GB, ROM : 128GB", 15.0, 400));
        p.add(new Product(2, "Apple iPhone 12", 79999.0, "Mobile", "RAM : 6GB, ROM : 256GB", 10.0, 200));
        p.add(new Product(3, "Dell XPS 13", 99999.0, "Laptop", "Intel Core i7, 16GB RAM, 512GB SSD", 12.0, 150));
        p.add(new Product(4, "Sony Bravia 55-inch", 54999.0, "TV", "4K UHD, Smart TV", 8.0, 100));
        p.add(new Product(5, "Nike Air Max", 6999.0, "Footwear", "Running Shoes", 20.0, 300));
        p.add(new Product(6, "Canon EOS Rebel T7i", 64999.0, "Camera", "24.2MP DSLR", 5.0, 50));*/
        ArrayList<Accounts> a = (ArrayList<Accounts>) loadObjectsFromFile1("accounts.ser");
        ArrayList<Product> p = (ArrayList<Product>) loadObjectsFromFile2("products.ser");
        Database database;

        if (a != null && p != null) {
            database = new Database(a, p);
            //System.out.println("Objects loaded successfully.");
        } 
        else 
        {
            System.out.println("Error loading objects.");
            database = null;
        }
       // Database database = new Database(a, p);
        Scanner obj = new Scanner(System.in);
        int flag=0;
        Accounts account = null;
        int login = 0;
        int c;
        while(true)
        {
            System.out.println("Press 1 for Login\nPress 2 for signup\nPress 3 for login as admin\nAny other number to exit");
            c = obj.nextInt();
            obj.nextLine();
            if(c==1)
            {
                while(true)
                {
                    System.out.print("Enter your mobile number : ");
                    String n;
                    try{
                        n = obj.nextLine();
                        if (n.length()!=10) {
                            throw new ValidMobileException("Mobile number format is not correct");
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                        break;
                    }
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
            else if(c==2)
            {
                System.out.print("Enter your Mobile number : ");
                String mobile;
                try{
                    mobile = obj.nextLine();
                    if (mobile.length()!=10) {
                        throw new ValidMobileException("Mobile number format is not correct");
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                    break;
                }
                System.out.print("Enter your name : ");
                String name = obj.nextLine();
                System.out.print("Create Password : ");
                String pass = obj.nextLine();
                database.addAccounts(new Accounts(name, mobile, pass, c));
                System.out.println("Account created successfully Login to continue");
            }
            else if(c==3)
            {
                   database.adminFunctionality(database);
            }
            else
            {
                saveObjectsToFile1(database.accounts, "accounts.ser");
                saveObjectsToFile2(database.products, "products.ser");
                System.out.println("Program ended.....");
                break;
            }
        } 
    }
}
