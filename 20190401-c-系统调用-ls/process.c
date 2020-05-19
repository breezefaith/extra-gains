#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define __USE_GNU
#include <unistd.h>
#include <wait.h>
#include <fcntl.h>

int call_redirected(char *program, char *args[], char *outfile);

int main()
{
    char *args[] = {"ls", "-l", "/", NULL};
    call_redirected("/bin/ls", args, "ls-out.txt");
    return 0;
}

int call_redirected(char *program, char *args[], char *outfile)
{
    pid_t pid = fork();
    int status = -1;
    if (pid < 0)
    {
        perror("fork failed");
        return 0;
    }
    else if (pid == 0)
    {
        int oldfd = open(outfile, O_WRONLY | O_CREAT | O_TRUNC, 0666);
        int newfd;
        if (oldfd < 0)
        {
            perror("open failed");
            exit(1);
        }
        else
        {
            newfd = dup2(oldfd, STDOUT_FILENO);
            // newfd = 1;
            if (newfd < 0)
            {
                perror("dup2 failed");
                exit(1);
            }
            else
            {
                if (execve(program, args, environ))
                {
                    perror("execve failed");
                    exit(1);
                }
            }
        }
    }
    else
    {
        if (wait(&status) < 0)
        {
            perror("wait failed");
            return 0;
        }
        else
        {
            if (WIFEXITED(status) && WEXITSTATUS(status) == 0)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }
}