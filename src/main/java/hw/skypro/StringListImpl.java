package hw.skypro;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private String[] stringArray;
    private int lastAddIndex = 0;
    private int size = 0;

    public StringListImpl(int size) {
        this.stringArray = new String[size];
    }


    private void checkArrayOutOfBounds(int index) {
        if (lastAddIndex <= index || index < 0) {
            throw new ArrayOutOfBoundsException();
        }
    }

    @Override
    public String add(String item) {

        if (stringArray.length == size * 0.75) {
            stringArray = Arrays.copyOf(stringArray, stringArray.length * 2);
        }
        stringArray[lastAddIndex++] = item;
        size++;
        return item;

    }

    @Override
    public String add(int index, String item) {

        checkArrayOutOfBounds(index);

        System.arraycopy(stringArray, index, stringArray, index + 1, lastAddIndex - index);
        stringArray[index] = item;
        lastAddIndex++;
        size++;

        return item;

    }

    @Override
    public String set(int index, String item) {

        checkArrayOutOfBounds(index);

        stringArray[index] = item;
        return item;

    }

    @Override
    public String remove(String item) {

        for (int index = 0; index < lastAddIndex; index++) {

            if (stringArray[index].equals(item)) {
                String tmp = stringArray[index];
                System.arraycopy(stringArray, index + 1, stringArray, index, lastAddIndex - index);
                size--;
                lastAddIndex--;
                return tmp;
            }
        }
        throw new ElementsNotFoundException();
    }

    @Override
    public String remove(int index) {
        checkArrayOutOfBounds(index);

        String tmp = stringArray[index];

        System.arraycopy(stringArray, index + 1, stringArray, index, lastAddIndex - index);
        size--;
        lastAddIndex--;
        return tmp;
    }

    @Override
    public boolean contains(String item) {

        for (int index = 0; index < lastAddIndex; index++) {

            if (stringArray[index].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {

        for (int index = 0; index < size; index++) {

            if (stringArray[index].equals(item)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int index = size - 1; index >= 0; index--) {

            if (stringArray[index].equals(item)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {

        checkArrayOutOfBounds(index);

        return stringArray[index];

    }

    @Override
    public boolean equals(StringList otherList) {

        if (otherList == null) {
            throw new NullPointerException();
        }
        if(size != otherList.size()){
            return false;
        }
        var eq = otherList.toArray();
        for (int i = 0; i < size; i++) {
            if (stringArray[i] != eq[i])
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
        stringArray = new String[stringArray.length];
        size = 0;
        lastAddIndex = 0;
    }

    @Override
    public String[] toArray() {

        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            if (stringArray[i] != null)
                strArr[i] = stringArray[i];
        }
        return strArr;
    }
}
