// 직접 정의하는 함수형 인터페이스를 모아 둔 패키지입니다.
package functionalinterface;

/** 입력과 반환값 없이 메시지를 출력하는 동작을 표현합니다. */
@FunctionalInterface // execute 하나를 구현하면 되는 계약입니다.
public interface MessageAction {
    // void는 호출한 곳으로 결과값을 돌려주지 않는다는 뜻입니다.
    void execute();
}
