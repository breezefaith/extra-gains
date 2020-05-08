#include <stdio.h>
#include <glob.h>

int main()
{
    glob_t globbuf;

    globbuf.gl_offs = 2;
    glob("*.c", GLOB_DOOFFS, NULL, &globbuf);
    glob("../*.c", GLOB_DOOFFS | GLOB_APPEND, NULL, &globbuf);
    globbuf.gl_pathv[0] = "ls";
    globbuf.gl_pathv[1] = "-l";
    execvp("ls", &globbuf.gl_pathv[0]);

    return 0;
}