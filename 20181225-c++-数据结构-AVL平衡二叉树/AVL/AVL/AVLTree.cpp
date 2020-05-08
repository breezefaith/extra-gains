#include "AVLTree.h"

//���������ת
AVLNode * AVLTree::rotate_ll(AVLNode * root)
{
	AVLNode * tmp;
	tmp = root->left;
	root->left = tmp->right;
	tmp->right = root;
	return tmp;
}

//���������ת
AVLNode * AVLTree::rotate_rr(AVLNode * root)
{
	AVLNode* tmp;
	tmp = root->right;
	root->right = tmp->left;
	tmp->left = root;
	return tmp;
}

//�����ڲ���ת
AVLNode * AVLTree::rotate_lr(AVLNode * root)
{
	AVLNode * tmp;
	tmp = root->left;
	root->left = rotate_rr(tmp);
	return rotate_ll(root);
}

//�����ڲ���ת
AVLNode * AVLTree::rotate_rl(AVLNode * root)
{
	AVLNode * tmp;
	tmp = root->right;
	root->right = rotate_ll(tmp);
	return rotate_rr(root);
}

//������
int AVLTree::height(AVLNode * root)
{
	if (root == NULL)
		return 0;
	int lh = height(root->left);
	int rh = height(root->right);
	return lh > rh ? lh + 1 : rh + 1;
}

//��߶Ȳ�
int AVLTree::diff(AVLNode * root)
{
	if (root == NULL)
		return 0;
	return height(root->left) - height(root->right);
}

//ƽ�����
AVLNode * AVLTree::balance(AVLNode * root)
{
	int df = diff(root);
	if (df > 1) {
		if (diff(root->left) > 0) {
			root = rotate_ll(root);
		}
		else {
			root = rotate_lr(root);
		}
	}
	else if (df < -1) {
		if (diff(root->right) > 0) {
			root = rotate_rl(root);
		}
		else {
			root = rotate_rr(root);
		}
	}

	return root;
}

//����ڵ�
AVLNode * AVLTree::insert(AVLNode * root, const int & d)
{
	if (root == NULL)
	{
		root = new AVLNode(d);
		return root;
	}	//�ݹ鷵������
	else if (d < root->data) {
		root->left = insert(root->left, d);//�ݹ�������
		root = balance(root);//ƽ�����������������ת
	}
	else if (d > root->data)
	{
		root->right = insert(root->right, d);//�ݹ�������
		root = balance(root);//ƽ�����������������ת
	}
	return root;
}

//ɾ�����нڵ�
void AVLTree::deleteTree(AVLNode *root)
{
	if (NULL == root)
		return;
	deleteTree(root->left);
	deleteTree(root->right);
	delete root;
	root = NULL;
	return;
}

//����
AVLNode * AVLTree::search(AVLNode * const root, const int & d, int& times)
{
	times++;
	if (root == NULL) {
		return NULL;
	}
	if (d == root->data)
		return root;
	else if (d > root->data)
		return search(root->right, d, times);
	else
		return search(root->left, d, times);
}

void AVLTree::inorderTraversal(const AVLNode * root)
{
	if (root == NULL)
		return;
	inorderTraversal(root->left);
	cout << root->data << " ";
	inorderTraversal(root->right);
}

//���캯��
AVLTree::AVLTree()
{
	root = NULL;
}

AVLTree::AVLTree(int * arr, int n)
{
	root = NULL;
	for (int i = 0; i < n; i++) {
		root = insert(root, arr[i]);
	}
}

//��������
AVLTree::~AVLTree()
{
	deleteTree(root);
}

void AVLTree::Insert(const int & d)
{
	root = insert(root, d);
}

AVLNode * AVLTree::Search(const int & d)
{
	int times = 0;
	AVLNode* node = search(root, d, times);
	cout << "������ " << times << " ��" << endl;
	return node;
}

void AVLTree::InorderTraversal()
{
	inorderTraversal(root);
	cout << endl;
}
