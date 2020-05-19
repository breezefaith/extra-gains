#include "AVLTree.h"

void testCreatorFromArray(AVLTree& root) {
	int arr[] = { 1,2,5,14,7,3,10,12,8,6,9,11,15,4,13,16 };
	cout << "根据数组{ 1,2,5,14,7,3,10,12,8,6,9,11,15,4,13,16 }创建AVL" << endl;
	root = *(new AVLTree(arr, 16));
	cout << "创建结果：" << endl;
	root.InorderTraversal();
	cout << endl;
}

void testInsert(AVLTree& root) {
	cout << "插入 " << 11 << "：" << endl;
	root.Insert(11);
	root.InorderTraversal();
	cout << endl;

	cout << "插入 " << -3 << "：" << endl;
	root.Insert(-3);
	root.InorderTraversal();
	cout << endl;

	cout << "插入 " << 19 << "：" << endl;
	root.Insert(19);
	root.InorderTraversal();
	cout << endl;
}

void testSearch(AVLTree& root, int searchNode) {
	AVLNode* node = NULL;

	cout << "查找" << searchNode << "：" << endl;
	node = root.Search(searchNode);
	cout << "查找结果：";
	if (node == NULL) {
		cout << "未查到结果" << endl;
	}
	else {
		cout << node->data << endl;
	}
	cout << endl;
}

void testSearch(AVLTree& root) {
	testSearch(root, -1);
	testSearch(root, 11);
	testSearch(root, 3);
	testSearch(root, 6);
}

int main() {
	AVLTree root;

	testCreatorFromArray(root);

	testInsert(root);

	testSearch(root);

	system("pause");
}