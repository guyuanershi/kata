// heapsort2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>

using namespace std;

int left(int i);
int right(int i);
void swap(int* a, int largest, int i);
void maxHeapMove(int* a, int i, int length);
void maxHeap(int* a, int length);

int _tmain(int argc, _TCHAR* argv[])
{
	int a[] = { 8, 5, 4, 6, 13, 7, 1, 9, 12, 11 };
	int length = sizeof(a) / sizeof(int);
	maxHeap(a, length);

	for (int i = 0; i < length; i++)
	{
		printf("%d ", a[i]);
	}

	cin >> length;
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

void swap(int* a, int largest, int i)
{
	int t = a[largest];
	a[largest] = a[i];
	a[i] = t;
}

void maxHeapMove(int* a, int i, int length)
{
	int l = left(i);
	int r = right(i);
	int largest = i;

	if (l < length && a[largest] < a[l])
		largest = l;
	if (r < length && a[largest] < a[r])
		largest = r;

	if (largest != i)
	{
		swap(a, largest, i);
		maxHeapMove(a, largest, length);
	}
}

void maxHeap(int* a, int length)
{
	for (int i = length / 2 ; i >= 0; i--)
	{
		maxHeapMove(a, i, length);
	}
}

