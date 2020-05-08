#include <iostream>

using namespace std;

void solution();

int main()
{
    int T = 0;
    int t = 0;

    cin >> T;
    while (t < T)
    {
        solution();
        t++;
    }

    return 0;
}

void solution()
{
    int N = 0, M = 0;
    int i = 0, j = 0;
    int n = 0;
    char command;
    int a, b;
    int **event_relation = NULL;
    cin >> N >> M;

    //initiallize
    event_relation = new int *[M + 1];
    for (i = 1; i <= M; i++)
    {
        event_relation[i] = new int[M + 1];
        for (j = 1; j <= M; j++)
        {
            //-1: unset
            event_relation[i][j] = -1;
            if (i == j)
            {
                //1:same team
                event_relation[i][j] = 1;
            }
        }
    }

    while (n < N)
    {
        cin >> command >> a >> b;
        if (command == 'A')
        {
            if (event_relation[a][b] == -1)
            {
                cout << "Not sure yet." << endl;
            }
            else if (event_relation[a][b] == 0)
            {
                cout << "In different gangs." << endl;
            }
            else
            {
                cout << "In the same gang." << endl;
            }
        }
        else if (command == 'D')
        {
            event_relation[a][b] = event_relation[b][a] = 0;
            for (i = 1; i <= M; i++)
            {
                if (event_relation[i][a] == 1 || event_relation[i][b] == 0)
                {
                    event_relation[i][b] = event_relation[b][i] = 0;
                    event_relation[i][a] = event_relation[a][i] = 1;
                }
                else if (event_relation[i][a] == 0 || event_relation[i][b] == 1)
                {
                    event_relation[i][b] = event_relation[b][i] = 1;
                    event_relation[i][a] = event_relation[a][i] = 0;
                }
            }
        }

        n++;
    }
}