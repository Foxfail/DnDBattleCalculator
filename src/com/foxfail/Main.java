package com.foxfail;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final String PLAYERS_FILE_NAME = "Players.xml";
    private static final String MONSTERS_FILE_NAME = "Monsters.xml";
    private static ArrayList<Creature> players = new ArrayList<>(),
            monsters = new ArrayList<>();

    private static int playersWin = 0, monstersWin = 0;

    private static Integer playerCount, monstersCount;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Загрузить игроков? (1-да, все остальное-нет): ");
        if (Integer.valueOf(scanner.next()) == 1) {
            System.out.println(" ");
            loadPlayersFromFile();
            System.out.println("Загружено " + players.size() + " игроков");
        } else {
            System.out.print("Количество игроков: ");
            playerCount = Integer.valueOf(scanner.next());
        }
        System.out.print("Загрузить противников? (1-да, все остальное-нет): ");
        if (Integer.valueOf(scanner.next()) == 1) {
            System.out.println(" ");
            loadMonstersFromFile();
            System.out.println("Загружено " + monsters.size() + " противников");
        } else {
            System.out.print("Количество противников: ");
            monstersCount = Integer.valueOf(scanner.next());
        }
        System.out.println(" ");


        if(players.size()==0) {
            enterPlayersManually(scanner);
            System.out.print("Сохранить игроков? (1 - да, все остальное - нет): ");
            try {if (Integer.valueOf(scanner.next()) == 1) {savePlayesToFile();}
            } catch (Exception e){System.out.print("Не сохранено");}
        }
        if(monsters.size()==0) {
            enterMonstersManually(scanner);
            System.out.print("Сохранить монстров(противников)? (1 - да, все остальное - нет): ");
            try {if (Integer.valueOf(scanner.next()) == 1) {saveMonstersToFile();}
            } catch (Exception e) {System.out.print("Не сохранено");}
        }

        System.out.print("Сколько боев программа должна провести: ");
        Integer fightCount = Integer.valueOf(scanner.next());
        System.out.println("");


        makeFightCalculation(fightCount);


        System.out.println(" ");
        System.out.println("Игроки победили " + playersWin + " раз");
        System.out.println("Монстры победили " + monstersWin + " раз");
    }

    private static void loadPlayersFromFile(){
        XMLDecoder xmlDecoder = null;
        try {
            xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(PLAYERS_FILE_NAME)));
            //noinspection unchecked
            players = (ArrayList<Creature>) xmlDecoder.readObject();
            System.out.println("Загружено");
        } catch (FileNotFoundException filenotfound){
            System.out.println("Ошибка при создании или открытии файла");
        }
    }
    private static void loadMonstersFromFile(){
        XMLDecoder xmlDecoder = null;
        try {
            xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(MONSTERS_FILE_NAME)));
            //noinspection unchecked
            monsters = (ArrayList<Creature>) xmlDecoder.readObject();
            System.out.println("Загружено");
        } catch (FileNotFoundException filenotfound){
            System.out.println("Ошибка при создании или открытии файла");
        }
    }

    private static void savePlayesToFile() {
        XMLEncoder xmlEncoder = null;
        try{
            xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(PLAYERS_FILE_NAME)));
            System.out.println("Сохранено в " + PLAYERS_FILE_NAME);
        } catch (FileNotFoundException filenotfound){
            System.out.println("Ошибка при создании или открытии файла");
        }
        xmlEncoder.writeObject(players);
        xmlEncoder.close();
    }
    private static void saveMonstersToFile() {
        XMLEncoder xmlEncoder = null;
        try{
            xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(MONSTERS_FILE_NAME)));
            System.out.println("Сохранено в " + MONSTERS_FILE_NAME);
        } catch (FileNotFoundException filenotfound){
            System.out.println("Ошибка при создании или открытии файла");
        }
//        xmlEncoder.writeObject(new Creature("Фекал",1,1,1,1,1,1,1,1,1,1));
        xmlEncoder.writeObject(monsters);
        xmlEncoder.close();
    }


    private static void enterPlayersManually(Scanner scanner){
        for(int i=0; i<playerCount;i++){
            System.out.print("Имя игрока " + (i + 1) + ": ");
            String playerName = scanner.next();

            System.out.print("Хиты игрока " + playerName + ": ");
            Integer playerHits = Integer.valueOf(scanner.next());

            System.out.print("КД игрока " + playerName + " : ");
            Integer playerKD = Integer.valueOf(scanner.next());
            System.out.print("СТОИ игрока " + playerName + " : ");
            Integer playerSTOY = Integer.valueOf(scanner.next());
            System.out.print("РЕАК игрока " + playerName + " : ");
            Integer playerREAK = Integer.valueOf(scanner.next());
            System.out.print("ВОЛЯ игрока " + playerName + " : ");
            Integer playerVOLYA = Integer.valueOf(scanner.next());

            System.out.print("Против чего будет главная атака " + playerName + " надо ввести число от 1 до 4 (1-КД, 2-СТОИ, 3-РЕАК, 4-ВОЛЯ): ");
            Integer playerATKVS = Integer.valueOf(scanner.next());
            System.out.print("Бонус броска основной атаки " + playerName + " : ");
            Integer playerATKBonus = Integer.valueOf(scanner.next());
            System.out.print("Кубик урона атаки игрока " + playerName + " надо ввести число граней кубика например 20 для 1к20 : ");
            Integer playerATKDice = Integer.valueOf(scanner.next());
            System.out.print("Бонус урона атаки игрока " + playerName + ": ");
            Integer playerATKDamageBonus = Integer.valueOf(scanner.next());

            System.out.print("Инициатива игрока(Не работает) " + playerName + ": ");
            Integer playerInit = Integer.valueOf(scanner.next());

            Creature player = new Creature(playerName, playerKD, playerSTOY, playerREAK, playerVOLYA, playerHits, playerATKVS, playerATKBonus, playerATKDice, playerATKDamageBonus, playerInit);
            players.add(player);
            System.out.println("");
        }
    }
    private static void enterMonstersManually(Scanner scanner){
        for(int i=0; i<monstersCount;i++){
            System.out.print("Имя монстра " + (i + 1) + ": ");
            String monsterName = scanner.next();

            System.out.print("Хиты монстра " + monsterName + ": ");
            Integer monsterHits = Integer.valueOf(scanner.next());

            System.out.print("КД монстра " + monsterName + " : ");
            Integer monsterKD = Integer.valueOf(scanner.next());
            System.out.print("СТОИ монстра " + monsterName + " : ");
            Integer monsterSTOY = Integer.valueOf(scanner.next());
            System.out.print("РЕАК монстра " + monsterName + " : ");
            Integer monsterREAK = Integer.valueOf(scanner.next());
            System.out.print("ВОЛЯ монстра " + monsterName + " : ");
            Integer monsterVOLYA = Integer.valueOf(scanner.next());

            System.out.print("Против чего будет главная атака " + monsterName + " надо ввести число от 1 до 4 (1-КД, 2-СТОИ, 3-РЕАК, 4-ВОЛЯ): ");
            Integer monsterATKVS = Integer.valueOf(scanner.next());
            System.out.print("Бонус броска основной атаки " + monsterName + " : ");
            Integer monsterATKBonus = Integer.valueOf(scanner.next());
            System.out.print("Кубик урона атаки монстра " + monsterName + " надо ввести число граней кубика например 20 для 1к20 : ");
            Integer monsterATKDice = Integer.valueOf(scanner.next());
            System.out.print("Бонус урона атаки монстра " + monsterName + ": ");
            Integer monsterATKDamageBonus = Integer.valueOf(scanner.next());

            System.out.print("Инициатива монстра(Не работает) " + monsterName + ": ");
            Integer monsterInit = Integer.valueOf(scanner.next());

            Creature monster = new Creature(monsterName, monsterKD, monsterSTOY, monsterREAK, monsterVOLYA, monsterHits, monsterATKVS, monsterATKBonus, monsterATKDice, monsterATKDamageBonus, monsterInit);
            monsters.add(monster);
            System.out.println("");
        }
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    private static void makeFightCalculation(Integer fightCount) {
        System.out.print("Считаю"); // бой
        for (int i = 0; i < fightCount; i++){

            for(Creature creature : players){
                creature.currentHits = creature.maxHits;
            }
            for(Creature creature : monsters){
                creature.currentHits = creature.maxHits;
            }
            boolean oneSideAlive = true;
            while(oneSideAlive) {
                for (int p = 0; p < players.size(); p++) {
                    Random random = new Random();
                    int rMonsterIndex = random.nextInt(monsters.size());
                    if (!monsters.get(rMonsterIndex).isAlive()) { // если первый раз выбрали мертвого монстра то может второй раз повезет
                        rMonsterIndex = random.nextInt(monsters.size());
                    }
                    players.get(p).figth(monsters.get(rMonsterIndex));
                }

                for (int m = 0; m < monsters.size(); m++) {
                    Random random = new Random();
                    int rPlayerIndex = random.nextInt(players.size());
                    if (!players.get(rPlayerIndex).isAlive()) { // если первый раз выбрали мертвого игрока то может второй раз повезет
                        rPlayerIndex = random.nextInt(players.size());
                    }
                    monsters.get(m).figth(players.get(rPlayerIndex));
                }

                int alivePlayers = 0;
                int aliveMonsters = 0;
                for(Creature creature : players){
                    if(creature.isAlive()){
                        alivePlayers++;
                    }
                }
                for(Creature creature : monsters){
                    if(creature.isAlive()){
                        aliveMonsters++;
                    }
                }
                if(alivePlayers == 0){
                    oneSideAlive = false;
                    monstersWin++;
                } else if(aliveMonsters == 0){
                    oneSideAlive = false;
                    playersWin++;
                }

            }
            System.out.print(".");
        }
    }
}
