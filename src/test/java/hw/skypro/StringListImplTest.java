package hw.skypro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StringListImplTest {


    StringListImpl out;

    @BeforeEach
    void init() {
        out = new StringListImpl(5);
    }

    @Test
    void addAdnGetTest() {

        out.add("0");
        out.add("1");
        out.add("2");
        out.add("3");

        assertThat(out.size()).isEqualTo(4);
        assertThat(out.get(2)).isEqualTo("2");
        assertThat(out.get(3)).isEqualTo("3");
    }

    @Test
    void addForIndex() {

        out.add("0");
        out.add("1");
        out.add("2");
        out.add("3");

        out.add(2, "new 2");

        assertThat(out.size()).isEqualTo(5);
        assertThat(out.get(2)).isEqualTo("new 2");
        assertThat(out.get(3)).isEqualTo("2");
        assertThat(out.get(4)).isEqualTo("3");

        assertThatExceptionOfType(ArrayOutOfBoundsException.class).isThrownBy(() -> out.remove(5));

    }

    @Test
    void setReplaceEl() {

        out.add("0");
        out.add("1");
        out.add("2");
        out.add("3");

        assertThat(out.set(2, "set 2")).isEqualTo("set 2");
        assertThat(out.get(3)).isEqualTo("3");

        assertThatExceptionOfType(ElementsNotFoundException.class).isThrownBy(() -> out.remove("222"));
    }

    @Test
    void removeEl() {

        out.add("0");
        out.add("1");
        out.add("add 2");
        out.add("3");

        assertThat(out.remove(2)).isEqualTo("add 2");
        assertThat(out.get(0)).isEqualTo("0");
        assertThat(out.get(1)).isEqualTo("1");
        assertThat(out.get(2)).isEqualTo("3");
    }

    @Test
    void removeForIndex() {

        out.add("0");
        out.add("1");
        out.add("2");
        out.add("3");
        assertThat(out.remove(2)).isEqualTo("2");
        assertThat(out.get(1)).isEqualTo("1");
        assertThat(out.get(2)).isEqualTo("3");
        assertThatExceptionOfType(ArrayOutOfBoundsException.class).isThrownBy(() -> out.remove(3));
    }

    @Test
    void contains() {
        out.add("0");
        out.add("1");

        assertThat(out.contains("1")).isTrue();
        assertThat(out.contains("222")).isFalse();


    }

    @Test
    void indexOf() {
        out.add("0");
        out.add("1");
        assertThat(out.indexOf("1")).isEqualTo(1);
        assertThat(out.indexOf("122")).isEqualTo(-1);
    }

    @Test
    void lastIndexOf() {
        out.add("0");
        out.add("1");
        out.add("1");
        out.add("1");
        assertThat(out.lastIndexOf("1")).isEqualTo(3);
        assertThat(out.lastIndexOf("122")).isEqualTo(-1);

    }

    @Test
    void get() {
        out.add("0");
        out.add("1");
        assertThat(out.get(1)).isEqualTo("1");
        assertThatExceptionOfType(ArrayOutOfBoundsException.class).isThrownBy(() -> out.get(2));

    }

    @Test
    void testEquals() {

        StringList stringList2 = new StringListImpl(5);

        out.add("0");
        out.add("1");

        stringList2.add("0");
        stringList2.add("1");

        assertThat(out.equals(stringList2)).isTrue();

        stringList2.set(0,"1");
        assertThat(out.equals(stringList2)).isFalse();

        stringList2.add("1");
        assertThat(out.equals(stringList2)).isFalse();
    }

    @Test
    void size() {

        out.add("0");
        out.add("1");

        assertThat(out.size()).isEqualTo(2);
    }

    @Test
    void isEmpty() {
        assertThat(out.isEmpty()).isTrue();
        out.add("0");
        assertThat(out.isEmpty()).isFalse();
    }

    @Test
    void clear() {

        out.add("0");
        assertThat(out.isEmpty()).isFalse();
        out.clear();
        assertThat(out.isEmpty()).isTrue();
    }

    @Test
    void toArray() {
        String[] strings = new String[out.size()];
        for (int i = 0; i < out.size(); i++) {
            strings[i] = out.get(i);
        }
        assertThat(out.toArray()).isEqualTo(strings);
    }
}