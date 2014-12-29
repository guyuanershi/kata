#include "stdafx.h"
#include "TreeL.h"
#include <stdlib.h>

typedef struct node* position;
typedef struct node
{
	position parent;
	position lchildren;
	position rchildren;
	int value;
	bool bDeleted;
};

typedef struct node* tree;

position create_position(int value)
{
	position ps = (position)malloc(sizeof(node));
	ps->parent = NULL;
	ps->lchildren = NULL;
	ps->rchildren = NULL;
	ps->value = value;
	ps->bDeleted = false;
	return ps;
}

void insert_position(int value, tree& pTree)
{
	if (pTree == NULL)
	{
		pTree = create_position(value);
		return;
	}

	if (value == pTree->value && pTree->bDeleted)
	{
		pTree->bDeleted = false;
	}
	else if (value <= pTree->value)
	{
		if (pTree->lchildren == NULL)
		{
			pTree->lchildren = create_position(value);
		}
		else
		{
			insert_position(value, pTree->lchildren);
		}
	}
	else
	{
		if (pTree->rchildren == NULL)
		{
			pTree->rchildren = create_position(value);
		}
		else
		{
			insert_position(value, pTree->rchildren);
		}
	}
}

position find_position(int value, tree pTree)
{
	if (pTree == NULL) return NULL;

	if (pTree->value == value && !pTree->bDeleted) return pTree;

	if (value <= pTree->value)
		find_position(value, pTree->lchildren);
	else
		find_position(value, pTree->rchildren);
}

position find_min(tree pTree)
{
	if (pTree == NULL) return NULL;

	position fs = pTree->lchildren;
	while (fs != NULL && !fs->bDeleted)
	{
		fs = fs->lchildren;
	}
	return fs;
}

position find_max(tree pTree)
{
	if (pTree == NULL) return NULL;

	position fs = pTree->rchildren;
	while (fs != NULL && !fs->bDeleted)
	{
		fs = fs->rchildren;
	}

	return fs;
}

bool is_root(position ps)
{
	if (ps == NULL) return false;
	return (ps->parent == NULL);
}

bool is_leaf(position ps)
{
	if (ps == NULL) return false;
	return (ps->lchildren == NULL && ps->rchildren == NULL);
}

void delete_position(int value, tree pTree)
{
	if (pTree == NULL) return;

	if (value == pTree->value)
	{
		pTree->bDeleted = true;
		return;
	}

	if (value <= pTree->value)
		delete_position(value, pTree->lchildren);
	else
		delete_position(value, pTree->rchildren);
}

