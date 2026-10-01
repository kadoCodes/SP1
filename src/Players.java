
public class Players {

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
        //Class auto assign
        switch (roleClass){
            case 'W':
                roleName = "Warrior";
                maxHealth = RValues.warriorHP;
                healthPoints = RValues.warriorHP;
                baseDamage = RValues.warriorDMG;
                break;
            case 'M':
                roleName = "Mage";
                maxHealth = RValues.mageHP;
                healthPoints = RValues.mageHP;
                baseDamage = RValues.mageDMG;
                break;
            case 'R':
                roleName = "Rogue";
                maxHealth = RValues.rogueHP;
                healthPoints = RValues.rogueHP;
                baseDamage = RValues.rogueDMG;
                break;
            case 'H':
                roleName = "Healer";
                maxHealth = RValues.healerHP;
                healthPoints = RValues.healerHP;
                baseDamage = RValues.healerDMG;
                break;
            default:
                System.out.println("Error assigning respective Class: Invalid roleClass");
        }



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



}

