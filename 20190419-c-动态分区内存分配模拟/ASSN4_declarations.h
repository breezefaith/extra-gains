typedef struct
{

    int pid; // process name

    int status; // process status

    int (*segments)[4][2]; // segments

} Process;

Process get_input();

void done(void *x);

void printer(int (*p)[][2]); // for the example program. Dont use this