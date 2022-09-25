package battle;

import droid.BaseDroid;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BattleTwoOnTwo {
    private final BaseDroid firstDroid;

    private final BaseDroid secondDroid;

    private BaseDroid attacker;

    private BaseDroid defender;

    private int currentRound = 0;
    private int attackScoreOne = 0;
    private int attackScoreTwo = 0;

    public BattleTwoOnTwo(BaseDroid firstDroid, BaseDroid secondDroid) {
        this.firstDroid = firstDroid;
        this.secondDroid = secondDroid;
    }
    public BaseDroid startFight() throws InterruptedException {
        do{
            Random random = new Random();
            if (random.nextBoolean()){
                attacker = firstDroid;
                defender = secondDroid;
                //if(attacker.getName() == firstDroid.getName())
                attackScoreOne++;
            }else {
                attacker = secondDroid;
                defender = firstDroid;

                attackScoreTwo++;
            }
            int realDamage = 0;
            if(attackScoreOne == 3){
                realDamage = boostAttacks();
                attackScoreOne = 0;
            }else if(attackScoreTwo == 3){
                realDamage = boostAttacks();
                attackScoreTwo = 0;
            } else {
                realDamage = Attacks();
            }
            roundInfo(realDamage);
            TimeUnit.SECONDS.sleep(1);
        }while (defender.isAlive());
        return attacker;
    }
    public int Attacks(){
        return defender.getHit(attacker.getDamage());
    }
    public int boostAttacks(){
        return defender.getBoostHit(attacker.getBoostDamage());
    }
    public void roundInfo(int realDamage){
        System.out.println("//----------------------------------------------//");
        System.out.println(attacker.getName() + " attacks on " + realDamage);
        System.out.println(firstDroid.getName() + " first droid " + firstDroid.getHealth());
        System.out.println(secondDroid.getName() + " second droid " + secondDroid.getHealth());
    }

}
