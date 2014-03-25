// HeapShort.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

int* createMaxHeap(int* arry, int length);
int left(int i);
int right(int i);
void swap(int* arry, int a, int b);
void printHeap(int* arry, int length);
void maxHeap(int* arry, int i, int length);

int _tmain(int argc, _TCHAR* argv[])
{
	int arry[10] = { 8, 5, 4, 6, 13, 7, 1, 9, 12, 11 };

	//get array length
	int length = sizeof(arry) / sizeof(int);

	int* a = createMaxHeap(arry, length);
	//int _l = length;
	//for (int i = _l - 1; i > 0; i--)
	//{
	//	swap(arry, i, 0);
	//	_l--;
	//	maxHeap(a, 0, _l);
	//}
	printHeap(a, length);

	return 0;
}

void printHeap(int* arry, int length)
{
	for (int i = 0; i < length; i++)
	{
		printf("%d ", arry[i]);
	}
	printf("\n");
}

int left(int i)
{
	return i * 2 + 1;
}

int right(int i)
{
	return i * 2 + 2;
}

void maxHeap(int* arry, int i, int length)
{
	int l = left(i);
	int r = right(i);

	int largest = i;

	if (l < length && arry[l] > arry[i])
		largest = l;
	if (r < length && arry[r] > arry[i])
		largest = r;

	if (largest != i)
	{
		swap(arry, largest, i);
		maxHeap(arry, largest, length);
	}
}

void swap(int* arry, int a, int b)
{
	int _t = arry[b];
	arry[b] = arry[a];
	arry[a] = _t;
}


int* createMaxHeap(int* arry, int length)
{
	//if it is empty, return
	if (length == 0)
		return arry;

	for (int i = length / 2 ; i >= 0; i--)
	{
		maxHeap(arry, i, length);
	}

	return arry;
}
