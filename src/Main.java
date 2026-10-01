public class Main {
    void main() {
        Players player1 = new Players("James", 'W');
        Players player2 = new Players("Bond", 'M');


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
