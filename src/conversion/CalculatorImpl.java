// conversion 폴더의 예제를 묶는 패키지 선언입니다.
package conversion;

/** Calculator가 정한 add 메서드를 일반 클래스로 구현합니다. */
public class CalculatorImpl implements Calculator {
    // 인터페이스에 선언된 메서드를 구현한다는 뜻입니다.
    @Override
    public int add(int a, int b) {
        // 전달받은 두 정수를 더한 뒤 호출한 곳으로 결과를 돌려줍니다.
        return a + b;
    }
}
