import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class Main {
    static final Map<Character, Integer> ROMAN_VALUES = new HashMap<>();
    static Scanner s = new Scanner(System.in);
    static boolean firstOperation = true;

    static {
        ROMAN_VALUES.put('I', 1);
        ROMAN_VALUES.put('V', 5);
        ROMAN_VALUES.put('X', 10);
        ROMAN_VALUES.put('L', 50);
        ROMAN_VALUES.put('C', 100);
        ROMAN_VALUES.put('D', 500);
        ROMAN_VALUES.put('M', 1000);
        ROMAN_VALUES.put('i', 1);
        ROMAN_VALUES.put('v', 5);
        ROMAN_VALUES.put('x', 10);
        ROMAN_VALUES.put('l', 50);
        ROMAN_VALUES.put('c', 100);
        ROMAN_VALUES.put('d', 500);
        ROMAN_VALUES.put('m', 1000);
    }

    public static String arabicToRoman(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException();
        }
            int[] arabicValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I", "m", "cm", "d", "cd", "c", "xc", "l", "xl", "x", "ix", "v", "iv", "i",};
            StringBuilder roman = new StringBuilder();
            int i = 0;
            while (number > 0) {
                if (number >= arabicValues[i]) {
                    roman.append(romanSymbols[i]);
                    number -= arabicValues[i];
                } else {
                    i++;
                }
            }
            return roman.toString();
        }

        public static String calc(String input) {
            input = input.trim();
            String[] parts = input.split("\\s*\\+\\s*|\\s*\\-\\s*|\\s*\\*\\s*|\\s*\\/\\s*");
            int num1;
            int num2;
            if (parts.length != 2) {
                throw new IllegalArgumentException("Ошибка: Некорректное выражение.");
            }
            try {
                if (Character.isDigit(parts[0].charAt(0))) {
                    num1 = Integer.parseInt(parts[0]);
                } else {
                    num1 = romanToArabic(parts[0]);
                }
                if (Character.isDigit(parts[1].charAt(0))) {
                    num2 = Integer.parseInt(parts[1]);
                } else {
                    num2 = romanToArabic(parts[1]);
                }
                if ((Character.isDigit(parts[0].charAt(0)) && !Character.isDigit(parts[1].charAt(0))) || (!Character.isDigit(parts[0].charAt(0)) && Character.isDigit(parts[1].charAt(0)))) {
                    throw new IllegalArgumentException("Ошибка: либо арабские числа, либо римские!");
                }

            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Ощибка: Некорректный формат ввода");
            }

            int result;
            char operator;
            if (input.contains("+")) {
                num1 = Character.isDigit(parts[0].charAt(0)) ? Integer.parseInt(parts[0]) : romanToArabic(parts[0]);
                num2 = Character.isDigit(parts[1].charAt(0)) ? Integer.parseInt(parts[1]) : romanToArabic(parts[1]);
                result = num1 + num2;
                operator = '+';
            } else if (input.contains("-")) {
                result = num1 - num2;
                operator = '-';
            } else if (input.contains("*")) {
                result = num1 * num2;
                operator = '*';
            } else {
                result = num1 / num2;
                operator = '/';
            }
            String resultString = input.replace(parts[0], Character.isDigit(parts[0].charAt(0)) ? Integer.toString(num1) : arabicToRoman(num1));
            resultString = resultString.replace(parts[1], Character.isDigit(parts[1].charAt(0)) ? Integer.toString(num2) : arabicToRoman(num2));
            String finalResult = (Character.isDigit(parts[0].charAt(0)) && Character.isDigit(parts[1].charAt(0))) ? Integer.toString(result) : arabicToRoman(result);
            return resultString + "=" + finalResult;
        }
        public static void main(String[] args) {
            System.out.println("Этот калькулятор работает как с арабскими числами, так и с римскими.(Пример V+L, 5+5");
            String answer = "Y";
            do {
                if (!firstOperation) {
                    System.out.println("Хотите выполнить еще одну операцию?(Y/N)");
                    answer = s.nextLine();
                    try {
                        if (answer == null || (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N"))){
                           throw  new IllegalArgumentException("Некорректный ответ. Введите Y или N");
                        }
                        if (!answer.equalsIgnoreCase("Y")) {
                            break;
                        }
                    }catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                } else {
                    answer = "Y";
                firstOperation = false;
            }
            System.out.println("Введите операцию: ");
                String input = s.nextLine();
                try {
                    System.out.println(calc(input));
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                }while (true);
            s.close();
        }
        public static int romanToArabic(String roman) {
        int result = 0;
        int prevValue = 0;
        int countRepeated = 1;
        for (int i = roman.length() - 1; i >= 0; i--){
            int value = ROMAN_VALUES.get(roman.charAt(i));
            if (value < prevValue){
                result -= value;
                countRepeated = 1;
            }else {
                result += value;
                if (value == prevValue){
                    countRepeated++;
                    if (countRepeated > 3){
                        throw new IllegalArgumentException("Ошибка: Римские числа не должны содержать одинаковые символы более трех раз подряд.");
                    }
                }else {
                    countRepeated = 1;
                }
            }prevValue = value;

        }return result;
        }

}

