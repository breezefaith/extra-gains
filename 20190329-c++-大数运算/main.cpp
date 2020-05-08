#include <algorithm>
#include <cmath>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <sstream>
#include <string>

using namespace std;

/* function list */
string to_String(long long int n);
string conversion(string s, int from, int to);
string _multiply(string num1, string num2);
string __mul(string num1, string num2);
string _addition(string num1, string num2);
string _sub(string num1, string num2);
string _changeDigt(string num, int n);
void swap(string &a, string &b);
string addition(string i1, string i2, int b);
string multiply(string i1, string i2, int b);
string divid(string i1, string i2, int b);

string to_String(long long int n) {
  char buf[200];
  string s;
  sprintf(buf, "%lld", n);
  s = buf;
  return s;
}

// convert s1 in d1-scale to s2 in d2-scale
string idx = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
int getValue(char ch) {
  if (ch >= '0' && ch <= '9')
    return ch - '0';
  else if (ch >= 'A' && ch <= 'Z')
    return ch - 'A' + 10;
  else
    return ch - 'a' + 36;
}

string conversion(string s, int from, int to) {
  string res = "";
  int r, i, g, t, sum = 1, len = s.size();
  while (sum != 0) {
    r = sum = 0;
    for (i = 0; i < len; i++) {
      t = getValue(s[i]);
      sum += t;
      g = t + from * r;
      s[i] = idx[g / to];
      r = g % to;
    }
    if (sum > 0)
      res = idx.substr(r, 1) + res;
  }
  if (res == "")
    res = "0";
  return res;
}

string _multiply(string num1, string num2) {
  int i, size, n;
  string mul;
  int begin1 = 0, begin2 = 0;

  if (num1[0] == '-') {
    begin1 = 1;
    num1[0] = '0';
  }
  if (num2[0] == '-') {
    begin2 = 1;
    num2[0] = '0';
  }
  size = (num1.length() - begin1 > num2.length() - begin2)
             ? num1.length() - begin1
             : num2.length() - begin2;

  if (num1.length() - begin1 < num2.length() - begin2) {
    for (i = num1.length() - begin1; i < num2.length() - begin2; i++)
      num1 = '0' + num1;
  } else if (num1.length() - begin1 > num2.length() - begin2) {
    for (i = num2.length() - begin2; i < num1.length() - begin1; i++)
      num2 = '0' + num2;
  }

  for (i = 0; i < size; i++) {
    if (size < pow(2, i) || size == pow(2, i)) {
      n = i;
      break;
    }
  }
  for (i = size; i < pow(2, n); i++) {
    num1 = '0' + num1;
    num2 = '0' + num2;
  }

  if (begin1 == 1)
    num1[0] = '-';

  if (begin2 == 1)
    num2[0] = '-';

  mul = __mul(num1, num2);

  string::iterator it = mul.end();

  if (mul[mul.length() - 1] == 0)
    mul = "0";
  else {
    string::iterator it = mul.begin();
    if (mul[0] == '-')
      it++;
    for (; *it == '0';) {
      if (*it == '0')
        it = mul.erase(it);
      else
        ++it;
    }
  }

  return mul;
}

string __mul(string num1, string num2) {
  string mul = "";
  bool Isneg = false;
  int begin1 = 0, begin2 = 0, n;
  int size1 = num1.length(), size2 = num2.length();
  int i;

  if (num1[0] == '-')
    size1--, begin1++;
  if (num2[0] == '-')
    size2--, begin2++;

  int size = (size1 > size2) ? size1 : size2;

  if (num1[0] == '-' && num2[0] != '-')
    Isneg = true;
  else if (begin1 != 1 && begin2 == 1)
    Isneg = true;

  if (size == 0)
    return NULL;
  else if (size == 1) {
    int k = (num1[begin1] - '0') * (num2[begin2] - '0');
    stringstream stream;
    string str1;
    stream << k;
    stream >> str1;
    mul += str1;
    if (Isneg)
      mul = '-' + mul;
    return mul;
  }
  string A = num1.substr(begin1, size / 2);
  string B = num1.substr(size / 2 + begin1, size - size / 2);
  string C = num2.substr(begin2, size / 2);
  string D = num2.substr(size / 2 + begin2, size - size / 2);
  string AC = __mul(A, C);
  string BD = __mul(B, D);
  string AB = _sub(A, B);
  string DC = _sub(D, C);
  string M0 = __mul(AB, DC);
  string M1 = _addition(M0, AC);
  string M2 = _addition(M1, BD);
  string P0 = _changeDigt(AC, size);
  string R0 = _changeDigt(M2, size / 2);
  string W0 = _addition(P0, R0);
  mul = _addition(W0, BD);
  if (Isneg)
    mul = '-' + mul;
  return mul;
}
string _addition(string num1, string num2) {
  string sum, temp;
  string temp1 = num1, temp2 = num2;
  int tempSum, carry = 0;
  bool overcarry = false, Isneg = false;
  int size = (num1.length() > num2.length()) ? num1.length() : num2.length();
  int i, j = 0;

  if (num1[0] == '-' && num2[0] == '-') {
    num1[0] = '0';
    num2[0] = '0';
    Isneg = true;
  } else if (num1[0] == '-' && num2[0] != '-') {
    num1[0] = '0';
    return _sub(num2, num1);
  } else if (num2[0] == '-' && num1[0] != '-') {
    num2[0] = '0';
    return _sub(num1, num2);
  }

  for (i = num1.length() - 1, j = 0; i >= 0; i--, j++)
    temp1[j] = num1[i];
  for (i = num2.length() - 1, j = 0; i >= 0; i--, j++)
    temp2[j] = num2[i];

  if (num1.length() < num2.length()) {
    sum = num2;
    temp = num2;
    for (i = num1.length(); i < num2.length(); i++)
      temp1 += '0';
  } else if (num1.length() > num2.length()) {
    sum = num1;
    temp = num1;
    for (i = num2.length(); i < num1.length(); i++)
      temp2 += '0';
  } else {
    sum = num1;
    temp = num1;
  }

  for (i = 0; i < size; i++) {
    tempSum = temp1[i] - '0' + temp2[i] - '0' + carry;

    if (tempSum > 9) {
      if (i == (size - 1))
        overcarry = true;
      carry = 1;
      sum[i] = tempSum - 10 + '0';
    } else {
      carry = 0;
      sum[i] = tempSum + '0';
    }
  }
  if (overcarry) {
    string str;
    str = carry + '0';
    sum += str;
    temp += '0';
    size++;
  }

  for (i = 0, j = sum.length() - 1; i < sum.length(), j >= 0; i++, j--) {
    temp[i] = sum[j];
  }

  for (i = 0; i < sum.length(); i++)
    sum[i] = temp[i];

  if (Isneg) {
    return "-" + sum;
  }
  return sum;
}

string _sub(string num1, string num2) {
  string minus, temp;
  string temp1 = num1, temp2 = num2;
  int tempMinus, Dec = 0;
  bool overDec = false, Isneg = false;
  int size = (num1.length() > num2.length()) ? num1.length() : num2.length();
  int i, j = 0;

  if (num1[0] == '-' && num2[0] == '-') {
    num2[0] = '0';
    num1[0] = '0';
    return _sub(num2, num1);
  } else if (num1[0] == '-' && num2[0] != '-') {
    string str = "-";
    num2 = str + num2;
    return _addition(num1, num2);
  } else if (num1[0] != '-' && num2[0] == '-') {
    num2[0] = '0';
    _addition(num1, num2);
  }

  for (i = num1.length() - 1, j = 0; i >= 0; i--, j++)
    temp1[j] = num1[i];
  for (i = num2.length() - 1, j = 0; i >= 0; i--, j++)
    temp2[j] = num2[i];

  if (num1.length() < num2.length()) {
    minus = num2;
    temp = num2;
    for (i = num1.length(); i < num2.length(); i++)
      temp1 += '0';
  } else if (num1.length() > num2.length()) {
    minus = num1;
    temp = num1;
    for (i = num2.length(); i < num1.length(); i++)
      temp2 += '0';
  } else {
    minus = num1;
    temp = num1;
  }

  for (i = size - 1; i >= 0; i--) {
    if (temp1[i] > temp2[i]) {
      break;
    } else if (temp1[i] < temp2[i]) {
      Isneg = true;
      swap(temp1, temp2);
      break;
    }
  }

  for (i = 0; i < size; i++) {
    tempMinus = (temp1[i] - '0') - (temp2[i] - '0') + Dec;

    if (tempMinus < 0) {
      if (i == (size - 1))
        overDec = true;
      Dec = -1;
      minus[i] = tempMinus + 10 + '0';
    } else {
      Dec = 0;
      minus[i] = tempMinus + '0';
    }
  }

  for (i = 0, j = size - 1; i < size, j >= 0; i++, j--)
    temp[i] = minus[j];

  for (i = 0; i < size; i++)
    minus[i] = temp[i];

  if (Isneg) {
    return "-" + minus;
  }
  return minus;
}
string _changeDigt(string num, int n) {
  if (num == "0")
    return num;
  else {
    for (int i = 0; i < n; i++)
      num += '0';
  }
  return num;
}
void swap(string &a, string &b) {
  string temp;
  temp = a;
  for (int i = 0; i < a.length(); i++)
    temp[i] = a[i];
  for (int j = 0; j < a.length(); j++)
    a[j] = b[j];
  for (int k = 0; k < a.length(); k++)
    b[k] = temp[k];
}

/**
 * remove front zero for divid
 */
string dezero(string a) {
  long long int i;
  for (i = 0; i < a.length(); i++) {
    if (a.at(i) > 48)
      break;
  }
  if (i == a.length())
    return "0";
  a.erase(0, i);
  return a;
}

/**
 * compare a and b for divid
 */
int judge(string a, string b) {
  if (a.length() > b.length())
    return 1;
  if (a.length() < b.length())
    return -1;
  long long int i;
  for (i = 0; i < a.length(); i++) {
    if (a.at(i) > b.at(i))
      return 1;
    if (a.at(i) < b.at(i))
      return -1;
  }
  return 0;
}

/**
 * subtraction for divid
 */
string subtraction(string a, string b) {
  const int n = 100;
  a = dezero(a);
  b = dezero(b);
  long long int i, j = 0;
  string c = "0";
  string c1, c2;
  string d = "-";
  if (judge(a, b) == 0)
    return c;
  if (judge(a, b) == 1) {
    c1 = a;
    c2 = b;
  }
  if (judge(a, b) == -1) {
    c1 = b;
    c2 = a;
    j = -1;
  }
  reverse(c1.begin(), c1.end());
  reverse(c2.begin(), c2.end());
  for (i = 0; i < c2.length(); i++) {
    if (c2.at(i) >= 48 && c2.at(i) <= 57)
      c2.at(i) -= 48;
    if (c2.at(i) >= 97 && c2.at(i) <= 122)
      c2.at(i) -= 87;
  }
  for (i = 0; i < c1.length(); i++) {
    if (c1.at(i) >= 48 && c1.at(i) <= 57)
      c1.at(i) -= 48;
    if (c1.at(i) >= 97 && c1.at(i) <= 122)
      c1.at(i) -= 87;
  }
  for (i = 0; i < c2.length(); i++) {
    c1.at(i) = c1.at(i) - c2.at(i);
  }
  for (i = 0; i < c1.length() - 1; i++) {
    if (c1.at(i) < 0) {
      c1.at(i) += n;
      c1.at(i + 1)--;
    }
  }
  for (i = c1.length() - 1; i >= 0; i--) {
    if (c1.at(i) > 0)
      break;
  }
  c1.erase(i + 1, c1.length());
  for (i = 0; i < c1.length(); i++) {
    if (c1.at(i) >= 10)
      c1.at(i) += 87;
    if (c1.at(i) < 10)
      c1.at(i) += 48;
  }
  reverse(c1.begin(), c1.end());
  if (j == -1)
    c1.insert(0, d);
  return c1;
}

/**
 * Internal implementation of divid
 * a and b are decimal string
 */
string _divid(string a, string b) {
  if (b.length() == 1 && b.at(0) == 48)
    return "error";
  long long int i, j;
  string c1, c2, d, e;
  if (judge(a, b) == 0)
    return "1";
  if (judge(a, b) == -1) {
    return "0";
  }
  c1 = dezero(a);
  c2 = dezero(b);
  d = "";
  e = "";
  for (i = 0; i < c1.length(); i++) {
    j = 0;
    d = d + c1.at(i);
    d = dezero(d);
    while (judge(d, b) >= 0) {
      d = subtraction(d, b);
      d = dezero(d);
      j++;
    }
    e = e + "0";
    e.at(i) = j;
  }
  for (i = 0; i < e.length(); i++) {
    if (e.at(i) >= 10)
      e.at(i) += 87;
    if (e.at(i) < 10)
      e.at(i) += 48;
  }
  e = dezero(e);
  return e;
}

/**
 * API for external calls
 */
string addition(string i1, string i2, int b) {
  string addResult;
  string i1m, i2m;
  i1m = conversion(i1, b, 10);
  i2m = conversion(i2, b, 10);
  addResult = conversion(_addition(i1m, i2m), 10, b);
  return addResult;
}

string multiply(string i1, string i2, int b) {
  string mulResult;
  string i1m, i2m;
  i1m = conversion(i1, b, 10);
  i2m = conversion(i2, b, 10);
  mulResult = conversion(_multiply(i1m, i2m), 10, b);
  return mulResult;
}

string divid(string i1, string i2, int b) {
  string i1m, i2m;
  string divResult;
  i1m = conversion(i1, b, 10);
  i2m = conversion(i2, b, 10);
  divResult = conversion(_divid(i1m, i2m), 10, b);
  return divResult;
}

int main() {
  string i1s, i2s;
  int b;

  //   cin >> i1s >> i2s >> b;
  i1s="202134241033040201033041030044242041011431012202332142";
  i2s="40033302401000440103430330034204222400";
  b=5;

  cout << addition(i1s, i2s, b) << " " << multiply(i1s, i2s, b) << " "
       << divid(i1s, i2s, b) << endl;

  return 0;
}