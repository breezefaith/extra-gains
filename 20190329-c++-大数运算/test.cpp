#include <iostream>
#include <string>

using namespace std;

void _reverse(string &srcNum) {
  size_t i = 0, j = srcNum.size() - 1;
  char temp = 0;
  while (i < j) {
    temp = srcNum[i];
    srcNum[i++] = srcNum[j];
    srcNum[j--] = temp;
  }
}
bool isZero(string &srcNum) {
  for (size_t i = 0; i < srcNum.size(); ++i) {
    if (srcNum[i] != '0')
      return false;
  }
  return true;
}
string m2n(string srcNum, int srcBase, int desBase) {
  if (srcBase > 62 || srcBase < 2 || desBase > 62 || desBase < 2)
    return "";
  char flag[63] =
      "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  string desNum, midNum;
  long long temp = 0, carry = 0;
  while (!srcNum.empty() && !isZero(srcNum)) {
    for (size_t i = 0; i < srcNum.size(); ++i) {
      if (srcNum[i] >= '0' && srcNum[i] <= '9')
        temp = srcNum[i] - '0';
      else if (srcNum[i] >= 'a' && srcNum[i] <= 'z')
        temp = srcNum[i] - 'a' + 10;
      else if (srcNum[i] >= 'A' && srcNum[i] <= 'Z')
        temp = srcNum[i] - 'A' + 36;
      else
        return "";
      carry = carry * srcBase + temp;
      if (carry / desBase != 0 || i == srcNum.size() - 1) {
        midNum.push_back(flag[carry / desBase]);
        carry %= desBase;
      }
    }
    desNum.push_back(flag[carry]);
    srcNum = midNum;
    carry = 0;
    midNum.clear();
  }
  _reverse(desNum);
  return desNum;
}

int main() {
  string s = "202134241033040201033041030044242041011431012202332142";
  string sc1 = m2n(s, 5, 10);
  string sc2 = m2n(sc1, 10, 5);
  cout << s <<endl;
  cout << sc1 << endl;
  cout << sc2 << endl;
  cout << (s == sc2) << endl;
  return 0;
}
