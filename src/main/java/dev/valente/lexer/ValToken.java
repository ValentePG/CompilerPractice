package dev.valente.lexer;

public class ValToken {

    public static final int TK_OPERATOR = 0;
    public static final int TK_NUMBER = 1;
    public static final int TK_ID = 2;
    public static final int TK_PONT = 3;

    private int type;
    private String text;

    public ValToken() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ValToken{" +
                "type=" + type +
                ", text='" + text + '\'' +
                '}';
    }

}
