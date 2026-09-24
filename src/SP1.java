import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class SP1 {
    //HeroBuilder\\

    Players[] player;

    //Declaring and initializing
    //Class Health
    int warriorHP = 120;
    int mageHP = 90;
    int rogueHP = 100;
    int healerHP = 110;

    //Class BaseDamage
    int warriorDMG = 5;
    int mageDMG = 9;
    int rogueDMG = 7;
    int healerDMG = 4;




    class Players{

        String name;
        char roleClass;

        //Automatically assigned depending on roleClass
        String roleName = "";
        int healthPoints;
        int maxHealth;
        int baseDamage;

        //Default
        int level = 0;
        int expPoints = 0;
        double gold = 0.0;

        double hpPercentage;
        boolean canLvlUp;
        boolean isAlive = true;
        boolean healthCritical;


        Item[] inventory = new Item[2];


        //The Constructor
        Players(String name, char roleClass){
            this.name = name;
            this.roleClass = roleClass;
        }


        void printCharacterSheet(){
            //Stats//

            String aliveStatus;
            if(isAlive){
                aliveStatus = "Alive";
            }
            else{
                aliveStatus = "Dead";
            }

            System.out.println("\n==== "+name+" ("+roleName+") ("+aliveStatus+") ====");
            System.out.println("Experience: "+expPoints+" | Level: "+level+" | Health: "+healthPoints+"/"+maxHealth+" | Gold: "+gold);


            //Status//
            System.out.println("====       -|STATUS|-       ====");
            //Level up
            if (canLvlUp){
                System.out.println("|Ready to lvl up!|");
            }

            //Health
            if (healthCritical && (healthPoints > 0)){
                System.out.println("|WARNING: Health critical! ("+healthPoints+")|");
            }
            System.out.println(" ");


        }

        void attack(Players target){

            if (isAlive && target.isAlive){

                System.out.println(name+" attacks "+target.name+" for "+baseDamage+" damage!");
                target.healthPoints -= baseDamage;


                if (target.healthPoints <= 0){
                    System.out.println(target.name+" has died.");
                    target.healthPoints = 0;
                    target.isAlive = false;
                }

            }

        }


        void heal(int amount){

            if(isAlive){
                int currentHP = healthPoints;

                System.out.println(name+" heals "+amount+" HP!");

                if (amount + healthPoints > maxHealth){
                    healthPoints = maxHealth;
                }
                else{
                    healthPoints += amount;
                }

                System.out.println("Health: "+currentHP+" -> "+healthPoints);

            }


        }


        void addGold(double amount){
            double currentGold = gold;
            gold += amount;
            System.out.println(name+" gained "+amount+" gold! Gold: "+currentGold+" -> "+gold);
        }


        void removeGold(double amount){

            if (gold - amount >= 0){
                double currentGold = gold;
                gold -= amount;
                System.out.println(name+" lost "+amount+" gold! Gold: "+currentGold+" -> "+gold);

            }

        }


        void addXP(int amount){

            expPoints += amount;

            System.out.println(name+" gains "+amount+" XP! Total: "+expPoints);
            if (expPoints >= 250 * level * 1.1){
                canLvlUp = true;
            }


        }


        void levelUp(){

            if (canLvlUp){
                level++;
                expPoints = 0;

                switch (roleClass){
                    case 'W':
                        maxHealth += 10;
                        break;
                    case 'M':
                        maxHealth += 4;
                        break;
                    case 'R':
                        maxHealth += 5;
                    case 'H':
                        maxHealth += 6;
                    default:
                        System.out.println("Couldn't find class to increase health");

                }
            }

        }



        void isHealthCritical(){

            if (healthPoints < (maxHealth/4)){
                healthCritical = true;
            }

        }


        void getHealthPercentage(){
            hpPercentage = healthPoints * (100.0/maxHealth);
        }

        void addItem(String name, int weight, int value, int slot){
            inventory[slot-1] = new Item(name, weight, value);
        }

        void removeItem(int slot){
            inventory[slot-1] = null;
        }


        void printInventory(){
            int inventoryCount = 0;

            for (Item invCount : inventory){
                if(invCount != null){
                    inventoryCount++;
                }
            }

            System.out.println("\n"+name+ "'s Inventory (" + inventoryCount + " items):");

            for (Item charInv : inventory) {

                if(charInv != null){
                    System.out.println("'"+charInv.name+ "' Value: " +charInv.value+ " Weight: " +charInv.weight );
                }


            }


        }



        void autoAssign(){
            //Class auto assign
            switch (roleClass){
                case 'W':
                    roleName = "Warrior";
                    maxHealth = warriorHP;
                    healthPoints = warriorHP;
                    baseDamage = warriorDMG;
                    break;
                case 'M':
                    roleName = "Mage";
                    maxHealth = mageHP;
                    healthPoints = mageHP;
                    baseDamage = mageDMG;
                    break;
                case 'R':
                    roleName = "Rogue";
                    maxHealth = rogueHP;
                    healthPoints = rogueHP;
                    baseDamage = rogueDMG;
                    break;
                case 'H':
                    roleName = "Healer";
                    maxHealth = healerHP;
                    healthPoints = healerHP;
                    baseDamage = healerDMG;
                    break;
                default:
                    System.out.println("Error assigning respective Class: Invalid roleClass");
            }

        }



    }



    class Item{
        String name;
        int weight;
        int value;

        //Constructor
        Item(String name, int weight, int value){
            this.name = name;
            this.weight = weight;
            this.value = value;
        }


    }


    class Weapon{
        String name;
        int damage;
        int durability;

        //Constructor
        Weapon(String name, int damage, int durability){
            this.name = name;
            this.damage = damage;
            this.durability = durability;
        }
    }


    class Armor{
        String name;
        int defense;
        int durability;

        //Constructor
        Armor(String name, int defense, int durability){
            this.name = name;
            this.defense = defense;
            this.durability = durability;
        }
    }



    void main() {
        Players player1 = new Players("James", 'W');
        Players player2 = new Players("Bond", 'M');

        //Assign values to the objects depending on their class, hp etc
        player1.autoAssign();
        player2.autoAssign();

        //Items + inventory
        player1.addItem("Health Potion", 1, 7, 1);
        player2.addItem("Mana Potion", 1, 5,1);
        player2.removeItem(1);

        //Weapons
        Weapon axe = new Weapon("Axe", 7, 100);
        Weapon wand = new Weapon("Wand", 10, 75);

        //Armor
        Armor ironChestplate = new Armor("Iron Chestplate", 8, 100);
        Armor leatherRobe = new Armor("Leather Robe", 5, 80);




        player1.printCharacterSheet();
        player2.printCharacterSheet();


        //Combat//
        player1.attack(player2);
        player2.attack(player1);

        player1.addGold(30);
        player1.removeGold(5);

        player1.attack(player2);
        player2.attack(player1);

        player1.addXP(5000);


        player1.printCharacterSheet();
        player2.printCharacterSheet();

        player1.printInventory();
        player2.printInventory();

    }



}
