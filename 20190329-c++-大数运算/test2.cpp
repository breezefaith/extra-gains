#include <iostream>
#include <string>
using namespace std;

string idx = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
int getValue(char ch) //将字符c转换为相对应的数字
{
  if (ch >= '0' && ch <= '9')
    return ch - '0';
  else if (ch >= 'A' && ch <= 'Z')
    return ch - 'A' + 10;
  else
    return ch - 'a' + 36;
}
//将from进制下的字符串转换为to进制下的整数
string change(string s, int from, int to) {
  string res = "";
  int r, i, g, t, sum = 1, len = s.size();
  while (sum != 0) // sum作为循环结束标志
  {
    r = sum = 0;
    for (i = 0; i < len; i++) {
      t = getValue(s[i]);
      sum += t; //统计各位数字和
      g = t + from * r;
      s[i] = idx[g / to]; //存储该进制下的商
      r = g % to;         //除n取余
    }
    if (sum > 0)
      res = idx.substr(r, 1) + res; //先得到的余数放在低位
  }
  if (res == "") // result为空串表明转换得到的结果为0
    res = "0";
  return res;
}

int main() {
  string s;
  int from, to;
  // cin>>from>>to>>s;
  s = "202134241033040201033041030044242041011431012202332142";
  from = 5;
  to = 10;
  cout << from << " " << s << endl;
  string s2 = change(s, from, to);
  cout << to << " " << s2 << endl << endl;
  string s3 = change(s2, to, from);
  cout << s3 << endl;
  cout << (s3 == s) << endl;
  return 0;
}