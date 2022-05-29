package reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reflection.dto.Animal;
import reflection.dto.Bird;

public class ReflectionBasic2 {

    private static List<String> getMethodNames(Method[] methods) {
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods)
            methodNames.add(method.getName());
        return methodNames;
    }

    @Test
    @DisplayName("생성자 개수 확인")
    public void givenClass_whenGetsAllConstructors_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("reflection.dto.Bird");
        Constructor<?>[] constructors = birdClass.getConstructors();

        assertEquals(3, constructors.length);
    }

    @Test
    @DisplayName("생성자 정보 가져오기")
    public void givenClass_whenGetsEachConstructorByParamTypes_thenCorrect()
        throws ClassNotFoundException, NoSuchMethodException {
        Class<?> birdClass = Class.forName("reflection.dto.Bird");

        Constructor<?> cons1 = birdClass.getConstructor();
        Constructor<?> cons2 = birdClass.getConstructor(String.class);
        Constructor<?> cons3 = birdClass.getConstructor(String.class, boolean.class);

        assertEquals("public reflection.dto.Bird()", cons1.toString());
        assertEquals("public reflection.dto.Bird(java.lang.String)", cons2.toString());
        assertEquals("public reflection.dto.Bird(java.lang.String,boolean)", cons3.toString());

    }

    /**
     * Java 리플렉션을 사용하면 모든 클래스의 생성자를 검사 하고 런타임에 클래스 개체를 만들 수 있다.
     * 이것은 java.lang.reflect.Constructor 클래스에 의해 가능
     *
     * Constructor 클래스의 newInstance 메소드를 호출하고 선언된 순서대로 필요한 매개변수를
     * 전달하여 클래스 객체를 인스턴스화한다. 그런 다음 결과를 필요한 유형으로 캐스팅한다.
     */
    @Test
    @DisplayName("생성자를 정보를 통해 클래스 생성하기")
    public void givenClass_whenInstantiatesObjectsAtRuntime_thenCorrect()
        throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> birdClass = Class.forName("reflection.dto.Bird");
        Constructor<?> cons1 = birdClass.getConstructor();
        Constructor<?> cons2 = birdClass.getConstructor(String.class);
        Constructor<?> cons3 = birdClass.getConstructor(String.class,
            boolean.class);

        Animal bird0 = (Animal) cons1.newInstance(); // 업캐스팅도 가능
        Bird bird1 = (Bird) cons1.newInstance();
        Bird bird2 = (Bird) cons2.newInstance("Weaver bird");
        Bird bird3 = (Bird) cons3.newInstance("dove", true);

        assertEquals("bird", bird0.getName());
        assertEquals("bird", bird1.getName());
        assertEquals("Weaver bird", bird2.getName());
        assertEquals("dove", bird3.getName());

        assertFalse(bird1.getWalks());
        assertTrue(bird3.getWalks());
    }

    /**
     * 런타임에 클래스의 필드를 검사하는 데 사용되는 두 가지 주요 메서드는 getFields() 및 getField(fieldName) 이다.
     * <p>
     * getFields() : 해당 클래스의 액세스 가능한 모든 공개 필드를 반환 - 클래스와 모든 슈퍼클래스의 모든 공개 필드를 반환한다.
     *
     * @throws ClassNotFoundException
     */
    @Test
    @DisplayName("클래스의 모든 public 필드 정보 가져오기")
    public void givenClass_whenGetsPublicFields_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("reflection.dto.Bird");
        Field[] fields = birdClass.getFields();

        assertEquals(1, fields.length);
        assertEquals("CATEGORY", fields[0].getName());
    }

    /**
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    @Test
    @DisplayName("필드 이름으로 public 필드 정보 가져오기")
    public void givenClass_whenGetsPublicFieldByName_thenCorrect()
        throws ClassNotFoundException, NoSuchFieldException {
        Class<?> birdClass = Class.forName("reflection.dto.Bird");
        Field field = birdClass.getField("CATEGORY");

        assertEquals("CATEGORY", field.getName());
    }

    /**
     * getDeclaredFields : 클래스에 선언된 private 필드를 검사할 수 있다.
     *
     * @throws ClassNotFoundException
     */
    @Test
    @DisplayName("클래스의 모든 private 필드 정보 가져오기")
    public void givenClass_whenGetsDeclaredFields_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("reflection.dto.Bird");
        Field[] fields = birdClass.getDeclaredFields();

        assertEquals(1, fields.length);
        assertEquals("walks", fields[0].getName());
    }

    /**
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    @Test
    @DisplayName("필드 이름으로 private 필드 정보 가져오기")
    public void givenClass_whenGetsFieldsByName_thenCorrect()
        throws ClassNotFoundException, NoSuchFieldException {
        Class<?> birdClass = Class.forName("reflection.dto.Bird");
        Field field = birdClass.getDeclaredField("walks");

        assertEquals("walks", field.getName());
    }

    /**
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    @Test
    @DisplayName("필드의 타입 정보 가져오기")
    public void givenClassField_whenGetsType_thenCorrect()
        throws ClassNotFoundException, NoSuchFieldException {
        Field field = Class.forName("reflection.dto.Bird")
            .getDeclaredField("walks");
        Class<?> fieldClass = field.getType();

        assertEquals("boolean", fieldClass.getSimpleName());
    }

    /**
     *
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    @Test
    @DisplayName("필드 값에 액세스하고 수정하기.")
    public void givenClassField_whenSetsAndGetsValue_thenCorrect()
        throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> birdClass = Class.forName("reflection.dto.Bird");
        Bird bird = (Bird) birdClass.getConstructor().newInstance();

        Field field = birdClass.getDeclaredField("walks");
        field.setAccessible(true); // 객체의 필드 접근 허용

        assertFalse(field.getBoolean(bird));
        assertFalse(bird.getWalks());

        field.set(bird, true); // 필드 값 변경

        assertTrue(field.getBoolean(bird));
        assertTrue(bird.getWalks());
    }

    /**
     * public static 으로 선언될 때 이를 포함하는 클래스의 인스턴스가 필요하지 않다.
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void givenClassField_whenGetsAndSetsWithNull_thenCorrect()
        throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> birdClass = Class.forName("reflection.dto.Bird");
        Field field = birdClass.getField("CATEGORY");
        field.setAccessible(true);

        assertEquals("domestic", field.get(null));
    }

    /**
     * 자바 리플렉션을 사용하면 생성자에 대해 했던 것처럼 런타임에
     * 메서드를 호출하고 필요한 매개변수를 전달할 수 있다.
     * 유사하게, 각각의 매개변수 유형을 지정하여 오버로드된 메소드를 호출할 수도 있다.
     */

    /**
     * getMethods : 클래스 및 수퍼클래스의 모든 공용 메소드의 배열을 리턴합니다.
     * @throws ClassNotFoundException
     */
    @Test
    @DisplayName("클래스 및 수퍼클래스 메소드 정보 모두 가져오기")
    public void givenClass_whenGetsAllPublicMethods_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("reflection.dto.Bird");
        Method[] methods = birdClass.getMethods();

        List<String> methodNames = getMethodNames(methods);
        assertTrue(methodNames.containsAll(Arrays
            .asList("equals", "notifyAll", "hashCode",
                "getWalks", "eats", "toString")));
    }

    @Test
    @DisplayName("클래스의 메소드 정보만 모두 가져오기")
    public void givenClass_whenGetsOnlyDeclaredMethods_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("reflection.dto.Bird");
        List<String> actualMethodNames
            = getMethodNames(birdClass.getDeclaredMethods());

        List<String> expectedMethodNames = Arrays
            .asList("setWalks", "getWalks", "getSound", "eats");

        assertEquals(expectedMethodNames.size(), actualMethodNames.size());
        assertTrue(expectedMethodNames.containsAll(actualMethodNames));
        assertTrue(actualMethodNames.containsAll(expectedMethodNames));
    }

    @Test
    @DisplayName("이름으로 메소드 정보 가져오기")
    public void givenMethodName_whenGetsMethod_thenCorrect() throws Exception {
        Bird bird = new Bird();
        Method walksMethod = bird.getClass().getDeclaredMethod("getWalks");
        Method setWalksMethod = bird.getClass().getDeclaredMethod("setWalks", boolean.class);

        assertTrue(walksMethod.canAccess(bird));
        assertTrue(setWalksMethod.canAccess(bird));
    }

    /**
     * 런타임에 메서드를 호출 하는 방법
     * 메서드를 호출하고 반환 유형을 적절한 데이터 유형으로 캐스팅한 다음 해당 값을 확인하는 방법
     */
    @Test
    @DisplayName("메서드를 불러와 메서드를 호출해 값 넣기")
    public void givenMethod_whenInvokes_thenCorrect()
        throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> birdClass = Class.forName("reflection.dto.Bird");
        Bird bird = (Bird) birdClass.getConstructor().newInstance();

        Method setWalksMethod = birdClass.getDeclaredMethod("setWalks", boolean.class);
        Method getWalksMethod = birdClass.getDeclaredMethod("getWalks");

        boolean walks = (boolean) getWalksMethod.invoke(bird); //false
        assertFalse(walks);
        assertFalse(bird.getWalks());

        setWalksMethod.invoke(bird, true); // walks = true;

        boolean walks2 = (boolean) getWalksMethod.invoke(bird); //true
        assertTrue(walks2);
        assertTrue(bird.getWalks());
    }
}