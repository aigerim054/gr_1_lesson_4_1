import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {250, 270, 280, 300,500,350,310,290};
    public static int[] heroesDamage = {25, 20, 15,0,30,20,25,23};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic","Golem","lucky","Berserk","Thor"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        healMedic();
        GolemSave();
        printStatistics();
    }


    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ---------------");
        /*String defence;
        if (bossDefenceType == null) {
            defence = "No defence";
        } else {
            defence = bossDefenceType;
        }*/
        System.out.println("Boss health: " + bossHealth + "; damage: "
                + bossDamage + "; defence: " + (bossDefenceType == null ? "No defence" : bossDefenceType));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + "; damage: "
                    + heroesDamage[i]);
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int hit = heroesDamage[i];
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coeff = random.nextInt(6) + 2; // 2,3,4,5,6,7
                    hit = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + hit);
                }
                if (bossHealth - hit < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - hit;
                }

            }
        }
    }


    public static void healMedic (){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i]>0 && heroesHealth[i]<100) {
                if(heroesHealth[3] >0 && heroesHealth[3]<100){
                    heroesHealth[3]=heroesHealth[3]-bossDamage;
                } else { int heal = 20;
                    heroesHealth[i]=heroesHealth[i] + heal;
            }
        } else if (heroesHealth[3]<0) {heroesHealth[3]=0;
                heroesHealth[i]=heroesHealth[i]- bossDamage;
            }
        }
}
public static void GolemSave (){
    int GolemSafe= bossDamage/5;
    int players=0;
    for (int i = 0; i < heroesHealth.length ; i++) {
        if(heroesHealth[i]>0){
            if (heroesHealth[4]>0) {
                //continue;
            } else {
                players++;
                heroesHealth[i]=heroesHealth[i]-(bossDamage-GolemSafe);
                heroesHealth[4]=heroesHealth[4]-bossDamage-(GolemSafe*players);
                System.out.println("golem saved");

            }
    } else if (heroesHealth[4]<0) {
            heroesHealth[4]=0;

        }

        }
    }

}

