package tests;

import com.company.instruments.customeCollection.CircularDoublyLinkedList;
import com.company.instruments.customeCollection.ICircularDoublyLinkedList;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CircularDoublyLinkedListTest {

    @Test
    void ShouldReturnLength0WhenListIsEmpty(){
        ICircularDoublyLinkedList circularDoublyLinkedList = new CircularDoublyLinkedList();

        int expected = 0;

        int actual = circularDoublyLinkedList.size();

        assertEquals(expected, actual);
    }

    //region Add tests

    @Test
    void ShouldReturnSizeOneWhenAddOneElementInList(){
        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();
        int expected = 1;

        boolean isOperationSuccess = circularDoublyLinkedList.add(1);
        int actual = circularDoublyLinkedList.size();

        assertTrue(isOperationSuccess);
        assertEquals(expected, actual);
    }

    @Test
    void ShouldReturnSizeTwoWhenAddTwoElementInList(){
        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();
        int expected = 2;

        circularDoublyLinkedList.add(1);
        boolean isOperationSuccess = circularDoublyLinkedList.add(1);

        int actual = circularDoublyLinkedList.size();

        assertTrue(isOperationSuccess);
        assertEquals(expected, actual);
    }

    //endregion

    //region Remove tests

    @Test
    void ShouldReturnSizeZeroWhenDeleteOneElementFromListWithOneElements(){
        ICircularDoublyLinkedList circularDoublyLinkedList = new CircularDoublyLinkedList();
        int expected = 0;
        circularDoublyLinkedList.add(1);

        boolean isOperationSuccess = circularDoublyLinkedList.remove(new Integer(1));
        int actual = circularDoublyLinkedList.size();

        assertTrue(isOperationSuccess);
        assertEquals(expected, actual);

    }

    @Test
    void ShouldReturnSizeZeroWhenDeleteByIndexOneElementFromListWithOneElements(){
        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();
        int expected = 42;
        circularDoublyLinkedList.add(42);

        int actual = circularDoublyLinkedList.remove(0);

        assertEquals(expected, actual);

    }

    @Test()
    void ShouldReturnIndexOutOfBoundsExceptionWhenDeleteByIndexWhenIndexOutOfBounds(){

        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> circularDoublyLinkedList.remove(42));
    }

    @Test
    void ShouldReturnSizeOneWhenDeletedElementIsNotInListWhenListWithOneElement(){
        ICircularDoublyLinkedList circularDoublyLinkedList = new CircularDoublyLinkedList();
        int expected = 1;
        circularDoublyLinkedList.add(1);

        boolean isOperationSuccess = circularDoublyLinkedList.remove(new Integer(2));
        int actual = circularDoublyLinkedList.size();

        assertFalse(isOperationSuccess);
        assertEquals(expected, actual);

    }

    @Test
    void ShouldReturnSizeOneWhenListWithOneNullElement(){
        ICircularDoublyLinkedList circularDoublyLinkedList = new CircularDoublyLinkedList();
        int expected = 0;
        circularDoublyLinkedList.add(null);

        boolean isOperationSuccess = circularDoublyLinkedList.remove(null);
        int actual = circularDoublyLinkedList.size();

        assertTrue(isOperationSuccess);
        assertEquals(expected, actual);

    }

    @Test
    void ShouldReturnSizeOneWhenDeleteOneElementFromListWithTwoElements(){
        ICircularDoublyLinkedList circularDoublyLinkedList = new CircularDoublyLinkedList();
        int expected = 1;
        circularDoublyLinkedList.add(1);
        circularDoublyLinkedList.add(1);

        boolean isOperationSuccess = circularDoublyLinkedList.remove(new Integer(1));
        int actual = circularDoublyLinkedList.size();

        assertTrue(isOperationSuccess);
        assertEquals(expected, actual);

    }

    //endregion

    //region Contains tests

    @Test
    void ShouldReturnTrueWhenElementIsInList(){
        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();
        circularDoublyLinkedList.add(1);

        boolean actual = circularDoublyLinkedList.contains(new Integer(1));

        assertTrue(actual);
    }

    @Test
    void ShouldReturnFalseWhenElementIsNotInList(){
        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();
        circularDoublyLinkedList.add(2);

        boolean actual = circularDoublyLinkedList.contains(new Integer(1));

        assertFalse(actual);
    }

    //endregion

    //region Get tests

    @Test
    void ShouldReturnElementWhenThisElementIsInList(){
        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();
        int expected = 42;
        circularDoublyLinkedList.add(42);

        Integer actual = circularDoublyLinkedList.get(0);

        assertEquals(expected, actual);
    }

    @Test()
    void ShouldReturnIndexOutOfBoundsExceptionWhenCanTryGetIndexWhenIndexOutOfBounds(){

        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();
        int expected = 42;
        circularDoublyLinkedList.add(42);

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> circularDoublyLinkedList.get(3));
    }

    //endregion

    //region Set tests

    @Test
    void ShouldReturnNewElement42WhenSetNewElementInList(){
        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();
        int expected = 42;
        int expectedOldVal = 21;
        circularDoublyLinkedList.add(expectedOldVal);

        int actualOldVal = circularDoublyLinkedList.set(0, 42);
        int actual = circularDoublyLinkedList.get(0);

        assertEquals(expected, actual);
        assertEquals(expectedOldVal, actualOldVal);
    }

    @Test()
    void ShouldReturnIndexOutOfBoundsExceptionWhenCanTrySetIndexWhenIndexOutOfBounds(){

        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> circularDoublyLinkedList.get(42));
    }

    //endregion

    //region IndexOf tests

    @Test
    void ShouldReturn0WhenElementIsFirstInList(){
        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();
        int expected = 0;
        circularDoublyLinkedList.add(42);
        circularDoublyLinkedList.add(43);
        circularDoublyLinkedList.add(44);

        int actual = circularDoublyLinkedList.indexOf(new Integer(42));

        assertEquals(expected, actual);
    }

    @Test
    void ShouldReturnMinus1WhenElementIsNotInList(){
        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();
        int expected = -1;
        circularDoublyLinkedList.add(42);

        int actual = circularDoublyLinkedList.indexOf(new Integer(21));

        assertEquals(expected, actual);
    }

    @Test
    void ShouldReturnLastIndexWhenElementIsLastInList(){
        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();
        int expected = 2;
        circularDoublyLinkedList.add(40);
        circularDoublyLinkedList.add(41);
        circularDoublyLinkedList.add(42);

        int actual = circularDoublyLinkedList.indexOf(new Integer(42));

        assertEquals(expected, actual);
    }

    //endregion

    //region ToArray tests

    @Test
    void ShouldReturnEmptyArrayWhenListIsEmpty(){
        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();
        Integer[] expected = new Integer[0];

        Object[] actual = circularDoublyLinkedList.toArray();

        assertTrue(equalsArray(expected, actual));
    }

    @Test
    void ShouldReturnArrayWhenList(){
        ICircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList();
        Integer[] expected = {1, 3};
        circularDoublyLinkedList.add(1);
        circularDoublyLinkedList.add(2);
        circularDoublyLinkedList.add(3);
        circularDoublyLinkedList.remove(new Integer(2));

        Object[] actual = circularDoublyLinkedList.toArray();

        assertTrue(equalsArray(expected, actual));
    }

    //endregion

    //region Find tests
    @Test
    public void test(){
        ICircularDoublyLinkedList<TestClass> circularDoublyLinkedList = new CircularDoublyLinkedList();
        String name = "name1";
        circularDoublyLinkedList.add(new TestClass("name1"));
        circularDoublyLinkedList.add(new TestClass("name2"));
        circularDoublyLinkedList.add(new TestClass("name3"));

        boolean actual = circularDoublyLinkedList.contains(x -> x.strAtr.equals(name));

        assertTrue(actual);
    }

    //endregion

    //region to array with predicate tests
//    @Test
//    public void testToArray(){
//        Integer[] expected = new Integer[] {1, 2, 3, 4};
//        ICircularDoublyLinkedList<TestClass> circularDoublyLinkedList = new CircularDoublyLinkedList();
//        circularDoublyLinkedList.add(new TestClass(1));
//        circularDoublyLinkedList.add(new TestClass(2));
//        circularDoublyLinkedList.add(new TestClass(3));
//        circularDoublyLinkedList.add(new TestClass(4));
//
//        Integer[] variable = new Integer[circularDoublyLinkedList.size()];
//
//        Integer[] actual = circularDoublyLinkedList.<Integer>toArray(variable, (x) ->  x.intAtr);
//
//        assertTrue(equalsArray(expected, actual));
//    }

    //endregion

    //region Select tests
//    @Test
//    public void testSelect(){
//        Integer[] expected = new Integer[] {1, 2, 3, 4};
//        ICircularDoublyLinkedList<TestClass> circularDoublyLinkedList = new CircularDoublyLinkedList();
//        circularDoublyLinkedList.add(new TestClass(1));
//        circularDoublyLinkedList.add(new TestClass(2));
//        circularDoublyLinkedList.add(new TestClass(3));
//        circularDoublyLinkedList.add(new TestClass(4));
//
//        Integer[] actual = circularDoublyLinkedList.<Integer>select((x) ->  x.intAtr);
//
//        assertTrue(equalsArray(expected, actual));
//    }

    //endregion

    //region private method for tests

    private boolean equalsArray(Integer[] a1, Object[] a2){
        if (a1.length != a2.length){
            return false;
        }

        for (int i = 0; i < a1.length; i++) {
            int element1 = a1[i];
            int element2 = (int) a2[i];

            if(element1 != element2){
                return false;
            }
        }

        return true;
    }

    //endregion

    private class TestClass{
        private String strAtr;
        private int intAtr;

        public TestClass(String strAtr) {
            this.strAtr = strAtr;
        }
        public TestClass(int intAtr){
            this.intAtr = intAtr;
        }

        public int getIntAtr() {
            return intAtr;
        }

        public void setIntAtr(int intAtr) {
            this.intAtr = intAtr;
        }

        public String getStrAtr() {
            return strAtr;
        }

        public void setStrAtr(String strAtr) {
            this.strAtr = strAtr;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestClass testClass = (TestClass) o;
            return intAtr == testClass.intAtr &&
                    Objects.equals(strAtr, testClass.strAtr);
        }

        @Override
        public int hashCode() {
            return Objects.hash(strAtr, intAtr);
        }
    }
}