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

    private BaseDroid[] attacker = new BaseDroid[2];

    private BaseDroid attackerTwo;
    private BaseDroid[] defender = new BaseDroid[2];
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
    public boolean isAliveTeam(BaseDroid defender, BaseDroid defenderTwo){return (defender.getHealth()+defenderTwo.getHealth()) > 0;}
    public void roundInfo(int realDamage){
        System.out.println("//----------------------------------------------//");
        if (attacker[0].isAlive() & attacker[1].isAlive()) {
            System.out.println(attacker[0].getName() + " and " + attacker[1].getName() + " attacks on " + realDamage);
            System.out.println(firstDroid.getName() + " first droid " + firstDroid.getHealth());
            System.out.println(secondDroid.getName() + " second droid " + secondDroid.getHealth());
            System.out.println(thirdDroid.getName() + "third droid " + thirdDroid.getHealth());
            System.out.println(fourthDroid.getName() + "fourth droid" + fourthDroid.getHealth());
        } else if (attacker[0].isAlive()){
            System.out.println(attacker[0].getName() + " attacks on " + realDamage);
            System.out.println(firstDroid.getName() + " first droid " + firstDroid.getHealth());
            System.out.println(secondDroid.getName() + " second droid " + secondDroid.getHealth());
            System.out.println(thirdDroid.getName() + "third droid " + thirdDroid.getHealth());
            System.out.println(fourthDroid.getName() + "fourth droid" + fourthDroid.getHealth());
        } else {
            System.out.println(attacker[1].getName() + " attacks on " + realDamage);
            System.out.println(firstDroid.getName() + " first droid " + firstDroid.getHealth());
            System.out.println(secondDroid.getName() + " second droid " + secondDroid.getHealth());
            System.out.println(thirdDroid.getName() + "third droid " + thirdDroid.getHealth());
            System.out.println(fourthDroid.getName() + "fourth droid " + fourthDroid.getHealth());
        }
    }
    /*public void initFight(){
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
    }*/
    public void initFightOne(){
        Random random = new Random();
        if(random.nextBoolean()){
                attacker[0] = firstDroid;
                attacker[1] = secondDroid;

                defender[0] = thirdDroid;
                defender[1] = fourthDroid;
                attackScoreOne++;
        } else {
            attacker[0] = thirdDroid;
            attacker[1] = fourthDroid;

            defender[0]= firstDroid;
            defender[1] = secondDroid;
            attackScoreTwo++;
        }
    }
    /*public int doFightTeam(){
        int realDamage;
        if (attackScoreOne == 2 | attackScoreTwo == 2){
            realDamage = boostAttacks();
        } else {
            realDamage = Attacks();
        }
        return realDamage;
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
    }*/
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
            Damage = takeSoloBoostDamageOne(realDamage);
        } else if(attacker[1].isAlive() & attackScore == 2)
            Damage = takeSoloBoostDamageTwo(realDamage);

        return Damage;
    }

    private int takeSoloBoostDamageOne(int realDamage){
        if(defender[0].isAlive()){
            realDamage = defender[0].getBoostHit(attacker[0].getBoostDamage());
        } else if (defender[1].isAlive()) {
            realDamage = defender[0].getBoostHit(attacker[0].getBoostDamage());
        }
        return realDamage;
    }
    private int takeSoloBoostDamageTwo(int realDamage){
        if(defender[0].isAlive()){
            realDamage = defender[0].getBoostHit(attacker[1].getBoostDamage());
        } else if (defender[1].isAlive()) {
            realDamage = defender[0].getBoostHit(attacker[1].getBoostDamage());
        }
        return realDamage;
    }
    private int takeTeamBoostDamage(int realDamage) {
        if (defender[0].isAlive()){
            realDamage += defender[0].getBoostHit(attacker[0].getBoostDamage());
            realDamage += defender[0].getBoostHit(attacker[1].getBoostDamage());
        } else {
            realDamage += defender[1].getBoostHit(attacker[0].getBoostDamage());
            realDamage += defender[1].getBoostHit(attacker[1].getBoostDamage());
        }
        return realDamage;
    }

   /* public int boostAttacks(){
        return defender.getBoostHit(attackerOne.getDamage()+attackerTwo.getDamage());
    }*/
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
