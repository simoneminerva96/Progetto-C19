package network.client;

import logic.manager.file.ReadXmlFile;
import logic.manager.game.commands.CommandType;
import logic.manager.game.States;
import network.data.PacketHandler;
import org.newdawn.slick.Input;

/**
 * Classe che si occupa di gestire il client locale.
 * Apre la connessione ed invia i comandi eseguiti dal giocatore al server
 */
public class LocalMultiManager {
    private Client client;
    private String message;
    private PacketHandler handler;
    private States connectionState;
    private ReadXmlFile readXmlFile;

    public LocalMultiManager() {
        handler = new PacketHandler();
        connectionState = States.INITIALIZATION;
        readXmlFile = new ReadXmlFile();
    }

    /**
     * Inizializzazione client con invio al server del messaggio nuovo client ("Hello").
     * L' indirizzo Ip é letto dal file di configurazione "configuration.xml"
     *
     */
    public void init() {
        String ip = readXmlFile.read("ip");
        int port = Integer.parseInt(readXmlFile.read("port"));
        client = new Client(ip, port);
        client.send(handler.build("Hello", client.getConnection()));
        connectionState = States.INITIALIZATION;
    }

    /**
     * Controllo stato della connessione.
     * Stato waiting -> server ha comunicato l'ID al client che è in attesa di iniziare la partita.
     * Stato countdown -> server ha comunicato inizio della partita e client fa partire il countdown.
     */
    public void checkConnection() {
        if (client.getID() != -1) {
            connectionState = States.WAITING;
        }
        if (client.isGameStarted()) {
            connectionState = States.COUNTDOWN;
        }
    }

    /**
     * Metodo che gestisce l'uscita del giocatore dalla partita.
     * Invia al server il comando exit che elimina il giocatore dalla lista dei client e chiude la connessione.
     */
    private void exit() {
        client.send(handler.build(message, client.getConnection()));
        client.close();
    }

    /**
     * Metodo che gestisce l'invio del comando eseguito dal giocatore al server.
     *
     * @param commandType comando eseguito
     */
    public void sendCommand(CommandType commandType) {
        message = client.getID() + "\n" + commandType.toString();
        client.send(handler.build(message, client.getConnection()));
        if(commandType.equals(CommandType.EXIT)){
            exit();
        }
    }

    public String[] getRcvdata() {
        return client.getRcvdata();
    }

    public States getConnectionState() {
        return connectionState;
    }

    public int getID(){
        return client.getID();
    }

}
