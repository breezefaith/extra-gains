/**  smsh1.c  small-shell version 1
 **		first really useful version after prompting shell
 **		this one parses the command line into strings
 **		uses fork, exec, wait, and ignores signals
 **/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>

#include "smsh.h"
#include "part1.h"
#include "part2.h"

#define DFL_PROMPT "> "

int main()
{
	char *cmdline, *prompt, **arglist;
	int result;
	void setup();

	int i = 0;
	int pipe_pos = -1;
	char *temp_argv1[10];
	char *temp_argv2[10];
	char *argv1[10];
	char *argv2[10];

	int in_pos1 = -1;
	int out_pos1 = -1;
	char in_file1[100] = "\0";
	char out_file1[100] = "\0";

	int in_pos2 = -1;
	int out_pos2 = -1;
	char in_file2[100] = "\0";
	char out_file2[100] = "\0";

	int inFds = -1;
	int outFds = -1;

	prompt = DFL_PROMPT;
	setup();

	while ((cmdline = next_cmd(prompt, stdin)) != NULL)
	{
		if ((arglist = splitline(cmdline)) != NULL)
		{
			// inFds = dup(STDIN_FILENO);
			// outFds = dup(STDOUT_FILENO);
			pipe_pos = is_pipe(arglist);
			if (pipe_pos == -1)
			{
				//no pipe
				in_pos1 = has_in_redir(arglist);
				out_pos1 = has_out_redir(arglist);
				if (in_pos1 == -2 || out_pos1 == -2)
				{
					perror("");
					continue;
				}
				parse_redir(arglist, argv1, in_pos1, in_file1, out_pos1, out_file1);
				result = execute_with_redir(argv1, in_file1, out_file1);
			}
			else
			{
				//with pipe
				parse_pipe(arglist, temp_argv1, temp_argv2);

				//fomat argv1
				in_pos1 = has_in_redir(temp_argv1);
				out_pos1 = has_out_redir(temp_argv1);
				if (in_pos1 == -2 || out_pos1 == -2)
				{
					perror("");
					continue;
				}
				parse_redir(temp_argv1, argv1, in_pos1, in_file1, out_pos1, out_file1);

				//format argv2
				in_pos2 = has_in_redir(temp_argv2);
				out_pos2 = has_out_redir(temp_argv2);
				if (in_pos2 == -2 || out_pos2 == -2)
				{
					perror("");
					continue;
				}
				parse_redir(temp_argv2, argv2, in_pos2, in_file2, out_pos2, out_file2);

				result = execute_with_pipe_and_redir(argv1, argv2, in_file1, out_file1, in_file2, out_file2);
			}
			in_pos1 = in_pos2 = out_pos1 = out_pos2 = -1;
			strcpy(in_file1, "\0");
			strcpy(in_file2, "\0");
			strcpy(out_file1, "\0");
			strcpy(out_file1, "\0");

			freelist(arglist);
		}
		free(cmdline);
	}
	return 0;
}

void setup()
/*
 * purpose: initialize shell
 * returns: nothing. calls fatal() if trouble
 */
{
	signal(SIGINT, SIG_IGN);
	signal(SIGQUIT, SIG_IGN);
}

void fatal(char *s1, char *s2, int n)
{
	fprintf(stderr, "Error: %s,%s\n", s1, s2);
	exit(n);
}
