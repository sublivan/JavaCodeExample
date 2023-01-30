package effective.lambdaAndStream.item45;

/**
 * char의 값을 스트림으로 처리할때는 주의해야한다.
 */
public class CharStream {
    public static void main(String[] args) {
        "Hello World".chars().forEach(System.out::print); // chars()가 반환하는 스트림의 원소는 int값
        System.out.println();
        "Hello World".chars().forEach(x -> System.out.print((char) x)); // 형변환
    }
}
