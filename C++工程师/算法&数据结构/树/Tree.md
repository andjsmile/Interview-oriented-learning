## 树

### 1.二叉树

要对二叉树3种遍历方法的6种实现都要熟悉

宽度优先遍历==层序遍历

还有之型遍历(锯齿形层序遍历)
这个主要考虑顺序的问题  
1.注意当前队列queue的大小,根据这个大小来实现遍历
2.遍历的下标，根据每层的bool来进行判断是从前往后，还是从后往前


### 2.二叉树特殊变种

二叉树的两个较为特殊的特例  堆，红黑树

最大堆-根结点的值最大
最小堆-根结点的值最小

快速找到最大，最小值可以用堆来实现

set，map的底层都是红黑树


### 3.AVL树，红黑树

AVL树也叫平衡二叉树，左右子树都是平衡二叉树，且左右子树高度差的绝对值小于等于1

BF=左子树高度-右子树高度, BF=0,-1,1

红黑树，是一种二叉查找树，

红黑树确保没有一条路径会比其他路径长两倍，是一种弱平衡二叉树

搜索，插入，删除较多的情况下使用红黑树

**红黑树性质**
1.每个结点非黑即红

2.根结点是黑色的

3.每个叶结点都是黑色的

4.如果一个结点是红色的，子结点必须是黑色的

5.对于任意结点，其到叶子结点每条路径中包含相同数目的黑色结点



### 4.map

map的底层是红黑树实现
map对数据结构的 搜索，插入，删除有较高的要求

AVL是平衡二叉树，对树的平衡性要求高，每次插入或者删除的时候，会引起树的reblance ，导致效率较低

红黑树是一种弱平衡二叉树，旋转的次数少，搜索、插入、删除情况较多一般使用红黑树  O(logN)


### 5.B+树

**B树**

（1）排序方式：所有节点关键字是按递增次序排列，并遵循左小右大原则；

（2）子节点数：非叶节点的子节点数>1，且<=M ，且M>=2，空树除外（注：M阶代表一个树节点最多有多少个查找路径，M=M路,当M=2则是2叉树,M=3则是3叉）；

（3）关键字数：枝节点的关键字数量大于等于ceil(m/2)-1个且小于等于M-1个（注：ceil()是个朝正无穷方向取整的函数 如ceil(1.1)结果为2);

（4）所有叶子节点均在同一层、叶子节点除了包含了关键字和关键字记录的指针外也有指向其子节点的指针只不过其指针地址都为null对应下图最后一层节点的空格子;

**B+树**

B+树是多路搜索树，主要为磁盘和其他直接存储辅助设备而设计的一种平衡二叉树

B+树中，每个解结点可以有多个孩子
       记录点放在叶子结点上，且是顺序存放
       叶子结点指针进行链接



- 1、B+树的层级更少：相较于B树B+每个非叶子节点存储的关键字数更多，树的层级更少所以查询数据更快；

- 2、B+树查询速度更稳定：B+所有关键字数据地址都存在叶子节点上，所以每次查找的次数都相同所以查询速度要比B树更稳定;

- 3、B+树天然具备排序功能：B+树所有的叶子节点数据构成了一个有序链表，在查询大小区间的数据时候更方便，数据紧密性很高，缓存的命中率也会比B树高。

- 4、B+树全节点遍历更快：B+树遍历整棵树只需要遍历所有的叶子节点即可，，而不需要像B树一样需要对每一层进行遍历，这有利于数据库做全表扫描



### 6.unordered_map 和 map

map  ->   红黑树

优点: 1.有序性，map结构化的最大优点
     2.查找，插入，删除时间复杂度稳定，都是O(logN)
缺点:
     查找，删除，插入操作平均时间复杂度慢，与n有关

unordered_map -> 哈希表

优点:
查找，删除，插入添加的速度快，时间复杂度为常数级别 O(c)

缺点:
(key,val)对的形式存储，空间的利用率高

查找，删除，添加的时间复杂度不稳定，取决于哈希函数


### 7.二叉树的序列化、反序列化操作

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Codec {
public:

    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        if(root==nullptr) return "$";
        return to_string(root->val)+","+serialize(root->left)+","+serialize(root->right);
        
    }
    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        if(data=="$")  
            return nullptr;
        stringstream s(data);
        return makeDeserialize(s);
        
    }
    TreeNode* makeDeserialize(stringstream &s)
    {
        string str;
        getline(s,str,',');
        if(str=="$")
            return nullptr;
        else
        {
            TreeNode *root=new TreeNode (stoi(str));
            root->left=makeDeserialize(s);
            root->right=makeDeserialize(s);
            return root;
        }
    }
    
};
```
stoi 函数  to_string
string       c_str()


