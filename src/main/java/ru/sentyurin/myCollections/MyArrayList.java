package ru.sentyurin.myCollections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class MyArrayList<T> {

	private static final int INIT_CAPACITY = 16;

	private Object[] elements = new Object[INIT_CAPACITY];
	private int lastElementIndex = -1; // this value indicates list is empty
	private int capacity = INIT_CAPACITY;

	/**
	 * Adds a new element to the end of this list.
	 * 
	 * @param element an element to be added
	 */
	public void add(T element) {
		if (lastElementIndex + 1 == capacity) {
			increaseCapacity();
		}
		elements[++lastElementIndex] = element;
	}

	/**
	 * Adds a new element to the specified position of this list.
	 * 
	 * @param element an element to be added
	 * @param index   an index of the element to be retured
	 * @throws IndexOutOfBoundsException if index is greater than the index of last
	 *                                   element of this list or it is less than
	 *                                   zero
	 */
	public void add(T element, int index) {
		if (index > lastElementIndex + 1 || index < 0)
			throw new IndexOutOfBoundsException();
		if (lastElementIndex + 1 == capacity) {
			increaseCapacity();
		}
		for (int x = lastElementIndex; x >= index; x--) {
			elements[x + 1] = elements[x];
		}
		lastElementIndex++;
		elements[index] = element;
	}

	/**
	 * Returns an element at the specified position of this list.
	 * 
	 * @param index an index of the element to be retured
	 * @throws IndexOutOfBoundsException if index is greater than the index of last
	 *                                   element of this list or it is less than
	 *                                   zero
	 */
	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index > lastElementIndex || index < 0)
			throw new IndexOutOfBoundsException();
		return (T) elements[index];
	}

	/**
	 * Removes an element at the specified position of this list.
	 * 
	 * @param index an index of the element to be removed
	 * @throws IndexOutOfBoundsException if index is greater than the index of last
	 *                                   element of this list or it is less than
	 *                                   zero
	 */
	public void remove(int index) {
		if (index > lastElementIndex || index < 0)
			throw new IndexOutOfBoundsException();
		for (int x = index; x < lastElementIndex; x++) {
			elements[x] = elements[x + 1];
		}
		lastElementIndex--;
		if (lastElementIndex + 1 < capacity / 3 && capacity > INIT_CAPACITY) {
			decreaseCapacity();
		}
	}

	/**
	 * Replaces an element at the specified position of this list with new element.
	 * 
	 * @param element a new element to be placed in this list
	 * @param index   an index of the element to be removed
	 * @throws IndexOutOfBoundsException if index is greater than the index of last
	 *                                   element of this list or it is less than
	 *                                   zero
	 */
	public void replace(T element, int index) {
		if (index > lastElementIndex || index < 0)
			throw new IndexOutOfBoundsException();
		elements[index] = element;
	}

	/**
	 * Removes all elements in this list
	 * 
	 */
	public void clear() {
		elements = new Object[INIT_CAPACITY];
		lastElementIndex = -1;
	}

	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return Returns the number of elements in this list
	 */
	public int size() {
		return lastElementIndex + 1;
	}

	/**
	 * Sorts elements in this list using quicksort algorithm.
	 */
	public void sort() {
		sort(0, lastElementIndex);
	}

	/**
	 * Sorts elements in this list using quicksort algorithm and users comparator.
	 * 
	 * @param comparator a comparator using to compare elements
	 */
	public void sort(Comparator<T> comparator) {
		sort(0, lastElementIndex, comparator);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (lastElementIndex == -1)
			return "[]";
		StringBuffer result = new StringBuffer();
		result.append("[");
		for (int i = 0; i < lastElementIndex; i++) {
			result.append(elements[i].toString()).append(", ");
		}
		result.append(elements[lastElementIndex].toString()).append("]");

		return result.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		for (int i = 0; i <= lastElementIndex; i++) {
			result = prime * result + Objects.hash(elements[i]);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyArrayList<?> other = (MyArrayList<?>) obj;
		if (size() != other.size())
			return false;
		for (int i = 0; i <= lastElementIndex; i++) {
			if (!elements[i].equals(other.get(i)))
				return false;
		}
		return true;
	}

	private void increaseCapacity() {
		capacity = capacity * 2;
		elements = Arrays.copyOf(elements, capacity);
	}

	private void decreaseCapacity() {
		capacity = capacity / 2;
		elements = Arrays.copyOf(elements, capacity);
	}

	@SuppressWarnings("unchecked")
	private void sort(int leftBound, int rightBound, Comparator<T> comparator) {
		if (leftBound >= rightBound)
			return;
		MyArrayList<T> leftSublist = new MyArrayList<>();
		MyArrayList<T> rightSublist = new MyArrayList<>();
		T firstElement = (T) elements[leftBound];
		for (int i = leftBound + 1; i <= rightBound; i++) {
			T nextElement = (T) elements[i];
			if (comparator.compare(firstElement, nextElement) < 0) {
				rightSublist.add(nextElement);
			} else {
				leftSublist.add(nextElement);
			}
		}
		for (int i = 0; i < leftSublist.size(); i++) {
			elements[leftBound + i] = leftSublist.get(i);
		}
		elements[leftBound + leftSublist.size()] = firstElement;
		for (int i = 0; i < rightSublist.size(); i++) {
			elements[rightBound - i] = rightSublist.get(i);
		}
		sort(leftBound, leftBound + leftSublist.size() - 1, comparator);
		sort(leftBound + leftSublist.size() + 1, rightBound, comparator);
	}

	@SuppressWarnings("unchecked")
	private void sort(int leftBound, int rightBound) {
		if (leftBound >= rightBound)
			return;
		MyArrayList<T> leftSublist = new MyArrayList<>();
		MyArrayList<T> rightSublist = new MyArrayList<>();
		Comparable<T> firstElement = (Comparable<T>) elements[leftBound];
		for (int i = leftBound + 1; i <= rightBound; i++) {
			T nextElement = (T) elements[i];
			if (firstElement.compareTo(nextElement) < 0) {
				rightSublist.add(nextElement);
			} else {
				leftSublist.add(nextElement);
			}
		}
		for (int i = 0; i < leftSublist.size(); i++) {
			elements[leftBound + i] = leftSublist.get(i);
		}
		elements[leftBound + leftSublist.size()] = firstElement;
		for (int i = 0; i < rightSublist.size(); i++) {
			elements[rightBound - i] = rightSublist.get(i);
		}
		sort(leftBound, leftBound + leftSublist.size() - 1);
		sort(leftBound + leftSublist.size() + 1, rightBound);
	}

}
