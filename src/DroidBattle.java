import battle.BattleTwoOnTwo;
import battle.TeamBattle;
import droid.BaseDroid;

import java.util.Scanner;

public class DroidBattle {
    public void battle() throws InterruptedException {
        BaseDroid droidFighter = new BaseDroid("Fighter", 6, 30, 9, 0);
        BaseDroid droidTank = new BaseDroid("Tank", 2, 50, 12, 1);
        BaseDroid droidDamager = new BaseDroid("Damager", 9, 25, 9, 2);
        BaseDroid droidSupport = new BaseDroid("Support", 3, 40, 4, 3);
        System.out.println("Виберіть тип бою, та свого робота по айді!");
        System.out.println(droidDamager.toString() + "\n" + droidSupport.toString() + "\n" + droidFighter.toString() + "\n" + droidTank.toString());
        BaseDroid[] droids = new BaseDroid[4];
        droids[0] = droidFighter;
        droids[1] = droidTank;
        droids[2] = droidDamager;
        droids[3] = droidSupport;
        changes(droids);
    }
    public void changes(BaseDroid[] droids) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.println("Вибери кількість роботів (2 - 1/1 || 4 - 2/2)");
        int a = in.nextInt();
        if (a == 2) {
            BattleTwoOnTwo arena = soloFight(droids, a);
            winner(arena.startFight());
        } else if (a == 4){
            TeamBattle arena = teamFight(droids, a);
            winner(arena.startTeamFight());
        }
    }
    public void winner(BaseDroid winner) {
        System.out.println("--------------");
        System.out.println("The winner is " + winner.getName());
    }
    public BattleTwoOnTwo soloFight(BaseDroid[] droids, int n) {
        BattleTwoOnTwo arena;
        BaseDroid[] droids1 = new BaseDroid[n];
        sort(droids1, droids, n);
        arena = new BattleTwoOnTwo(droids1[0], droids1[1]);
        return arena;
    }
    public TeamBattle teamFight(BaseDroid[] droids, int n) {
        TeamBattle arena;
        BaseDroid[] droids1 = new BaseDroid[n];
        sort(droids1, droids, n);
        arena = new TeamBattle(droids1[0], droids1[1], droids1[2], droids1[3]);
        return arena;
    }
    public void sort (BaseDroid[] droids1, BaseDroid[] droids, int n){
        int[] num = new int[n];
        takeNum(num);
        for (int j = 0; j < num.length; j++){
            for (int i = 0; i < 4; i++) {
                if (num[j] == droids[i].getId()){
                    droids1[j] = droids[i];
                }
            }
        }
    }
    public void takeNum(int[] num){
        Scanner in = new Scanner(System.in);
        if (num.length > 2) {
            System.out.println("Введіть ID роботів для вашої команди:");
            for (int i = 0; i < 2; i++) {
                num[i] = in.nextInt();
            }
            System.out.println("Введіть ID роботів для ворожої команди: ");
            for (int i = 2; i < 4; i++) {
                num[i] = in.nextInt();
            }
        } else {
            System.out.println("Введіть ID робота для вашої і для ворожої команди: ");
            for (int i = 0; i < num.length; i++) {
                num[i] = in.nextInt();
            }
       }
    }
}