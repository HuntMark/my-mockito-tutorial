package my;

import static org.mockito.Matchers.argThat;

import org.mockito.ArgumentMatcher;

import java.util.Map;

public class MapContainsMatcher<K, V> extends ArgumentMatcher<Map<K, V>> {

    private K key;
    private V value;

    public MapContainsMatcher(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean matches(Object argument) {
        @SuppressWarnings("unchecked")
        Map<K, V> map = (Map<K, V>) argument;
        return map.containsKey(this.key)
                && map.get(this.key).equals(this.value);
    }

    public static <K, V> Map<K, V> contains(K key, V value) {
        return argThat(new MapContainsMatcher<>(key, value));
    }
}
