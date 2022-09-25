package battle;

import droid.BaseDroid;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TeamBattle {
    private final BaseDroid firstDroid;

    private final BaseDroid secondDroid;

    private final BaseDroid thirdDroid;

    private int currentRound = 0;
    private final BaseDroid fourthDroid;

    private BaseDroid attackerOne;

    private BaseDroid attackerTwo;
    private BaseDroid defenderOne;
    private BaseDroid defenderTwo;
    private int attackScoreOne = 0;
    private int attackScoreTwo = 0;

    public TeamBattle(BaseDroid firstDroid, BaseDroid secondDroid, BaseDroid thirdDroid, BaseDroid fourthDroid) {
        this.firstDroid = firstDroid;
        this.secondDroid = secondDroid;
        this.thirdDroid = thirdDroid;
        this.fourthDroid = fourthDroid;
    }

    public BaseDroid startTeamFight () throws InterruptedException{
        do{
            initFight();


            TimeUnit.SECONDS.sleep(1);
        }while (isAliveTeam(defenderOne, defenderTwo));

        return attackerOne;
    }
    public boolean isAliveTeam(BaseDroid defender, BaseDroid defenderTwo){return (defender.getHealth()+defenderTwo.getHealth()) > 0;}
    public void initFight(){
        Random random = new Random();
        if(random.nextBoolean()){
            attackerOne = firstDroid;
            attackerTwo = secondDroid;

            attackScoreOne++;
            if (random.nextBoolean()) {
                defenderOne = thirdDroid;
            }else {
                defenderTwo = fourthDroid;
            }
        } else {
            attackerOne = thirdDroid;
            attackerTwo = fourthDroid;

            attackScoreTwo++;
            if(random.nextBoolean()) {
                defenderOne = firstDroid;
            } else {
                defenderTwo = secondDroid;
            }
        }
    }
    public int doFight(){
        int realDamage;
        if (attackScoreOne == 3){
            realDamage = boostAttacks();
        } else if (attackScoreTwo == 3){
            realDamage = boostAttacks();
        } else {
            if(attackerOne.isAlive()){

            }
            realDamage = Attacks();
        }
        return realDamage;
    }
    public int Attacks(){
        return defender.getHit(attackerOne.getDamage());
    }
    public int boostAttacks(){
        return defender.getBoostHit(attackerOne.getDamage()+attackerTwo.getDamage());
    }
/*    public void initFight(){
        Random random = new Random();
        if (currentRound%2 == 0){
            if(random.nextBoolean()){
                attacker = firstDroid;
                if(random.nextBoolean()){
                  defender = thirdDroid;
                } else {
                    defender = fourthDroid;
                }
            } else {
                attacker = secondDroid;
            }
        } else {
            if(random.nextBoolean()){
                attacker = thirdDroid;
                if(random.nextBoolean()){
                    defender = secondDroid;
                } else {
                    defender = firstDroid;
                }
        } else {
                attacker = fourthDroid;
                if(random.nextBoolean()){
                    defender = secondDroid;
                } else {
                    defender = firstDroid;
                }
            }


    }
*/
}
