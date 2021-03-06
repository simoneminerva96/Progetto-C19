package logic.thread;

import logic.manager.field.FieldManager;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Il thread gestisce il movimento e lo sparo(randomico) degli invaders.
 * Inoltre, puó determinare la generazione di un invaders "bonus" per punti extra
 */
public class ThreadInvader implements Runnable {
    private FieldManager fieldManager;
    private AtomicBoolean running;
    private int sleepInterval;

    public ThreadInvader(int sleepInterval, FieldManager fieldManager) {
        this.sleepInterval = sleepInterval;
        this.fieldManager = fieldManager;
        running = new AtomicBoolean(false);
    }

    /**
     * Avvio thread.
     */
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Thread che esegue il controllo degli alieni.
     */
    public void run() {
        running.set(true);
        Random rand = new Random();
        while (running.get()) {
            try {
                Thread.sleep(sleepInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fieldManager.checkInvadersEndReached();
            fieldManager.moveInvaders();
            if(rand.nextInt(10) > 4){
                fieldManager.invaderShot();
            }
            if(rand.nextInt(100) > 95){
                fieldManager.bonusInvader();
            }
        }
    }

    /**
     * Metodo che termina l'esecuzione del thread.
     */
    public void stop() {
        running.set(false);
    }

}