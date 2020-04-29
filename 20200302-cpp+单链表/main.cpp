//
// Created by Ryan XIA on 2017/2/22.
//

#include "LinkedList.h"

#include <iostream>

/*Implement your Q2 here. Uncomment it when you finish Q1.
LinkedList* MergeList(LinkedList* list1, LinkedList* list2){
}
*/


int main() {
    auto * list = new LinkedList();
    list->append(1);
    list->append(2);
    list->append(3);
    list->append(4);
    list->display();


    list->insert(1,111);
    list->display();
    list->insert(4, 444);
    list->display();
    list->insert(3, 333);
    list->display();
    list->insert(6, 666);
    list->display();
    list->insert(8, 888);
    list->display();
    list->insert(9,999);
    list->display();
    list->insert(11,111111);




    list->remove(-1);
    list->display();
    list->remove(1);
    list->display();
    list->remove(3);
    list->display();
    list->remove(10);

    list->search(2);
    delete list;

/*Test case for Q2. Uncomment it when you finish Q1.
    auto * list1 = new LinkedList();
    list1->append(1);
    list1->append(3);
    list1->append(5);
    auto * list2 = new LinkedList();
    list2->append(2);
    list2->append(4);
    list2->append(6);
    list2->append(8);
    auto * MergedList = MergeList(list1, list2);
    MergedList->display();
    delete list1;
    delete list2;
    delete MergedList;
*/
    return 0;
}