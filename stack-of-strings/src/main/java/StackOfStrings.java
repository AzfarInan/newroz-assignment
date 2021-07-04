import java.util.Scanner;

public class StackOfStrings {
    public static void main(String[] args) {
        String[] A = new String[10];
        int N = A.length-1;
        int top = -1;

        CustomStack customStack = new CustomStack(A, N, top);
    }
}
