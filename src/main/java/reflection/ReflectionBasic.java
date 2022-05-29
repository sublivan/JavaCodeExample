package reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reflection.dto.Animal;
import reflection.dto.Goat;

public class ReflectionBasic {

    @Test
    @DisplayName("Class 에서 객체의 이름을 가져오기.")
    public void givenObject_whenGetsClassName_thenCorrect() {
        Animal goat = new Goat("goat");
        Class<?> clazz = goat.getClass();

        assertEquals("Goat", clazz.getSimpleName()); // 객체의 기본이름 반환
        assertEquals("reflection.dto.Goat", clazz.getName()); // 패키지 선언 포함 정규화된 클래스이름 반환
        assertEquals("reflection.dto.Goat", clazz.getCanonicalName());
    }

    /**
     * 정적 forName 메서드 에 전달하는 이름 에는 패키지 정보가 포함되어야 한다.
     * 그렇지 않으면 ClassNotFoundException이 발생
     */
    @Test
    @DisplayName("정규화된 클래스 이름으로 클래스의 객체 생성하기")
    public void givenClassName_whenCreatesObject_thenCorrect() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("reflection.dto.Goat");

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("reflection.dto.Goat", clazz.getName());
        assertEquals("reflection.dto.Goat", clazz.getCanonicalName());
    }

    /**
     * java.lang.reflect.Modifier 클래스 는 특정 Modifiers(수정자)의 존재 여부에 대해
     * 반환된 Integer를 분석하는 정적 메소드를 제공한다.
     * 모든 클래스의 수정자를 검사할 수 있다.
     */
    @Test
    @DisplayName("사용되는 접근 지정자(Modifiers,수정자) 정보 가져오기")
    public void givenClass_whenRecognisesModifiers_thenCorrect() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("reflection.dto.Goat");
        Class<?> animalClass = Class.forName("reflection.dto.Animal");

        int goatMods = goatClass.getModifiers();
        int animalMods = animalClass.getModifiers();
        System.out.println("goatMods = " + goatMods);
        System.out.println("animalMods = " + animalMods);

        assertTrue(Modifier.isPublic(goatMods)); // public
        assertTrue(Modifier.isAbstract(animalMods)); // 상속
        assertTrue(Modifier.isPublic(animalMods));  //
    }

    @Test
    @DisplayName("패키지 이름 가져오기")
    public void givenClass_whenGetsPackageInfo_thenCorrect() {
        Goat goat = new Goat("goat");
        Class<?> goatClass = goat.getClass();
        Package pkg = goatClass.getPackage();

        assertEquals("reflection.dto", pkg.getName());
    }

    /**
     *  Java 리플렉션을 사용하여
     *  모든 Java 클래스의 수퍼클래스를 얻을 수 있다.
     */
    @Test
    @DisplayName("슈퍼클래스 정보 가져오기")
    public void givenClass_whenGetsSuperClass_thenCorrect() {
        Goat goat = new Goat("goat");
        String str = "any string";

        Class<?> goatClass = goat.getClass();
        Class<?> goatSuperClass = goatClass.getSuperclass();

        assertEquals("Animal", goatSuperClass.getSimpleName());
        assertEquals("Object", str.getClass().getSuperclass().getSimpleName()); //java.lang.String 클래스가 java.lang.Object 클래스 의 하위 클래스
    }

    /**
     * Java 리플렉션을 사용 하여 주어진 클래스에 의해 구현된 인터페이스 목록을 얻을 수 있다.
     * 클래스가 implements 키워드로 구현된 것으로 명시적으로 선언한 인터페이스만 반환된 배열에 나타난다.
     */
    @Test
    @DisplayName("인터페이스 목록 가져오기")
    public void givenClass_whenGetsImplementedInterfaces_thenCorrect()
        throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("reflection.dto.Goat");
        Class<?> animalClass = Class.forName("reflection.dto.Animal");

        Class<?>[] goatInterfaces = goatClass.getInterfaces();
        Class<?>[] animalInterfaces = animalClass.getInterfaces();

        assertEquals(1, goatInterfaces.length);
        assertEquals(1, animalInterfaces.length);
        assertEquals("Locomotion", goatInterfaces[0].getSimpleName());
        assertEquals("Eating", animalInterfaces[0].getSimpleName());
    }

    /**
     * java 리플렉션을 사용하여 모든 객체 클래스의 생성자 정보를 가져올 수 있다.
     */
    @Test
    @DisplayName("클래스의 생성자 정보 가져오기")
    public void givenClass_whenGetsConstructor_thenCorrect() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("reflection.dto.Goat");

        Constructor<?>[] constructors = goatClass.getConstructors();

        assertEquals(1, constructors.length); // 생성자의 개수
        assertEquals("reflection.dto.Goat", constructors[0].getName()); // 생성자 이름 비교
    }

    @Test
    @DisplayName("클래스의 필드 정보 가져오기")
    public void givenClass_whenGetsFields_thenCorrect() throws ClassNotFoundException {
        Class<?> animalClass = Class.forName("reflection.dto.Animal");
        Field[] fields = animalClass.getDeclaredFields();

        List<String> actualFields = getFieldNames(fields);

        assertEquals(2, actualFields.size());
        assertTrue(actualFields.containsAll(Arrays.asList("name", "CATEGORY")));
    }

    @Test
    @DisplayName("클래스의 메서드 정보 가져오기")
    public void givenClass_whenGetsMethods_thenCorrect() throws ClassNotFoundException {
        Class<?> animalClass = Class.forName("reflection.dto.Animal");
        Method[] methods = animalClass.getDeclaredMethods();
        List<String> actualMethods = getMethodNames(methods);

        assertEquals(3, actualMethods.size());
        assertTrue(actualMethods.containsAll(Arrays.asList("getName",
            "setName", "getSound")));
    }

    /**
     * Field[] 에서 메서드 이름을 검색 리스트 반환
     * @param fields
     * @return List<String>
     */
    private static List<String> getFieldNames(Field[] fields) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields)
            fieldNames.add(field.getName());
        return fieldNames;
    }

    /**
     * Method[] 에서 메서드 이름을 검색 리스트 반환
     * @param methods
     * @return List<String>
     */
    private static List<String> getMethodNames(Method[] methods) {
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods)
            methodNames.add(method.getName());
        return methodNames;
    }
}