package ru.ashebalkin.skypro.course2.service;

import ru.ashebalkin.skypro.course2.exceptions.*;

import java.util.Arrays;

import static java.util.Arrays.copyOf;


public class IntListImpl implements IntList {

        private int[] array;
        private static int position = 0;
        int INIT_SIZE = 20;
        int CUT_RATE = 4;

        public IntListImpl() {
                array = new int[INIT_SIZE];
        }


        // Добавление элемента.
        // Вернуть добавленный элемент
        // в качестве результата выполнения.
        @Override
        public int add(int item) {
                if (position == array.length - 1) {
                        resizeArray(array.length * 2);
                }
                array[position++] = item;
                return array[position];
        }

        private void resizeArray(int newLength) {
                array = copyOf(array, newLength);
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
                if (index > position ) {
                        throw new ArrayOutOfRangeExceptions();
                }

                resizeArray(array.length * 2);

                int[] arrayLeft = new int[index + 1];
                int[] arrayRight = new int[array.length - index - 1];

                System.arraycopy(array, 0, arrayLeft, 0, index);
                System.arraycopy(array, index, arrayRight, 0, array.length - index - 1);

                arrayLeft[index] = item;

                position++;

                array = copyOf(arrayLeft, arrayLeft.length + arrayRight.length);

                System.arraycopy(arrayRight, 0, array, arrayLeft.length, arrayRight.length);

                return array[index];


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

        private void modifyArrayByIndex(int index) {
                int[] arrayLeft = new int[index];
                int[] arrayRight = new int[array.length - index];

                System.arraycopy(array, 0, arrayLeft, 0, index);
                System.arraycopy(array, index + 1, arrayRight, 0, array.length - index - 1);

                array = copyOf(arrayLeft, arrayLeft.length + arrayRight.length);

                System.arraycopy(arrayRight, 0, array, arrayLeft.length, arrayRight.length);

                position--;

                if (array.length > INIT_SIZE && position < array.length / CUT_RATE) {
                    resizeArray(array.length / 2);
                }
        }

        // Проверка на существование элемента.
        // Вернуть true/false;
        @Override
        public boolean contains(int item) {
                int[] arrayForSearch = Arrays.copyOf(array, position);

                sort(arrayForSearch);

                return contains(arrayForSearch, item);

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

        // Сравнить текущий список с другим.
        // Вернуть true/false или исключение,
        // если передан null.
        @Override
        public boolean equals(IntList otherList) {
                if (otherList == null) {
                        throw new NullObjectException();
                }

                int [] arrCopyArray = Arrays.copyOf(array,position);

                return Arrays.equals(arrCopyArray, otherList.toArray());
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

        // Создать новый массив
        // из строк в списке
        // и вернуть его.
        @Override
        public int[] toArray() {
                return copyOf(array, position);
        }

        private static void sort(int[] arr) {
                for (int i = 1; i < arr.length; i++) {
                        int temp = arr[i];
                        int j = i;
                        while (j > 0 && arr[j - 1] >= temp) {
                                arr[j] = arr[j - 1];
                                j--;
                        }
                        arr[j] = temp;
                }
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
