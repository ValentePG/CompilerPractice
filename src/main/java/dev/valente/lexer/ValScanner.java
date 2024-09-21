package dev.valente.lexer;


import dev.valente.exceptions.LexicalException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ValScanner {

    char[] caracteres;
    int    pos;
    int    estado;


    public ValScanner(String filename) {

        try{

            String txtConteudo;
            txtConteudo = new String(Files.readAllBytes(Paths.get(filename)),StandardCharsets.UTF_8);
            caracteres = txtConteudo.toCharArray();
            pos = 0;

        } catch (Exception err){

            err.printStackTrace();

        }
    }

    public ValToken nextToken(){

        char currentChar;

        ValToken token;

        String term = "";

        if(isEOF()){
            return null;
        }
        estado = 0;
        while (true){

            currentChar = nextChar();

            switch (estado){
                case 0:

                    if(isIdentifier(currentChar)){
                        estado = 1;
                        term += currentChar;
                    } else if(isNumber(currentChar)){
                        estado = 2;
                        term += currentChar;
                    } else if(isOperator(currentChar)){
                        term += currentChar;

                        token = new ValToken();
                        token.setText(term);
                        token.setType(ValToken.TK_OPERATOR);
                        System.out.println(token);
                        return token;
                    } else if(isSpace(currentChar)){
                        estado = 0;
                    } else {
                        throw new LexicalException("Lexical error");
                    }
                    break;

                case 1:

                    if(isIdentifier(currentChar) || isNumber(currentChar)){
                        estado = 1;
                        term += currentChar;
                    } else if(isOperator(currentChar) || isSpace(currentChar) || isEOF(currentChar)){
                        if(!isEOF(currentChar)){
                            back();
                        }
                        token = new ValToken();
                        token.setText(term);
                        token.setType(ValToken.TK_ID);
                        System.out.println(token);
                        return token;
                    }
                    else {
                        throw new LexicalException("Malformed Identifier");
                    }
                    break;

                case 2:

                    if(isNumber(currentChar) || currentChar == '.'){

                        term += currentChar;
                        estado = 2;

                    } else if(!isIdentifier(currentChar) || isEOF(currentChar)){
                        if(!isEOF(currentChar)){
                            back();
                        }

                        token = new ValToken();
                        token.setText(term);
                        token.setType(ValToken.TK_NUMBER);

                        System.out.println(token);
                        return token;
                    } else {
                        throw new LexicalException("Unrecognized Number");
                    }
                    break;
            }


        }

    }

    private boolean isNumber(char c){
        return c >= '0' && c <= '9';
    }

    private boolean isIdentifier(char c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '=' || c == '<' || c == '>' || c == '!';
    }

    private boolean isSpace(char c){
        return c == ' ' || c == '\n' || c == '\r' || c == '\t';
    }

    private boolean isEOF(){
        return pos >= caracteres.length;
    }

    private boolean isEOF(char c){
        return c == '\0';
    }

    private char nextChar(){
        if(isEOF()){
            return '\0';
        }
        return caracteres[pos++];
    }

    private void back(){
        pos--;
    }


}
