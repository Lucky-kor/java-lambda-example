// 자바에서 제공하는 함수형 인터페이스를 학습하는 패키지입니다.
package functionalinterface;

import java.util.function.Consumer; // 입력 하나를 사용하고 값을 반환하지 않습니다.
import java.util.function.Supplier; // 입력 없이 값을 제공합니다.
import java.util.function.Function; // 입력 하나를 다른 값으로 변환합니다.
import java.util.function.BiFunction; // 입력 두 개를 받아 값을 반환합니다.
import java.util.function.UnaryOperator; // 입력과 결과의 타입이 같은 변환입니다.
import java.util.function.Predicate; // 입력 하나에 대한 조건을 판단합니다.

/** 각 인터페이스의 입력, 출력, 호출 메서드를 비교합니다. */
public class StandardFunctionalInterfaceExample {
    // 선언한 모든 람다를 실제로 호출하여 결과를 확인합니다.
    public static void main(String[] args) {
        // Consumer<String>: 문자열 하나를 받고 반환값은 없습니다.
        Consumer<String> printer = text -> System.out.println("출력: " + text);
        printer.accept("람다 학습"); // accept로 출력 동작을 실행합니다.

        // Supplier<String>: 입력 없이 문자열을 반환합니다.
        Supplier<String> greetingSupplier = () -> "안녕하세요";
        String greeting = greetingSupplier.get(); // get을 호출할 때 본문이 실행됩니다.
        System.out.println("공급된 값: " + greeting); // 안녕하세요

        // Function<String, Integer>: 문자열을 받아 길이를 반환합니다.
        // 제네릭에는 int 대신 Integer를 씁니다. int 결과는 자동으로 박싱됩니다.
        Function<String, Integer> lengthFunction = text -> text.length();
        System.out.println("문자열 길이: " + lengthFunction.apply("Hello")); // 5

        // BiFunction<T, U, R>: 앞의 두 타입이 입력이고 마지막 타입이 결과입니다.
        BiFunction<String, Integer, Boolean> longerThan = (text, limit) -> text.length() > limit;
        System.out.println("길이가 2보다 큰가: " + longerThan.apply("Hello", 2)); // true
        System.out.println("길이가 5보다 큰가: " + longerThan.apply("Hello", 5)); // false

        // UnaryOperator<String>은 Function<String, String>의 특수한 형태입니다.
        UnaryOperator<String> trimOperator = text -> text.trim();
        Function<String, String> trimFunction = text -> text.trim(); // 같은 변환을 일반 계약으로 표현합니다.
        System.out.println("UnaryOperator: [" + trimOperator.apply(" Java ") + "]"); // [Java]
        System.out.println("Function: [" + trimFunction.apply(" Java ") + "]"); // [Java]

        // 참·거짓 조건을 표현할 때에는 boolean을 반환하는 Predicate가 적합합니다.
        Predicate<String> longerThanTwo = text -> text.length() > 2;
        System.out.println("Hello 조건 판단: " + longerThanTwo.test("Hello")); // true
        System.out.println("Hi 조건 판단: " + longerThanTwo.test("Hi")); // false
        // 이 단계에서는 람다로 작성하고, 같은 호출을 줄이는 메서드 참조는 뒤에서 배웁니다.
    }
}
