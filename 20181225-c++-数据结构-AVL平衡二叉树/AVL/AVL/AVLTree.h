#include<iostream>
#include<cstdio>
using namespace std;

#pragma once
class AVLNode {
public:
	int data;
	AVLNode * left;
	AVLNode * right;
	AVLNode() :data(0), left(NULL), right(NULL) {}
	AVLNode(int d) :data(d), left(NULL), right(NULL) {}
};

class AVLTree
{
private:
	AVLNode* root;

	AVLNode* rotate_ll(AVLNode* root);
	AVLNode* rotate_rr(AVLNode* root);
	AVLNode* rotate_lr(AVLNode* root);
	AVLNode* rotate_rl(AVLNode* root);

	int height(AVLNode* root);//������
	int diff(AVLNode* root);//��߶Ȳ�
	AVLNode * balance(AVLNode *root);

	AVLNode * insert(AVLNode* root,const int& d);
	void deleteTree(AVLNode * root);
	AVLNode * search(AVLNode * const root, const int & d, int& times);
	void inorderTraversal(const AVLNode*root);
public:
	AVLTree();
	AVLTree(int* arr, int n);
	~AVLTree();
	void Insert(const int& d);//����ڵ�
	AVLNode* Search(const int& d);//���ҽڵ�
	void InorderTraversal();//�������
};

