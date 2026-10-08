// conversion 폴더의 예제를 묶는 패키지 선언입니다.
package conversion;

/** 2단계: 같은 덧셈 동작을 일반 클래스부터 람다까지 차례로 표현합니다. */
public class LambdaConversionExample {
    public static void main(String[] args) {
        // 1. 이름이 있는 구현 클래스를 사용합니다.
        // CalculatorImpl.java에 작성한 add 메서드가 실행됩니다.
        Calculator classCalculator = new CalculatorImpl();
        System.out.println("구현 클래스: " + classCalculator.add(1, 2)); // 3

        // 2. 별도의 클래스 이름 없이, 객체를 만드는 자리에서 직접 구현합니다.
        // 이런 형태를 익명 클래스라고 합니다. 아직은 add 메서드 전체를 적습니다.
        Calculator anonymousCalculator = new Calculator() {
            @Override
            public int add(int a, int b) {
                return a + b;
            }
        };
        System.out.println("익명 클래스: " + anonymousCalculator.add(1, 2)); // 3

        // 3. 함수형 인터페이스의 추상 메서드 구현을 람다로 표현합니다.
        // new Calculator(), @Override, 메서드 이름 등을 직접 적지 않습니다.
        // Calculator에 add 하나만 있으므로 어떤 메서드를 구현하는지 알 수 있습니다.
        // (매개변수) -> { 실행할 코드 } 형태로 작성합니다.
        Calculator explicitLambda = (int a, int b) -> {
            return a + b;
        };
        System.out.println("타입을 적은 람다: " + explicitLambda.add(1, 2)); // 3

        // 4. add의 선언에서 두 매개변수가 int임을 알 수 있어 타입을 생략합니다.
        // 타입을 생략해도 매개변수가 두 개이므로 괄호는 유지합니다.
        Calculator inferredLambda = (a, b) -> {
            return a + b;
        };
        System.out.println("타입을 생략한 람다: " + inferredLambda.add(1, 2)); // 3

        // 5. 본문이 값 하나를 반환하는 return 문뿐이면 더 줄일 수 있습니다.
        // 중괄호와 return, return 문 끝의 세미콜론을 함께 없앱니다.
        // 마지막 세미콜론은 변수 선언을 끝내는 것이므로 그대로 둡니다.
        Calculator shortLambda = (a, b) -> a + b;
        System.out.println("본문을 줄인 람다: " + shortLambda.add(1, 2)); // 3

        // 람다로 바꿔도 호출 방법은 같습니다. 변수.add(값, 값)으로 호출합니다.
        // 어떤 문법을 생략할 수 있는지는 LambdaSyntaxExample에서 따로 연습합니다.
    }
}
