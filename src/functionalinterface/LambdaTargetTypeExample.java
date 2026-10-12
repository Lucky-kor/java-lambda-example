// 람다를 어떤 타입의 변수에 저장하는지 학습하는 패키지입니다.
package functionalinterface;

/** Object 타입과 함수형 인터페이스 타입에서 호출할 수 있는 메서드를 비교합니다. */
public class LambdaTargetTypeExample {
    // 이 예제를 실행하는 시작점입니다.
    public static void main(String[] args) {
        // 익명 클래스에 add를 선언해도 변수 타입 Object에는 add가 없습니다.
        Object object = new Object() {
            // 이 익명 클래스 안에서만 새로 정의한 메서드입니다.
            int add(int left, int right) {
                return left + right; // 두 값을 더해 반환합니다.
            }
        };
        // Object에 선언된 메서드는 호출할 수 있습니다.
        System.out.println("Object 메서드 호출: " + object.equals(object)); // true
        // System.out.println(object.add(1, 2)); // Object 타입에는 add가 없어 컴파일 오류입니다.

        // 함수형 인터페이스 타입을 사용하면 호출할 메서드의 계약이 명확해집니다.
        IntAddition addition = (left, right) -> left + right;
        // IntAddition에 선언된 add를 통해 람다 본문을 실행합니다.
        System.out.println("함수형 인터페이스 메서드 호출: " + addition.add(1, 2)); // 3

        // Object invalid = (left, right) -> left + right;
        // Object는 함수형 인터페이스가 아니므로 위 람다의 대상 타입이 될 수 없습니다.
        // 람다는 대상 타입에서 매개변수와 반환 타입을 결정합니다.
    }
}
