// HeapSort3.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

int right(int i);
int left(int i);
void swap(int* a, int i, int largest);
void moveMaxHeap(int* a, int i, int length);
void maxHeap(int* a, int length);

int _tmain(int argc, _TCHAR* argv[])
{
	int a[] = { 8, 5, 4, 6, 13, 7, 1, 9, 12, 11 };
	int length = sizeof(a) / sizeof(int);
	maxHeap(a, length);

	return 0;
}

int left(int i)
{
	return i * 2 + 1;
}

int right(int i)
{
	return i * 2 + 2;
}

void swap(int* a, int i, int largest)
{
	int t = a[i];
	a[i] = a[largest];
	a[largest] = t;
}

void moveMaxHeap(int* a, int i, int length)
{
	int l = left(i);
	int r = right(i);
	int largest = i;

	if (l < length && a[l] > a[largest])
		largest = l;
	if (r < length && a[r] > a[largest])
		largest = r;

	if (largest != i)
	{
		swap(a, i, largest);
		moveMaxHeap(a, largest, length);
	}
}

void maxHeap(int* a, int length)
{
	for (int i = length / 2; i >= 0; i--)
	{
		moveMaxHeap(a, i, length);
	}
}
