#include "AVLTree.h"

//左左外侧旋转
AVLNode * AVLTree::rotate_ll(AVLNode * root)
{
	AVLNode * tmp;
	tmp = root->left;
	root->left = tmp->right;
	tmp->right = root;
	return tmp;
}

//右右外侧旋转
AVLNode * AVLTree::rotate_rr(AVLNode * root)
{
	AVLNode* tmp;
	tmp = root->right;
	root->right = tmp->left;
	tmp->left = root;
	return tmp;
}

//左右内侧旋转
AVLNode * AVLTree::rotate_lr(AVLNode * root)
{
	AVLNode * tmp;
	tmp = root->left;
	root->left = rotate_rr(tmp);
	return rotate_ll(root);
}

//右左内侧旋转
AVLNode * AVLTree::rotate_rl(AVLNode * root)
{
	AVLNode * tmp;
	tmp = root->right;
	root->right = rotate_ll(tmp);
	return rotate_rr(root);
}

//求树高
int AVLTree::height(AVLNode * root)
{
	if (root == NULL)
		return 0;
	int lh = height(root->left);
	int rh = height(root->right);
	return lh > rh ? lh + 1 : rh + 1;
}

//求高度差
int AVLTree::diff(AVLNode * root)
{
	if (root == NULL)
		return 0;
	return height(root->left) - height(root->right);
}

//平衡操作
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

//插入节点
AVLNode * AVLTree::insert(AVLNode * root, const int & d)
{
	if (root == NULL)
	{
		root = new AVLNode(d);
		return root;
	}	//递归返回条件
	else if (d < root->data) {
		root->left = insert(root->left, d);//递归左子树
		root = balance(root);//平衡操作包含了四种旋转
	}
	else if (d > root->data)
	{
		root->right = insert(root->right, d);//递归右子树
		root = balance(root);//平衡操作包含了四种旋转
	}
	return root;
}

//删除所有节点
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

//查找
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

//构造函数
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

//析构函数
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
	cout << "共查找 " << times << " 次" << endl;
	return node;
}

void AVLTree::InorderTraversal()
{
	inorderTraversal(root);
	cout << endl;
}
