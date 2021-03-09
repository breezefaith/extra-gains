import numpy as np
import pandas as pd


def cal_cf_t(bv, roe, g, row, col):
    if row <= 0 or col <= 0:
        raise Exception("Invalid Row or Column")

    bv_t_1 = bv.iloc[row - 1, col]
    roe_t = roe.iloc[row, col]
    g_t = g.iloc[row, col]

    cf = bv_t_1 * (roe_t - g_t)

    return cf


def cal_d(bv, roe, g, p, r, row, col):
    if row <= 0 or col <= 0:
        raise Exception("Invalid Row or Column")
    tmp1 = 0
    tmp2 = 0
    for t in range(1, row + 1):
        cf = cal_cf_t(bv, roe, g, row, col)
        tmp1 = tmp1 + t * cf / pow(1 + r, t)
        tmp2 = tmp2 + cf / pow(1 + r, t)
        pass

    d = tmp1 / p + (row + (1 + r) / r) * (p - tmp2) / p

    return d


# bv = pd.read_excel(r"A_BV.slim.xlsx")
# roe = pd.read_excel(r"A_ROE.slim.xlsx")
# g = pd.read_excel(r"GDP年增长率.slim.xlsx")

# 设置常量值
r = 0.05
p = 39.884

# 读取文件
bv = pd.read_excel(r"A_BV.xlsx")
roe = pd.read_excel(r"A_ROE.xlsx")
g = pd.read_excel(r"GDP年增长率.xlsx")

# 标题行
headers = [col for col in bv.columns]

# 年份列
years = bv.iloc[:, 0].values

# 总行数
rows = len(bv)

# 总列数（不包含年份列）
cols = len(bv.loc[1]) - 1

# 求解每一个D，构造输出值
list = []
for i in range(1, rows):
    # 把年份作为第一列
    l = [years[i]]
    for j in range(1, cols + 1):
        l.append(cal_d(bv, roe, g, p, r, i, j))
        pass
    list.append(l)
    pass

# 构造输出对象
out = pd.DataFrame(np.array(list), columns=headers)

# 存入xlsx文件
out.to_excel(r'D.xlsx', index=False)
