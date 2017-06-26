package my;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.array;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.describedAs;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.emptyIterableOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.eventFrom;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.hasXPath;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.hamcrest.Matchers.theInstance;
import static org.hamcrest.Matchers.typeCompatibleWith;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HamcrestTest {

    @Test
    public void test_any() throws Exception {
        // Given
        String myString = "hello";

        // Then
        assertThat(myString, is(any(String.class)));
    }

    @Test
    public void test_anything() throws Exception {
        // Given
        String myString = "hello";
        Integer four = 4;

        // Then
        assertThat(myString, is(anything()));
        assertThat(four, is(anything()));
    }

    @Test
    public void test_arrayContaining_items() throws Exception {
        // Given
        String[] strings = {"why", "hello", "there"};

        // Then
        assertThat(strings, is(arrayContaining("why", "hello", "there")));
    }

    @Test
    public void test_arrayContaining_list_of_matchers() throws Exception {
        // Given
        String[] strings = {"why", "hello", "there"};

        // Then
        java.util.List<org.hamcrest.Matcher<? super String>> itemMatchers = new ArrayList<>();
        itemMatchers.add(equalTo("why"));
        itemMatchers.add(equalTo("hello"));
        itemMatchers.add(endsWith("here"));
        assertThat(strings, is(arrayContaining(itemMatchers)));
    }

    @Test
    public void test_arrayContaining_matchers() throws Exception {
        // Given
        String[] strings = {"why", "hello", "there"};

        // Then
        assertThat(strings, is(arrayContaining(startsWith("wh"), equalTo("hello"), endsWith("here"))));
    }

    @Test
    public void test_arrayContainingInAnyOrder_items() throws Exception {
        // Given
        String[] strings = {"why", "hello", "there"};

        // Then
        assertThat(strings, is(arrayContainingInAnyOrder("hello", "there", "why")));
    }

    @Test
    public void test_arrayContainingInAnyOrder_collection_of_matchers() throws Exception {
        // Given
        String[] strings = {"why", "hello", "there"};

        // Then
        Set<Matcher<? super String>> itemMatchers = new HashSet<>();
        itemMatchers.add(equalTo("hello"));
        itemMatchers.add(equalTo("why"));
        itemMatchers.add(endsWith("here"));
        assertThat(strings, is(arrayContainingInAnyOrder(itemMatchers)));
    }

    @Test
    public void test_arrayContainingInAnyOrder_matchers() throws Exception {
        // Given
        String[] strings = {"why", "hello", "there"};

        // Then
        assertThat(strings, is(arrayContainingInAnyOrder(endsWith("lo"), startsWith("the"), equalTo("why"))));
    }

    @Test
    public void test_arrayWithSize_exact() throws Exception {
        // Given
        String[] strings = {"why", "hello", "there"};

        // Then
        assertThat(strings, is(arrayWithSize(3)));
    }

    @Test
    public void test_arrayWithSize_matcher() throws Exception {
        // Given
        String[] strings = {"why", "hello", "there"};

        // Then
        assertThat(strings, is(arrayWithSize(greaterThan(2))));
    }

    @Test
    public void test_closeTo_double() throws Exception {
        // Given
        Double testValue = 6.3;

        // Then
        assertThat(testValue, is(closeTo(6, 0.5)));
    }

    @Test
    public void test_closeTo_bigDecimal() throws Exception {
        // Given
        BigDecimal testValue = new BigDecimal(324.0);

        // Then
        assertThat(testValue, is(closeTo(new BigDecimal(350), new BigDecimal(50))));
    }

    @Test
    public void test_comparesEqualTo() throws Exception {
        // Given
        String testValue = "value";

        // Then
        assertThat(testValue, comparesEqualTo("value"));
    }

    @Test
    public void test_contains_items() throws Exception {
        // Given
        List<String> strings = Arrays.asList("why", "hello", "there");

        // Then
        assertThat(strings, contains("why", "hello", "there"));
    }

    @Test
    public void test_contains_list_of_matchers() throws Exception {
        // Given
        List<String> strings = Arrays.asList("why", "hello", "there");

        // Then
        List<org.hamcrest.Matcher<? super String>> matchers = new ArrayList<>();
        matchers.add(startsWith("wh"));
        matchers.add(endsWith("lo"));
        matchers.add(equalTo("there"));
        assertThat(strings, contains(matchers));
    }

    @Test
    public void test_contains_single_matcher() throws Exception {
        // Given
        List<String> strings = Arrays.asList("hello");

        // Then
        assertThat(strings, contains(startsWith("he")));
    }

    @Test
    public void test_contains_matchers() throws Exception {
        // Given
        List<String> strings = Arrays.asList("why", "hello", "there");

        // Then
        assertThat(strings, contains(startsWith("why"), endsWith("llo"), equalTo("there")));
    }

    @Test
    public void test_containsInAnyOrder_items() throws Exception {
        // Given
        List<String> strings = Arrays.asList("why", "hello", "there");

        // Then
        assertThat(strings, containsInAnyOrder("hello", "there", "why"));
    }

    @Test
    public void test_containsInAnyOrder_list_of_matchers() throws Exception {
        // Given
        List<String> strings = Arrays.asList("why", "hello", "there");

        // Then
        List<org.hamcrest.Matcher<? super String>> matchers = new ArrayList<>();
        matchers.add(equalTo("there"));
        matchers.add(startsWith("wh"));
        matchers.add(endsWith("lo"));
        assertThat(strings, containsInAnyOrder(matchers));
    }

    @Test
    public void test_containsInAnyOrder_matchers() throws Exception {
        // Given
        List<String> strings = Arrays.asList("why", "hello", "there");

        // Then
        assertThat(strings, containsInAnyOrder(endsWith("llo"), equalTo("there"), startsWith("why")));
    }

    @Test
    public void test_containsString() throws Exception {
        // Given
        String testValue = "value";

        // Then
        assertThat(testValue, containsString("alu"));
    }

    @Test
    public void test_empty() throws Exception {
        // Given
        Set<String> testCollection = new HashSet<>();

        // Then
        assertThat(testCollection, is(empty()));
    }

    @Test
    public void test_emptyArray() throws Exception {
        // Given
        String[] testArray = new String[0];

        // Then
        assertThat(testArray, is(emptyArray()));
    }

    @Test
    public void test_emptyCollectionOf() throws Exception {
        // Given
        Set<String> testCollection = new HashSet<>();

        // Then
        assertThat(testCollection, is(emptyCollectionOf(String.class)));
    }

    @Test
    public void test_emptyIterable() throws Exception {
        // Given
        Set<String> testCollection = new HashSet<>();

        // Then
        assertThat(testCollection, is(emptyIterable()));
    }

    @Test
    public void test_emptyIterableOf() throws Exception {
        // Given
        Set<String> testCollection = new HashSet<>();

        // Then
        assertThat(testCollection, is(emptyIterableOf(String.class)));
    }

    @Test
    public void test_endsWith() throws Exception {
        // Given
        String testValue = "value";

        // Then
        assertThat(testValue, endsWith("lue"));
    }

    @Test
    public void test_equalTo_value() throws Exception {
        // Given
        String testValue = "value";

        // Then
        assertThat(testValue, equalTo("value"));
    }

    @Test
    public void test_equalTo_array() throws Exception {
        // Given
        String[] testValues = {"why", "hello", "there"};

        // Then
        String[] specifiedValues = {"why", "hello", "there"};
        assertThat(testValues, equalTo(specifiedValues));
    }

    @Test
    public void test_equalToIgnoringCase() throws Exception {
        // Given
        String testValue = "value";

        // Then
        assertThat(testValue, equalToIgnoringCase("VaLuE"));
    }

    @Test
    public void test_equalToIgnoringWhiteSpace() throws Exception {
        // Given
        String testValue = "this    is   my    value    ";

        // Then
        assertThat(testValue, equalToIgnoringWhiteSpace("this is my value"));
    }

    @Test
    public void test_eventFrom() throws Exception {
        // Given
        Object source = new Object();
        EventObject testEvent = new EventObject(source);

        // Then
        assertThat(testEvent, is(eventFrom(source)));
    }

    @Test
    public void test_eventFrom_type() throws Exception {
        // Given
        Object source = new Object();
        EventObject testEvent = new MenuEvent(source);

        // Then
        assertThat(testEvent, is(eventFrom(MenuEvent.class, source)));
    }

    @Test
    public void test_greaterThan() throws Exception {
        // Given
        Integer testValue = 5;

        // Then
        assertThat(testValue, is(greaterThan(3)));
    }

    @Test
    public void test_greaterThanOrEqualTo() throws Exception {
        // Given
        Integer testValue = 3;

        // Then
        assertThat(testValue, is(greaterThanOrEqualTo(3)));
    }

    @Test
    public void test_hasEntry() throws Exception {
        // Given
        Integer testKey = 1;
        String testValue = "one";

        Map<Integer, String> testMap = new HashMap<>();
        testMap.put(testKey, testValue);

        // Then
        assertThat(testMap, hasEntry(1, "one"));
    }

    @Test
    public void test_hasEntry_matchers() throws Exception {
        // Given
        Integer testKey = 2;
        String testValue = "two";

        Map<Integer, String> testMap = new HashMap<>();
        testMap.put(testKey, testValue);

        // Then
        assertThat(testMap, hasEntry(greaterThan(1), endsWith("o")));
    }

    @Test
    public void test_hasItem() throws Exception {
        // Given
        List<Integer> testList = Arrays.asList(1, 2, 7, 5, 4, 8);

        // Then
        assertThat(testList, hasItem(4));
    }

    @Test
    public void test_hasItem_matcher() throws Exception {
        // Given
        List<Integer> testList = Arrays.asList(1, 2, 7, 5, 4, 8);

        // Then
        assertThat(testList, hasItem(is(greaterThan(6))));
    }

    @Test
    public void test_hasItemInArray() throws Exception {
        // Given
        Integer[] test = {1, 2, 7, 5, 4, 8};

        // Then
        assertThat(test, hasItemInArray(4));
    }

    @Test
    public void test_hasItemInArray_matcher() throws Exception {
        // Given
        Integer[] test = {1, 2, 7, 5, 4, 8};

        // Then
        assertThat(test, hasItemInArray(is(greaterThan(6))));
    }

    public void test_hasItems() throws Exception {
        // Given
        List<Integer> testList = Arrays.asList(1, 2, 7, 5, 4, 8);

        // Then
        assertThat(testList, hasItems(4, 2, 5));
    }

    @Test
    public void test_hasItems_matcher() throws Exception {
        // Given
        List<Integer> testList = Arrays.asList(1, 2, 7, 5, 4, 8);

        // Then
        assertThat(testList, hasItems(greaterThan(6), lessThan(2)));
    }

    @Test
    public void test_hasKey() throws Exception {
        // Given
        Map<String, String> testMap = new HashMap<>();
        testMap.put("hello", "there");
        testMap.put("how", "are you?");

        // Then
        assertThat(testMap, hasKey("hello"));
    }

    @Test
    public void test_hasKey_matcher() throws Exception {
        // Given
        Map<String, String> testMap = new HashMap<>();
        testMap.put("hello", "there");
        testMap.put("how", "are you?");

        // Then
        assertThat(testMap, hasKey(startsWith("h")));
    }

    @Test
    public void test_hasProperty() throws Exception {
        // Given
        JTextField testBean = new JTextField();
        testBean.setText("Hello, World!");

        // Then
        assertThat(testBean, hasProperty("text"));
    }

    @Test
    public void test_hasProperty_value() throws Exception {
        // Given
        JTextField testBean = new JTextField();
        testBean.setText("Hello, World!");

        // Then
        assertThat(testBean, hasProperty("text", startsWith("H")));
    }

    @Test
    public void test_hasSize() throws Exception {
        // Given
        List<Integer> testList = Arrays.asList(1, 2, 3, 4, 5);

        // Then
        assertThat(testList, hasSize(5));
    }

    @Test
    public void test_hasSize_matcher() throws Exception {
        // Given
        List<Integer> testList = Arrays.asList(1, 2, 3, 4, 5);

        // Then
        assertThat(testList, hasSize(lessThan(10)));
    }

    @Test
    public void test_hasToString() throws Exception {
        // Given
        Integer testValue = 4;

        // Then
        assertThat(testValue, hasToString("4"));
    }

    @Test
    public void test_hasToString_matcher() throws Exception {
        // Given
        Double testValue = 3.14;

        // Then
        assertThat(testValue, hasToString(containsString(".")));
    }

    @Test
    public void test_hasValue() throws Exception {
        // Given
        Map<String, String> testMap = new HashMap<>();
        testMap.put("hello", "there");
        testMap.put("how", "are you?");

        // Then
        assertThat(testMap, hasValue("there"));
    }

    @Test
    public void test_hasValue_matcher() throws Exception {
        // Given
        Map<String, String> testMap = new HashMap<>();
        testMap.put("hello", "there");
        testMap.put("how", "are you?");

        // Then
        assertThat(testMap, hasValue(containsString("?")));
    }

    @Test
    public void test_hasXPath() throws Exception {
        // Given
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Node testNode = docBuilder.parse(
                new InputSource(new StringReader("<xml><top><middle><bottom>value</bottom></middle></top></xml>")))
                .getDocumentElement();

        // Then
        assertThat(testNode, hasXPath("/xml/top/middle/bottom"));
    }

    @Test
    public void test_hasXPath_matcher() throws Exception {
        // Given
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Node testNode = docBuilder.parse(
                new InputSource(new StringReader("<xml><top><middle><bottom>value</bottom></middle></top></xml>")))
                .getDocumentElement();

        // Then
        assertThat(testNode, hasXPath("/xml/top/middle/bottom", startsWith("val")));
    }

    @Test
    public void test_hasXPath_namespace() throws Exception {
        // Given
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Node testNode = docBuilder.parse(
                new InputSource(new StringReader("<xml xmlns:prefix='http://namespace-uri'><top><middle><prefix:bottom>value</prefix:bottom></middle></top></xml>")))
                .getDocumentElement();

        NamespaceContext namespace = new NamespaceContext() {
            public String getNamespaceURI(String prefix) {
                return "http://namespace-uri";
            }

            public String getPrefix(String namespaceURI) {
                return null;
            }

            public Iterator<String> getPrefixes(String namespaceURI) {
                return null;
            }
        };

        // Then
        assertThat(testNode, hasXPath("//prefix:bottom", namespace));
    }

    @Test
    public void test_hasXPath_namespace_matcher() throws Exception {
        // Given
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Node testNode = docBuilder.parse(
                new InputSource(new StringReader("<xml xmlns:prefix='http://namespace-uri'><top><middle><prefix:bottom>value</prefix:bottom></middle></top></xml>")))
                .getDocumentElement();

        NamespaceContext namespace = new NamespaceContext() {
            public String getNamespaceURI(String prefix) {
                return "http://namespace-uri";
            }

            public String getPrefix(String namespaceURI) {
                return null;
            }

            public Iterator<String> getPrefixes(String namespaceURI) {
                return null;
            }
        };

        // Then
        assertThat(testNode, hasXPath("//prefix:bottom", namespace, startsWith("val")));
    }

    @Test
    public void test_instanceOf() throws Exception {
        // Given
        Object string = "Hello, World!";

        // Then
        assertThat(string, instanceOf(String.class));
    }

    @Test
    public void test_isEmptyOrNullString() throws Exception {
        // Given
        String emptyString = "";
        String nullString = null;

        // Then
        assertThat(emptyString, isEmptyOrNullString());
        assertThat(nullString, isEmptyOrNullString());
    }

    @Test
    public void test_isEmptyString() throws Exception {
        // Given
        String emptyString = "";

        // Then
        assertThat(emptyString, isEmptyString());
    }

    @Test
    public void test_isIn() throws Exception {
        // Given
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(6);
        set.add(4);

        // Then
        assertThat(4, isIn(set));
    }

    @Test
    public void test_isOneOf() throws Exception {
        // Then
        assertThat(4, isOneOf(3, 4, 5));
    }

    @Test
    public void test_iterableWithSize() throws Exception {
        // Given
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(6);
        set.add(4);

        // Then
        assertThat(set, iterableWithSize(3));
    }

    @Test
    public void test_iterableWithSize_matcher() throws Exception {
        // Given
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(6);
        set.add(4);

        // Then
        assertThat(set, iterableWithSize(lessThan(4)));
    }

    @Test
    public void test_lessThan() throws Exception {
        // Then
        assertThat("apple", lessThan("zoo"));
    }

    @Test
    public void test_lessThanOrEqualTo() throws Exception {
        // Then
        assertThat(2, lessThanOrEqualTo(2));
    }

    @Test
    public void test_not_matcher() throws Exception {
        // Then
        assertThat("zoo", not(lessThan("apple")));
    }

    @Test
    public void test_not_value() throws Exception {
        // Then
        assertThat("apple", not("orange"));
    }

    @Test
    public void test_notNullValue() throws Exception {
        // Then
        assertThat("apple", notNullValue());
    }

    @Test
    public void test_nullValue() throws Exception {
        // Given
        Object nothing = null;

        // Then
        assertThat(nothing, nullValue());
    }

    @Test
    public void test_sameInstance() throws Exception {
        // Given
        Object one = new Object();
        Object two = one;

        // Then
        assertThat(one, sameInstance(two));
    }

    public class Bean {

        private Integer number;
        private String text;

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    @Test
    public void test_samePropertyValuesAs() throws Exception {
        // Given
        Bean one = new Bean();
        one.setText("text");
        one.setNumber(3);

        Bean two = new Bean();
        two.setText("text");
        two.setNumber(3);

        // Then
        assertThat(one, samePropertyValuesAs(two));
    }

    @Test
    public void test_startsWith() throws Exception {
        // Given
        String test = "Beginnings are important!";

        // Then
        assertThat(test, startsWith("Beginning"));
    }

    @Test
    public void test_stringContainsInOrder() throws Exception {
        // Given
        String test = "Rule number one: two's company, but three's a crowd!";

        // Then
        assertThat(test, stringContainsInOrder(Arrays.asList("one", "two", "three")));
    }

    @Test
    public void test_theInstance() throws Exception {
        // Given
        Object one = new Object();
        Object two = one;

        // Then
        assertThat(one, theInstance(two));
    }

    @Test
    public void test_typeCompatibleWith() throws Exception {
        // Given
        Integer integer = 3;

        // Then
        assertThat(integer.getClass(), typeCompatibleWith(Number.class));
    }

    @Test
    public void test_allOf_individual() throws Exception {
        // Given
        String test = "starting off well, gives content meaning, in the end";

        // Then
        assertThat(test, allOf(startsWith("start"), containsString("content"), endsWith("end")));
    }

    @Test
    public void test_anyOf_individual() throws Exception {
        // Given
        String test = "Some things are present, some things are not!";

        // Then
        assertThat(test, anyOf(containsString("present"), containsString("missing")));
    }

    @Test
    public void test_anyOf_iterable() throws Exception {
        // Given
        String test = "Hello, world!";

        List<Matcher<? super String>> matchers = Arrays.asList(containsString("Hello"), containsString("Earth"));

        // Then
        assertThat(test, anyOf(matchers));
    }

    @Test
    public void test_array() throws Exception {
        // Given
        String[] test = {"To be", "or not to be", "that is the question!"};

        // Then
        assertThat(test, array(startsWith("To"), containsString("not"), instanceOf(String.class)));
    }

    @Test
    public void test_both() throws Exception {
        // Given
        String test = "Hello, world!";

        // Then
        assertThat(test, both(startsWith("Hello")).and(endsWith("world!")));
    }

    @Test
    public void test_either() throws Exception {
        // Given
        String test = "Hello, world!";

        // Then
        assertThat(test, either(startsWith("Hello")).or(endsWith("universe!")));
    }

    @Test
    public void test_is_matcher() throws Exception {
        // Given
        Integer test = 5;

        // Then
        assertThat(test, is(greaterThan(3)));
    }

    @Test
    public void test_is_value() throws Exception {
        // Given
        Integer test = 5;

        // Then
        assertThat(test, is(5));
    }

    @Test
    public void test_describedAs() throws Exception {
        // Given
        Integer actual = 7;
        Integer expected = 10;

        // Then
        assertThat(actual, describedAs("input > %0", greaterThan(expected), expected));
    }
}
