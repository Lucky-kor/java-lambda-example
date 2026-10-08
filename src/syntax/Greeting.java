// syntax 폴더의 예제를 묶는 패키지 선언입니다.
package syntax;

/** 매개변수가 하나인 람다의 괄호 생략을 연습하기 위한 계약입니다. */
@FunctionalInterface
public interface Greeting {
    // 이름 하나를 받아 인사말 문자열을 반환합니다.
    String greet(String name);
}
