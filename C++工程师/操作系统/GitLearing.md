##  learning the git 

#### 1.创建repository 版本库
在需要的文件夹下面直接
git init

#### 2.时光穿梭机
> 1.版本回退
掌握工作区的状态,是否有修改  git status
查看修改的内容               git diff

对于已经 add commit的文件需要回到回退版本
第一步 查看git日志           git log
第二步 查看版本号            日志commit后面的数字     
HEAD 表示当前版本 
HEAD^ 上一个版本 
HEAD^^ 上上一个版本
HEAD~100 前一百个版本

回退到上一个版本            git reset --hard HEAD^

重返未来，需要查看未来commitId    git relog
                            git reset --hard commitId

> 2.工作区和暂存区
working directory and stage(index)

git add    添加文件   是将文件修改添加到暂存区
git commit 提交文件   把暂存区的所有内容提交到当前分支

每次的修改 如果不 git add到暂存区，那么不会加入到 commit

> 3.撤销修改
撤销(丢弃))工作区的修改         git checkout -- file
-- 这个很重要
git checkout file 表示切换到file branch 切换分支 

unstage 暂存区的修改撤销掉      git reset HEAD file
git reset 既可以回退版本，也可以暂存区回退到工作区


> 4.删除文件
两种删除方式
在文件管理器中  rm file
git add file  和  git rm file 是相同的结果

git rm file   git commit 

误删除

将版本库的文件替换工作区的文件，一键还原
git checkout -- file

如果你用的rm删除文件，那就相当于只删除了工作区的文件，
如果想要恢复，直接用git checkout -- <file>就可以 

如果你用的是git rm删除文件，那就相当于不仅删除了文件，而且还添加到了暂存区，需要先git reset HEAD <file>，
然后再git checkout -- <file> 

如果你想彻底把版本库的删除掉，先git rm，再git commit 



#### 3.远程仓库

链接远程仓库
git remote add origin git@github.com:Geekdengshuo/repositoryName.git

本地库推送到远程仓库

git push -u origin master   第一次推送
git push origin master 推送   

克隆远程仓库到本地
git clone git@github.com:Geekdengshuo/repositoryName.git

使用ssh原生的git命令 要比https要快很多


#### 4.分支管理

1.master分支
master分支是一条主线，master指向最先的提交，HEAD指向master

2.创建和删除分支

创建分支并切换到分支         git checkout -b dev
  相当于两个步骤 创建分支    git branch dev
                 切换分支    git checkout dev

查看当前分支  * 表示当前分支  git branch

可以在当前分支dev继续提交 此时HEAD指向 dev dev向前移动

切换到主分支                 git checkout master

将分支dev合并到master        git merge dev

合并后可以删除分支           git branch -d dev

删除远程分支                 git push origin -d <branch name>
                             git push origin:<branch name>
3.解决合并冲突

master分支和 dev分支都有更新 git无法实现快速合并

查看分支合并情况

git log --graph 查看分支合并图

git log --graph --pretty=oneline --abbrev-commit

4.分支管理策略

分支合并 git merge 如果可能 git会使用 Fast forward方式
这种方式删除分支，会丢掉分支信息

可以使用 --no-ff 模式 表示禁用Fast forward模式

git merge -no-ff -m "merge with no-ff" dev
-m 添加 commit 描述


5.bug分支

修改master分支bug 但当前dev分支还没有提交

git stash  将当前工作“隐藏”起来

修改完后，恢复到当前dev分支进行恢复

git stash list    查看列表
git stash apply   恢复，并不删除
git stash drop    删除


git stash pop     恢复同时把stash 内容删除

6.feature 分支

新建特征分支，再将特征分支合并到主分支上

强制删除 分支
git branch -D feature-branch


7.多人协作

远程库信息      git remote
远程库相信信息  git remtoe -v

推送分支        git push origin master
推送其他分支    git push origin dev

master 主分支必须推送
dev    开发分支需要远程同步
bug分支只是本地修复bug，没有必要推送到远程
feature 分支看是否进行合作


抓取分支
小伙伴进行协同开发
1.git clone 仓库
2.git branch 只有master分支
3.git checkout -b dev origin/dev   创建远程origin的dev分支到本地
4.git add   git commit             进行修改
5.git push origin dev              将dev分支push到远程

小伙伴进行修改
你也对dev分支进行了修改

1.git add    git commit 
2.git push origin dev              你的push和小伙伴有冲突
3.git pull                         git pull 先把最新的提交从origin/dev抓下来
4.git pull 失败
本地dev分支和远程 origin/dev分支没有链接，根据提示设置链接

5.git branch --set-upstream-to=origin/dev dev
6.git pull             
合并有冲突  解决冲突
7.git commit -m ""     提交
8.git push origin dev



8.rebase
rebase操作可以把本地未push的分叉提交历史整理成直线
rebase的目的是使得我们在查看历史提交的变化时更容易，
因为分叉的提交需要三方对比


git rebase


#### 5.标签管理
标签是版本库的一个快照，将版本库打一个标签 tag

tag就是一个commit  tag 的标号比commit更好找  
tag  v1.2     commit 6a5819e


创建标签
切换到需要标签的分支上   git branch
                         git checkout master
打标签                   git tag v1.0
查看标签                 git tag

对commit进行打标签       git tag v0.9 f52c633
带有说明的标签           git tag -a v0.1 -m "version 1.0 released" 1094adb

查看标签信息             git show v0.1
删除标签                 git tag -d v1.0
标签推送远程             git push origin v1.0
推送全部标签             git push origin --tags

删除远程标签  
先删除本地               git tag -d v0.9
远程删除                 git push origin:refs/tags/v0.9



#### 使用Github

参加一个开源项目
1.向访问其他的主页，看到项目主页 进行fork
2.fork后在自己仓库克隆一个
3.从自己github clone代码到本地    必须从自己的github克隆这样才有权限推送修改

4.发送一个  pull request 看作者是否接受你的修改






