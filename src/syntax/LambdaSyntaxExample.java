// syntax 폴더의 예제를 묶는 패키지 선언입니다.
package syntax;

// 앞 단계에서 정의한 덧셈 계약을 가져와 문법 연습에도 사용합니다.
import conversion.Calculator;

/** 3단계: 생략이 가능한 경우와 생략하면 안 되는 경우를 비교합니다. */
public class LambdaSyntaxExample {
    public static void main(String[] args) {
        // [매개변수 하나] 타입을 적으면 매개변수 괄호가 필요합니다.
        Greeting typedGreeting = (String name) -> "안녕하세요, " + name;

        // 타입을 생략할 수 있으며, 매개변수가 하나이면 괄호도 생략할 수 있습니다.
        Greeting parenthesesGreeting = (name) -> "안녕하세요, " + name;
        Greeting shortGreeting = name -> "안녕하세요, " + name;
        System.out.println(typedGreeting.greet("민수")); // 안녕하세요, 민수
        System.out.println(parenthesesGreeting.greet("민수")); // 안녕하세요, 민수
        System.out.println(shortGreeting.greet("민수")); // 안녕하세요, 민수

        // [매개변수 없음] 매개변수가 없어도 빈 괄호 ()는 반드시 적습니다.
        // 반환 타입이 void이므로 출력만 하고 값을 반환하지 않습니다.
        Notice blockNotice = () -> {
            System.out.println("학습 시작");
        };

        // 메서드 호출 한 문장만 실행할 때는 중괄호를 생략할 수 있습니다.
        // println은 반환값이 없는 메서드이므로 return을 붙이지 않습니다.
        Notice shortNotice = () -> System.out.println("학습 시작");
        blockNotice.show(); // 학습 시작
        shortNotice.show(); // 학습 시작

        // [매개변수 둘] 타입은 생략할 수 있지만 매개변수 괄호는 생략할 수 없습니다.
        Calculator singleExpression = (a, b) -> a + b;
        System.out.println("표현식 본문: " + singleExpression.add(2, 3)); // 5

        // [여러 문장] 지역 변수 선언과 반환처럼 문장이 여러 개면 중괄호가 필요합니다.
        // int 값을 반환하는 블록 본문이므로 return도 적어야 합니다.
        Calculator multipleStatements = (a, b) -> {
            int result = a + b;
            return result;
        };
        System.out.println("여러 문장 본문: " + multipleStatements.add(2, 3)); // 5

        // 아래 코드는 잘못된 예입니다. 한 줄씩 주석을 해제하여 오류를 읽고 되돌리세요.
        // Greeting wrongParentheses = String name -> "안녕하세요, " + name;
        // 이유: 매개변수 타입을 적었다면 괄호를 생략할 수 없습니다.

        // Notice missingParentheses = -> System.out.println("학습 시작");
        // 이유: 매개변수가 없을 때에도 ()가 필요합니다.

        // Calculator twoParameters = a, b -> a + b;
        // 이유: 매개변수가 두 개이므로 (a, b)로 작성해야 합니다.

        // Calculator mixedTypes = (int a, b) -> a + b;
        // 이유: 매개변수 타입은 모두 적거나 모두 생략해야 합니다.

        // Calculator missingBraces = (a, b) -> return a + b;
        // 이유: return을 사용하는 본문에는 중괄호가 필요합니다.

        // Calculator missingReturn = (a, b) -> { int result = a + b; };
        // 이유: int를 반환하는 블록 본문인데 결과를 return하지 않았습니다.
    }
}
