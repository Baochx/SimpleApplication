01-Sorts.java
classcical sorts: 
bubble sort, select sort, insert sort, quick sort, merge sort, radix sort,(heap sort, shell sort)


02-ZTE.java
sort by frequency.if the frequency is same, occurs first top surface.


03-DaJiang1.java
一个n*m的矩阵，输入0表示增值，1表示计算结果，0 和 1 后面跟着两个坐标，表示处理的范围。
示例：
2 3 2         // 2 * 3 的矩阵 2 条命令
0 1 1 1 3 1   // 0 表示增值 从矩阵的第 1 行第 1 列到第 1 行第 3 列 每个点 + 1
1 1 1 1 3     // 1 表示求和 从矩阵的第 1 行第 1 列到第 1 行第 3 列
3             // 求和结果为3 如有多个 打印多行
2 3 2
0 1 1 2 3 1
1 1 1 2 3
6


04-DaJiang2.java
给定一棵完全二叉树的顶点个数，求它的叶子节点个数。


05-Trim.java
去除一个字符串里面的空格


06-Nbits.java
1除以7的第n位小数是多少？


07-WFSTree.java
构造一棵二元搜索树(能根据给定的初始值和节点数初始化一棵树，并动态地增加新的节点)，然后按广度优先搜索打印出所有的节点。
使用自己实现的队列


08-MatrixCompute.java
构造增量矩阵，求与其转置相乘的结果


09-PreOrder.java
前序遍历一棵二叉树(通过数组构建的)


10-KeywordsFrequency.java
给定一些关键词，找到它们在一段(连续)文本中出现的频率，与查找单词频率不同，文本不能通过标点、空格等分隔成单词然后逐一比较


11-MergeSortPractice.java
-----------------
long uid;
long ordered;
double price;
Date createTime;
-----------------
以上是用户订单的主要内容，现有 海量 这样的订单需要分别按价格和时间进行排序，请设计排序算法进行排序


12-TrimSameChar.java
将第一个字符串中不存在于第二个字符串中的字符输出


13-StringCost.java
求将第一个字符串转换为第二个字符串的代价，规则为：去除连续的字符cost为2，新增n个新的字符cost = n + 2,新增字符只能加在后面
for example, abc c: cost = 2; abc cb: cost = 2 + 1 + 2
input: 
	T     //测试次数
	str1
	str2  //1次测试输入完成
	...   //多次测试输入
output: 
	cost1 //1次测试结果输出
    ...   //多次测试结果输出	


14-GradeQuerySystem.java
一个学生成绩管理器,可以输入多个学生的成绩，每个学生的成绩可以有多门，输入和查询格式如下：
input:name=xiaoqiang,math=99,lang=100;
query:name=xiaoqiang;


15-MyQueue.java
use two Stacks to implement a queue!


16-StringAlg.java
Some algorithms about String:
1.回文字符串
2.语句反序(单词不用反序)，不增加长度
3.字符串压缩和恢复:aabbbcccc -> 2a3b4c
4.用递归实现把一个整数逆序输出


17-MapAlg.java
Some algorithms about Map:
1.间接利用TreeMap的排序特性来解决频率统计类问题


18-PrimeNumber.java
1.所有数都可以用6*n+r来表示(n是自然数，r是余数0~5)，但只有6*n+1和6*n+5可能是素数，大大缩短了查找范围
2.素数的倍数不可能是素数，用BitSet标记素数的倍数。这是目前已知的求素数最快的方法。


19-CommonElements.java
求两个不同数组中的共同元素
方法1: 除重,不排序，位图法。且允许用额外的空间，那么考虑位图法：为一个数组建立位图，用另外一个去重的数组来匹配。如果元素的范围比较小(以范围最小的数组为准)先各自数组除重(其实只需要范围最小那个数组除重就Ok了，因为只需要用它来建立位图，且建立位图时不会重复)
方法2: 不除重，排序(Arrays.sort(),自实现排序算法)，再进行逐一比较，把相同的取出来。
方法3: 除重，排序(TreeSet，排序兼具除重功能)，再进行逐一比较，把相同的取出来。
方法4: 除重,不排序，再进行m*n次交叉比较


20-MaxOfM.java
使用快排算法来求数组指定范围内第m大的值


21-BagProblem.java
01背包问题，通过动态规划的思想和方法来解决
1.找到状态转移方程：背包大于等于小于物品体积时如何选择？
2.建立记忆存储数组


22-WordList.java
能否将一串单词首尾相连？可以返回1，不可以返回-1
ab bc cc ca,1 ab bc kad,-1














