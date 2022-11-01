package org.example.controller;

import org.example.Service.WordService;
import org.example.dto.Word;
import org.example.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainController {
    @Autowired
    WordService wordService;

    public void start() {
        boolean condition = true;
        while (condition) {
            menu();
            int action = Util.getAction();
            switch (action) {
                case 1 -> addWord();
                case 2 -> wordList();
                case 3 -> multipleChoice();
                case 4 -> spelling();
                case 5 -> searching();
                case 6 -> deleteById();
                case 0 -> condition = false;
            }

        }

    }

    private void deleteById() {
        System.out.println("Enter the Id (numbers not text)");
        Scanner scanner = new Scanner(System.in);
        int id  = scanner.nextInt();
        wordService.deleteById(id);
    }

    private void searching() {
        System.out.println("Enter the word");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.next();
        wordService.search(word);
    }

    private void spelling() {
        wordService.spelling();

    }

    private void multipleChoice() {
        wordService.multipleChoice();

    }

    private void wordList() {
      wordService.wordList();
    }

    private void addWord() {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word:");
        String wordcha = scanner.next();
        System.out.println("Enter the Translate:");
        String translate = scanner.next();
        while (true) {
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Enter description of word. Minimum size is 3 Characters ");
            String description = scanner2.next();
            if (description.length() > 3){
                Word word = new Word(wordcha, translate, description);
                wordService.addWord(word);
                break;
            }
                System.out.println("mazgi so'zning tarifi juda kam qayta kirit");

        }



    }

    private void menu() {
        System.out.println("1->Add word");
        System.out.println("2->WordList");
        System.out.println("3->Multiple Choice");
        System.out.println("4->Spelling");
        System.out.println("5->Searching");
        System.out.println("6-> Delete by id");
        System.out.println("0->exit");
    }
}
