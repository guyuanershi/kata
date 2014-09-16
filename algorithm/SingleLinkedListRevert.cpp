"""
Write a program to reverse the direction of a given singly-linked list. In other
words, after the reversal all pointers should now point backwards. Your algorithm
should take linear time.
"""

struct Node
{
	int data;
	Node* next;
};


void revers1(struct Node** head_ref)
{
	struct Node* prev = NULL;
	struct Node* current = *head_ref;
	struct Node* next;

	while (current != NULL)
	{
		next = current->next;
		current->next = prev;
		prev = current;
		current = next;
	}
	*head_ref = prev;
}

void revers2(struct Node** head_ref)
{
	struct Node* first = *head_ref;
	if (first == NULL) return;

	Node* rest = first->next;
	if (rest == NULL) return;

	revers2(&rest);

	first->next->next = first;

	first->next = NULL;

	*head_ref = next;
}

void push(struct Node** head_ref, int new_data)
{
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