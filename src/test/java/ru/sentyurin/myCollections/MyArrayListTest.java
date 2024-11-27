package ru.sentyurin.myCollections;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {

	private static final int LIST_SISE_IN_EFFICIENCY_TESTS = 1000;
	private static final int MILLISECONDS_TIMEOUT_FOR_EFFICIENCY_TESTS = 10;

	@Test
	@Timeout(value = MILLISECONDS_TIMEOUT_FOR_EFFICIENCY_TESTS, unit = TimeUnit.MILLISECONDS)
	public void additionShouldWorkEfficiently() {
		Random random = new Random();
		MyArrayList<Integer> list = new MyArrayList<>();
		for (int i = 0; i < LIST_SISE_IN_EFFICIENCY_TESTS; i++) {
			list.add(random.nextInt());
		}
	}

	@Test
	@Timeout(value = MILLISECONDS_TIMEOUT_FOR_EFFICIENCY_TESTS, unit = TimeUnit.MILLISECONDS)
	public void additionToSpecifiedIndexShouldWorkEfficiently() {
		Random random = new Random();
		MyArrayList<Integer> list = new MyArrayList<>();
		for (int i = 1; i <= LIST_SISE_IN_EFFICIENCY_TESTS; i++) {
			list.add(random.nextInt(), random.nextInt(0, i));
		}
	}

	@Test
	@Timeout(value = MILLISECONDS_TIMEOUT_FOR_EFFICIENCY_TESTS, unit = TimeUnit.MILLISECONDS)
	public void removalOfFirstElementShouldWorkEfficiently() {
		MyArrayList<Integer> list = new MyArrayList<>();
		for (int i = 0; i < LIST_SISE_IN_EFFICIENCY_TESTS; i++) {
			list.add(1);
		}
		for (int i = 0; i < LIST_SISE_IN_EFFICIENCY_TESTS; i++) {
			list.remove(0);
		}
	}

	@Test
	@Timeout(value = MILLISECONDS_TIMEOUT_FOR_EFFICIENCY_TESTS, unit = TimeUnit.MILLISECONDS)
	public void removalOfLastElementShouldWorkEfficiently() {
		MyArrayList<Integer> list = new MyArrayList<>();
		for (int i = 0; i < LIST_SISE_IN_EFFICIENCY_TESTS; i++) {
			list.add(1);
		}
		for (int i = 0; i < LIST_SISE_IN_EFFICIENCY_TESTS; i++) {
			list.remove(list.size() - 1);
		}
	}

	@Test
	@Timeout(value = MILLISECONDS_TIMEOUT_FOR_EFFICIENCY_TESTS, unit = TimeUnit.MILLISECONDS)
	public void replacementShouldWorkEfficiently() {
		Random random = new Random();
		MyArrayList<Integer> list = new MyArrayList<>();
		IntStream.range(0, LIST_SISE_IN_EFFICIENCY_TESTS).forEach(list::add);
		for (int i = 0; i < LIST_SISE_IN_EFFICIENCY_TESTS; i++) {
			list.replace(random.nextInt(), random.nextInt(0, list.size()));
		}
	}

	@Test
	@Timeout(value = MILLISECONDS_TIMEOUT_FOR_EFFICIENCY_TESTS, unit = TimeUnit.MILLISECONDS)
	public void sortingShouldWorkEfficiently() {
		Random random = new Random();
		MyArrayList<Integer> list = new MyArrayList<>();
		IntStream.range(0, LIST_SISE_IN_EFFICIENCY_TESTS).forEach(i -> list.add(random.nextInt()));
		list.sort();
	}

	@Test
	public void getShouldReturnCorrectValue() {
		MyArrayList<Integer> list = new MyArrayList<>();
		for (int i = 0; i < LIST_SISE_IN_EFFICIENCY_TESTS; i++) {
			list.add(i);
		}
		for (int i = 0; i < LIST_SISE_IN_EFFICIENCY_TESTS; i++) {
			assertEquals(i, list.get(i));
		}
	}

	@Test
	public void sizeShouldReturnCorrectValue() {
		MyArrayList<Integer> list = new MyArrayList<>();
		assertEquals(0, list.size());
		for (int i = 1; i <= LIST_SISE_IN_EFFICIENCY_TESTS; i++) {
			list.add(i);
			assertEquals(i, list.size());
		}
	}

	@Test
	public void gettingWithIndexOutOfBoundsShouldThrowException() {
		MyArrayList<Integer> list = new MyArrayList<>();
		IntStream.range(0, 3).forEach(list::add);
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
	}

	@Test
	public void removalWithIndexOutOfBoundsShouldThrowException() {
		MyArrayList<Integer> list = new MyArrayList<>();
		IntStream.range(0, 3).forEach(list::add);
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
	}

	@Test
	public void replacementWithIndexOutOfBoundsShouldThrowException() {
		MyArrayList<Integer> list = new MyArrayList<>();
		IntStream.range(0, 3).forEach(list::add);
		assertThrows(IndexOutOfBoundsException.class, () -> list.replace(5, list.size()));
		assertThrows(IndexOutOfBoundsException.class, () -> list.replace(5, -1));
	}

	@Test
	public void additionWithIndexOutOfBoundsShouldThrowException() {
		MyArrayList<Integer> list = new MyArrayList<>();
		IntStream.range(0, 3).forEach(list::add);
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(7, list.size() + 2));
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(7, -1));
	}

	@Test
	public void replacementShoulWorkCorrrectly() {
		MyArrayList<String> list = new MyArrayList<>();
		IntStream.range(0, 3).forEach(i -> list.add(""));
		list.replace("1", 0);
		list.replace("2", 1);
		list.replace("3", 2);

		MyArrayList<String> listToCompare = new MyArrayList<>();
		IntStream.range(1, 4).forEach(i -> listToCompare.add(Integer.toString(i)));

		assertEquals(listToCompare, list);
	}

	@Test
	public void clearShouldWorkCorrectly() {
		MyArrayList<String> list = new MyArrayList<>();
		IntStream.range(0, 1000).forEach(i -> list.add(""));
		list.clear();
		assertEquals(0, list.size());
		assertEquals(new MyArrayList<String>(), list);
	}

	@Test
	public void sortShouldWorkCorrectly() {
		MyArrayList<String> list = new MyArrayList<>();
		list.add("Slava");
		list.add("Ann");
		list.add("Ilia");
		list.add("Andrey");
		list.sort();

		MyArrayList<String> listToCompare = new MyArrayList<>();
		listToCompare.add("Andrey");
		listToCompare.add("Ann");
		listToCompare.add("Ilia");
		listToCompare.add("Slava");

		assertEquals(listToCompare, list);
	}

	@Test
	public void sortWithComparatorShouldWorkCorrectly() {
		Comparator<String> comparator = (a, b) -> a.length() - b.length();

		MyArrayList<String> list = new MyArrayList<>();
		list.add("Slava");
		list.add("Ann");
		list.add("Ilia");
		list.add("Andrey");
		list.sort(comparator);

		MyArrayList<String> listToCompare = new MyArrayList<>();
		listToCompare.add("Ann");
		listToCompare.add("Ilia");
		listToCompare.add("Slava");
		listToCompare.add("Andrey");

		assertEquals(listToCompare, list);
	}

	@Test
	public void toStringShouldWorkCorrectly() {
		MyArrayList<Integer> list = new MyArrayList<>();
		assertEquals("[]", list.toString());
		IntStream.range(0, 3).forEach(list::add);
		assertEquals("[0, 1, 2]", list.toString());
	}

	@Test
	public void equalsMethodShoulFullfillTheContract() {
		MyArrayList<Integer> list = new MyArrayList<>();
		IntStream.range(0, 3).forEach(list::add);
		MyArrayList<Integer> equalList = new MyArrayList<>();
		IntStream.range(0, 3).forEach(equalList::add);
		MyArrayList<Integer> unequalList = new MyArrayList<>();
		IntStream.range(1, 4).forEach(unequalList::add);
		assertTrue(list.equals(list));
		assertFalse(list.equals(null));
		assertFalse(list.equals("[0, 1, 2]"));
		assertFalse(list.equals(new MyArrayList<String>()));
		assertTrue(list.equals(equalList));
		assertFalse(list.equals(unequalList));
	}

	@Test
	public void hashcodeMethodShouldReturnEqualValuesForEqualObjects() {
		MyArrayList<Integer> list = new MyArrayList<>();
		IntStream.range(0, 3).forEach(list::add);
		MyArrayList<Integer> equalList = new MyArrayList<>();
		IntStream.range(0, 3).forEach(equalList::add);
		assertEquals(list, equalList);
		assertEquals(list.hashCode(), equalList.hashCode());
	}

}
