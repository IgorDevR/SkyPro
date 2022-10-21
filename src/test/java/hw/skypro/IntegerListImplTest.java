package hw.skypro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class IntegerListImplTest {


    IntegerListImpl out;

    @BeforeEach
    void init() {
        out = new IntegerListImpl(5);
    }


    @Test
    void grow(){

        assertThat(out.getIntegersArray().length).isEqualTo(5);

        out.add(7);
        out.add(2);
        out.add(4);
        out.add(3);
        out.add(0);
        out.add(0);

        assertThat(out.getIntegersArray().length).isEqualTo((int)(5 * 1.5));
    }

    @Test
    void quickSort() {

        out.add(7);
        out.add(2);
        out.add(4);
        out.add(3);
        out.add(0);

        assertThat(out.get(0)).isEqualTo(7);
        assertThat(out.get(1)).isEqualTo(2);
        assertThat(out.get(2)).isEqualTo(4);
        assertThat(out.get(3)).isEqualTo(3);
        assertThat(out.get(4)).isEqualTo(0);

        out.quickSort(out.getIntegersArray(),0, out.getIntegersArray().length - 1);

        assertThat(out.get(0)).isEqualTo(0);
        assertThat(out.get(1)).isEqualTo(2);
        assertThat(out.get(2)).isEqualTo(3);
        assertThat(out.get(3)).isEqualTo(4);
        assertThat(out.get(4)).isEqualTo(7);
    }

    @Test
    void binarySearch() {

        out.add(0);
        out.add(2);
        out.add(4);
        out.add(3);

        assertThat(out.binarySearch(3)).isTrue();
        assertThat(out.binarySearch(22)).isFalse();
    }


    @Test
    void addAdnGetTest() {

        out.add(0);
        out.add(1);
        out.add(2);
        out.add(3);

        assertThat(out.size()).isEqualTo(4);
        assertThat(out.get(2)).isEqualTo(2);
        assertThat(out.get(3)).isEqualTo(3);
    }

    @Test
    void addForIndex() {

        out.add(0);
        out.add(1);
        out.add(2);
        out.add(3);

        out.add(2, 2);

        assertThat(out.size()).isEqualTo(5);
        assertThat(out.get(2)).isEqualTo(2);
        assertThat(out.get(3)).isEqualTo(2);
        assertThat(out.get(4)).isEqualTo(3);

        assertThatExceptionOfType(ArrayOutOfBoundsException.class).isThrownBy(() -> out.removeForIndex(5));

    }

    @Test
    void setReplaceEl() {

        out.add(0);
        out.add(1);
        out.add(2);
        out.add(3);

        assertThat(out.set(3, 0)).isEqualTo(0);
        assertThat(out.get(3)).isEqualTo(0);

        assertThatExceptionOfType(ArrayOutOfBoundsException.class).isThrownBy(() -> out.set(222, 10));
    }

    @Test
    void removeForItem() {

        out.add(0);
        out.add(1);
        out.add(2);
        out.add(3);

        assertThat(out.removeForItem(2)).isEqualTo(2);
        assertThat(out.get(0)).isEqualTo(0);
        assertThat(out.get(1)).isEqualTo(1);
        assertThat(out.get(2)).isEqualTo(3);
    }

    @Test
    void removeForIndex() {

        out.add(0);
        out.add(1);
        out.add(2);
        out.add(3);
        assertThat(out.removeForIndex(2)).isEqualTo(2);
        assertThat(out.get(1)).isEqualTo(1);
        assertThat(out.get(2)).isEqualTo(3);
        assertThatExceptionOfType(ArrayOutOfBoundsException.class).isThrownBy(() -> out.removeForIndex(3));
    }

    @Test
    void contains() {
        out.add(0);
        out.add(1);

        assertThat(out.contains(1)).isTrue();
        assertThat(out.contains(222)).isFalse();


    }

    @Test
    void indexOf() {
        out.add(0);
        out.add(1);
        assertThat(out.indexOf(1)).isEqualTo(1);
        assertThat(out.indexOf(122)).isEqualTo(-1);
    }

    @Test
    void lastIndexOf() {
        out.add(0);
        out.add(1);
        out.add(1);
        out.add(1);
        assertThat(out.lastIndexOf(1)).isEqualTo(3);
        assertThat(out.lastIndexOf(122)).isEqualTo(-1);

    }

    @Test
    void get() {
        out.add(0);
        out.add(1);
        assertThat(out.get(1)).isEqualTo(1);
        assertThatExceptionOfType(ArrayOutOfBoundsException.class).isThrownBy(() -> out.get(2));

    }

    @Test
    void equalsTest() {

        IntegerList integerList2 = new IntegerListImpl(5);

        out.add(0);
        out.add(1);

        integerList2.add(0);
        integerList2.add(1);

        assertThat(out.equals(integerList2)).isTrue();

        integerList2.set(0, 1);
        assertThat(out.equals(integerList2)).isFalse();

        integerList2.add(1);
        assertThat(out.equals(integerList2)).isFalse();
    }

    @Test
    void size() {

        out.add(0);
        out.add(1);

        assertThat(out.size()).isEqualTo(2);
    }

    @Test
    void isEmpty() {
        assertThat(out.isEmpty()).isTrue();
        out.add(0);
        assertThat(out.isEmpty()).isFalse();
    }

    @Test
    void clear() {

        out.add(0);
        assertThat(out.isEmpty()).isFalse();
        out.clear();
        assertThat(out.isEmpty()).isTrue();
    }

    @Test
    void toArray() {
        Integer[] integers = new Integer[out.size()];
        for (int i = 0; i < out.size(); i++) {
            integers[i] = out.get(i);
        }
        assertThat(out.toArray()).isEqualTo(integers);
    }
}