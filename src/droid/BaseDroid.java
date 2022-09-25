package droid;

import java.util.Random;

public class BaseDroid {
    private final String name;
    private final int damage;
    private int health;
    private final int boostDamage;
    private final int id;

    public BaseDroid(String name, int damage, int health, int boostDamage, int id) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.boostDamage = boostDamage;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public int getBoostDamage() {
        return boostDamage;
    }
    public boolean isAlive() {
        return health > 0;
    }
    public boolean isAliveTeam(BaseDroid defender, BaseDroid defenderTwo){return (defender.health+defenderTwo.health) > 0;}
    public int getHit(int damage) {
        Random random = new Random();

        int actualDamage = random.nextInt(damage);
        if(actualDamage == 0){
            System.out.println("Защита спроцювала на 100%! ");
        }

        this.health -= actualDamage;

        if (health < 0) {
            health = 0;
        }

        return actualDamage;
    }
    public int getTeamHit(int damage) {
        Random random = new Random();

        int actualDamage = random.nextInt(damage);
        if(actualDamage == 0){
            System.out.println("Защита спроцювала на 100%! ");
        }

        this.health -= actualDamage;

        if (health < 0) {
            health = 0;
        }

        return actualDamage;
    }
    public int getBoostHit(int boostDamage){
        Random random = new Random();

        int actualDamage = random.nextInt(this.boostDamage)+1;
        this.health -= actualDamage;
        if (health <0) {
            health = 0;
        }
        return actualDamage;
    }
    //@Override
    /*public String toString() {
        return name + " health = " + health;
    }*/

    @Override
    public String toString() {
        return "BaseDroid{" +
                "name='" + name + '\'' +
                ", damage=" + damage +
                ", health=" + health +
                ", boostDamage=" + boostDamage +
                ", id=" + id +
                '}';
    }
}
