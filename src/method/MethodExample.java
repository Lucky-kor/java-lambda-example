// method 폴더의 예제를 묶는 패키지 선언입니다.
package method;

/** 1단계: 기존 일반 메서드의 선언과 호출을 복습합니다. */
public class MethodExample {
    public static void main(String[] args) {
        // sum은 객체를 통해 호출하는 메서드이므로 먼저 객체를 만듭니다.
        MethodExample example = new MethodExample();

        // 1과 2를 메서드에 전달하고, 반환된 덧셈 결과를 변수에 저장합니다.
        int result = example.sum(1, 2);
        System.out.println("일반 메서드: " + result); // 예상 결과: 일반 메서드: 3
    }

    // int num1, int num2는 입력을 받는 매개변수입니다.
    // 메서드 이름 앞의 int는 결과로 정수를 반환한다는 뜻입니다.
    public int sum(int num1, int num2) {
        return num1 + num2;
    }
}
