#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main()
{
	vector<string> v;

	string s;
	int sum = 0;

	while (cin >> s)
	{
		if (s == "C")
		{
			sum -= atoi(v.back().c_str());
			v.pop_back();
		}
		else if (s == "D")
		{
			int cur = 2 * atoi(v.back().c_str());
			v.push_back(to_string(cur));
			sum += cur;
		}
		else if (s == "+")
		{
			int cur = 0;
			int i = 1;
			for (vector<string>::reverse_iterator it = v.rbegin();it != v.rend() && i <= 2;it++, i++)
			{
				cur += atoi(it.operator*().c_str());
			}
			v.push_back(to_string(cur));
			sum += cur;
		}
		else
		{
			v.push_back(s);
			sum += atoi(s.c_str());
		}
	}

	cout << sum << endl;
	return 0;
}