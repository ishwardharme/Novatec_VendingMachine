package vendinMachine;
import java.util.Scanner;
// code by Ishwar Dharme
//ishwardharme320@gmail.com
public class VendingMachine {
    private int teaLeavesStock;
    private int coffeePowderStock;
    private double sugarStock;
    private int milkStock;
    private int waterStock;
    private int teaCupsSold;
    private int coffeeCupsSold;
    private double totalCostAmount;
    private double totalSaleAmount;
 // private double profitLoss;
    private double profit;
    private double loss;
    private int milkLeakage;
    private int waterLeakage;

    public VendingMachine() {
        // Initialize stock levels and other variables
        teaLeavesStock = 500;
        coffeePowderStock = 500;
        sugarStock = 600;
        milkStock = 8000;
        waterStock = 15000;
        teaCupsSold = 0;
        coffeeCupsSold = 0;
        totalCostAmount = 0;
        totalSaleAmount = 0;
 //     profitLoss = 0;
        profit=0;
        loss=0;
        milkLeakage = 0;
        waterLeakage = 0;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option to process:");
            System.out.println("1. Dispense beverage");
            System.out.println("2. Refill Containers");
            System.out.println("3. Show Report");
            System.out.println("4. Exit");
            System.out.print("Enter Option no.: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    dispenseBeverage(scanner);
                    break;
                case 2:
                    refillContainers(scanner);
                    break;
                case 3:
                    showReport();
                    break;
                case 4:
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void dispenseBeverage(Scanner scanner) {
        System.out.println("Select a beverage:");
        System.out.println("1. Light Coffee");
        System.out.println("2. Strong Coffee");
        System.out.println("3. Light Tea");
        System.out.println("4. Strong Tea");
        System.out.print("Enter beverage: ");
        int beverage = scanner.nextInt();

        System.out.print("Enter number of cups: ");
        int cups = scanner.nextInt();

        System.out.print("With sugar? (yes/no): ");
        String withSugar = scanner.next();

        switch (beverage) {
            case 1:
                dispenseCoffee(cups, withSugar.equalsIgnoreCase("yes"), false);
                break;
            case 2:
                dispenseCoffee(cups, withSugar.equalsIgnoreCase("yes"), true);
                break;
            case 3:
                dispenseTea(cups, withSugar.equalsIgnoreCase("yes"), false);
                break;
            case 4:
                dispenseTea(cups, withSugar.equalsIgnoreCase("yes"), true);
                break;
            default:
                System.out.println("Invalid beverage selection.");
        }
    }

    private void dispenseCoffee(int cups, boolean withSugar, boolean strong) {
    	double sugar = strong ? 2 : 1.5;
        int sugarRequired = withSugar ? 2 : 0;
        int milkRequired = strong ? 50 : 60;
        int coffeePowderRequired = strong ? 4 : 2;
        int waterRequired = 100;

        if (canDispense(cups,sugar, sugarRequired,milkRequired, coffeePowderRequired, waterRequired)) {
            int sugarUsed = sugarRequired * cups;
            double sugarofcoffe=sugar*cups;
            int milkUsed = milkRequired * cups;
            int coffeePowderUsed = coffeePowderRequired * cups;
            int waterUsed = waterRequired * cups;

            teaLeavesStock -= 0; // No tea leaves for coffee
            coffeePowderStock -= coffeePowderUsed;
            sugarStock -= (sugarUsed + sugarofcoffe);
            
            milkStock -= milkUsed;
            waterStock -= waterUsed;

            totalCostAmount += (sugarUsed * 1) + (sugarofcoffe*1)+ (milkUsed * 0.15) + (waterUsed * 0.02) + (coffeePowderUsed * 2);
            totalSaleAmount += 17.5 * cups;

            coffeeCupsSold += cups;

            System.out.println("Coffee dispensed: " + cups + " cup(s)");
        } else {
            System.out.println("Insufficient stock to dispense coffee.");
        }
    }

    private void dispenseTea(int cups, boolean withSugar, boolean strong) {
    	
    	double sugar= strong ? 2 : 1.5;
        int sugarRequired = withSugar ? 2 : 0;
        int milkRequired = strong ? 30 : 40;
        int teaLeavesRequired = strong ? 4 : 3;
        int waterRequired = 150;

        if (canDispense(cups, sugar, sugarRequired, milkRequired, teaLeavesRequired, waterRequired)) {
        	double sugarofTea=sugar*cups;
            int sugarUsed = sugarRequired * cups;
            int milkUsed = milkRequired * cups;
            int teaLeavesUsed = teaLeavesRequired * cups;
            int waterUsed = waterRequired * cups;

            teaLeavesStock -= teaLeavesUsed;
            coffeePowderStock -= 0; // No coffee powder for tea
            sugarStock -= (sugarUsed + sugarofTea);
            milkStock -= milkUsed;
            waterStock -= waterUsed;

            totalCostAmount += (sugarUsed * 1) +(sugarofTea*1)+ (milkUsed * 0.15) + (waterUsed * 0.02) + (teaLeavesUsed * 1);
            totalSaleAmount += 15.5 * cups;

            teaCupsSold += cups;

            System.out.println("Tea dispensed: " + cups + " cup(s)");
        } else {
            System.out.println("Insufficient stock to dispense tea.");
        }
    }

    private boolean canDispense(int cups, double sugar,int sugarRequired, int milkRequired, int coffeePowderRequired, int waterRequired) {
        return sugarStock >= (sugar* cups) &&
        		sugarStock >= (sugarRequired * cups) &&
                milkStock >= (milkRequired * cups) &&
                coffeePowderStock >= (coffeePowderRequired * cups) &&
                waterStock >= (waterRequired * cups);
    }

    private void refillContainers(Scanner scanner) {
        System.out.println("Select an ingredient to refill:");
        System.out.println("1. Water");
        System.out.println("2. Milk");
        System.out.println("3. Tea Leaves");
        System.out.println("4. Coffee Powder");
        System.out.println("5. Sugar");
        System.out.print("Enter Ingredient: ");
        int ingredient = scanner.nextInt();

        System.out.print("Enter number of units: ");
        int units = scanner.nextInt();

        switch (ingredient) {
            case 1:
                waterStock += units;
                break;
            case 2:
                milkStock += units;
                break;
            case 3:
                teaLeavesStock += units;
                break;
            case 4:
                coffeePowderStock += units;
                break;
            case 5:
                sugarStock += units;
                break;
            default:
                System.out.println("Invalid ingredient selection.");
        }
    }

    private void showReport() {
        System.out.println("Remaining Qty of each ingredient:");
        System.out.println("Tea Leaves: " + teaLeavesStock);
        System.out.println("Coffee Powder: " + coffeePowderStock);
        System.out.println("Sugar: " + sugarStock);
        System.out.println("Milk: " + milkStock);
        System.out.println("Water: " + waterStock);

        int usedWater=(15000 - waterStock);
                       waterLeakage =usedWater/500;
                       waterLeakage *=25;
                      usedWater +=waterLeakage;
        int usedMilk=(8000 - milkStock);
               milkLeakage =usedMilk/200;
               milkLeakage *=10;
                   usedMilk +=milkLeakage;
        
        System.out.println("Used Qty of each ingredient:");
        System.out.println("Tea Leaves: " + (500 - teaLeavesStock));
        System.out.println("Coffee Powder: " + (500 - coffeePowderStock));
        System.out.println("Sugar: " + (600 - sugarStock));
        System.out.println("Milk: " + usedMilk);
        System.out.println("Water: " + usedWater);

        System.out.println("Loss due to leakage of water: " + waterLeakage);
        System.out.println("Loss due to leakage of milk: " + milkLeakage);

        System.out.println("No of cups sold of each beverage:");
        System.out.println("Tea: " + teaCupsSold);
        System.out.println("Coffee: " + coffeeCupsSold);
          
        totalCostAmount +=0.15*milkLeakage+0.02*waterLeakage;
        
        System.out.println("Total Cost amount: " + totalCostAmount);
        System.out.println("Total Sale amount: " + totalSaleAmount);
      //  profitLoss = totalSaleAmount - totalCostAmount;    
      //  System.out.println("Profit Loss: " + profitLoss);
        
        if(totalSaleAmount>totalCostAmount) {
        	profit = totalSaleAmount - totalCostAmount;
        	System.out.println("profit : "+profit);
        }else {
        	loss = totalCostAmount-totalSaleAmount;
        	System.out.println("loss : "+loss);
        }
    }

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.start();
    }
}

