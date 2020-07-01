#include <stdio.h>
#include <stdlib.h>

#include "student.h"
#include "func.h"

int main()
{
    while (1)
    {
        showMenus();
        execMenu();
    }
    getchar();
    return 0;
}