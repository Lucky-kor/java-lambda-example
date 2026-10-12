// 람다를 메서드 참조로 바꾸는 형태를 비교하는 패키지입니다.
package methodreference;

import java.util.function.BiFunction; // 입력 두 개와 결과 하나를 표현합니다.
import java.util.function.Function; // 입력 하나와 결과 하나를 표현합니다.

/** 정적 메서드, 특정 객체, 입력으로 받는 객체, 생성자 참조를 비교합니다. */
public class MethodReferenceExample {
    // 각 람다와 메서드 참조를 같은 입력으로 실행합니다.
    public static void main(String[] args) {
        // 1. 정적 메서드: 두 입력이 Math.max의 인수로 전달됩니다.
        BiFunction<Integer, Integer, Integer> maxLambda = (left, right) -> Math.max(left, right);
        BiFunction<Integer, Integer, Integer> maxReference = Math::max; // 클래스명::정적메서드
        System.out.println("정적 메서드 람다: " + maxLambda.apply(3, 7)); // 7
        System.out.println("정적 메서드 참조: " + maxReference.apply(3, 7)); // 7

        // 2. 특정 객체의 인스턴스 메서드: 호출할 객체를 미리 만듭니다.
        Calculator calculator = new Calculator(); // add를 호출할 객체입니다.
        BiFunction<Integer, Integer, Integer> addLambda = (left, right) -> calculator.add(left, right);
        BiFunction<Integer, Integer, Integer> addReference = calculator::add; // 객체변수::메서드
        System.out.println("특정 객체 람다: " + addLambda.apply(1, 2)); // 3
        System.out.println("특정 객체 참조: " + addReference.apply(1, 2)); // 3

        // 3. 입력으로 받는 객체의 메서드: 첫 입력이 호출 대상, 두 번째 입력이 인수입니다.
        BiFunction<String, String, Boolean> equalsLambda = (left, right) -> left.equals(right);
        BiFunction<String, String, Boolean> equalsReference = String::equals; // 정적 메서드가 아닙니다.
        System.out.println("입력 객체 람다: " + equalsLambda.apply("Java", "Java")); // true
        System.out.println("입력 객체 참조: " + equalsReference.apply("Java", "Java")); // true
        System.out.println("다른 내용 비교: " + equalsReference.apply("Java", "java")); // false

        // 4. 생성자 참조: String 입력을 생성자에 전달하고 새 Member를 반환합니다.
        Function<String, Member> memberLambda = name -> new Member(name);
        Function<String, Member> memberReference = Member::new; // 클래스명::new
        Member first = memberLambda.apply("홍길동"); // apply할 때 객체를 생성합니다.
        Member second = memberReference.apply("홍길동"); // 같은 이름의 별도 객체를 생성합니다.
        System.out.println("생성자 람다: " + first.getName()); // 홍길동
        System.out.println("생성자 참조: " + second.getName()); // 홍길동
        // 메서드 참조를 변수에 저장하는 것과 apply로 실행하는 것은 구분해야 합니다.
    }
}
