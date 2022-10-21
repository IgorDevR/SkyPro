package hw.skypro;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] integersArray;


    private int lastAddIndex = 0;
    private int size = 0;

    public IntegerListImpl(int size) {
        this.integersArray = new Integer[size];
    }

    public Integer[] getIntegersArray() {
        return integersArray;
    }

    //protected для проверки в тестах
    protected void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;

        return i + 1;
    }

    //protected для проверки в тестах
    protected boolean binarySearch(int element) {

        quickSort(integersArray, 0, integersArray[integersArray.length - 1]);

        int min = 0;
        int max = size - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == integersArray[mid]) {
                return true;
            }

            if (element < integersArray[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private void checkArrayOutOfBounds(int index) {
        if (lastAddIndex <= index || index < 0) {
            throw new ArrayOutOfBoundsException();
        }
    }

    private void grow() {
        if (integersArray.length == size ) {
            integersArray = Arrays.copyOf(integersArray, (int) (integersArray.length * 1.5f ));
        }
    }


    @Override
    public Integer add(int item) {

        grow();

        integersArray[lastAddIndex++] = item;
        size++;
        return item;

    }

    @Override
    public Integer add(int index, int item) {

        checkArrayOutOfBounds(index);
        grow();

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

//        for (int index = 0; index < lastAddIndex; index++) {
//
//            if (integersArray[index].equals(item)) {
//                return true;
//            }
//        }
//        return false;

        return binarySearch(item);
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
