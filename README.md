# 자바 람다 표현식 학습 가이드

이 프로젝트는 일반 메서드에서 시작해 **람다로 바꾸는 과정과 생략 규칙**을 익힌 다음, **함수형 인터페이스, 지역 변수 캡처, 메서드 참조와 종합 실습**으로 학습을 이어갑니다.

1~3단계는 함수형 인터페이스의 정의만 알고 진행할 수 있습니다. 4단계부터 직접 만든 인터페이스와 자바가 제공하는 인터페이스를 비교합니다. 스트림과 수집 연산은 이 프로젝트의 예제에 포함하지 않습니다.

## 학습 목표

- 같은 덧셈을 구현 클래스, 익명 클래스, 람다로 표현할 수 있다.
- 람다의 매개변수와 본문이 기존 메서드의 어느 부분인지 찾을 수 있다.
- 매개변수 타입과 괄호를 언제 생략할 수 있는지 설명할 수 있다.
- 본문의 중괄호와 `return`을 언제 함께 생략하는지 설명할 수 있다.
- 값을 반환하는 람다와 반환값이 없는 람다를 구분할 수 있다.
- 람다의 대상 타입과 표준 함수형 인터페이스의 입력·출력 계약을 설명할 수 있다.
- 바깥 지역 변수를 캡처할 때의 재대입 제한을 설명할 수 있다.
- 네 가지 메서드·생성자 참조 형태를 람다로 풀어 쓸 수 있다.
- 익명 클래스를 람다로 바꾸고, 메서드 참조로 바꿀 수 있는 조건을 판단할 수 있다.

## 파일 구성과 학습 순서

`src`를 소스 루트로 두고, 그 아래에서 학습 개념별로 폴더를 나눕니다. 폴더명과 Java의 패키지 이름을 맞춥니다.

```text
java-lambda-example/
├── README.md
└── src/
    ├── method/                         1. 일반 메서드
    │   └── MethodExample.java
    ├── conversion/                     2. 람다로 변환
    │   ├── Calculator.java
    │   ├── CalculatorImpl.java
    │   └── LambdaConversionExample.java
    ├── syntax/                         3. 문법 생략
    │   ├── Greeting.java
    │   ├── Notice.java
    │   └── LambdaSyntaxExample.java
    ├── functionalinterface/            4. 함수형 인터페이스
    │   ├── IntAddition.java
    │   ├── MessageAction.java
    │   ├── LambdaTargetTypeExample.java
    │   ├── CustomFunctionalInterfaceExample.java
    │   └── StandardFunctionalInterfaceExample.java
    ├── capture/                        5. 지역 변수 캡처
    │   └── LocalVariableCaptureExample.java
    ├── methodreference/                6. 메서드와 생성자 참조
    │   ├── Calculator.java
    │   ├── Member.java
    │   └── MethodReferenceExample.java
    └── lambdapractice/                 7. 종합 변환 실습
        └── LambdaRefactoringExample.java
```

파일 이름은 학습 주제를 나타내고, 폴더 이름은 소문자로 작성합니다. 학습 순서는 아래 표에서 안내합니다. `syntax`에서는 앞 단계의 `conversion.Calculator`를 가져와 같은 덧셈 계약으로 문법을 비교합니다. 각 파일 맨 위의 `package`는 소속 폴더를, `import`는 다른 폴더에서 가져올 타입을 나타냅니다.

| 순서 | 읽을 파일 | 실행할 클래스 |
| --- | --- | --- |
| 1 | [MethodExample.java](src/method/MethodExample.java) | `MethodExample` |
| 2 | [Calculator.java](src/conversion/Calculator.java) → [CalculatorImpl.java](src/conversion/CalculatorImpl.java) → [LambdaConversionExample.java](src/conversion/LambdaConversionExample.java) | `LambdaConversionExample` |
| 3 | [Greeting.java](src/syntax/Greeting.java), [Notice.java](src/syntax/Notice.java) → [LambdaSyntaxExample.java](src/syntax/LambdaSyntaxExample.java) | `LambdaSyntaxExample` |
| 4-1 | [LambdaTargetTypeExample.java](src/functionalinterface/LambdaTargetTypeExample.java) | `LambdaTargetTypeExample` |
| 4-2 | [IntAddition.java](src/functionalinterface/IntAddition.java), [MessageAction.java](src/functionalinterface/MessageAction.java) → [CustomFunctionalInterfaceExample.java](src/functionalinterface/CustomFunctionalInterfaceExample.java) | `CustomFunctionalInterfaceExample` |
| 4-3 | [StandardFunctionalInterfaceExample.java](src/functionalinterface/StandardFunctionalInterfaceExample.java) | `StandardFunctionalInterfaceExample` |
| 5 | [LocalVariableCaptureExample.java](src/capture/LocalVariableCaptureExample.java) | `LocalVariableCaptureExample` |
| 6 | [Calculator.java](src/methodreference/Calculator.java), [Member.java](src/methodreference/Member.java) → [MethodReferenceExample.java](src/methodreference/MethodReferenceExample.java) | `MethodReferenceExample` |
| 7 | [LambdaRefactoringExample.java](src/lambdapractice/LambdaRefactoringExample.java) | `LambdaRefactoringExample` |

`Calculator`, `CalculatorImpl`, `Greeting`, `Notice`, `IntAddition`, `MessageAction`, `Member`는 보조 파일이며 자체 `main` 메서드는 없습니다.

## 실행 방법

### IntelliJ IDEA

프로젝트 JDK를 설정하고 `src`가 Sources Root인지 확인하세요. `src` 아래에서 학습할 개념의 폴더를 열고, 해당 파일의 `main` 메서드 옆 실행 아이콘을 눌러 실행합니다. 각 개념 폴더 자체를 Sources Root로 지정하지 않습니다.

### 터미널

JDK 8 이상이 필요합니다. 프로젝트 폴더에서 다음 명령을 실행하세요.

```sh
mkdir -p out
javac -encoding UTF-8 -d out src/*/*.java
java -cp out method.MethodExample
java -cp out conversion.LambdaConversionExample
java -cp out syntax.LambdaSyntaxExample
java -cp out functionalinterface.LambdaTargetTypeExample
java -cp out functionalinterface.CustomFunctionalInterfaceExample
java -cp out functionalinterface.StandardFunctionalInterfaceExample
java -cp out capture.LocalVariableCaptureExample
java -cp out methodreference.MethodReferenceExample
java -cp out lambdapractice.LambdaRefactoringExample
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

## 4. 함수형 인터페이스: 대상 타입부터 표준 계약까지

### 4-1. 람다의 대상 타입

학습 파일: [LambdaTargetTypeExample.java](src/functionalinterface/LambdaTargetTypeExample.java)

익명 클래스에 `add`를 작성해도 변수를 `Object` 타입으로 선언하면 `object.add(1, 2)`를 호출할 수 없습니다. 컴파일러는 변수에 선언된 타입에서 호출할 메서드를 찾기 때문입니다. 반면 `IntAddition`에는 `add`가 선언되어 있으므로 `addition.add(1, 2)`를 호출할 수 있습니다.

`Object invalid = (left, right) -> left + right;`도 컴파일되지 않습니다. 람다에는 함수형 인터페이스라는 대상 타입이 필요합니다. 대상 타입이 매개변수와 반환 타입을 알려 줍니다.

예상 출력:

```text
Object 메서드 호출: true
함수형 인터페이스 메서드 호출: 3
```

직접 해보기: 두 오류 예제를 한 줄씩 주석 해제하고 메시지를 읽은 뒤 되돌리세요. 익명 클래스 안에 메서드가 존재하는 것과 변수 타입으로 호출할 수 있는 것이 왜 다른지 설명하세요.

### 4-2. 직접 만든 계약

학습 파일: [CustomFunctionalInterfaceExample.java](src/functionalinterface/CustomFunctionalInterfaceExample.java)

| 계약 | 입력 | 반환값 | 호출 |
| --- | --- | --- | --- |
| `IntAddition` | int 두 개 | int | `add(1, 2)` |
| `MessageAction` | 없음 | 없음(void) | `execute()` |

`@FunctionalInterface`가 붙은 두 파일을 먼저 읽고 람다의 형태를 예상하세요. 실행 결과는 `덧셈: 3`, `안녕하세요`입니다. 람다를 선언하는 순간에 본문이 실행되는 것이 아니라, 계약의 메서드를 호출할 때 실행됩니다.

직접 해보기: 숫자와 인사말을 바꾸세요. 입력이 없는 람다에도 `()`를 적어야 하는 이유를 3단계와 연결해 설명하세요.

### 4-3. 자바가 제공하는 함수형 인터페이스

학습 파일: [StandardFunctionalInterfaceExample.java](src/functionalinterface/StandardFunctionalInterfaceExample.java)

표준 계약은 `java.util.function` 패키지에 있습니다. `T`, `U`, `R`은 실제 사용할 타입을 넣는 자리입니다. 제네릭 타입 인수에는 원시 타입 `int` 대신 `Integer`를 사용합니다. int와 Integer 사이의 자동 변환을 박싱·언박싱이라고 합니다.

| 인터페이스 | 입력 | 출력 | 호출 메서드 |
| --- | --- | --- | --- |
| `Consumer<T>` | T 하나 | void | `accept` |
| `Supplier<T>` | 없음 | T | `get` |
| `Function<T, R>` | T 하나 | R | `apply` |
| `BiFunction<T, U, R>` | T, U | R | `apply` |
| `UnaryOperator<T>` | T 하나 | T | `apply` |
| `Predicate<T>` | T 하나 | boolean | `test` |

`UnaryOperator<String>`은 `Function<String, String>`처럼 입력과 결과가 같은 타입인 변환입니다. 조건을 판단한다는 의도는 `Function<String, Boolean>`보다 `Predicate<String>`으로 분명하게 표현할 수 있습니다.

예상 출력:

```text
출력: 람다 학습
공급된 값: 안녕하세요
문자열 길이: 5
길이가 2보다 큰가: true
길이가 5보다 큰가: false
UnaryOperator: [Java]
Function: [Java]
Hello 조건 판단: true
Hi 조건 판단: false
```

직접 해보기: `Predicate`에 빈 문자열과 길이가 정확히 2인 문자열을 전달하세요. `BiFunction`의 기준값을 바꾸고 `>`와 `>=`의 결과를 비교하세요. 각 변수를 선언하기 전에 필요한 입력과 출력부터 적어 보세요.

## 5. 지역 변수 캡처

학습 파일: [LocalVariableCaptureExample.java](src/capture/LocalVariableCaptureExample.java)

람다 안에서 바깥 지역 변수를 사용하는 것을 캡처라고 합니다. `score`는 람다의 매개변수이고 `prefix`는 `main`의 지역 변수입니다.

```java
String prefix = "결과: "; // 초기화 후 재대입하지 않는 지역 변수입니다.
Consumer<Integer> printer = score -> System.out.println(prefix + score); // prefix를 캡처합니다.
printer.accept(95); // 호출할 때 score에 95가 전달됩니다.
```

캡처되는 지역 변수는 `final`이거나 **effectively final**, 즉 초기화 이후 다시 대입하지 않는 변수여야 합니다. `prefix = "수정됨: ";`를 추가하면 컴파일 오류입니다. 이 대입을 람다 선언 앞으로 옮겨도 해결되지 않습니다. 처음 초기화하는 문자열 자체를 바꾸는 것은 가능합니다.

예상 출력:

```text
결과: 95
결과: 80
확정 점수: 100
```

직접 해보기: `prefix` 초기값을 바꾸고 실행하세요. 이후 재대입 주석을 해제해 오류를 확인하고 되돌리세요. `accept(95)`와 `accept(80)`처럼 입력값을 바꾸는 것은 왜 재대입 제한과 다른지 설명하세요.

## 6. 메서드 참조와 생성자 참조

학습 파일: [MethodReferenceExample.java](src/methodreference/MethodReferenceExample.java)

람다가 기존 메서드나 생성자를 호출하는 역할만 할 때, 대상 함수형 인터페이스의 입력·출력과 맞으면 `::` 형태로 표현할 수 있습니다. 메서드 참조를 선언해도 즉시 호출되지는 않습니다. 예제에서는 `apply`가 실행하는 지점입니다.

| 형태 | 람다 | 참조 | 입력이 전달되는 곳 |
| --- | --- | --- | --- |
| 정적 메서드 | `(a, b) -> Math.max(a, b)` | `Math::max` | 두 입력 모두 메서드 인수 |
| 특정 객체의 메서드 | `(a, b) -> calculator.add(a, b)` | `calculator::add` | 미리 만든 객체에서 호출, 두 입력은 인수 |
| 입력 객체의 메서드 | `(a, b) -> a.equals(b)` | `String::equals` | 첫 입력이 호출 대상, 두 번째는 인수 |
| 생성자 | `name -> new Member(name)` | `Member::new` | 입력은 생성자 인수, 결과는 새 객체 |

`String::equals`는 클래스 이름으로 시작하지만 정적 메서드 참조가 아닙니다. `equals`는 인스턴스 메서드이며, 첫 번째 입력 문자열에서 호출합니다. **표기만 외우지 말고 람다로 풀어 써서 확인하세요.**

예상 출력:

```text
정적 메서드 람다: 7
정적 메서드 참조: 7
특정 객체 람다: 3
특정 객체 참조: 3
입력 객체 람다: true
입력 객체 참조: true
다른 내용 비교: false
생성자 람다: 홍길동
생성자 참조: 홍길동
```

생성자 예제는 같은 이름을 가진 별도 객체 두 개를 만듭니다. `Member.getName()`으로 저장된 값을 확인하므로 기본 객체 출력 문자열에 의존하지 않습니다.

직접 해보기: `Math.max`를 `Math.min`으로 람다와 참조 양쪽에서 변경하세요. 문자열 대소문자를 바꿔 비교하세요. 생성자로 전달할 이름을 바꾸고 저장 결과를 확인하세요.

## 7. 익명 클래스 → 람다 → 메서드 참조 종합 연습

학습 파일: [LambdaRefactoringExample.java](src/lambdapractice/LambdaRefactoringExample.java)

`Predicate<String>`의 `test`를 익명 클래스로 구현한 다음 아래 람다로 줄입니다.

```java
Predicate<String> startsWithA = text -> text.startsWith("A"); // 입력은 문자열 하나이며 접두사는 고정입니다.
```

이를 `Predicate<String> startsWithA = String::startsWith;`로 바꾸면 오류입니다. 입력 문자열은 호출 대상에 사용되고, `startsWith`에 필요한 접두사 인수가 남아 있기 때문입니다. 메서드 참조는 고정된 `"A"`를 대신 기억하거나 채워 주지 않습니다.

접두사도 입력받도록 **계약을 바꾼 경우**에는 다음이 가능합니다.

```java
BiPredicate<String, String> lambda = (text, prefix) -> text.startsWith(prefix); // 입력 두 개입니다.
BiPredicate<String, String> reference = String::startsWith; // 첫 입력은 호출 대상, 두 번째는 인수입니다.
```

`BiPredicate`는 두 입력을 받아 boolean을 반환하며 `test`로 호출합니다. 기존 `Predicate`와는 입력 개수가 다르므로 단순히 표기만 줄인 것이 아닙니다. 접두사 A를 고정하려면 기존 람다를 사용하는 것이 명확합니다.

예상 출력:

```text
익명 클래스 Apple: true
람다 Apple: true
익명 클래스 Banana: false
람다 Banana: false
두 입력 람다: true
두 입력 메서드 참조: true
접두사 변경: true
```

직접 해보기: `"apple"`, 빈 문자열, 다른 접두사를 전달하세요. 두 입력 버전을 다시 익명 클래스로 작성해 보세요. `Predicate` 버전과 `BiPredicate` 버전의 호출 인수 개수를 설명하세요.

## 파일명 정리 내역

| 이전 이름 | 현재 이름 또는 분리된 예제 |
| --- | --- |
| `funtionalinterface` | `functionalinterface` — 패키지 철자 수정 |
| `ExampleV1` | `LambdaTargetTypeExample` |
| `ExampleV2` | `CustomFunctionalInterfaceExample`, `StandardFunctionalInterfaceExample` |
| `ExampleFunction` | `IntAddition` |
| `MyFunctionalInterface` | `MessageAction` |
| `CaptureExample` | `LocalVariableCaptureExample` |
| `MethodReferenceExampleV1` | `MethodReferenceExample` |
| `LambdaRefactorExample` | `LambdaRefactoringExample` |

`lambdapractice`는 Java 패키지 관례에 맞춰 소문자로 유지합니다. 실행 클래스 이름이 바뀐 파일은 IntelliJ에서 새 파일의 `main` 옆 실행 버튼으로 실행하세요.

## 이후 학습 코드를 추가하는 방법

- 현재 개념의 연습은 해당 폴더에 주제가 드러나는 `…Example.java` 파일로 추가합니다.
- 새로운 개념을 배우면 `src` 아래에 소문자 폴더를 하나 만들고, 파일의 `package`를 폴더명과 맞춥니다.
- 예제에서만 필요한 인터페이스나 구현 클래스는 그 개념의 폴더에 함께 둡니다.
- 실행할 예제에는 `main`을 두고 설명 주석을 작성합니다. README의 학습 순서 표, 파일 링크, 실행 명령과 예상 출력도 함께 갱신합니다.
- 현재 컴파일 명령의 `src/*/*.java`는 `src` 바로 아래 개념 폴더의 Java 파일을 함께 컴파일합니다. 더 깊은 하위 폴더를 추가하면 컴파일 경로도 조정합니다.

추가 학습 폴더는 해당 수업을 진행할 때 만듭니다. 현재 구성은 위 1~7단계를 다룹니다.

## 최종 점검

1. 함수형 인터페이스란 무엇인가요?
2. 람다에서 메서드 이름을 생략해도 어떤 메서드를 구현하는지 알 수 있는 이유는 무엇인가요?
3. 매개변수 타입을 생략할 수 있는 이유는 무엇인가요?
4. 매개변수 괄호를 생략할 수 있는 조건 두 가지는 무엇인가요?
5. 매개변수가 없을 때도 `()`를 적어야 하나요?
6. `return a + b;`를 표현식 본문으로 줄일 때 무엇을 함께 없애나요?
7. 여러 문장이 있는 본문의 중괄호를 없앨 수 있나요?
8. `void` 메서드를 구현하는 출력 람다에 반환값이 필요한가요?
9. Object 타입의 변수로 익명 클래스에 새로 선언한 add를 호출할 수 없는 이유는 무엇인가요?
10. Consumer, Supplier, Function, Predicate의 입력·출력과 호출 메서드는 어떻게 다른가요?
11. UnaryOperator와 Function은 어떤 관계인가요?
12. 캡처하는 지역 변수에 다시 대입하면 왜 컴파일되지 않나요?
13. calculator::add와 String::equals에서 첫 입력의 역할은 어떻게 다른가요?
14. Member::new는 언제 객체를 생성하나요?
15. A를 고정한 Predicate를 String::startsWith로 바로 바꿀 수 없는 이유는 무엇인가요?

각 코드를 실행하기 전에 결과를 예상하고, 실행한 뒤 문법을 하나씩 바꿔 보세요. 짧게 쓰는 것보다 **입력·출력 계약과 코드가 실행되는 지점을 설명하는 것**이 학습의 목표입니다.
