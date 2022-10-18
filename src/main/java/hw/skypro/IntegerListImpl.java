package hw.skypro;

import java.util.Arrays;

import static hw.skypro.SortMetods.sortInsertion;

public class IntegerListImpl implements IntegerList {

    private Integer[] integersArray;
    private int lastAddIndex = 0;
    private int size = 0;

    public IntegerListImpl(int size) {
        this.integersArray = new Integer[size];       
    }

    private void sort(){
        sortInsertion(integersArray);
    }

    private void checkArrayOutOfBounds(int index) {
        if (lastAddIndex <= index || index < 0) {
            throw new ArrayOutOfBoundsException();
        }
    }
    private void resize() {
        if (integersArray.length == size * 0.75) {
            integersArray = Arrays.copyOf(integersArray, integersArray.length * 2);
        }
    }


    @Override
    public Integer add(int item) {
       
        resize();

        integersArray[lastAddIndex++] = item;
        size++;
        return item;

    }

    @Override
    public Integer add(int index, int item) {

        checkArrayOutOfBounds(index);
        resize();

        System.arraycopy(integersArray, index, integersArray, index + 1, lastAddIndex - index);
        integersArray[index] = item;
        lastAddIndex++;
        size++;

        return item;

    }

    @Override
    public Integer set(int index, int item) {

        checkArrayOutOfBounds(index);

        integersArray[index] = item;
        return item;

    }

    @Override
    public Integer removeForItem(int item) {

        for (int index = 0; index < size; index++) {

            if (integersArray[index].equals(item)) {
                Integer tmp = integersArray[index];
                System.arraycopy(integersArray, index + 1, integersArray, index, lastAddIndex - index);
                size--;
                lastAddIndex--;
                return tmp;
            }
        }
        throw new ElementsNotFoundException();
    }

    @Override
    public Integer removeForIndex(int index) {

        checkArrayOutOfBounds(index);

        Integer tmp = integersArray[index];

        System.arraycopy(integersArray, index + 1, integersArray, index, lastAddIndex - index);
        size--;
        lastAddIndex--;
        return tmp;
    }

    @Override
    public boolean contains(int item) {

        for (int index = 0; index < lastAddIndex; index++) {

            if (integersArray[index].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(int item) {

        for (int index = 0; index < size; index++) {

            if (integersArray[index].equals(item)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(int item) {

        for (int index = size - 1; index >= 0; index--) {

            if (integersArray[index].equals(item)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {

        checkArrayOutOfBounds(index);

        return integersArray[index];

    }

    @Override
    public boolean equals(IntegerList otherList) {

        if (otherList == null) {
            throw new NullPointerException();
        }
        if (size != otherList.size()) {
            return false;
        }
        var eq = otherList.toArray();
        for (int i = 0; i < size; i++) {

            if (integersArray[i] != eq[i])
                return false;
        }

        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    @Override
    public void clear() {
        integersArray = new Integer[integersArray.length];
        size = 0;
        lastAddIndex = 0;
    }

    @Override
    public Integer[] toArray() {

        Integer[] strArr = new Integer[size];
        for (int i = 0; i < size; i++) {
            if (integersArray[i] != null) {
                strArr[i] = integersArray[i];
            }

        }
        return strArr;
    }
}
