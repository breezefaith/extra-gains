/**  smsh1.c  small-shell version 1
 **		first really useful version after prompting shell
 **		this one parses the command line into strings
 **		uses fork, exec, wait, and ignores signals
 **/

#include	<stdio.h>
#include	<stdlib.h>
#include	<unistd.h>
#include	<signal.h>
#include	"smsh.h"
#include	"part1.h"

#define	DFL_PROMPT	"> "

int main()
{
	char	*cmdline, *prompt, **arglist;
	int	result;
	void	setup();

	int i = 0;
	int next_start = -1;
	char *argv1[10];
	char *argv2[10];

	prompt = DFL_PROMPT ;
	setup();

	while ( (cmdline = next_cmd(prompt, stdin)) != NULL ){
		if ( (arglist = splitline(cmdline)) != NULL  ){
			if(is_pipe(arglist) == -1)
			{
				result = execute(arglist);
			}else{
				parse_pipe(arglist,argv1,argv2);
				result = execute_with_pipe(argv1,argv2);
			}
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
	signal(SIGINT,  SIG_IGN);
	signal(SIGQUIT, SIG_IGN);
}

void fatal(char *s1, char *s2, int n)
{
	fprintf(stderr,"Error: %s,%s\n", s1, s2);
	exit(n);
}
