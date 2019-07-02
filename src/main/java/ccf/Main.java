package ccf;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        char[] tq = new char[9];
        for (int i = 0; i < tq.length; i++) {
            tq[i] = (char) ('1' + i);
        }
        char[] tw = {'+', '-', 'x', '/'};
        Stack<Integer> stackQ = new Stack<>();
        Stack<Character> stackW = new Stack<>();
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            String s = scan.next();
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                for (int k = 0; k < n; k++) {
                    if (c == tq[k]) {
                        stackQ.push((Integer.valueOf(""+c)) );
                        break;
                    }
                }
                for (int k = 0; k < n; k++) {
                    if (c == tw[k]) {
                        stackW.push(c);
                        break;
                    }
                }
            }
            int c = stackQ.pop();
            int r = c;
            System.out.println(r);
            while (!stackQ.empty()) {


            }

        }
        scan.close();
    }
}
