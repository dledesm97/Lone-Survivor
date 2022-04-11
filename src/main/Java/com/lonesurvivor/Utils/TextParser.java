package com.lonesurvivor.Utils;

import com.lonesurvivor.Views.LoneSurvivorBase;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

/**
 * checks if command has valid verb and noun
 */
public class TextParser {

    //parser should lowercase all words, remove white spaces and articles, separate the verbs and nouns
    //Ingest Text, Parse it, Identify Keywords, Process Command
    private JSONParserClass jsonParserClass;

    private List<JSONArray> commands;
    private List<String> validCommand;
    private JSONArray verbList;
    private JSONArray nounList;
    private JSONArray commList;

    /*NOTE: CTOR INITIALIZES JSONParserClass && list of valid commands to track
        -but why?
     */
    public TextParser() throws IOException, ParseException {
        jsonParserClass = new JSONParserClass();
        validCommand = new ArrayList<>();
    }

    public List<String> getValidCommand() {
        return validCommand;
    }

    public void InitialInput(String text) {
        List<String> command;
        String newStr = text.trim().toLowerCase();

        if (newStr.equals("")) {
            LoneSurvivorBase.GUI.setMultipleText("Please enter a command.");
            //LoneSurvivorBase.GUI.commandPromptsTextField
        } else {
            command = TokenizeCommand(newStr);
            command.forEach(System.out::println);
            ParseCommand(command);
        }
    }

    public List<String> TokenizeCommand(String text) {
        String delims = " \t,.:;?!\"'";
        List<String> tokenList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(text, delims);

        String x;
        while(tokenizer.hasMoreTokens()) {
            x = tokenizer.nextToken();
            tokenList.add(x);
        }

        return tokenList;
    }

    public void ParseCommand(List<String> command) {
        commands = jsonParserClass.commandParser();
        verbList = commands.get(0);
        nounList = commands.get(1);
        commList = commands.get(2);

        String verb;
        String noun;
        String comm;

        //clears list so there is only ever two elements in it at a time
        validCommand.clear();
        if (command.size() != 2) {
            LoneSurvivorBase.GUI.setMultipleText("Valid command must contain only two words. Type 'help commands' for a list of valid commands.");
        }
        else {
            verb = command.get(0);
            noun = command.get(1);
            if (verbList.contains(verb) && nounList.contains(noun)) {
                comm = verb + " " + noun;
                if (commList.contains(comm)) {
                    //validCommand.clear();
                    validCommand.add(verb);
                    validCommand.add(noun);
                }
                else {
                    LoneSurvivorBase.GUI.setMultipleText(comm + " is not a valid action");
                }
            }
            else{
                LoneSurvivorBase.GUI.setMultipleText(verb + " " + noun + " is not a valid action");
            }
        }
    }

}
