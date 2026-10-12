// 익명 클래스, 람다, 메서드 참조를 비교하는 종합 연습 패키지입니다.
package lambdapractice;

import java.util.function.Predicate; // 입력 하나로 조건을 판단합니다.
import java.util.function.BiPredicate; // 입력 두 개로 조건을 판단합니다.

/** 고정된 인수와 외부에서 받는 인수가 메서드 참조에 미치는 차이를 연습합니다. */
public class LambdaRefactoringExample {
    // 같은 조건을 여러 형태로 작성하고 경계가 되는 입력도 확인합니다.
    public static void main(String[] args) {
        // 1. 익명 클래스에서 Predicate의 test 메서드를 직접 구현합니다.
        Predicate<String> anonymousStartsWithA = new Predicate<String>() {
            @Override // Predicate에 선언된 test를 구현합니다.
            public boolean test(String text) {
                return text.startsWith("A"); // 접두사 A는 이 구현 안에서 고정되어 있습니다.
            }
        };
        // 2. 같은 입력과 조건을 람다로 줄입니다.
        Predicate<String> lambdaStartsWithA = text -> text.startsWith("A");
        System.out.println("익명 클래스 Apple: " + anonymousStartsWithA.test("Apple")); // true
        System.out.println("람다 Apple: " + lambdaStartsWithA.test("Apple")); // true
        System.out.println("익명 클래스 Banana: " + anonymousStartsWithA.test("Banana")); // false
        System.out.println("람다 Banana: " + lambdaStartsWithA.test("Banana")); // false

        // Predicate<String> invalid = String::startsWith;
        // 입력 하나는 호출 대상 문자열에 사용되므로 접두사 인수가 부족합니다.
        // 메서드 참조가 고정값 A를 자동으로 채워 주지는 않습니다.

        // 3. 접두사까지 외부 입력으로 받는 별도의 두 입력 계약을 만듭니다.
        BiPredicate<String, String> startsWithLambda = (text, prefix) -> text.startsWith(prefix);
        BiPredicate<String, String> startsWithReference = String::startsWith;
        // 첫 입력 Apple이 호출 대상이고, 두 번째 입력 A가 startsWith의 인수입니다.
        System.out.println("두 입력 람다: " + startsWithLambda.test("Apple", "A")); // true
        System.out.println("두 입력 메서드 참조: " + startsWithReference.test("Apple", "A")); // true
        System.out.println("접두사 변경: " + startsWithReference.test("Banana", "B")); // true
        // 입력 개수가 달라졌으므로 기존 Predicate를 그대로 대체한 것은 아닙니다.
    }
}
