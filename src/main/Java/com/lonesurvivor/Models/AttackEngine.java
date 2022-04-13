package com.lonesurvivor.Models;

import java.util.Objects;

public class AttackEngine {

    //SANDBOX CONFLICT RESOLUTION ENGINE - METHOD IN PLAYER CLASS
    // TICKET #249
    public void conflictResolutionEngine(String command){
        int playerAttackStat = (int) ( Math.random() * 40);
        int enemyAttackStat = (int) ( Math.random() * 50);

        while(Objects.equals(command, "attack")){//while "attack is true ++
            playerAttackStat++;
            enemyAttackStat++;
            if(playerAttackStat > enemyAttackStat){//if player stat is greater player wins
                //placeholder logic
                System.out.println(" You Win Lone Survivor your attack score is " + playerAttackStat);
                break;
            }else {//else enemy stat is greater , enemy wins
                //placeholder logic
                System.out.println(" The enemy has defeated you with a level " + enemyAttackStat + " attack score");
                break;
            }
        }


    }

    public static void main(String[] args) {
        AttackEngine attackEngine = new AttackEngine();
        attackEngine.conflictResolutionEngine("attack");
    }
}
