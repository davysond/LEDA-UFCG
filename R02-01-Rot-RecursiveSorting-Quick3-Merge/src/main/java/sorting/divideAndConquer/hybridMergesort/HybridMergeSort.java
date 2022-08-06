package sorting.divideAndConquer.hybridMergesort;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> { 

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if(validacao(array, leftIndex, rightIndex));
		
		int size = array.length;
		 
		if(size>=0 && size<= SIZE_LIMIT) {	
			insertionSort(array,leftIndex,rightIndex);
		} 
		else { 
			
			if(leftIndex >= rightIndex) {  
				return;   
			}
			
			int middle = (leftIndex + rightIndex)/2; 
			sort(array, leftIndex, middle);
			sort(array, middle+1, rightIndex);
			
			merge(array, leftIndex, middle, rightIndex);
		}
	}

	private void insertionSort(T[] array, int leftIndex, int rightIndex) {
		
		for (int i = leftIndex+1; i <= rightIndex; i++) {
			int j = i;
			while(j>0 && array[j-1].compareTo(array[j]) > 0) {
				Util.swap(array, j-1, j);
				j--;
			}
		} 
		
		INSERTIONSORT_APPLICATIONS++;
	}

	private void merge(T[] array, int leftIndex, int middle, int rightIndex) {

		
		T[] aux = Arrays.copyOf(array, array.length);
		
		int begin = leftIndex;
		int half = middle+1;
		int control = leftIndex;
		
		while(begin<=middle && half<=rightIndex) {
			if(aux[begin].compareTo(aux[half]) > 0) {
				array[control] = aux[half];
				half++;
			}else {
				array[control] = aux[begin];
				begin++;
			}
			control++;
		}
		
		while(begin<=middle) {
			array[control] = aux[begin];
			control++;
			begin++;
		}
		
		while(half<=rightIndex) {
			array[control] = aux[half];
			control++;
			half++;
		}
		
		MERGESORT_APPLICATIONS++;
		 
	}

	private boolean validacao(T[] array, int leftIndex, int rightIndex) {

	if (array == null)
		return false;
	if (array.length == 0)
		return false;
	if (leftIndex < 0 || rightIndex < 0)
		return false;
	if (leftIndex >= rightIndex)
		return false;
	if (rightIndex >= array.length)
		return false;

	return true; 
}
}