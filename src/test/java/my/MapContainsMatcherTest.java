package my;

import static my.MapContainsMatcher.contains;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class MapContainsMatcherTest {

    public interface TestClass<K, V> {
        boolean usesMaps(Map<K, V> map);
    }

    @Mock
    private TestClass<String, String> testClass1;

    @Mock
    private TestClass<Integer, Long> testClass2;

    private Map<String, String> stringStringMap = new HashMap<>();
    private Map<Integer, Long> integerLongMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        stringStringMap.put("first", "firstValue");
        stringStringMap.put("second", "secondValue");
        stringStringMap.put("third", "thirdValue");

        integerLongMap.put(1, 10L);
        integerLongMap.put(2, 20L);
        integerLongMap.put(3, 30L);
    }

    @Test
    public void test() throws Exception {
        when(testClass1.usesMaps(contains("first", "firstValue"))).thenReturn(true);
        when(testClass2.usesMaps(contains(1, 10L))).thenReturn(true);

        assertTrue(testClass1.usesMaps(stringStringMap));
        assertTrue(testClass2.usesMaps(integerLongMap));

        Mockito.reset(testClass1);
        Mockito.reset(testClass2);

        when(testClass1.usesMaps(contains("fifth", "fifthValue"))).thenReturn(true);
        when(testClass2.usesMaps(contains(5, 50L))).thenReturn(true);

        assertFalse(testClass1.usesMaps(stringStringMap));
        assertFalse(testClass2.usesMaps(integerLongMap));
    }
}
