public class CustomStack {
    String[] A;
    int N;
    int top;

    public CustomStack(String[] A, int N, int top){
        this.A = A;
        this.N = N;
        this.top = top;
    }

    public String push(String value){
        if (top != N){
            A[++top] = value;
            return "Pushed";
        } else {
            return "Stack Overflow";
        }
    }

    public String peek(){
        if (top < 0)
            return "Empty Stack";
        else
            return "Stack Top: " + A[top];
    }

    public String pop(){
        if (top >= 0){
            A[top] = null;
            top--;
            return "Popped";
        } else {
            return "Stack Underflow";
        }
    }

}
