Git常用指令

```
1、两个指令：
$ git config -- global user.name "your name"
$ git config -- global user.email "your email"

2、创建空目录：
$ mkdir learngit

3、进入该目录：
$ cd learngit

4、显示当前目录
$ pwd

5、把该目录变成Git可管理的仓库
$ git init

6、添加文件到仓库
$ git add <filename>
$ git commit -m"explain"

7、退出指令
:wq

8、修改文本内容之后，可使用git status查看运行结果
$ git status

9、使用gif diff查看具体修改了什么内容
$ git diff

10、使用git log命令可查看最近到最远的提交日志
$ git log
嫌输出信息太多，可以加上 --pretty=oneline
$ git log --pretty=oneline

11、使用git reset --hard HEAD^可以回到上一个版本
$git reset --hard HEAD^
注意：上一个版本是HEAD^,上上版本是HEAD^^,往上100个版本HEAD~100

12、指定回到某一版本 git reset --hard <版本号>
$ git reset --hard 1094a

13、查看每一次命令
$ git reflog

14、丢弃工作区的修改
$ git checkout -- file

15、撤销暂存区的修改
$ git reset HEAD <file>

16、rm命令用来删除文件
$ rm test.txt

17、用版本库里的版本替换工作区的版本,无论修改还是删除，都可“一键还原”
$ git checkout -- file

18、创建SSH key
$ ssh-keygen -t rsa -C "youreamil@example.com"
* 注意：如果在ssh后面加上空格，会得到Bad escape character 'ygen'的错误

19、添加远程库
	a、首先，登陆GitHub，然后，在右上角找到“Create a new repo”按钮，创建一个新的仓库：
	b、在Repository name填入learngit，其他保持默认设置，点击“Create repository”按钮，就成功地创建了一个新的Git仓库：
	c、现在，我们根据GitHub的提示，在本地的learngit仓库下运行命令
		$ git remote add origin git@github.com:michaelliao/learngit.git
	* michaelliao替换成你自己的GitHub账户名
	d、使用命令git push -u origin master第一次推送master分支的所有内容
	e、每次本地提交后，只要有必要，就可以使用命令git push origin master 推送最新修改

20、从远程库克隆
$ git clone git@github.com:Rzihan/<仓库名字>

21、查看分支
$ git branch

22、创建分支
$ git branch<name>

23、切换分支
$ git checkout<name>

24、创建+切换分支
$ git checkout -b <name>

25、合并某个分支到当前分支
$ git merge<name>

26、删除分支
$git branch -d <name>

27、合并分支，--no-ff，表示禁用Fast forward
$ git merge --no-ff -m"merge with no-ff" dev

28、将现场临时存储起来
$ git stash

29、查看存储刚刚存储的工作现场
$ git stach list

30、恢复工作现场的内容
$ git stach apply 恢复后，stash内容并不删除，需要用 git stash drop来删除
$ git stash pop 恢复的同时把stash的内容也删除了
$ git stash apply stash@{0} 恢复到指定的stash

31、查看远程仓库的信息
$ git remote
$ git remote -v 显示更详细的信息

32、推送本地分支到远程库
$ git push origin master
$ git push origin dev

33、抓取分支
$ git clone git@github.com:Rzihan/learngit.git
* clone时，默认情况只能看到本地的master分支
	
34、创建远程的origin的dev分支到本地
$ git checkout -b dev origin/dev

35、设置本地dev分支与远程origin/dev分支的连接
$ git branch --set-upstream-to=origin/dev dev

36、抓取分支
$ git pull

37、把分叉的提交历史“整理”成一条直线，看上去更直观
$git rebase

38、标签
$ git tag <name> 创建标签
$ git tag 查看所有标签
$ git log --pretty=oneline --abbrev-commit 查看历史提交的版本号
$ git show<tagname> 查看标签的详细信息
$ git tag -a <name> -m "说明" commit id 创建带有说明的标签，用-a指定标签名，-m指定说明文字

39、操作标签
$ git tage -d <name> 删除标签
$ git push origin <tagname> 推送某个标签到远程
$ git push origin --tags 一次性推送尚未推送到远程的本地标签
$ git push origin:refs/tags/<tagname> 可以删除一个远程标签

40、忽略特殊文件
$ git add -f App.class 强制添加到Git;
$ git check-ignore -v App.class 检查是哪个规则写错了

41、配置别名
$ git config --global alias.st status 告诉Git，以后st就表示status
$ git config --global alias.co checkout
$ git config --global alias.ci commit
$ git config --global alias.br branch
$ git config --global alias.unsatge 'reset HEAD'
* 配置Git的时候，加上--global是针对当前用户起作用的，如果不加，那只针对当前的仓库起作用。

```

