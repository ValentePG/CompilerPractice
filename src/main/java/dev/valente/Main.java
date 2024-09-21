package dev.valente;

import dev.valente.lexer.ValScanner;
import dev.valente.parser.ValParser;

public class Main {
    public static void main(String[] args) {


        ValScanner scanner = new ValScanner("ValLanguage.val");
        ValParser parser = new ValParser(scanner);

        parser.E();
        System.out.println("Compilation sucessfull!");

    }
}