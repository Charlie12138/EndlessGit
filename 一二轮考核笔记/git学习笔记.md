## 创建版本库
---
**1**.初始化一个Git仓库，使用==git init==命令。  
**2**.添加文件到Git仓库，分两步：  
第一步，使用命令==git add <file>==，注意，可反复多次使用，添加多个文件；  
第二步，使用命令==git commit==，完成。  
简单解释一下git commit命令，-m后面输入的是本次提交的说明，可以输入任意内容
---
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

   ==使用Windows的童鞋注意了，如果你在资源管理器里新建一个`.gitignore`文件，它会非常弱智地提示你必须输入文件名，但是在文本编辑器里“保存”或者“另存为”就可以把文件保存为`.gitignore`了。==

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