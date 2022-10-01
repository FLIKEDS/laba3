import battle.BattleTwoOnTwo;
import battle.TeamBattle;
import droid.BaseDroid;

import java.util.Scanner;

public class DroidBattle {
    public void battle() throws InterruptedException {
        BaseDroid droidFighter = new BaseDroid("Fighter", 6, 30, 9, 1);
        BaseDroid droidTank = new BaseDroid("Tank", 2, 50, 12, 2);
        BaseDroid droidDamager = new BaseDroid("Damager", 9, 25, 9, 3);
        BaseDroid droidSupport = new BaseDroid("Support", 3, 40, 4, 4);
        System.out.println("Виберіть тип бою, та свого робота по айді!");
        System.out.println(droidDamager.toString() + "\n" + droidSupport.toString() + "\n" + droidFighter.toString() + "\n" + droidTank.toString());
        changes(droidFighter, droidTank, droidDamager, droidSupport);
    }
    public void changes(BaseDroid droidFighter, BaseDroid droidTank, BaseDroid droidDamager, BaseDroid droidSupport) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.println("Вибери");
        int a = in.nextInt();
        if (a == 1){
            BattleTwoOnTwo arena = SoloFight(droidFighter, droidTank, droidDamager, droidSupport);
            BaseDroid winner = arena.startFight();

            System.out.println("--------------");
            System.out.println("The winner is " + winner.getName());
        } else {
            TeamBattle arena = teamFight(droidFighter, droidTank, droidDamager, droidSupport);
            BaseDroid winner = arena.startTeamFight();

            System.out.println("--------------");
            System.out.println("The winner is " + winner.getName());

        }

    }
    public BattleTwoOnTwo SoloFight(BaseDroid droidFighter, BaseDroid droidTank, BaseDroid droidDamager, BaseDroid droidSupport){
        Scanner in = new Scanner(System.in);
        System.out.println("Виберіть робота");
        int a = in.nextInt();
        int b = in.nextInt();
        if (droidFighter.getId() == a){
            BattleTwoOnTwo arena;
                if (droidTank.getId() == b){
                    arena = new BattleTwoOnTwo(droidFighter, droidTank);
                    return arena;
                } else if (droidDamager.getId() == b){
                    arena = new BattleTwoOnTwo(droidFighter, droidDamager);
                    return arena;
                } else {
                    arena = new BattleTwoOnTwo(droidFighter, droidSupport);
                    return arena;
                }
        } else if (a == droidTank.getId()){
            BattleTwoOnTwo arena;
            if (droidDamager.getId() == b){
                arena = new BattleTwoOnTwo(droidTank, droidDamager);
            } else {
                arena = new BattleTwoOnTwo(droidTank, droidSupport);
            }
            return arena;
        } else /*if (droidDamager.getId() == a)*/{
                BattleTwoOnTwo arena = new BattleTwoOnTwo(droidDamager, droidSupport);
                return arena;

        }
    }
    public TeamBattle teamFight(BaseDroid droidFighter, BaseDroid droidTank, BaseDroid droidDamager, BaseDroid droidSupport){
        Scanner in = new Scanner(System.in);
        TeamBattle arena;
        System.out.println("Виберіть робота");
        int a = in.nextInt();
        int b = in.nextInt();
        if ((droidFighter.getId() == a & droidTank.getId() == b) | (droidFighter.getId() == b & droidTank.getId() == a)){
            arena = new TeamBattle(droidFighter, droidTank, droidDamager, droidSupport);
            return arena;
        } else if ((droidFighter.getId() == a & droidDamager.getId() == b) | (droidFighter.getId() == b & droidDamager.getId() == a)){
            arena = new TeamBattle(droidFighter, droidDamager, droidTank, droidSupport);
            return arena;
        } else if ((droidFighter.getId() == a & droidSupport.getId() == b) | (droidFighter.getId() == b & droidSupport.getId() == a)){
            arena = new TeamBattle(droidFighter, droidSupport, droidTank, droidDamager);
            return arena;
        } else if ((droidTank.getId() == a & droidDamager.getId() == b) | (droidTank.getId() == b & droidDamager.getId() == a)){
            arena = new TeamBattle(droidTank, droidDamager, droidFighter, droidSupport);
            return arena;
        } else if ((droidTank.getId() == a & droidSupport.getId() == b) | (droidTank.getId() == b & droidSupport.getId() == a)){
            arena = new TeamBattle(droidTank, droidSupport, droidFighter, droidDamager);
            return arena;
        } else{
            arena = new TeamBattle(droidDamager, droidSupport, droidFighter, droidTank);
            return arena;
        }
    }
}