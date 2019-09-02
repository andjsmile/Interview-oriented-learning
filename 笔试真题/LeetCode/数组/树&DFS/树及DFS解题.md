
## 生成二叉树问题

1.使用递归注意区间的  左闭右开 区间
2.下标会在递归过程中改变    int mid=(start+end)/2;
3.注意下标是否会越界,及时返回



## 链表转换二叉树问题

1.链表的快慢指针问题
  先要判断链表是否为空，链表是否只为一个结点
  快慢指针前进的while循环判断条件,只判断快指针和快指针的下一个结点是否存在

```cpp
        if(head==nullptr) return nullptr;
        if(head->next == nullptr) return new TreeNode(head->val);
        ListNode *start = new ListNode(0);
        start ->next=head;
        ListNode *fast = start, *slow = start, *slow_pre = nullptr;
        while(fast!=nullptr&&fast->next!=nullptr){
            fast = fast->next->next;
            slow_pre = slow;
            slow = slow->next;
        }

```