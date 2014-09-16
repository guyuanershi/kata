"""
Write a program to reverse the direction of a given singly-linked list. 
In other words, after the reversal all pointers should now point backwards. 
Your algorithm should take linear time.
"""

struct Node
{
	int data;
	Node* next;
};

// using Node**, is because we will change the address of the pointer
void revers1(struct Node** head_ref)
{
	// prev is used to store the reverted list
	struct Node* prev = NULL;					// C style struct declaration, C++ style needn't 'struct' keyword anymore
	struct Node* current = *head_ref;
	struct Node* next;

	while (current != NULL)
	{
		// store the next
		next = current->next;
		// let the current link to the prev (revert)
		current->next = prev;
		// update prev list
		prev = current;
		// update current to stored next
		current = next;
	}
	// change the head
	*head_ref = prev;
}

// this is a iteration style
void revers2(struct Node** head_ref)
{
	struct Node* first = *head_ref;
	if (first == NULL) return;

	Node* rest = first->next;
	if (rest == NULL) return;

	revers2(&rest);

	// let the next->next to link to the first
	// thus, there is loop between first and next
	first->next->next = first;

	// here we will kill the loop by breaking first to next
	// and this will also change the list direction
	first->next = NULL;

	*head_ref = next;
}

void push(struct Node** head_ref, int new_data)
{
	// malloc is going to create a space for Node, and return (void*)
	// we cast it to (Node*)
	struct Node* new_node = (struct Node*) malloc(sizeof(struct Node));
	new_node->data = new_data;
	new_node->next = (*head_ref);
	(*head_ref) = new_node;
}

void printList(struct Node* head_ref)
{
	struct Node* temp = head_ref;
	while (temp != NULL)
	{
		printf("%d    ", temp->data);
		temp = temp->next;
	}
	printf("\n");
}


int main(int argc, _TCHAR* argv[])
{
	struct Node* head = NULL;

	push(&head, 1);
	push(&head, 2);
	push(&head, 3);

	revers1(&head);
	revers2(&head);

	printList(head);
	return 0;
}