// 메서드 참조 예제에서 사용하는 보조 클래스의 패키지입니다.
package methodreference;

/** 이미 만든 객체의 인스턴스 메서드를 참조하는 데 사용합니다. */
public class Calculator {
    // 정수 두 개를 받아 합을 반환하는 인스턴스 메서드입니다.
    public int add(int left, int right) {
        return left + right; // 람다와 메서드 참조 모두 이 계산을 호출합니다.
    }
}
