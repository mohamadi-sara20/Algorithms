package algorithms;

public class Primality {

    public static void main(String[] args){
        int[] numbers = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97
                        ,-2,-3,-5,-7,-11,-13,-17,-19,-23,-29,-31,-37,-41,-43,-47,-53,-59,-61,
                        -67,-71,-73,-79,-83,-89,-97};
        for(int n: numbers)
            System.out.println(Primality.isPrime(n, 2));
    }
    private static boolean isPrime(int number, int i){
        if(-2 < number && number < 2) return false;
        if(number == 2 || number == -2) return true;
        else if(number % i == 0){
            return false;
        }
        else if(i < number - 1) return isPrime(number, i + 1);
        return true;

    }
}
