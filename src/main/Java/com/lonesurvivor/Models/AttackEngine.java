package com.lonesurvivor.Models;

import com.lonesurvivor.Views.LocationFrame;

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

    public void attack(){
        //calcAttackPower();

        if ((Player.getInstance().getCurrentLocation().getNpc()) != null){
            if (Player.getInstance().getCurrentLocation().getNpc().getPower() > 0){
                int opponentDamage = (int) (Player.getInstance().getCurrentLocation().getNpc().getPower() - Player.getInstance().getAttackPoints());
                Player.getInstance().getCurrentLocation().getNpc().setPower((double) opponentDamage);
                LocationFrame.textDisplayGui("\t you attacked the " + Player.getInstance().getCurrentLocation().getNpc().getName() + "!!\n\tYou inflicted " + Player.getInstance().getAttackPoints() + " damage!");
                Player.getInstance().setActionTracker(3);
            } else{
               LocationFrame.textDisplayGui(Player.getInstance().getCurrentLocation().getNpc().getName() + " is already dead. Calm down killer.");

            }
        } else{
            LocationFrame.textDisplayGui("There is nothing to attack, goofy.");
        }
    }

    public static void main(String[] args) {
        AttackEngine attackEngine = new AttackEngine();
        attackEngine.conflictResolutionEngine("attack");
    }
}
