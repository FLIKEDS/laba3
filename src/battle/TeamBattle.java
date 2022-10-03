package battle;

import droid.BaseDroid;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TeamBattle {
    private final BaseDroid[] teamOne = new BaseDroid[2];
    private final BaseDroid[] teamTwo = new BaseDroid[2];
    private BaseDroid[] attacker = new BaseDroid[2];
    private BaseDroid[] defender = new BaseDroid[2];
    private int attackScoreOne = 0;
    private int attackScoreTwo = 0;
    public TeamBattle(BaseDroid firstDroid, BaseDroid secondDroid, BaseDroid thirdDroid, BaseDroid fourthDroid) {
        this.teamOne[0] = firstDroid;
        this.teamOne[1] = secondDroid;
        this.teamTwo[0] = thirdDroid;
        this.teamTwo[1] = fourthDroid;
    }
    public BaseDroid startTeamFight () throws InterruptedException{
        do{
            initFightOne();
            int realDamage = Attacks();
            if (attackScoreOne == 2){
                attackScoreOne = 0;
            } else if (attackScoreTwo == 2){
                attackScoreTwo = 0;
            }
            roundInfo(realDamage);

            TimeUnit.SECONDS.sleep(1);
        }while (defender[0].isAlive() & defender[1].isAlive());

        return defender[0];
    }
    public void roundInfo(int realDamage){
        System.out.println("//----------------------------------------------//");
        if (attacker[0].isAlive() & attacker[1].isAlive()) {
            System.out.println(attacker[0].getName() + " and " + attacker[1].getName() + " attacks on " + realDamage);
            printer();
        } else if (attacker[0].isAlive()){
            System.out.println(attacker[0].getName() + " attacks on " + realDamage);
            printer();
        } else {
            System.out.println(attacker[1].getName() + " attacks on " + realDamage);
            printer();
        }
    }
    public void printer(){
        System.out.println(teamOne[0].toString() + teamOne[1].toString() + teamTwo[0] + teamTwo[1]);
    }
    @Override
    public String toString() {
        return "TeamBattle{" +
                "teamOne=" + Arrays.toString(teamOne) +
                ", teamTwo=" + Arrays.toString(teamTwo) +
                '}';
    }
    public void initFightOne(){
        Random random = new Random();
        if(random.nextBoolean()){
                attacker[0] = teamOne[0];
                attacker[1] = teamOne[1];

                defender[0] = teamTwo[0];
                defender[1] = teamTwo[1];
                attackScoreOne++;
        } else {
            attacker[0] = teamTwo[0];
            attacker[1] = teamTwo[1];

            defender[0]= teamOne[0];
            defender[1] = teamOne[1];
            attackScoreTwo++;
        }
    }
    public int Attacks(){
        int realDamage = 0;
        if (attackScoreOne == 2){
            return booms(realDamage, attackScoreOne);
        } else if (attackScoreTwo == 2){
           return booms(realDamage, attackScoreTwo);
        } else {
            return boomsOne(realDamage);
        }
    }
    private int boomsOne(int realDamage){
        if(attacker[0].isAlive() & attacker[1].isAlive()){
            if(defender[0].isAlive()){
                realDamage += defender[0].getHit(attacker[0].getDamage());
                realDamage += defender[0].getHit(attacker[0].getDamage());
            } else /*if (defender[1].isAlive())*/{
                realDamage += defender[1].getHit(attacker[0].getDamage());
                realDamage += defender[1].getHit(attacker[0].getDamage());
            }
            return realDamage;
        } else if (attacker[0].isAlive()){
            if(defender[1].isAlive()) {
                realDamage += defender[0].getHit(attacker[0].getDamage());
            } else {
                realDamage += defender[1].getHit(attacker[0].getDamage());
            }
            return realDamage;
        } else {
            if(defender[1].isAlive()) {
                realDamage += defender[0].getHit(attacker[1].getDamage());
            } else {
                realDamage += defender[1].getHit(attacker[1].getDamage());
            }
            return realDamage;
        }
    }
    private int booms(int realDamage, int attackScore) {
        int Damage = 0;
        if (attacker[0].isAlive() & attacker[1].isAlive() & attackScore == 2){
            Damage = takeTeamBoostDamage(realDamage);
        } else if (attacker[0].isAlive() & attackScore == 2){
            Damage = takeBoostDamage(realDamage, attacker[0]);
        } else if(attacker[1].isAlive() & attackScore == 2)
            Damage = takeBoostDamage(realDamage, attacker[1]);

        return Damage;
    }
    private int takeBoostDamage(int realDamage, BaseDroid attacker){
        if(defender[0].isAlive()){
            realDamage = defender[0].getBoostHit(attacker.getBoostDamage());
        } else if (defender[1].isAlive()) {
            realDamage = defender[0].getBoostHit(attacker.getBoostDamage());
        }
        return realDamage;
    }
    private int takeTeamBoostDamage(int realDamage) {
        BaseDroid defenderD =  defender[0].isAlive()?  defender[0] :  defender[1];
        realDamage += defenderD.getBoostHit(attacker[0].getBoostDamage());
        realDamage += defenderD.getBoostHit(attacker[1].getBoostDamage());
        return realDamage;
    }
}
