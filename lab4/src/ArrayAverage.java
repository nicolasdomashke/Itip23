public class ArrayAverage {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        Object[] arr2 = {true, false};
        int sum = 0;
        try {
            for (int i = 0; i < 6; i++) {
                sum += (Integer) arr1[i]; //Change the array
            }
            System.out.println(sum / 5);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index's completely wrong!");
        } catch (ClassCastException e) {
            System.out.println("Type's absolutely incorrect!");
        }
    }
}