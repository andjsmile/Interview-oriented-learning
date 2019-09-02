## LeetCode题解


#### 111. 二叉树的最小深度 
minimum depth of binary tree
```cpp
/*
struct TreeNode{
    int val;
    TreeNode *left;
    TreeNode *right;
};
*/
class Solution {
public:
    int run(TreeNode *root) {
        if(root==nullptr) return 0;
        if(root->right==nullptr && root->left==nullptr){
            return 1;
        }
        if(root->left && root->right==nullptr){
            return 1+run(root->left);
        }
        if(root->right && root->left==nullptr){
            return 1+run(root->right);
        }
        return min(run(root->left),run(root->right))+1;
    }
};
```

#### 150. 逆波兰表达式求值 
evaluate-reverse-polish-notation

```cpp
class Solution {
public:
    int evalRPN(vector<string>& tokens) {
        // use lamda expression
        unordered_map<string, function<int (int, int) > > map = {
            { "+" , [] (int a, int b) { return a + b; } },
            { "-" , [] (int a, int b) { return a - b; } },
            { "*" , [] (int a, int b) { return a * b; } },
            { "/" , [] (int a, int b) { return a / b; } }
        };
        std::stack<int> stack;
        for (string& s : tokens) {
            if (!map.count(s)) {
                stack.push(stoi(s));
            } else {
                int op1 = stack.top();
                stack.pop();
                int op2 = stack.top();
                stack.pop();
                stack.push(map[s](op2, op1));
            }
        }
        return stack.top();
    }
};
```

#### 149. 直线上最多的点数
max-points-on-a-line
```cpp
/**
 * Definition for a point.
 * struct Point {
 *     int x;
 *     int y;
 *     Point() : x(0), y(0) {}
 *     Point(int a, int b) : x(a), y(b) {}
 * };
 */
class Solution {
public:
    int maxPoints(vector<Point> &points) {
        int size = points.size();
        if(size == 0)
            return 0;
        else if(size == 1)
            return 1;
             
        int ret = 0;
        for(int i = 0;i<size;i++){
             
            int curmax = 1;
            map<double,int>mp;
            int vcnt = 0; //垂直点
            int dup = 0; //重复点
            for(int j = 0;j<size;j++){
                 
                if(j!=i){
                    double x1 = points[i].x - points[j].x;
                    double y1 = points[i].y - points[j].y;
                    if(x1 == 0 && y1 == 0){   //重复
                        dup++;
                    }else if(x1 == 0){      //垂直
                        if(vcnt == 0)
                            vcnt = 2;
                        else
                            vcnt++;
                        curmax = max(vcnt,curmax);
                    }else{
                        double k = y1/x1;          //斜率
                        if(mp[k] == 0)
                            mp[k] = 2;
                        else
                            mp[k]++;
                        curmax = max(mp[k],curmax);
                    }                   
                }
            }
            ret = max(ret,curmax+dup);           
        }
        return ret;
         
    }
};
```

#### 148. 排序链表
sort list 

```cpp
/*
  考点：
  1\. 快慢指针；2\. 归并排序。
  此题经典，需要消化吸收。
  复杂度分析:
             T(n)            拆分 n/2, 归并 n/2 ，一共是n/2 + n/2 = n
            /    \           以下依此类推：
          T(n/2) T(n/2)      一共是 n/2*2 = n
         /    \  /     \
        T(n/4) ...........   一共是 n/4*4 = n
       一共有logn层，故复杂度是 O(nlogn)，空间复杂度是
 */
class Solution {
public:
    ListNode *sortList(ListNode *head) {
        if(head==nullptr || head->next==nullptr) return head;
        ListNode *p=head; 
        ListNode *q=head->next;
        // 链表的快慢指针
        while(q && q->next){
            p=p->next;
            q=q->next->next;
        }
        ListNode *left=sortList(p->next);
        p->next=NULL;  // 断链表
        ListNode *right=sortList(head);
        return merge(left,right);
    }
    ListNode* merge(ListNode *left,ListNode* right){
        ListNode dummy(0);
        ListNode *p=&dummy;
        while(left && right){
            if(left->val < right->val){
                p->next=left;
                left=left->next;
            }
            else{
                p->next=right;
                right=right->next;
            }
            p=p->next;
        }
        if(left) p->next=left;
        if(right) p->next=right;
        return dummy.next;
    }
};

// 改进版本
// 自底向上的
class Solution {
public:
    ListNode* sortList(ListNode* head) {
        ListNode dummyHead(0);
        dummyHead.next=head;
        ListNode *p=head;
        int length=0;
        while(p){
            ++length;
            p=p->next;
        }
        
        for(int size=1;size<length;size<<=1){
            ListNode *cur=dummyHead.next;
            ListNode *tail=&dummyHead;
            while(cur){
                ListNode* left =cur;
                ListNode *right=cut(left,size);
                cur=cut(right,size);
                tail->next=merge(left,right);
                while(tail->next){
                    tail=tail->next;
                }
            }
        }
        return dummyHead.next;
          
    }
    // 链表剪裁
    ListNode* cut(ListNode* head,int n){
        ListNode* p=head;
        while(--n && p){
            p=p->next;
        }
        if(!p)  return nullptr;
        ListNode *next=p->next;
        p->next=nullptr;
        return next;
    }
    // 链表合并
    ListNode* merge(ListNode * head1,ListNode *head2){
        ListNode dummyHead(0);
        ListNode *p=&dummyHead;
        while(head1 && head2){
            if(head1->val <head2->val){
                p->next=head1;
                p=head1;
                head1=head1->next;
            }
            else{
                p->next=head2;
                p=head2;
                head2=head2->next;
            }
        }
        p->next=head1? head1:head2;    // 只存在一个的情况
        return dummyHead.next;
    }
};
```
#### 143.重排链表
reorder link list
```cpp
// 1.借助容器保存链表结点实现
class Solution {
public:
    void reorderList(ListNode* head) {
        if(head==NULL)
            return;
        vector<ListNode*> nodes;
        ListNode *iter=head;
        while(iter!=NULL)
        {
            nodes.push_back(iter);
            iter=iter->next;
        }
        int list_length=nodes.size();
        int left=0;
        int right=list_length-1;
        while(left<right){
            nodes[left]->next=nodes[right];
            nodes[right--]->next=nodes[++left];
        }
        nodes[left]->next=NULL;
    }
};
// 2.利用快慢指针的原地排序
// 需要进一步理解
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    void reorderList(ListNode *head) {
        if(head == NULL || head->next == NULL || head->next->next == NULL)
            return;
         
        //快慢指针找中点
        ListNode* fast = head;
        ListNode* low = head;
        while(fast->next != NULL && fast->next->next != NULL){
            fast = fast->next->next;
            low = low->next;
        }
         
        //对low后面的部分逆序
        fast = low->next;
        low->next = NULL;
        while(fast != NULL){
            ListNode* temp = fast->next;
            fast->next = low->next;
            low->next = fast;
            fast = temp;
        }
         
        //合并low前面和后面两部分
        ListNode* p = head;
        ListNode* q = low->next;
        while(p != NULL && q != NULL){
            low->next = q->next;
            q->next = p->next;
            p->next = q;
            p = q->next;
            q = low->next;
        }
    }
};
```

#### 147.链表插入排序
Insertion Sort List

**题目未看懂**
```cpp
// 实现插入排序
class Solution {
public:
    ListNode* insertionSortList(ListNode* head) 
    {
        //so hard with listnode ,when use array can easily implement .
        ListNode* new_head = new ListNode(0);
        new_head -> next = head;
        ListNode* pre = new_head;
        ListNode* cur = head;
        while (cur)
        {
            if (cur -> next && cur -> next -> val < cur -> val)
            {
                while (pre -> next && pre -> next -> val < cur -> next -> val)
                    pre = pre -> next;
                /* Insert cur -> next after pre.*/
                ListNode* temp = pre -> next;
                pre -> next = cur -> next;
                cur -> next = cur -> next -> next;
                pre -> next -> next = temp;
                /* Move pre back to new_head. */
                pre = new_head;
            }
            else cur = cur -> next;
        }
        ListNode* res = new_head -> next;
        delete new_head;
        return res;   
    }
};
```