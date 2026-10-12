// 생성자 참조 예제에서 사용하는 보조 클래스의 패키지입니다.
package methodreference;

/** 생성자 참조로 전달한 이름이 실제 객체에 저장되는지 확인합니다. */
public class Member {
    private final String name; // 생성할 때 받은 이름을 보관합니다.

    // String 하나를 받아 Member 객체를 초기화하는 생성자입니다.
    public Member(String name) {
        this.name = name; // 왼쪽은 객체의 필드, 오른쪽은 생성자의 매개변수입니다.
    }

    // 객체 주소처럼 보이는 기본 출력 대신 저장된 이름을 확인하기 위한 메서드입니다.
    public String getName() {
        return name; // 생성자로 전달했던 이름을 반환합니다.
    }
}
