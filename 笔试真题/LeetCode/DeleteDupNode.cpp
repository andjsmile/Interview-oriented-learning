// 删除链表中重复的结点，链表已排好序
// 重复结点，两个结点都删除
//

// 递归的方法

struct ListNode {
    int val;
    ListNode *next;
};
class Solution {
public:
    ListNode* deleteDuplication(ListNode* pHead)
    {
        if (pHead==NULL)
            return NULL;
        if (pHead!=NULL && pHead->next==NULL)
            return pHead;

        ListNode* current;

        if ( pHead->next->val==pHead->val){
            current=pHead->next->next;
            while (current != NULL && current->val==pHead->val)
                current=current->next;
            return deleteDuplication(current);
        }

        else {
            current=pHead->next;
            pHead->next=deleteDuplication(current);
            return pHead;
        }
    }
};


// 遍历链表的方法

ListNode* deleteDuplication(ListNode* pHead)
{
    if(pHead==nullptr || pHead->next==nullptr) 
        return pHead;
    ListNode dummy=new ListNode(0);
    dummy.next=pHead;

    ListNode pre=dummy;
    ListNode last=dummy.next;

    while(last!=nullptr)
    {
        if(last.next!=nullptr && last.val==last.next.val)
        {
            // 找到最后一个相同点
            while(!last.next && last.val==last.next.val)
            {
                last=last.next;
            }
            pre.next=last.next;
            last=last.next;
        }
        else
        {
            pre=pre.next;
            last=last.next;
        }
    }
    return dummy.next;
}
