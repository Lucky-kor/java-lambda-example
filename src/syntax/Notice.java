// syntax 폴더의 예제를 묶는 패키지 선언입니다.
package syntax;

/** 매개변수와 반환값이 없는 람다를 연습하기 위한 계약입니다. */
@FunctionalInterface
public interface Notice {
    // 매개변수가 없으며, void이므로 결과값을 반환하지 않습니다.
    void show();
}
