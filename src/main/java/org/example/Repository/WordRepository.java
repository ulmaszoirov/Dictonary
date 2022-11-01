package org.example.Repository;

import org.example.db.Database;
import org.example.dto.Word;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class WordRepository {
    public void addword(Word word) {
        Connection con = null;
        try {
            con= Database.getConnection();
            PreparedStatement statement = con.prepareStatement("insert into word(word,translate,description) values(?,?,?)");
                   statement.setString(1,word.getWord());
                   statement.setString(2,word.getTranslate());
                   statement.setString(3,word.getDescription());
                   statement.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }finally {
            try {
                con.close();

            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public List<Word> wordList() {
        List<Word> wordList = new LinkedList<>();
            Connection con = null;
            try {
                con= Database.getConnection();
                PreparedStatement statement = con.prepareStatement("select *from word");
                ResultSet resultSet= statement.executeQuery();
                while (resultSet.next())
                {
                  Integer id = resultSet.getInt("id");
                  String wordcha =  resultSet.getString("word");
                  String translate = resultSet.getString("translate");
                  String desctription = resultSet.getString("description");
                  Word word  = new Word(id,wordcha,translate,desctription);
                  wordList.add(word);
                }
                return wordList;
            }catch (SQLException e)
            {
                throw new RuntimeException(e);
            }finally {
                try {
                    con.close();

                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
    }

    public boolean checkWordById(int id) {

        Connection con = null;
        try {
            con= Database.getConnection();
            PreparedStatement statement = con.prepareStatement("select *from word where id = ?");
            statement.setInt(1,id);
            ResultSet resultSet= statement.executeQuery();
            return resultSet.next();

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }finally {
            try {
                con.close();

            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }

    public void deleteById(int id) {

        Connection con = null;
        try {
            con= Database.getConnection();
            PreparedStatement statement = con.prepareStatement("delete from word where id = ?");
            statement.setInt(1,id);
            statement.executeUpdate();

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }finally {
            try {
                con.close();

            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public Word search(String word) {

        Connection con = null;
        Word word1 = null;
        try {
            con= Database.getConnection();
            PreparedStatement statement = con.prepareStatement("select * from word where word = ?");
            statement.setString(1,word);
           ResultSet resultSet =  statement.executeQuery();
           if(resultSet.next())
           {
               Integer id = resultSet.getInt("id");
               String wordcha =  resultSet.getString("word");
               String translate = resultSet.getString("translate");
               String desctription = resultSet.getString("description");
                word1 = new Word(id,wordcha,translate,desctription);
           }
           return word1 ;

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }finally {
            try {
                con.close();

            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }

    public boolean isWordsExist() {


        Connection con = null;
        try {
            con= Database.getConnection();
            PreparedStatement statement = con.prepareStatement("select * from word");
            ResultSet resultSet =  statement.executeQuery();

            return resultSet.next() ;

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }finally {
            try {
                con.close();

            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }

    public boolean isEnoughWord() {

        Connection con = null;
        try {
            con= Database.getConnection();
            PreparedStatement statement = con.prepareStatement("select * from word");
            ResultSet resultSet =  statement.executeQuery();
            int i=0;
            while (resultSet.next())
            {
             i++;
            }
            if(i>=4)
             return true;

            return false;

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }finally {
            try {
                con.close();

            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
