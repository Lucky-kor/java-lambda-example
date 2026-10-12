// 람다에서 바깥 지역 변수를 사용하는 예제를 모은 패키지입니다.
package capture;

import java.util.function.Consumer; // 점수를 받아 출력하고 반환값은 없는 계약입니다.

/** 바깥 지역 변수를 읽는 캡처와 effectively final 규칙을 확인합니다. */
public class LocalVariableCaptureExample {
    // prefix와 label은 이 메서드 안에서 선언한 지역 변수입니다.
    public static void main(String[] args) {
        // final을 적지 않아도 초기화 뒤 다시 대입하지 않으면 effectively final입니다.
        String prefix = "결과: ";
        // score는 람다 매개변수이고, prefix는 람다 바깥 지역 변수입니다.
        Consumer<Integer> scorePrinter = score -> {
            // 바깥 변수 prefix를 사용하는 것을 지역 변수 캡처라고 합니다.
            System.out.println(prefix + score);
        };
        // prefix = "수정됨: "; // 재대입하면 위 캡처가 컴파일 오류가 됩니다.
        // 이 재대입을 람다 선언 앞으로 옮겨도 effectively final 조건을 만족하지 못합니다.
        scorePrinter.accept(95); // 결과: 95
        scorePrinter.accept(80); // 결과: 80 — 매개변수는 호출마다 다른 값을 받을 수 있습니다.

        // final을 명시한 지역 변수도 람다에서 사용할 수 있습니다.
        final String label = "확정 점수: ";
        Consumer<Integer> finalScorePrinter = score -> System.out.println(label + score);
        finalScorePrinter.accept(100); // 확정 점수: 100
        // label = "변경: "; // final 변수 자체에 재대입할 수 없어 컴파일 오류입니다.
    }
}
