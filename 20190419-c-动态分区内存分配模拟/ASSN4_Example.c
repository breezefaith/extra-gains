#include <stdio.h>
#include <stdlib.h>
#include <memory.h>
#include "ASSN4_declarations.h"

#define STATUS_NEW 0
#define STATUS_IN 1
#define STATUS_OUT 2
#define STATUS_TERMINATE 3

//segments and length of each process_ptr
int seg_length[8][4][2] = {
    {{1, 5}, {2, 10}, {3, 3}, {4, 2}},
    {{1, 15}, {2, 10}, {3, 5}, {0, 0}},
    {{1, 5}, {2, 5}, {3, 5}, {0, 0}},
    {{1, 4}, {2, 4}, {0, 0}, {0, 0}},
    {{1, 15}, {2, 10}, {0, 0}, {0, 0}},
    {{1, 3}, {2, 6}, {3, 0}, {0, 0}},
    {{1, 5}, {0, 0}, {0, 0}, {0, 0}},
    {{1, 5}, {2, 9}, {3, 5}, {0, 0}},
};

/**
 * define process_ptr linked list node
 */
typedef struct process_list_node
{
    Process *process_ptr;
    struct process_list_node *next;
} process_list_node, *process_list_node_ptr;

/**
 * define initiallize process_ptr linked list function
 */
process_list_node_ptr initiallize_process_list();

/**
 * define segment node of system table and free table
 */
typedef struct table_node
{
    int start_pos;           //start postion
    int size;                //length of segment
    int pid;                 //process_ptr id
    int segid;               //segment id
    struct table_node *next; //pointer of next node

} table_node, *table_node_ptr;

/**
 * intiallize a segment table and set its head pointer
 */
table_node_ptr initiallize_busy_table();
table_node_ptr initiallize_free_table();

/**
 * fill up segment table of given process_ptr by first fit algorithm
 */
void first_fit(process_list_node_ptr process_head, table_node_ptr busy_head, table_node_ptr free_head, Process *process_ptr);

/**
 * swap out
 */
void swap_out(process_list_node_ptr process_head, table_node_ptr busy_head, table_node_ptr free_head, Process *process_ptr);

/**
 * swap in
 */
void swap_in(process_list_node_ptr process_head, table_node_ptr busy_head, table_node_ptr free_head, Process *process_ptr);

/**
 * terminate the given process_ptr
 */
void terminate(process_list_node_ptr process_head, table_node_ptr busy_head, table_node_ptr free_head, Process *process_ptr);

/**
 * print segments table of given process_ptr
 */
void print_segs_table(process_list_node_ptr process_head, table_node_ptr busy_head, Process *process_ptr);

// run with gcc ASSN4_Example.c ASSN4_grader.o -o ASSN4_Example; ./ASSN4_Example
int main()
{
    printf("%3s, %7s %20s\n", "pid", "status", "---------------segments---------------");
    
    /**
     * the head node of linked list of holes not in use and its pointer(with head pointer)
     */
    table_node_ptr free_head = NULL;

    /**
     * the head node of linked list of holes in use and its pointer(with head pointer)
     */
    table_node_ptr busy_head = NULL;

    /**
     * the head node of linked list of process_ptr
     */
    process_list_node_ptr process_head = NULL;

    busy_head = initiallize_busy_table();
    free_head = initiallize_free_table();
    process_head = initiallize_process_list();

    Process x = get_input();
    /* Call get_input function in a loop to get all the input
    Use the test x.pid!=-1 to see when the input is over */
    while (x.pid != -1)
    {
        printf("%3d, %7d, ", x.pid, x.status);
        switch (x.status)
        {
        case STATUS_NEW: //new
            first_fit(process_head, busy_head, free_head, &x);
            break;

        case STATUS_IN: //in
            swap_in(process_head, busy_head, free_head, &x);
            break;

        case STATUS_OUT: //out
            swap_out(process_head, busy_head, free_head, &x);
            break;

        case STATUS_TERMINATE: //terminate
            terminate(process_head, busy_head, free_head, &x);
            break;

        default:
            break;
        }
        print_segs_table(process_head, busy_head, &x);
        x = get_input();
    }

    return 0;
}

Process get_input()
{
    Process process;
    // status (0,1,2,3 representing new, in, out, terminated)
    //scanf("%d%d", &process_ptr.pid, &process_ptr.status);
    process.pid = 1;
    process.status = 0;
    process.segments = NULL;
    return process;
}

void done(void *x)
{
}

void printer(int (*p)[][2])
{
    if (p == NULL)
    {
        return;
    }
}

process_list_node_ptr initiallize_process_list()
{
    process_list_node process_head_node;
    process_list_node_ptr process_head = &process_head_node;

    process_head->process_ptr = NULL;
    process_head->next = NULL;

    return process_head;
}

table_node_ptr initiallize_busy_table()
{
    table_node busy_head_node;
    table_node_ptr busy_head = &busy_head_node;

    busy_head->start_pos = -1;
    busy_head->size = -1;
    busy_head->pid = -1;
    busy_head->segid = -1;
    busy_head->next = NULL;

    return busy_head;
}

table_node_ptr initiallize_free_table()
{
    table_node free_head_node;
    table_node_ptr free_head = &free_head_node;

    free_head->start_pos = -1;
    free_head->size = -1;
    free_head->pid = -1;
    free_head->segid = -1;
    free_head->next = NULL;

    table_node node;
    node.start_pos = 0;
    node.size = 200;
    node.pid = -1;
    node.segid = -1;
    node.next = NULL;

    free_head->next = &node;

    return free_head;
}

void first_fit(process_list_node_ptr process_head, table_node_ptr busy_head, table_node_ptr free_head, Process *process_ptr)
{
    process_list_node_ptr p_process = process_head->next;
    /* check if process_ptr has been in memory */
    while (p_process != NULL)
    {
        if (p_process->process_ptr->pid == process_ptr->pid)
        {
            break;
        }
        p_process = p_process->next;
    }

    if (p_process != NULL)
    {
        printf("process %d has been in memory\n", process_ptr->pid);
        return;
    }

    // initiallize segments table of the given process
    int seg_table[1][4][2] = {0};
    process_ptr->segments = seg_table;
    memset(process_ptr->segments, 0, sizeof(int) * 4 * 2 * 1);

    /* search enough space in free table for every segment of new process */
    int i = 0;
    //int **p_process_length = seg_length[process_ptr->pid - 1];
    for (i = 0; i < 4; i++)
    {
        /* (0,0) represents the end of segments like as i>=4 */
        if (seg_length[process_ptr->pid - 1][i][0] == 0 && seg_length[process_ptr->pid - 1][i][1] == 0)
        {
            break;
        }

        /* start search */
        table_node_ptr p_free_table = free_head->next;
        while (p_free_table != NULL)
        {
            /* search success */
            if (p_free_table->size >= seg_length[process_ptr->pid - 1][i][1])
            {
                break;
            }
            p_free_table = p_free_table->next;
        }

        if (p_free_table == NULL)
        {
            printf("there is no enough space for process %d's %d segment", process_ptr->pid, i + 1); //i is index,i+1 is segment number
            return;
        }

        /* allocate memory for new process's specific segment */
        table_node *new_busy_node = (table_node_ptr)malloc(sizeof(table_node));
        new_busy_node->pid = process_ptr->pid;
        new_busy_node->segid = i + 1;
        new_busy_node->size = seg_length[process_ptr->pid - 1][i][1];
        new_busy_node->start_pos = p_free_table->start_pos;

        table_node_ptr p_busy_table = busy_head;
        while (p_busy_table->next != NULL)
        {
            p_busy_table = p_busy_table->next;
        }
        p_busy_table->next = &new_busy_node;

        /* fill up process segments table */
        process_ptr->segments[0][i][0] = new_busy_node->size;
        process_ptr->segments[0][i][1] = new_busy_node->start_pos;

        /* shrink free table */
        p_free_table->start_pos = p_free_table->start_pos + new_busy_node->size;
        p_free_table->size = p_free_table->size - new_busy_node->size;
    }
}
void swap_out(process_list_node_ptr process_head, table_node_ptr busy_head, table_node_ptr free_head, Process *process_ptr)
{
    /* check if process_ptr has been in memory */
    process_list_node_ptr p_process = process_head;
    do
    {
        if (p_process->process_ptr->pid == process_ptr->pid)
        {
            break;
        }
        p_process = p_process->next;
    } while (p_process != NULL);

    if (p_process == NULL)
    {
        printf("process %d hasn't been in memory\n", process_ptr->pid);
        return;
    }

    //all segments table's item set -1
    int i = 0;
    for (i = 0; i < 4; i++)
    {
        // if (process_ptr->segments[0][i][0] == 0 && process_ptr->segments[0][i][0] == 0)
        // {
        //     continue;
        // }
        process_ptr->segments[0][i][0] = -1;
        process_ptr->segments[0][i][1] = -1;
    }

    //remove busy table item and update free table
    table_node_ptr p_busy_table = busy_head;
    while (p_busy_table->next != NULL)
    {
        //all items with pid equals to process's id in busy table should be removed
        if (p_busy_table->next->pid == process_ptr->pid)
        {
            p_busy_table->next = p_busy_table->next->next;
            table_node_ptr new_free_node = (table_node_ptr)malloc(sizeof(table_node));
            new_free_node->start_pos = p_busy_table->next->start_pos;
            new_free_node->size = p_busy_table->next->size;
            new_free_node->pid = -1;
            new_free_node->segid = -1;

            table_node_ptr p_free_table = free_head;
            while (p_free_table->next != NULL)
            {
                if (p_free_table != free_head)
                {
                    if (p_free_table->start_pos < new_free_node->start_pos && p_free_table->next == NULL)
                    {
                        new_free_node->next = NULL;
                        p_free_table->next = new_free_node;
                    }
                    else if (p_free_table->start_pos > new_free_node->start_pos && p_free_table->next == NULL)
                    {
                        table_node_ptr p_free_table2 = free_head;
                        while (p_free_table2 != NULL && p_free_table2->next != p_free_table)
                        {
                            p_free_table2 = p_free_table2->next;
                        }
                        new_free_node->next = p_free_table;
                        if (new_free_node->start_pos + new_free_node->size == p_free_table->start_pos)
                        {
                            new_free_node->size = new_free_node->size + p_free_table->size;
                            new_free_node->next = p_free_table->next;
                            free(p_free_table);
                        }
                        p_free_table2->next = new_free_node;
                    }
                    else if (p_free_table->start_pos < new_free_node->start_pos && p_free_table->next->start_pos > new_free_node->start_pos)
                    {
                        new_free_node->next = p_free_table->next;
                        if (new_free_node->start_pos + new_free_node->size == p_free_table->start_pos)
                        {
                            new_free_node->size = new_free_node->size + p_free_table->size;
                            new_free_node->next = p_free_table->next;
                            free(p_free_table);
                        }
                        p_free_table->next = new_free_node;
                    }
                }

                p_free_table = p_free_table->next;
            }

            free(p_busy_table->next);
        }
        p_busy_table = p_busy_table->next;
    }
}
void swap_in(process_list_node_ptr process_head, table_node_ptr busy_head, table_node_ptr free_head, Process *process_ptr)
{
    /* check if process_ptr has been in memory */
    process_list_node_ptr p_process = process_head;
    do
    {
        if (p_process->process_ptr->pid == process_ptr->pid)
        {
            break;
        }
        p_process = p_process->next;
    } while (p_process != NULL);

    if (p_process == NULL)
    {
        printf("process %d hasn't been in memory\n", process_ptr->pid);
        return;
    }

    if (p_process->process_ptr->status != STATUS_OUT)
    {
        printf("process %d isn't in swap-out status\n", process_ptr->pid);
        return;
    }

    memset(process_ptr->segments, 0, sizeof(int) * 4 * 2 * 1);

    /* search enough space in free table for every segment of new process */
    int i = 0;
    //int **p_process_length = seg_length[process_ptr->pid - 1];
    for (i = 0; i < 4; i++)
    {
        /* (0,0) represents the end of segments like as i>=4 */
        if (seg_length[process_ptr->pid - 1][i][0] == 0 && seg_length[process_ptr->pid - 1][i][1] == 0)
        {
            break;
        }

        /* start search */
        table_node_ptr p_free_table = free_head;
        do
        {
            /* search success */
            if (p_free_table->size >= seg_length[process_ptr->pid - 1][i][1])
            {
                break;
            }
            p_free_table = p_free_table->next;
        } while (p_free_table != NULL);

        if (p_free_table == NULL)
        {
            printf("there is no enough space for process %d's %d segment", process_ptr->pid, i + 1); //i is index,i+1 is segment number
            return;
        }

        /* allocate memory for new process's specific segment */
        table_node *new_busy_node = (table_node_ptr)malloc(sizeof(table_node));
        new_busy_node->pid = process_ptr->pid;
        new_busy_node->segid = i + 1;
        new_busy_node->size = seg_length[process_ptr->pid - 1][i][1];
        new_busy_node->start_pos = p_free_table->start_pos;

        table_node_ptr p_busy_table = busy_head;
        while (p_busy_table->next != NULL)
        {
            p_busy_table = p_busy_table->next;
        }
        p_busy_table->next = &new_busy_node;

        /* fill up process segments table */
        process_ptr->segments[0][i][0] = new_busy_node->size;
        process_ptr->segments[0][i][1] = new_busy_node->start_pos;

        /* shrink free table */
        p_free_table->start_pos = p_free_table->start_pos + new_busy_node->size;
        p_free_table->size = p_free_table->size - new_busy_node->size;
    }
}
void terminate(process_list_node_ptr process_head, table_node_ptr busy_head, table_node_ptr free_head, Process *process_ptr)
{
    /* check if process_ptr has been in memory */
    process_list_node_ptr p_process = process_head;
    do
    {
        if (p_process->process_ptr->pid == process_ptr->pid)
        {
            break;
        }
        p_process = p_process->next;
    } while (p_process != NULL);

    if (p_process == NULL)
    {
        printf("process %d hasn't been in memory\n", process_ptr->pid);
        return;
    }

    free(p_process->process_ptr->segments);

    //remove busy table item and update free table
    table_node_ptr p_busy_table = busy_head;
    while (p_busy_table->next != NULL)
    {
        //all items with pid equals to process's id in busy table should be removed
        if (p_busy_table->next->pid == process_ptr->pid)
        {
            p_busy_table->next = p_busy_table->next->next;
            table_node_ptr new_free_node = (table_node_ptr)malloc(sizeof(table_node));
            new_free_node->start_pos = p_busy_table->next->start_pos;
            new_free_node->size = p_busy_table->next->size;
            new_free_node->pid = -1;
            new_free_node->segid = -1;

            table_node_ptr p_free_table = free_head;
            while (p_free_table->next != NULL)
            {
                if (p_free_table != free_head)
                {
                    if (p_free_table->start_pos < new_free_node->start_pos && p_free_table->next == NULL)
                    {
                        new_free_node->next = NULL;
                        p_free_table->next = new_free_node;
                    }
                    else if (p_free_table->start_pos > new_free_node->start_pos && p_free_table->next == NULL)
                    {
                        table_node_ptr p_free_table2 = free_head;
                        while (p_free_table2 != NULL && p_free_table2->next != p_free_table)
                        {
                            p_free_table2 = p_free_table2->next;
                        }
                        new_free_node->next = p_free_table;
                        if (new_free_node->start_pos + new_free_node->size == p_free_table->start_pos)
                        {
                            new_free_node->size = new_free_node->size + p_free_table->size;
                            new_free_node->next = p_free_table->next;
                            free(p_free_table);
                        }
                        p_free_table2->next = new_free_node;
                    }
                    else if (p_free_table->start_pos < new_free_node->start_pos && p_free_table->next->start_pos > new_free_node->start_pos)
                    {
                        new_free_node->next = p_free_table->next;
                        if (new_free_node->start_pos + new_free_node->size == p_free_table->start_pos)
                        {
                            new_free_node->size = new_free_node->size + p_free_table->size;
                            new_free_node->next = p_free_table->next;
                            free(p_free_table);
                        }
                        p_free_table->next = new_free_node;
                    }
                }

                p_free_table = p_free_table->next;
            }

            free(p_busy_table->next);
        }
        p_busy_table = p_busy_table->next;
    }
}
void print_segs_table(process_list_node_ptr process_head, table_node_ptr busy_head, Process *process_ptr)
{
    /* check if process_ptr has been in memory */
    process_list_node_ptr p_process = process_head;
    do
    {
        if (p_process->process_ptr->pid == process_ptr->pid)
        {
            break;
        }
        p_process = p_process->next;
    } while (p_process != NULL);

    if (p_process == NULL)
    {
        printf("process %d hasn't been in memory\n", process_ptr->pid);
        return;
    }

    int i = 0;
    for (i = 0; i < 4; i++)
    {
        printf("(limit: %d, size: %d)\t", p_process->process_ptr->segments[0][i][0], p_process->process_ptr->segments[0][i][1]);
    }
}
