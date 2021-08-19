package com.solvd.unique;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) throws IOException {
        String count = countUniqueWords();
        logger.info(count);
    }

    public static String countUniqueWords () throws IOException {
        String result;
        Map<String, Integer> wordsCounter = new HashMap<>();
        logger.info("Reading from file and counting ");
        String[] wordsArray = readTextFromFile("words_test").split(",|;|\\.|\\?|!|\\s+");
        wordsArray = Arrays.stream(wordsArray).filter(e -> !e.isBlank()).collect(Collectors.toList()).toArray(wordsArray);

        for (String word : wordsArray){
            if (wordsCounter.containsKey(word)){
                int currentCountForExistingWord = wordsCounter.get(word);
                wordsCounter.put(word,currentCountForExistingWord+1);
            }else {
                wordsCounter.put(word, 1);
            }
        }


        int maxValueFromMap = Collections.max(wordsCounter.values());

        result = wordsCounter.entrySet().stream().filter(e -> e.getValue()==maxValueFromMap).findFirst().get().getKey();

        printAllWords(wordsCounter);

        return new StringBuilder("Word <").append(result).append("> occurs ").append(maxValueFromMap).append(" times").toString();

    }

    private static void printAllWords(Map<String, Integer> wordsMap){

        for (Map.Entry wordCountPair : wordsMap.entrySet()) {
            logger.error("Word : <"+wordCountPair.getKey() + "> - " + wordCountPair.getValue() + " times ");
        }

    }

    public static String readTextFromFile (String fileName) throws IOException {

        StringBuilder fileContent = new StringBuilder();
        InputStream is = Main.class.getClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        while(reader.ready()) {
            String line = reader.readLine();
            fileContent.append(line);
        }

        return fileContent.toString();
    }



    private static String userInputString(String promptText, Scanner scanner){
        logger.info(promptText);
        return scanner.nextLine();
    }



}
