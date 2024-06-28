/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : D
 * Group    : 14
 * Members  :
 * 1. 5026231 - Jonathan Berlianto
 * 2. 5026231229 - Lailatul Fitaliqoh
 * 3. 5026231232 - Bara Ardiwinata
 * ------------------------------------------------------
 */
import javax.sound.sampled.*;
import java.io.File;

public class Main {
    // Metode untuk memutar suara dadu
    public static void playDiceSound() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    Main.class.getResourceAsStream("rolling_diceSE.wav"));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println("Error playing dice sound: " + e.getMessage());
        }
    }

    // Metode untuk memutar suara ular
    public static void playSnakeSound() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    Main.class.getResourceAsStream("snakeSE.wav"));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println("Error playing snake sound: " + e.getMessage());
        }
    }

    // Metode untuk memutar suara tangga
    public static void playLadderSound() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    Main.class.getResourceAsStream("ladderSE.wav"));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println("Error playing ladder sound: " + e.getMessage());
        }
    }

    // Metode untuk memutar musik latar belakang
    public static void playBackgroundMusic() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    Main.class.getResourceAsStream("musik_game.wav"));
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Memainkan secara terus menerus
        } catch (Exception e) {
            System.err.println("Error playing background music: " + e.getMessage());
        }
    }

    // Metode untuk memutar suara permainan selesai
    public static void playGameEndSound() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    Main.class.getResourceAsStream("winSE.wav"));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println("Error playing game end sound: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SnL g1 = new SnL(100);
        playBackgroundMusic(); // Memulai musik latar belakang saat permainan dimulai
        g1.play();
    }
}