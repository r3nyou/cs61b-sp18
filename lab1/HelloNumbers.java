public class HelloNumbers {
    public static void main(String[] args) {
        int i = 0, sum = 0;
        while (i < 10) {
            sum += i++;
            System.out.print(sum + " ");
        }
    }
}
