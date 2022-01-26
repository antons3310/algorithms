package ru.ashebalkin.skypro.course2.service;

import ru.ashebalkin.skypro.course2.exceptions.ArrayOutOfRangeExceptions;
import ru.ashebalkin.skypro.course2.exceptions.ItemNotFoundException;
import ru.ashebalkin.skypro.course2.exceptions.NullObjectException;

import java.util.Arrays;

import static java.util.Arrays.copyOf;


public class IntListImpl implements IntList {

        private int[] array;
        private static int position = 0;
        int INIT_SIZE = 20;
        int CUT_RATE = 3;
        double EXPAND_RATE = 1.5;

        public IntListImpl() {
                array = new int[INIT_SIZE];
        }

        private static void swapElements(int[] arr, int indexA, int indexB) {
                int tmp = arr[indexA];
                arr[indexA] = arr[indexB];
                arr[indexB] = tmp;
        }

        public static void sort(int[] arr, int begin, int end) {
                if (begin < end) {
                        int partitionIndex = partition(arr, begin, end);

                        sort(arr, begin, partitionIndex - 1);
                        sort(arr, partitionIndex + 1, end);
                }
        }

        private static int partition(int[] arr, int begin, int end) {
                int pivot = arr[end];
                int i = (begin - 1);

                for (int j = begin; j < end; j++) {
                        if (arr[j] <= pivot) {
                                i++;

                                swapElements(arr, i, j);
                        }
                }

                swapElements(arr, i + 1, end);
                return i + 1;
        }

        // Добавление элемента.
        // Вернуть добавленный элемент
        // в качестве результата выполнения.
        @Override
        public int add(int item) {
                if (position == array.length - 1) {
                        grow();
                }
                array[position++] = item;
                return array[position];
        }

        // Установить элемент
        // на определенную позицию,
        // затерев существующий.
        // Выбросить исключение,
        // если индекс меньше
        // фактического количества элементов
        // или выходит за пределы массива.
        @Override
        public int set(int index, int item) {

                if (index < position ) {
                        array[index] = item;

                        return array[index];
                } else {
                        throw new ArrayOutOfRangeExceptions();
                }
        }

        // Удаление элемента.
        // Вернуть удаленный элемент
        // или исключение, если подобный
        // элемент отсутствует в списке.
        @Override
        public int remove(int item) {
                int index = -1;

                for (int i = 0; i < position; i++) {
                        if (array[i] == item) {
                                index = i;
                                break;
                        }
                }

                if (index == -1) {
                        throw new ItemNotFoundException();
                }

                modifyArrayByIndex(index);

                return item;

        }

        // Удаление элемента по индексу.
        // Вернуть удаленный элемент
        // или исключение, если подобный
        // элемент отсутствует в списке.
        @Override
        public void removeByIndex(int index) {

                if (index < position) {
                        modifyArrayByIndex(index);

                } else {
                        throw new ArrayOutOfRangeExceptions();
                }

        }

        private void grow() {
                array = copyOf(array, (int) (array.length * EXPAND_RATE));
        }

        private void resize() {
                array = copyOf(array, array.length / CUT_RATE);
        }

        // Поиск элемента.
        // Вернуть индекс элемента
        // или -1 в случае отсутствия.
        @Override
        public int indexOf(int item) {

                int index = -1;

                if (contains(item)) {
                        for (int i = 0; i < position; i++) {
                                if (array[i] == item) {
                                        index = i;
                                        break;
                                }
                        }
                }
            return index;
        }

        // Поиск элемента с конца.
        // Вернуть индекс элемента
        // или -1 в случае отсутствия.
        @Override
        public int lastIndexOf(int item) {
            int index = -1;

            if (contains(item)){
                for (int i = position - 1; i > 0; i--) {
                    if (array[i] == item) {
                        index = i;
                        break;
                    }
                }
            }

            return index;
        }

        // Получить элемент по индексу.
        // Вернуть элемент или исключение,
        // если выходит за рамки фактического
        // количества элементов.
        @Override
        public int get(int index) {
                if (index <= position + 1) {
                        return array[index];
                } else {
                        throw new ArrayOutOfRangeExceptions();
                }
        }

        // Добавление элемента
        // на определенную позицию списка.
        // Если выходит за пределы фактического
        // количества элементов или массива,
        // выбросить исключение.
        // Вернуть добавленный элемент
        // в качестве результата выполнения.
        @Override
        public int addByIndex(int index, int item) {
                if (index > position) {
                        throw new ArrayOutOfRangeExceptions();
                }

                if (position == array.length - 1) {
                        grow();
                }

                int[] currArray = new int[position + 1];

                System.arraycopy(array, 0, currArray, 0, index);
                currArray = copyOf(currArray, position + 1);
                currArray[index] = item;
                System.arraycopy(array, index, currArray, index + 1, position - index);
                array = copyOf(currArray, array.length);

                position++;

                return array[index];

        }

        // Вернуть фактическое количество элементов.
        @Override
        public int size() {
                return position;
        }

        // Вернуть true,
        // если элементов в списке нет,
        // иначе false.
        @Override
        public boolean isEmpty() {
                return position == 0;
        }

        // Удалить все элементы из списка.
        @Override
        public void clear() {
                array = new int[array.length];
        }

        private void modifyArrayByIndex(int index) {
                int[] currArray = new int[index];

                System.arraycopy(array, 0, currArray, 0, index - 1);
                currArray = copyOf(currArray, array.length);
                System.arraycopy(array, index, currArray, index - 1, position - index);
                array = currArray;

                position--;

                if (array.length > INIT_SIZE && position < array.length / CUT_RATE) {
                        resize();
                }
        }

        // Проверка на существование элемента.
        // Вернуть true/false;
        @Override
        public boolean contains(int item) {
                int[] arrayForSearch = Arrays.copyOf(array, position);

                sort(arrayForSearch, 0, arrayForSearch.length - 1);

                return contains(arrayForSearch, item);

        }

        // Сравнить текущий список с другим.
        // Вернуть true/false или исключение,
        // если передан null.
        @Override
        public boolean equals(IntList otherList) {
                if (otherList == null) {
                        throw new NullObjectException();
                }

                int[] arrCopyArray = copyOf(array, position);
                int[] arrOtherList = copyOf(otherList.toArray(), otherList.size());

                return Arrays.equals(arrCopyArray, arrOtherList);
        }

        // Создать новый массив
        // из строк в списке
        // и вернуть его.
        @Override
        public int[] toArray() {
                return copyOf(array, array.length);
        }

        private static boolean contains(int[] arr, int element) {
                int min = 0;
                int max = arr.length - 1;

                while (min <= max) {
                        int mid = (min + max) / 2;

                        if (element == arr[mid]) {
                                return true;
                        }

                        if (element < arr[mid]) {
                                max = mid - 1;
                        } else {
                                min = mid + 1;
                        }
                }
                return false;
        }


}
