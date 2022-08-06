package sorting.divideAndConquer.quicksort3;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
	
	if(validacao(array, leftIndex, rightIndex)) {
			
			if(leftIndex>=rightIndex) {
				return;
			}
			 
			int pivot = selectPivot(array, leftIndex, rightIndex);
			sort(array, leftIndex, pivot-1);
			sort(array, pivot+1, rightIndex);		 
		}
		
	}

	private int selectPivot(T[] array, int leftIndex, int rightIndex) {
		
		int i = leftIndex;
		int pivotIndex = pickPivotIndex(array,leftIndex,rightIndex);
		T pivot = array[pivotIndex];
		Util.swap(array, i, pivotIndex);
		
		
		for (int j = i+1 ; j <= rightIndex; j++) {
			if(array[j].compareTo(pivot) <= 0) {
				i++;
				util.Util.swap(array, i, j);
			}
		}
		  
		util.Util.swap(array, leftIndex, i);
		return i;
	}
	
	private int pickPivotIndex(T[] array, int leftIndex, int rightIndex) {
		
		//It will find the index of the element in the middle of the array.
		int mid = (leftIndex+rightIndex)/2;
		
		//Here we create a new array which is a copy of the one in the paramater but with size 3.
		T[] aux = Arrays.copyOf(array, 3);
		
		//Here we add the left element in the first position of the new array, the element in the middle in the second position and so on.
		aux[0] = array[leftIndex];
		aux[1] = array[mid];
		aux[2] = array[rightIndex];
		
		//Now we sort the helper array, so the element in the middle will be the one of our choice.
		Arrays.sort(aux, null);
		
		//return the index that we want.
		if(aux[1].compareTo(array[leftIndex]) == 0) return leftIndex;
		else if (aux[1].compareTo(array[mid]) == 0) return mid;
		else return rightIndex;

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