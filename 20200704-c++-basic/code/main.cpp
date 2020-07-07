#include <iostream>

using namespace std;

class Building
{
protected:
    double area;
};

class House : public Building
{
protected:
    double price;
    int number;

public:
    House()
    {
        area = 0;
        price = 10000.0;
        number = 101;
    }

    House(double a, double p, int n)
    {
        area = a;
        price = p;
        number = n;
    }

    double getArea()
    {
        return area;
    }

    double getPrice()
    {
        return price;
    }

    int getNumber()
    {
        return number;
    }

    friend ostream &operator<<(ostream &os, const House &h);
};

ostream &operator<<(ostream &os, const House &h)
{
    os << "门牌号：" << h.number << "，面积：" << h.area << "，价格：" << h.price;
    return os;
}

int main()
{
    House hs[3] = {House(50, 100, 101), House(70, 70, 102), House(100, 50, 103)};

    cout << "房屋列表：" << endl;
	int i = 0;
    for (i = 0; i < 3; i++)
    {
        cout << hs[i] << endl;
    }

    cout << "可接受的房屋面积的最小值：";
    int minArea = 0;
    cin >> minArea;
    cout << "可接受的房屋售价的最大值：";
    int maxPrice = 0;
    cin >> maxPrice;

    int idealNumber[3];
    int idealLen = 0;

    for (i = 0; i < 3; i++)
    {
        if (hs[i].getArea() >= minArea && hs[i].getPrice() <= maxPrice)
        {
            idealNumber[idealLen++] = hs[i].getNumber();
        }
    }

    if (idealLen == 0)
    {
        cout << "不存在满足输入条件的房屋" << endl;
    }
    else
    {
        cout << "满足输入条件的房屋门牌号为";
        for (i = 0; i < idealLen; i++)
        {
            cout << idealNumber[i] << " ";
        }
		cout << endl;
    }

    return 0;
}