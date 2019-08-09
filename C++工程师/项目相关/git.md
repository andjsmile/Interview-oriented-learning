# 项目管理 git

廖雪峰 - git教程

## git 中Merge 和 Rebase 的区别


git merge 合并分支命令

**Merge 会自动根据两个分支的共同祖先和两个分支的最新提交进行一个三方合并，然后将合并中修改的内容生成一个新的commit，即merge合并两个分支并生成一个新的提交,并且仍然后保存原来分支的commit记录**



**Git 的分支模型称为必杀技特性**

创建分支命令 git branch (branchname)
创建新分支并立即切换到该分支下 git checkout -b (branchname) 
删除分支命令                git branch -d (branchname)
切换分支命令 git checkout (branchname)

合并分支命令 git merge

列出分支命令 git branch


git rebase  

把分叉的提交历史“整理”成一条直线，看上去更直观。缺点是本地的分叉提交已经被修改过了

rebase的目的是使得我们在查看历史提交的变化时更容易，因为分叉的提交需要三方对比


**Rebase 会从两个分支的共同祖先开始提取当前分支上的修改，然后将当前分支上的所有修改合并到目标分支的最新提交后面，如果提取的修改有多个，那git将依次应用到最新的提交后面。Rebase后只剩下一个分支的commit记录**



**处理BUG分支**
git stash  当前工作环境存储命令


假如你再dev下工作，现在需要你修复一个bug

1.git status  -- 查看当前工作环境
2.git stash   -- 保存当前dev工作环境
// 需要从主分支master上修改代码
3.git checkout master
4.git checkout -b issue-101
5.修改bug文件，git  add    ，git commit
6.git checkout master   -- 回主分支
7.git merge --no-ff -m "merged bug fix 101" issue-101   -- 合并分支代码
8.git checkout dev    -- 回到工作代码
9.git stash pop      -- 恢复之前的工作环境(git stash apply   git stash drop)


