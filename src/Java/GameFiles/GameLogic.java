package Java.GameFiles;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

/**
 * Takes input, gives player their current state, and decides logic of the game (the brain)
 */
public class GameLogic extends JPanel {

    private TextParser parser;
    private CommandProcessor processor;
    private JSONParser jsonParser;
    private FileReader reader;
    private BufferedReader in;
    private Location location;
    private String input;
    private List<String> command;
    private Player player;


    public GameLogic() throws IOException, ParseException {
        //parser = new TextParser();
        //processor = new CommandProcessor();
        player = new Player();
        //in = new BufferedReader(new InputStreamReader(System.in));
    }

    public Component playerInterface() throws IOException, ParseException {
        System.out.println("You are currently located in the ");
        System.out.println("Enter a command (or 'help commands' to see a list of commands): ");
        input = in.readLine();
        parser.InitialInput(input);
        if (parser.getValidCommand().size() == 2) {
            command = parser.getValidCommand();
            processor.processCommand(command);
        }
        /*else{
            System.out.println("Valid command must contain only two words. Type 'help game' for a list of valid commands.");
        }*/
        //command = parser.getValidCommand();
        //processor.processCommand(command);

        return null;
    }

    public void playerActions() {

    }
}
