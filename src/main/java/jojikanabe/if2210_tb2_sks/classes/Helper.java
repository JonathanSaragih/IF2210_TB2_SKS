package jojikanabe.if2210_tb2_sks.classes;

public class Helper {
    static public String convertString(String input) {
        String[] words = input.toLowerCase().split("_");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }

        return result.toString().trim();
    }

    static public int convertStringToNumber(String input) {
        return (int) input.charAt(0) - (int) 'A';
    }
}
