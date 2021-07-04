import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class CustomStackTest {

    private static CustomStack customStack;
    private static String[] A;
    private static int N;
    private static int top;

    @BeforeAll
    public static void setup() {
        A = new String[5];
        N = A.length-1;
        top = -1;
        customStack = new CustomStack(A, N, top);
    }

    @Test
    @DisplayName("Stack Pop Test")
    public void popTest(){
        customStack.push("John Cena");
        assertEquals("Popped", customStack.pop());
    }

    @Test
    @DisplayName("Stack Underflow Test")
    public void stackUnderFlowTest(){
        assertEquals("Stack Underflow", customStack.pop());
    }


    @Test
    @DisplayName("Stack Push Test")
    public void pushTest(){
        assertEquals("Pushed", customStack.push("Batman"));
    }

    @Test
    @DisplayName("Stack Overflow Test")
    public void stackOverFlowTest(){
        customStack.push("Superman");
        customStack.push("Batman");
        customStack.push("Flash");
        customStack.push("Cyborg");
        customStack.push("Wonder Women");
        assertEquals("Stack Overflow", customStack.push("Captain America"));
    }

    @Test
    @DisplayName("Stack Top / Peek Test")
    public void stackTopTest(){
        String value = "Iron Man";
        customStack.push(value);
        assertEquals("Stack Top: " + value, customStack.peek());
    }

    @Test
    @DisplayName("Empty Stack Test")
    public void emptyStackTest(){
        String value = "Iron Man";
        customStack.push(value);
        customStack.pop();
        assertEquals("Empty Stack", customStack.peek());
    }


}
