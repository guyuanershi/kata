#include "stdafx.h"
#include "TreeS.h"


typedef struct node *possition;
struct node
{
	possition parent; // pointer to parent node
	possition leftNode; // pointer to left child node
	possition rightNode; // pointer to right child node
	int value;
};

typedef struct node *tree; // pointer for whole tree

possition create_possition(int value)
{
	// create a new possition
	possition ps = (possition)malloc(sizeof(node));
	ps->value = value;
	ps->parent = NULL;
	ps->leftNode = NULL;
	ps->rightNode = NULL;
	return ps;
}

// here need referenc of the tree pointer
// because the pointer will be change to other place
void insert_possition(possition ps, tree& pTree)
{
	if (pTree == NULL)
	{
		pTree = ps;
		return;
	}

	// possiton value is less than tree's root value
	// then we need to add it to the left node
	if (ps->value <= pTree->value)
	{
		if (pTree->leftNode == NULL)
		{
			ps->parent = pTree;
			pTree->leftNode = ps;
		}
		else
		{
			insert_possition(ps, pTree->leftNode);
		}
	}
	else
	{
		if (pTree->rightNode == NULL)
		{
			ps->parent = pTree;
			pTree->rightNode = ps;
		}
		else
		{
			insert_possition(ps, pTree->rightNode);
		}
	}
}

possition find_possition(int value, tree pTree)
{
	if (pTree == NULL) return NULL;

	// value equals to the tree's root value
	if (pTree->value == value) return pTree;

	if (value <= pTree->value)
		find_possition(value, pTree->leftNode);
	else
		find_possition(value, pTree->rightNode);
}

possition find_min(tree pTree)
{
	if (pTree == NULL) return NULL;

	if (pTree->leftNode == NULL) return pTree;

	find_min(pTree->leftNode);
}

possition find_max(tree pTree)
{
	if (pTree == NULL) return NULL;

	if (pTree->rightNode == NULL) return pTree;

	find_max(pTree->rightNode);
}

bool is_root(possition ps)
{
	return ps->parent == NULL;
}

bool is_leaf(possition ps)
{
	return (ps->leftNode == NULL && ps->rightNode == NULL);
}

void delete_node(possition ps)
{
	if (ps == NULL) return;

	if (is_leaf(ps))
	{
		// delete the leaf
		// there need to do three things:
		// 1. release the parent's two children
		// 2. release the node's parent
		// 3. free the node
		ps->parent->leftNode = NULL;
		ps->parent->rightNode = NULL;
		ps->parent = NULL;
		free(ps);
	}
	else
	{
		// delete a node not leaf
		// so we need to find the max node of its left child or the min node of the its right node
		possition replacement = (ps->leftNode != NULL) ? find_max(ps->leftNode) : find_min(ps->rightNode);
		ps->value = replacement->value;
		delete_node(replacement);
	}
}

void print_sorted_tree(tree tr)
{
	if (tr == NULL) return;
	print_sorted_tree(tr->leftNode);
	printf("%d \n", tr->value);
	print_sorted_tree(tr->rightNode);
}

void main(void)
{
	tree tr = NULL;
	possition np;
	insert_possition(create_possition(18), tr);
	insert_possition(create_possition(5), tr);
	insert_possition(create_possition(2), tr);
	insert_possition(create_possition(8), tr);
	insert_possition(create_possition(81), tr);
	insert_possition(create_possition(101), tr);
	printf("Original:\n");
	print_sorted_tree(tr);

	np = find_possition(8, tr);
	if (np != NULL) {
		delete_node(np);
		printf("After deletion:\n");
		print_sorted_tree(tr);
	}
}