## 创建版本库
---
**1**.初始化一个Git仓库，使用==git init==命令。  
**2**.添加文件到Git仓库，分两步：  
第一步，使用命令==git add <file>==，注意，可反复多次使用，添加多个文件；  
第二步，使用命令==git commit==，完成。  

*简单解释一下git commit命令，-m后面输入的是本次提交的说明，可以输入任意内容*

## 版本回退
---
- 1.==git status==命令可以让我们时刻掌握仓库当前的状态  
- 2.==git diff==  命令可以查看文件的修改内容    
 提交修改和提交新文件是一样的两步，第一步是==git add==     
 执行第二步==git commit -m " 说明"==

---

 HEAD指向的版本就是当前版本，因此，Git允许我们在版本的历史之间穿梭，使用命令==git reset --hard commit_id==。   
- 1.穿梭前，用==git log==可以查看提交历史，以便确定要回退到哪个版本。   简明的话 使用
 ==git log --pretty=oneline==
- 2.要重返未来，用==git reflog==查看命令历史，以便确定要回到未来的哪个版本。
- 3.==git reset --hard HEAD^== 返回上一个版本
- 4.==cat==+文件名 可查看文件内容
- 5.==git reset --hard+版本号== 可回溯
---
## 利用Git从本地上传到GitHub
- 第一步： 进入要所要上传文件的目录输入命令 “==git init==”
- 第二步： 创建一个本地仓库origin，使用命令 “==git remote add origin git@github.com:yourName/yourRepo.git”==
  youname是你的GitHub的用户名，yourRepo是你要上传到GitHub的仓库
- 第三步： 比如你要添加一个文件xxx到本地仓库，使用命令 “git add xxx”，可以使用“git add .”自动判断添加哪些文件                           然后把这个添加提交到本地的仓库，使用命令 ==”git commit -m ”==说明这次的提交“ “     
  最后把本地仓库origin提交到远程的GitHub仓库，使用命令 ==git push origin master==

## 从本地上传文件到organization

* 克隆一个库到自己电脑上：git clone + 组织的库地址

* 将自己的修改添加到本地的clone下来的仓库

* git add .

* git commit -m "xxx"

* git push (如果不行先用==git pull --rebase origin master==，拉取远程的文件把本地的覆盖，再上传)

  

##  从GitHub克隆项目到本地

- 第一步： 到GitHub的某个仓库，然后复制右边的有个“==HTTPS clone url==”
- 第二步： 回到要存放的目录下，使用命令 " ==git clone https://github.com/chenguolin/scrapy.git=="，黄色的url只是一个例子
- 第三步： 如果本地的版本不是最新的，可以使用命令 “==git fetch origin==”，origin是本地仓库
- 第四步： 把更新的内容合并到本地分支，可以使用命令 “==git merge origin/master==”

如果你不想手动去合并，那么你可以使用： ==git pull <本地仓库> master== // 这个命令可以拉去最新版本并自动合并

##  GitHub的分支管理
##### 创建
- 1 创建一个本地分支：==git branch  新分支名字==(或者==git checkout -b 新分支名==   创建并切换分支)
- 2 将本地分支同步到GitHub上面：==git push 本地仓库名  新分支名==
- 3 切换到新建立的分支： ==git checkout  新分支名==
- 4 为你的分支加入一个新的远程端： ==git remote add <远程端名字> <地址>==
- 5 查看当前仓库有几个分支: ==git branch==
##### 删除
- 1 从本地删除一个分支： ==git branch -d <分支名称>==
- 2 同步到GitHub上面删除这个分支： ==git push <本地仓库名> :<GitHub端分支>==

## Git忽略特殊文件

##### 忽略文件的原则是：

1. 忽略操作系统自动生成的文件，比如缩略图等；

2. 忽略编译生成的中间文件、可执行文件等，也就是如果一个文件是通过另一个文件自动生成的，那自动生成的文件就没必要放进版本库，比如Java编译产生的`.class`文件；

3. 忽略你自己的带有敏感信息的配置文件，比如存放口令的配置文件。

   * **举个例子：**
   * 假设你在Windows下进行Python开发，Windows会自动在有图片的目录下生成隐藏的缩略图文件，如果有自定义目录，目录下就会有`Desktop.ini`文件，因此你需要忽略Windows自动生成的垃圾文件：

   ```
   # Windows:
   Thumbs.db
   ehthumbs.db
   Desktop.ini
   ```

   * 然后，继续忽略Python编译产生的`.pyc`、`.pyo`、`dist`等文件或目录：

   ```
   # Python:
   *.py[cod]
   *.so
   *.egg
   *.egg-info
   dist
   build
   ```

   * 加上你自己定义的文件，最终得到一个完整的`.gitignore`文件，内容如下：

   ```
   # Windows:
   Thumbs.db
   ehthumbs.db
   Desktop.ini
   
   # Python:
   *.py[cod]
   *.so
   *.egg
   *.egg-info
   dist
   build
   
   # My configurations:
   db.ini
   deploy_key_rsa
   ```

   * 最后一步就是把`.gitignore`也提交到Git，就完成了！当然检验`.gitignore`的标准是`git status`命令是不是说`working directory clean`。

   ==使用Windows注意了，如果你在资源管理器里新建一个`.gitignore`文件，它会提示你必须输入文件名，但是在文本编辑器里“保存”或者“另存为”就可以把文件保存为`.gitignore`了。==

   有些时候，你想添加一个文件到Git，但发现添加不了，原因是这个文件被`.gitignore`忽略了：

   ```
   $ git add App.class
   The following paths are ignored by one of your .gitignore files:
   App.class
   Use -f if you really want to add them.
   ```

   ==如果你确实想添加该文件，可以用`-f`强制添加到Git：==

   ```
   $ git add -f App.class
   ```

   或者你发现，可能是`.gitignore`写得有问题，需要找出来到底哪个规则写错了，可以用`git check-ignore`命令检查：

   ```
   $ git check-ignore -v App.class
   .gitignore:3:*.class    App.class
   ```

   Git会告诉我们，`.gitignore`的第3行规则忽略了该文件，于是我们就可以知道应该修订哪个规则。

   ##### 小结

   - 忽略某些文件时，需要编写`.gitignore`；
   - `.gitignore`文件本身要放到版本库里，并且可以对`.gitignore`做版本管理！

------

###  新增

* 每次提交，Git都把它们串成一条时间线，这条时间线就是一个分支 ，也是主分支master。master指向的是当前提交，HEAD指向的是最新的提交。

* 每提交一次，master向前移动一步。

* 建一个分支dev它会指向master指向的提交。对工作区的修改和提交就是针对`dev`分支了，比如新提交一次后，`dev`指针往前移动一步，而`master`指针不变。

* 用命令“git merge dev”把dev分支合并到master上。（“Fast-forward ”表示直接把`master`指向`dev`的当前提交，合并速度非常快。）

* ```
  Git鼓励大量使用分支：
  
  查看分支：git branch
  
  创建分支：git branch <name>
  
  切换分支：git checkout <name>
  
  创建+切换分支：git checkout -b <name>
  
  合并某分支到当前分支：git merge <name>
  
  删除分支：git branch -d <name>
  ```

   合并分支时，加上`--no-ff`参数就可以用普通模式合并，合并后的历史有分支，能看出来曾经做过合并，而`fast forward`合并就看不出来曾经做过合并。 从分支历史上就可以看出分支信息。 

* Git还提供了一个`stash`功能，可以把当前工作现场“储藏”起来，等以后恢复现场后继续工作。

* 当修复了紧急bug回来master，可以用“git stash list”查看现有stash。

  * 一是用`git stash apply`恢复，但是恢复后，stash内容并不删除，你需要用`git stash drop`来删除；
  * 另一种方式是用`git stash pop`，恢复的同时把stash内容也删了：

* 开发一个新feature，最好新建一个分支；如果要丢弃一个没有被合并过的分支，可以通过`git branch -D <name>`强行删除。

* 你的小伙伴已经向`origin/dev`分支推送了他的提交，而碰巧你也对同样的文件作了修改，并试图推送，就会推送失败，因为你的小伙伴的最新提交和你试图推送的提交有冲突，解决办法也很简单，Git已经提示我们，先用`git pull`把最新的提交从`origin/dev`抓下来，然后，在本地合并，解决冲突，再推送。`git pull`也失败的话：

* ```
  $ git pull
  There is no tracking information for the current branch.
  Please specify which branch you want to merge with.
  See git-pull(1) for details.
  
      git pull <remote> <branch>
  
  If you wish to set tracking information for this branch you can do so with:
  
      git branch --set-upstream-to=origin/<branch> dev
  ```

*  原因是没有指定本地`dev`分支与远程`origin/dev`分支的链接，根据提示，设置`dev`和`origin/dev`的链接：

```
$ git branch --set-upstream-to=origin/dev dev
Branch 'dev' set up to track remote branch 'dev' from 'origin'.
```

* 遇到蓝色字体要写说明怎么退出？

  **按住shift ZZ 就返回并且保存了**