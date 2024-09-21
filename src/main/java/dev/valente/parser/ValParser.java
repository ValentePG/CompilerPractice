package dev.valente.parser;


import dev.valente.lexer.ValScanner;
import dev.valente.lexer.ValToken;
import dev.valente.exceptions.SyntaticalException;

public class ValParser {

    ValScanner scanner;
    ValToken token;

    public ValParser(ValScanner scanner) {
        this.scanner = scanner;
    }

    public void E(){
        T();
        El();
    }

    public void El(){
        token = scanner.nextToken();
        if(token != null){
            OP();
            T();
            El();
        }
    }

    public void T(){
        token = scanner.nextToken();
        if(token.getType() != ValToken.TK_ID && token.getType() != ValToken.TK_NUMBER){
            throw new SyntaticalException("Erro Sintático ID ou Número esperado ");
        }
    }

    public void OP(){

        if(token.getType() != ValToken.TK_OPERATOR){
            throw new SyntaticalException("Erro Sintático operador esperado");
        }
    }
}
