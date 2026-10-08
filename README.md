# 자바 람다 표현식 학습 가이드

이 프로젝트는 **일반 클래스와 익명 클래스로 작성한 코드를 람다 표현식으로 바꾸는 과정**, 그리고 **람다에서 문법을 생략할 수 있는 조건**을 연습합니다.

함수형 인터페이스는 “추상 메서드가 하나인 인터페이스”라는 정의를 바탕으로 사용합니다. 예제에 필요한 인터페이스만 직접 정의합니다. 표준 함수형 인터페이스, 메서드 참조, 스트림, 변수 캡처, `this`는 이번 학습 범위에 포함하지 않습니다.

## 학습 목표

- 같은 덧셈을 구현 클래스, 익명 클래스, 람다로 표현할 수 있다.
- 람다의 매개변수와 본문이 기존 메서드의 어느 부분인지 찾을 수 있다.
- 매개변수 타입과 괄호를 언제 생략할 수 있는지 설명할 수 있다.
- 본문의 중괄호와 `return`을 언제 함께 생략하는지 설명할 수 있다.
- 값을 반환하는 람다와 반환값이 없는 람다를 구분할 수 있다.

## 파일 구성과 학습 순서

`src`를 소스 루트로 두고, 그 아래에서 학습 개념별로 폴더를 나눕니다. 폴더명과 Java의 패키지 이름을 맞춥니다.

```text
java-lambda-example/
├── README.md
└── src/
    ├── method/                         1. 일반 메서드 복습
    │   └── MethodExample.java
    ├── conversion/                     2. 일반 구현에서 람다로 변환
    │   ├── Calculator.java
    │   ├── CalculatorImpl.java
    │   └── LambdaConversionExample.java
    └── syntax/                         3. 람다 문법 생략 규칙
        ├── Greeting.java
        ├── Notice.java
        └── LambdaSyntaxExample.java
```

파일 이름은 학습 주제를 나타내고, 폴더 이름은 소문자로 작성합니다. 학습 순서는 아래 표에서 안내합니다. `syntax`에서는 앞 단계의 `conversion.Calculator`를 가져와 같은 덧셈 계약으로 문법을 비교합니다. 각 파일 맨 위의 `package`는 소속 폴더를, `import`는 다른 폴더에서 가져올 타입을 나타냅니다.

| 순서 | 읽을 파일 | 실행할 클래스 |
| --- | --- | --- |
| 1 | [MethodExample.java](src/method/MethodExample.java) | `MethodExample` |
| 2 | [Calculator.java](src/conversion/Calculator.java) → [CalculatorImpl.java](src/conversion/CalculatorImpl.java) → [LambdaConversionExample.java](src/conversion/LambdaConversionExample.java) | `LambdaConversionExample` |
| 3 | [Greeting.java](src/syntax/Greeting.java), [Notice.java](src/syntax/Notice.java) → [LambdaSyntaxExample.java](src/syntax/LambdaSyntaxExample.java) | `LambdaSyntaxExample` |

`Calculator`, `CalculatorImpl`, `Greeting`, `Notice`는 예제 실행을 돕는 파일이며 자체 `main` 메서드는 없습니다.

## 실행 방법

### IntelliJ IDEA

프로젝트 JDK를 설정하고 `src`가 Sources Root인지 확인하세요. `src` 아래에서 학습할 개념의 폴더를 열고, 해당 파일의 `main` 메서드 옆 실행 아이콘을 눌러 실행합니다. `method`, `conversion`, `syntax` 폴더 자체를 Sources Root로 지정하지 않습니다.

### 터미널

JDK 8 이상이 필요합니다. 프로젝트 폴더에서 다음 명령을 실행하세요.

```sh
mkdir -p out
javac -encoding UTF-8 -d out src/method/*.java src/conversion/*.java src/syntax/*.java
java -cp out method.MethodExample
java -cp out conversion.LambdaConversionExample
java -cp out syntax.LambdaSyntaxExample
```

코드를 수정하면 `javac` 명령부터 다시 실행합니다. 오류 확인을 위해 주석을 해제한 코드는 다시 주석 처리한 뒤 다음 예제로 넘어가세요.

## 1. 일반 메서드 복습

`MethodExample`은 객체를 만들고 `sum(1, 2)`를 호출합니다.

```java
public int sum(int num1, int num2) {
    return num1 + num2;
}
```

- `num1`, `num2`는 입력을 받는 매개변수입니다.
- 메서드 이름 앞의 `int`는 반환 타입입니다.
- 중괄호 안은 실행할 본문입니다.
- `return`은 계산 결과를 호출한 곳에 돌려줍니다.

예상 출력:

```text
일반 메서드: 3
```

직접 해보기: `sum(1, 2)`의 입력을 `sum(10, 20)`으로 바꿔 결과를 예상하세요.

## 2. 같은 코드를 람다로 바꾸기

일반 메서드를 아무 자리에서나 람다로 바꿀 수 있는 것은 아닙니다. 이번에는 `Calculator`라는 함수형 인터페이스의 구현을 람다로 표현합니다.

```java
@FunctionalInterface
public interface Calculator {
    int add(int a, int b);
}
```

`Calculator`에는 구현할 추상 메서드가 `add` 하나 있습니다. `@FunctionalInterface`는 이 조건을 컴파일러가 확인하도록 하며, 어노테이션 자체가 함수형 인터페이스를 만드는 것은 아닙니다.

### ① 일반 구현 클래스

`CalculatorImpl`은 `implements Calculator`로 계약을 구현하고, `add`의 본문에 덧셈을 작성합니다.

```java
Calculator classCalculator = new CalculatorImpl();
```

### ② 익명 클래스

별도 클래스 이름 없이 객체를 만드는 자리에서 구현을 작성합니다.

```java
Calculator anonymousCalculator = new Calculator() {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
};
```

### ③ 타입을 적은 람다

`Calculator`의 추상 메서드가 하나이므로 구현할 메서드의 이름과 반환 타입을 알 수 있습니다. 생성 구문과 메서드 선언을 줄이고 매개변수와 본문을 화살표로 연결합니다.

```java
Calculator explicitLambda = (int a, int b) -> {
    return a + b;
};
```

화살표 왼쪽은 **매개변수**, 오른쪽은 **실행할 본문**입니다.

### ④ 매개변수 타입 생략

`add(int a, int b)`에 타입이 정해져 있으므로 `int`를 생략할 수 있습니다.

```java
Calculator inferredLambda = (a, b) -> {
    return a + b;
};
```

### ⑤ 반환할 표현식만 남기기

본문에 `return a + b;`만 있으므로 중괄호, `return`, 그 문장의 세미콜론을 함께 생략합니다.

```java
Calculator shortLambda = (a, b) -> a + b;
```

끝에 남은 세미콜론은 **변수 선언을 끝내는 세미콜론**입니다. 이것까지 없애는 것은 아닙니다.

모든 방식은 `변수.add(1, 2)`로 호출합니다. 람다를 적는 부분과 메서드를 호출하는 부분을 구분하세요.

예상 출력:

```text
구현 클래스: 3
익명 클래스: 3
타입을 적은 람다: 3
타입을 생략한 람다: 3
본문을 줄인 람다: 3
```

직접 해보기:

- 다섯 구현 모두 `add(10, 20)`으로 호출해 결과를 비교하세요.
- 가장 짧은 람다에 타입, 중괄호, `return`을 다시 넣어 보세요.
- 각 단계에서 생략된 부분을 익명 클래스 코드에서 찾아보세요.

## 3. 문법 생략 규칙

### 매개변수 타입과 괄호

| 조건 | 가능한 표현 | 기억할 규칙 |
| --- | --- | --- |
| 매개변수 없음 | `() -> ...` | 빈 괄호는 필요 |
| 하나이며 타입 명시 | `(String name) -> ...` | 타입을 적으면 괄호 필요 |
| 하나이며 타입 생략 | `(name) -> ...` 또는 `name -> ...` | 괄호도 생략 가능 |
| 둘이며 타입 명시 | `(int a, int b) -> ...` | 모든 매개변수의 타입 명시 |
| 둘이며 타입 생략 | `(a, b) -> ...` | 타입은 생략해도 괄호 필요 |

이 예제처럼 매개변수 타입을 직접 적는 방식에서는 타입을 전부 적거나 전부 생략해야 합니다. `(int a, b)`처럼 섞으면 안 됩니다.

`Greeting`은 `String greet(String name)`을 선언합니다. 다음 세 표현은 같은 인사말을 반환합니다.

```java
Greeting first = (String name) -> "안녕하세요, " + name;
Greeting second = (name) -> "안녕하세요, " + name;
Greeting third = name -> "안녕하세요, " + name;
```

### 중괄호와 return

**값을 반환하며 본문이 return 문 하나인 경우**에는 반환할 표현식만 남길 수 있습니다.

```java
Calculator first = (a, b) -> { return a + b; };
Calculator second = (a, b) -> a + b;
```

`(a, b) -> return a + b`처럼 중괄호만 없애고 `return`을 남길 수는 없습니다.

**반환값 없이 메서드 호출 한 문장을 실행하는 경우**에는 다음과 같이 줄일 수 있습니다. `Notice.show`의 반환 타입은 `void`입니다.

```java
Notice first = () -> { System.out.println("학습 시작"); };
Notice second = () -> System.out.println("학습 시작");
```

`println`은 값을 반환하지 않으므로 `return`을 붙이지 않습니다. “한 줄이면 무조건 생략 가능”이라고 외우지 말고, 어떤 문장인지 확인하세요. 화면에서 한 줄로 작성했는지는 기준이 아닙니다.

**여러 문장이 필요한 경우**에는 중괄호를 유지합니다. 값을 반환하는 블록 본문이므로 `return`도 필요합니다.

```java
Calculator multipleStatements = (a, b) -> {
    int result = a + b;
    return result;
};
```

여기서 `int result = a + b;`라는 변수 선언을 유지하면서 중괄호만 없앨 수는 없습니다. 중간 변수를 없애고 본문 자체를 `a + b`로 바꾸면 짧은 표현식 형태가 됩니다.

### 실행 결과와 연습

`LambdaSyntaxExample`의 예상 출력:

```text
안녕하세요, 민수
안녕하세요, 민수
안녕하세요, 민수
학습 시작
학습 시작
표현식 본문: 5
여러 문장 본문: 5
```

직접 해보기:

- `Greeting`의 세 구현에 전달하는 이름을 바꾸고 결과를 비교하세요.
- `Notice`의 블록 본문에 출력문을 하나 더 추가하고 중괄호가 필요한 이유를 설명하세요.
- 파일 아래의 잘못된 예제를 하나씩 주석 해제하고 오류를 확인한 뒤 되돌리세요.
- 짧은 람다를 긴 형태로, 긴 람다를 짧은 형태로 바꾸는 연습을 하세요.

## 이후 학습 코드를 추가하는 방법

- 현재 개념의 연습은 해당 폴더에 주제가 드러나는 `…Example.java` 파일로 추가합니다.
- 새로운 개념을 배우면 `src` 아래에 소문자 폴더를 하나 만들고, 파일의 `package`를 폴더명과 맞춥니다.
- 예제에서만 필요한 인터페이스나 구현 클래스는 그 개념의 폴더에 함께 둡니다.
- 실행할 예제에는 `main`을 두고 설명 주석을 작성합니다. README의 학습 순서 표, 파일 링크, 실행 명령과 예상 출력도 함께 갱신합니다.
- 새 폴더를 만들면 터미널의 컴파일 명령에도 해당 폴더의 Java 파일 경로를 추가합니다.

추가 학습 폴더는 해당 수업을 진행할 때 만듭니다. 현재 구성은 위 세 개념만 다룹니다.

## 최종 점검

1. 함수형 인터페이스란 무엇인가요?
2. 람다에서 메서드 이름을 생략해도 어떤 메서드를 구현하는지 알 수 있는 이유는 무엇인가요?
3. 매개변수 타입을 생략할 수 있는 이유는 무엇인가요?
4. 매개변수 괄호를 생략할 수 있는 조건 두 가지는 무엇인가요?
5. 매개변수가 없을 때도 `()`를 적어야 하나요?
6. `return a + b;`를 표현식 본문으로 줄일 때 무엇을 함께 없애나요?
7. 여러 문장이 있는 본문의 중괄호를 없앨 수 있나요?
8. `void` 메서드를 구현하는 출력 람다에 반환값이 필요한가요?

각 코드를 실행하기 전에 결과를 예상하고, 실행한 뒤 문법을 하나씩 바꿔 보세요. 짧게 쓰는 것보다 **왜 생략할 수 있는지 설명하는 것**이 이번 학습의 목표입니다.
