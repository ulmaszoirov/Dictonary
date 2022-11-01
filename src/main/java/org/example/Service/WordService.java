package org.example.Service;

import org.example.Repository.WordRepository;
import org.example.dto.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service
public class WordService {
    @Autowired
    WordRepository wordRepository;


    public void addWord(Word word) {
        wordRepository.addword(word);
        System.out.println("New word is added");
    }

    public void wordList() {
        List<Word> wordList = wordRepository.wordList();
        if (wordList.isEmpty()) {
            System.out.println("No Dictionary");
            return;
        }
        for (Word word : wordList)
            System.out.println(word.toString());
    }

    public void deleteById(int id) {
        Boolean result = wordRepository.checkWordById(id);
        if (!result) {
            System.out.println("No such id");
            return;
        }
        wordRepository.deleteById(id);
        System.out.println("Deleted");

    }

    public void search(String word) {
        Word word1 = wordRepository.search(word);
        if (word1 == null) {
            System.out.println("No such word");
            return;
        }
        System.out.println(word1);


    }

    public void multipleChoice() {
        Boolean result = wordRepository.isWordsExist();
        if (!result) {
            System.out.println("No words for multiple Choice");
            return;
        }
        Boolean result1 = wordRepository.isEnoughWord();
        if (!result1) {
            System.out.println("words is not enough for multiple Choice");
            return;
        }
        List<Word> wordList = wordRepository.wordList();
        Random random = new Random();
        int countYes = 0;
        int countNo = 0;
        int loop = 0;

        ArrayList<Integer> arrayListForQuestion = new ArrayList<>();

        while (loop < wordList.size()) {

            ArrayList<Integer> arrayList = new ArrayList<>();

            int a = random.nextInt(0, wordList.size());
            arrayListForQuestion.add(a);

            for (int i = 0; i < 3; i++) {
                int n = random.nextInt(0, wordList.size());
                if (arrayList.contains(n)) {
                    i--;
                    continue;
                }
                arrayList.add(n);
            }


            System.out.println(wordList.get(arrayList.get(a)).getWord() + " Enter the translate of this word");
            System.out.println("a." + wordList.get(arrayList.get(0)).getTranslate());
            System.out.println("b." + wordList.get(arrayList.get(1)).getTranslate());
            System.out.println("c." + wordList.get(arrayList.get(2)).getTranslate());
            System.out.println("d." + wordList.get(arrayList.get(3)).getTranslate());
            Scanner scanner = new Scanner(System.in);
            String ans = scanner.next();
            if ((a == 0 && ans.equals("a")) || (a == 1 && ans.equals("b")) || (a == 2 && ans.equals("c")) || (a == 3 && ans.equals("d"))) {
                System.out.println("Correct");
                countYes++;
            } else {
                System.out.println("wrong");
                countNo++;
            }

            loop++;

        }

        System.out.println(countYes);
        System.out.println(countNo);

    }

    public void spelling() {
        List<Word> wordList = wordRepository.wordList();
        Random random = new Random();
        int a = random.nextInt(0, wordList.size());
        Word word = wordList.get(a);
        System.out.println(word.getTranslate() + " spell this word");
        int b = random.nextInt(0, word.getTranslate().length() - 1);
        String subString = word.getWord().substring(0, b);
        subString = subString + word.getWord().substring(b + 1);
        System.out.println(subString + " here is example");

        while (true) {
            System.out.println("Enter your answer: ");
            Scanner scanner = new Scanner(System.in);
            String ans = scanner.next();
            if (ans.equals(word.getWord())) {
                System.out.println("Correct");
                break;
            } else {
                System.out.println("Wrong reEnter it");
            }

        }


    }


}
