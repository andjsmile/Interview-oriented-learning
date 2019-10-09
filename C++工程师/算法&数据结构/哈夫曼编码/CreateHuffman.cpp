/**
 * 实现一个哈夫曼树的生成函数
 * input:一个整数数组, elem
 * output:哈夫曼树的头指针 BTreeNode *
*/
#include <iostream>
#include <cstdlib>
#include <memory>
#include <vector>
using namespace std;

struct BTreeNode
{
    int val;
    BTreeNode *left;
    BTreeNode *right;

    BTreeNode(int x):val(x),left(NULL),right(NULL)
    {}
    /* data */
};

BTreeNode* createHuffman(vector<int> elem)
{
    int i,j;
    int n=elem.size();
    vector<BTreeNode*> b(n,NULL);
    BTreeNode *q;
    // 所有元素的初始化
    for(int i=0;i<n;++i)
    {
        b[i]->val=elem[i];
        b[i]->left=b[i]->right=NULL;
    }
    for(i=1;i<n;++i)
    {
        int k1=-1,k2;
        for(j=0;i<n;++j)
        {
            if(b[j]!=NULL && k1==-1)
            {
                k1=j;
                continue;
            }
            if(b[j]!=NULL)
            {
                k2=j;
                break;
            }
        }
        for(j=k2;j<n;++j)
        {
            if(b[j]!=NULL)
            {
                if(b[j]->val < b[k1]->val)
                {
                    k2=k1;
                    k1=j;
                }
                else if(b[j]->val < b[k2]->val)
                    k2=j;
            }
        }
        q->val=b[k1]->val+b[k2]->val;
        q->left=b[k1];
        q->right=b[k2];

        b[k1]=q;
        b[k2]=NULL;
    }
    return q;
}
