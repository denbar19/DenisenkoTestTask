package akvelon.test.denisenko;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BalanceBrackets implements BalanceVerificator {

    // order in strings must be respective for each type of brackets
    // to be able compare int not chars
    private static final String OPEN_BRACKETS = "({[";
    private static final String CLOSE_BRACKETS = ")}]";

    private String toVerify;
    private int notBalancedBracketIndex;

    public int verify(String str) {
        toVerify = str;
        if (!checkStringForBrackets()) {
            System.out.print("Input string doesn't contain brackets!\n");
            return 0;
        }

        try {
            if (checkCharacterBelongBrackets() && checkBracketsIsBalanced()) {
                System.out.print("BALANCED\n");
                return -1;
            } else {
                System.out.printf("NOT BALANCED (%d)\n", notBalancedBracketIndex);
                return notBalancedBracketIndex;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Compares each char from the given string to any from list of appropriate brackets.
     *
     * @return check result
     */
    private boolean checkStringForBrackets() {
        String brackets = OPEN_BRACKETS + CLOSE_BRACKETS;
        boolean res = false;
        for (char chr : toVerify.toCharArray()) {
            if (brackets.indexOf(chr) != -1) {
                res = true;
            }
        }
        return res;
    }

    private boolean openBracketCheck(char ch) {
        return OPEN_BRACKETS.indexOf(ch) != -1;
    }

    private boolean closeBracketCheck(char ch) {
        return CLOSE_BRACKETS.indexOf(ch) != -1;
    }

    private boolean checkBracketsIsBalanced() throws IllegalArgumentException {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 1; i <= toVerify.length(); i++) {
            char charToCheck = toVerify.charAt(i - 1);
            if (openBracketCheck(charToCheck)) {
                stack.push(charToCheck);
                continue;
            }
            // filter non close bracket
            if (CLOSE_BRACKETS.indexOf(charToCheck) == -1) {
                continue;
            }
            char bracket;
            if (!stack.isEmpty()) {
                bracket = stack.pop();
            } else {
                notBalancedBracketIndex = i;
                return false;
            }
            /*
              compare open bracket from the stack and close bracket
              if
             */
            if (OPEN_BRACKETS.indexOf(bracket) != CLOSE_BRACKETS.indexOf(charToCheck)) {
                for (char chr : OPEN_BRACKETS.toCharArray()) {
                    if (stack.contains(chr)) {
                        notBalancedBracketIndex = i - 1;
                        break;
                    } else {
                        notBalancedBracketIndex = i;
                    }
                }
                return false;
            }
        }
        return stack.isEmpty();
    }

    private boolean checkCharacterBelongBrackets() throws IllegalArgumentException {
        char first = toVerify.charAt(0);
        char last = toVerify.charAt(toVerify.length() - 1);
        if (openBracketCheck(first) && closeBracketCheck(last)) {
            return true;
        }
        if (closeBracketCheck(first)) {
            notBalancedBracketIndex = 1;
            return false;
        } else if (openBracketCheck(last)) {
            notBalancedBracketIndex = toVerify.length();
            return false;
        }
        getIllegalChars();
        throw new IllegalArgumentException();
    }

    public void getIllegalChars() {
        StringBuilder sb = new StringBuilder();
        String regexBegin = "^\\w+";
        String regexEnd = "\\w+$";
        Matcher m1 = Pattern.compile(regexBegin).matcher(toVerify);
        if (m1.find()) {
            sb.append(m1.group());
        }

        Matcher m2 = Pattern.compile(regexEnd).matcher(toVerify);
        if (m2.find()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(m2.group());
        }
        String insert = sb.toString();
        System.out.printf("A character '%s' doesn’t belong to any known brackets type\n", insert);
    }

    public static void main(String[] args) {
        BalanceVerificator bb = new BalanceBrackets();
        try {
            bb.verify("{[]()()}");
            bb.verify("{(}"); // 2
            bb.verify("{()[}"); // 4
            bb.verify("{)}"); // 2
            bb.verify("([])}"); // 5
            bb.verify("}[])}"); // 1
            bb.verify("{)])}"); // 2

            bb.verify("asdf");
            bb.verify("(asdf)");

            bb.verify("a{}");
            bb.verify("a{}b");
            bb.verify("az{}bz");
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
    }
}
