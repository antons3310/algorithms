package ru.ashebalkin.skypro.course2.service;

import ru.ashebalkin.skypro.course2.exceptions.ArrayNullInsertAttemptException;
import ru.ashebalkin.skypro.course2.exceptions.ArrayOutOfRangeExceptions;
import ru.ashebalkin.skypro.course2.exceptions.ItemNotFoundException;
import ru.ashebalkin.skypro.course2.exceptions.NullObjectException;

import java.util.Arrays;
import java.util.List;


public class StringListImpl implements StringList {

        private String[] array;
        private static int position = 0;
        int INIT_SIZE = 8;

        public StringListImpl() {
                array = new String[INIT_SIZE];
        }


        // Добавление элемента.
        // Вернуть добавленный элемент
        // в качестве результата выполнения.
        @Override
        public String add(String item) {
                if (position == array.length - 1) {
                        resizeArray(array.length * 2);
                }
                array[position++] = item;
                return array[position];
        }

        private void resizeArray(int newLength) {
                array = Arrays.copyOf(array, newLength);
        }


        // Добавление элемента
        // на определенную позицию списка.
        // Если выходит за пределы фактического
        // количества элементов или массива,
        // выбросить исключение.
        // Вернуть добавленный элемент
        // в качестве результата выполнения.
        @Override
        public String add(int index, String item) {
                if (index == array.length - 1) {
                        return add(item);
                }

                if (index == 0 && position == array.length - 1) {
                        resizeArray(array.length * 2);
                }

                if (item == null) {
                        throw new ArrayNullInsertAttemptException();
                }

                if (index >= 0 && index <= position + 1) {
                        String[] arrayLeft = new String[index + 1];
                        String[] arrayRight = new String[array.length - index - 1];

                        System.arraycopy(array, 0, arrayLeft, 0, index);
                        System.arraycopy(array, index, arrayRight, 0, array.length - index - 1);

                        arrayLeft[index] = item;

                        array = Arrays.copyOf(arrayLeft, arrayLeft.length + arrayRight.length);

                        System.arraycopy(arrayRight, 0, array, arrayLeft.length, arrayRight.length);

                        return array[index];

                } else {
                        throw new ArrayOutOfRangeExceptions();
                }
        }

        // Установить элемент
        // на определенную позицию,
        // затерев существующий.
        // Выбросить исключение,
        // если индекс меньше
        // фактического количества элементов
        // или выходит за пределы массива.
        @Override
        public String set(int index, String item) {
                if (item == null) {
                        throw new ArrayNullInsertAttemptException();
                }

                if (index >= 0 && index <= position + 1) {
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
        public String remove(String item) {
                int deletedIndex = -1;

                if (item == null) {
                        throw new NullObjectException();
                }

                for (int i = 0; i < position; i++) {
                        if (array[i].equals(item)) {
                                deletedIndex = i;
                                break;
                        }
                }

                if (deletedIndex == -1) {
                        throw new ItemNotFoundException();
                }

                String[] arrayLeft = new String[deletedIndex];
                String[] arrayRight = new String[array.length - deletedIndex];

                System.arraycopy(array, 0, arrayLeft, 0, deletedIndex);
                System.arraycopy(array, deletedIndex + 1, arrayRight, 0, array.length - deletedIndex - 1);

                array = Arrays.copyOf(arrayLeft, arrayLeft.length + arrayRight.length);

                System.arraycopy(arrayRight, 0, array, arrayLeft.length, arrayRight.length);

                return item;

        }

        // Удаление элемента по индексу.
        // Вернуть удаленный элемент
        // или исключение, если подобный
        // элемент отсутствует в списке.
        @Override
        public void remove(int index) {

                if (array[index] == null) {
                        throw new ItemNotFoundException("Не найден элемент по переданному индексу");
                }

                if (index <= position + 1) {
                        String[] arrayLeft = new String[index];
                        String[] arrayRight = new String[array.length - index];

                        System.arraycopy(array, 0, arrayLeft, 0, index);
                        System.arraycopy(array, index + 1, arrayRight, 0, array.length - index - 1);

                        array = Arrays.copyOf(arrayLeft, arrayLeft.length + arrayRight.length);

                        System.arraycopy(arrayRight, 0, array, arrayLeft.length, arrayRight.length);

                } else {
                        throw new ArrayOutOfRangeExceptions();
                }

        }

        // Проверка на существование элемента.
        // Вернуть true/false;
        @Override
        public boolean contains(String item) {
                boolean result = false;

                if (item == null) {
                        throw new NullObjectException();
                }

                for (int i = 0; i < position; i++) {
                        if (array[i].equals(item)) {
                                result = true;
                                break;
                        }
                }
                return result;

        }

        // Поиск элемента.
        // Вернуть индекс элемента
        // или -1 в случае отсутствия.
        @Override
        public int indexOf(String item) {
                int index = -1;

                if (item == null) {
                        throw new NullObjectException();
                }

                for (int i = 0; i < position; i++) {
                        if (array[i].equals(item) && array[i] != null) {
                                index = i;
                                break;
                        }
                }
                return index;
        }

        // Поиск элемента с конца.
        // Вернуть индекс элемента
        // или -1 в случае отсутствия.
        @Override
        public int lastIndexOf(String item) {
                int index = -1;

                if (item == null) {
                        throw new NullObjectException();
                }

                for (int i = position - 1; i > 0; i--) {
                        if (array[i].equals(item)) {
                                index = i;
                                break;
                        }
                }
                return index;
        }

        // Получить элемент по индексу.
        // Вернуть элемент или исключение,
        // если выходит за рамки фактического
        // количества элементов.
        @Override
        public String get(int index) {
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
        public boolean equals(StringList otherList) {
                if (otherList == null) {
                        throw new NullObjectException();
                }

                return Arrays.equals(array, otherList.toArray());
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
                array = new String[array.length];
                for (int i = 0; i < array.length; i++) {
                        System.out.println("i = " + i);
                        System.out.println("array[i] = " + array[i]);
                }
                System.out.println("array.length = " + array.length);

        }

        // Создать новый массив
        // из строк в списке
        // и вернуть его.
        @Override
        public String[] toArray() {
                return Arrays.copyOf(array,array.length);
        }


}
