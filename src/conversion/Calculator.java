// conversion 폴더의 예제를 묶는 패키지 선언입니다.
package conversion;

/**
 * 정수 두 개를 받아 덧셈 결과를 돌려주는 함수형 인터페이스입니다.
 * 함수형 인터페이스는 추상 메서드가 하나인 인터페이스입니다.
 */
@FunctionalInterface // 추상 메서드가 하나인지 컴파일러가 확인하도록 합니다.
public interface Calculator {
    // 여기서는 메서드의 이름, 입력, 반환 타입만 정합니다.
    // 실제 덧셈 코드는 구현 클래스, 익명 클래스 또는 람다에서 작성합니다.
    int add(int a, int b);
}
