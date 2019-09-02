## 各种排序算法

#### 1.插入排序
- 1.遍历所有的元素
- 2.当前元素与前面元素比较并交换位置

```cpp
// 时间复杂度O(N^2)
void InsertSortVersion1(vector<int> &nums){
    int len=nums.size();
    if(len==0 || len==1) return;
    // 1.loop all the element
    for(int i=1;i<len;++i){
        if(nums[i]<nums[i-1])          
        {
            int flag=nums[i];
            for(int j=i-1;j>=0&& nums[j]>flag ;--j){
                nums[j+1]=nums[j];    // back forward the element;
            }
            nums[j+1]=flag;
        }
    }
}
// other version
void InsertSortVersion2(vector<int>&nums){
    for(int i=1;i<nums.size();++i){
        int temp=nums[i];
        int j=i-1;
        while(j>=0 && nums[j]>temp){
            nums[j+1]=nums[j];
            j--;
        }
        nums[j+1]=temp;
    }
}
```