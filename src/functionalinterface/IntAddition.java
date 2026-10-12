// 직접 정의하는 함수형 인터페이스를 모아 둔 패키지입니다.
package functionalinterface;

/** 정수 두 개를 더하는 동작의 입력과 반환 타입을 정합니다. */
@FunctionalInterface // 추상 메서드가 하나인지 컴파일러가 확인합니다.
public interface IntAddition {
    // 두 int를 받아 int를 반환하며, 실제 계산 방법은 구현할 때 작성합니다.
    int add(int left, int right);
}
