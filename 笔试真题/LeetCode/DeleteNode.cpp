// 删除链表的结点
// 给出链表结点
//

struct ListNode 
{
    int val;
    ListNode *next;
};

void DeleteNode(ListNode **pListNode ,ListNode *pDeleted)
{
    if(pListNode==nullptr || *pListNode==nullptr) return;

    // 不是尾结点
    if(pDeleted->next!=nullptr)
    {
        ListNode *pNext=pDeleted->next;
        pDeleted->val=pNext->val;
        pDeleted->next=pNext->next;
    }
    // 链表只有一个结点
    else if(*pListNode==pDeleted)
    {
        delete pDeleted;
        pDeleted=nullptr;
        *pListNode=nullptr;
    }

    // 删除尾结点
    else
    {
        ListNode *pNode=*pListNode;
        while(pNode->next!=pDeleted)
        {
            pNode=pNode->next;
        }
        pNode->next=nullptr;
        delete pDeleted;
        pDeleted=nullptr;
    }
}
