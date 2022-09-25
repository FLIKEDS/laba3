import battle.BattleTwoOnTwo;
import droid.BaseDroid;

import java.util.Scanner;

public class DroidBattle {
    public void battle() throws InterruptedException {
        BaseDroid droidFighter = new BaseDroid("Fighter", 6, 30, 9, 1);
        BaseDroid droidTank = new BaseDroid("Tank", 2, 50, 12, 2);
        BaseDroid droidDamager = new BaseDroid("BoostDamager", 9, 25, 9, 3);
        BaseDroid droidSupport = new BaseDroid("Support", 3, 40, 4, 4);
        System.out.println("Виберіть тип бою, та свого робота по айді!");
        System.out.println(droidDamager.toString() + "\n" + droidSupport.toString() + "\n" + droidFighter.toString() + "\n" + droidTank.toString());


        BattleTwoOnTwo arena = new BattleTwoOnTwo(droidFighter, droidTank);
        BaseDroid winner = arena.startFight();

        System.out.println("--------------");
        System.out.println("The winner is " + winner.getName());
    }
    public void changes() throws InterruptedException {
        //System.out.println(droidDamager.toString() + "\n" + droidSupport.toString() + "\n" + droidFighter.toString() + "\n" + droidTank.toString());
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        if (a == 1){
            System.out.println("Вибери");
        }
        for (int i = 0; i < 4; i++) {
1

        }


    }
}