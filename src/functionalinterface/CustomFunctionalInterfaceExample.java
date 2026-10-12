// 직접 만든 함수형 인터페이스를 사용하는 패키지입니다.
package functionalinterface;

/** 입력·반환값이 있는 계약과 없는 계약을 각각 람다로 구현합니다. */
public class CustomFunctionalInterfaceExample {
    // 두 계약을 차례로 실행합니다.
    public static void main(String[] args) {
        // IntAddition.add의 입력과 반환 타입은 모두 int입니다.
        IntAddition addition = (left, right) -> left + right;
        // 람다 선언과 별개로 add를 호출해야 계산이 실행됩니다.
        System.out.println("덧셈: " + addition.add(1, 2)); // 3

        // MessageAction.execute는 입력이 없고 반환 타입이 void입니다.
        MessageAction greeting = () -> System.out.println("안녕하세요");
        // 반환값이 없으므로 출력 동작 자체를 호출합니다.
        greeting.execute(); // 안녕하세요
    }
}
