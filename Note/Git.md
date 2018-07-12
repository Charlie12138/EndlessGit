# Git基础知识

## 创建版本库:grinning:

- 把某个目录变成Git可以管理的仓库：**git init**
- 添加文件到Git仓库（即版本库）分为两步：
   	1. 将文件添加到暂存区：**git add <filename>**
  	2. 将文件提交到版本库：**git commit -m "<message>"**

## 时光机穿梭

- 查看仓库状态：**git status**

- 查看具体修改的内容：**git diff**

- 查看历史记录：**git log** 或 **git log --pretty=oneline** (后者单行显示简单的信息)

- **版本回退**：**git reset --hard <commit-id>**    回退到前几个则可把<commit-id>换成HEAD(^)，^的数量即为上多少个版本，数量过多如100可写成HEAD~100

- **重返未来**：**git reflog** 查看命令历史，以便确定要回到未来的哪一个版本

- **撤销修改**：

  1. **git checkout -- file**    改乱了工作区某个文件的内容，想直接丢弃工作区的修改，--十分重要，没有就变成了“切换到另一个分支”的命令
  2. **git reset HEAD <filename>**    将改乱的工作区的内容添加到了暂存区，想丢弃修改，该命令为第一步，第二步为上一个命令

- **删除文件**：假设中用**rm <filename>**删除了文件，或者在文件管理器中直接删除，则是删除了**工作区**中的文件，有两个选择：

  1. 再将该文件从**版本库**中删除：**git rm <filename>**，并且**git commit -m "xxxxxx"**

  2. 删错了，再从**版本库**中将误删的文件恢复到最新版本：**git checkout -- <filename>**  

     但是只能恢复文件到最新版本，会丢失**最近一次提交后修改的内容** :sweat_smile: 

## 远程仓库（如:GitHub, 码云）

- 创建SSH KEY：**ssh-keygen -t rsa -C "youremail@example.com"**，并在github上**Add SSH Key**

- 关联一个远程库：**git remote add <remote repository name, normally "origin"> git@server-name:Wiwens/repo-name.git**

- 把本地库内容推送到远程仓库：

  1. 第一次推送master分支的所有内容：**git push -u origin master**
  2. 后来的推送最新修改：**git push origin master**

- 克隆远程仓库(如github)：**git clone git@github.com:Wiwens/<RepoName>.git**

  ps: git支持多种协议，包括https，但通过ssh支持的原生git协议速度最快

## 分支管理

- 查看分支：**git branch**

- 创建分支：**git branch <branch-name>**

- 切换分支：**git checkout <branch-name>**

- 创建+切换分支：**git checkout -b <branch-name>**

- 合并某分支到当前分支：**git merge <branch-name>**

- 删除分支：**git branch -d <branch-name>**  

  如果要删除一个没有被合并过的分支：**git branch -D <branch-name>** 强行删除

- **解决冲突**：当Git无法自动合并分支时，必须首先解决冲突，**把Git合并失败的文件手动编辑为我们希望的内容，再提交**

- 查看分支合并图：

  1. 详细：**git log --graph**
  2. 单行：**git log --graph --pretty=oneline --abbrev-commit**

### 分支管理策略

- 合并分支时，使用 **git merge --no-ff -m "<message>"  <branch-name>** 命令则是用普通模式合并，合并后的历史有分支，能看出来曾经做过合并，而 **fast forward** 合并就看不出来曾经做过合并

### bug分支

- 修复bug时，会通过创建新的bug分支进行修复，然后合并，最后删除
- 手头工作没有完成时，先把工作现场 **git stash**, 再去修复bug；修复完后，再**git stash pop**, 回到工作现场
- 查看所有的**stash**：**git stash list**
- 把**stash**恢复的两种方法：
  1. 先**git stash apply**恢复，但是恢复后，stash内容并不删除，再用**git stash drop**删除
  2. 直接**igt stash pop**, 恢复的同时也把stash内容删除了
- 可以多次**stash**, 恢复时，先用**git stash list**查看，然后恢复指定的stash：**git stash apply stash@{<digit>}**

### feature分支

- 开发一个新feature，最好新建一个分支

- 丢弃一个没有被合并过的分支：**git branch -D <branch-name>** 强行删除

  

## 多人协作

- 查看远程库信息： **git remote -v**   
- 本地新建的分支如果不推送到远程，对其他人就是不可见的
- 从本地推送分支： **git push origin branch-name**, 若推送失败，先用 **git pull** 抓取远程的新提交
- 在本地创建和远程分支对应的分支：**git checkout -b branch-name origin/branch-name**, 本地和远程分支的名称最好一致
- 建立本地分支和远程分支的关联：**git branch --set-upstream branch-name origin/branch-name**  (eg: git pull 提示 no tracking information的时候，则要使用这个 :sweat_smile:)
- 从远程抓取分支：**git pull**, 如果有冲突，要先处理好冲突 *（修改文件内容，再add）*

### 将提交历史合并成一条直线

- 把本地未push的分叉提交历史整理成直线：**git rebase**

  *目的是使我们在查看历史提交的变化时更容易，因为分叉的提交需要三方对比*

## 标签    (不可修改，本质是指针)

- 新建一个标签：**git tag <tagname> <commit id>**, 没有加commit id默认打在**HEAD**上，
- 指定标签信息：**git tag -a <tagname> -m "balabala.." <commit id>**
- 查看所有标签：**git tag**
- 查看某个标签的具体信息：**git show <tagname>**
- 删除一个本地标签：**git tag -d <tagname>**
- 删除一个远程标签：先删除本地标签，再 **git push origin :refs/tags/<tagname>**
- 把一个标签推送到远程：**git push origin <tagname>**
- 一次性推送全部尚未推送到远程的本地标签：**git push origin --tags**

---

## 使用GitHub

- 参与一个开源项目

   1. **Fork**一个开源仓库，就相当于在自己的账号下克隆了一个相同的仓库

  	2. 从自己的账号下**clone**到本地：**git clone git@github.com:Wiwens/<RepoName>.git**

      ps：一定要从自己的账号下**clone**仓库，这样才可以推送修改。如果从原作者的仓库地址克隆，因为没有权限，无法推送修改

  	3. 通过在GiuHub上发起一个**pull reqest**来给官方仓库贡献代码*（接不接受看对方心情）*

---

## 一个本地库关联多个远程库 ，同时与多个远程库互相同步

- 以learngit本地库为例，关联GitHub和码云各一个远程库

  1. 先删除已经关联的名为**origin**的远程库：**git remote rm origin**

  2. 关联GitHub的远程库：**git remote add github git@github.com:Wiwens/learngit.git

     此时远程库的名字叫**github**，不叫origin了

  3. 再关联码云的远程库：**git remote add gitee git@gitee.com:zhangmiaoxin/learngit.git**

     同样，此时远程库的名称叫**gitee**，不叫origin

- :ok_hand:**如果关联多个远程库，需要用不同的名称来标识不同的远程库**，因此一个叫github一个叫gitee

- 查看远程库信息：**git remote -v**

- 推送信息到两个不同的远程库：

  - 推送到GitHub：**git push github master**
  - 推送到码云（gitee)：**git push gitee master**

---

## 自定义Git

### 忽略特殊文件

- ###由于某些文件必须放在GIt工作目录中，但又不能提交，每次 git status 都会显示 Untracked files，强迫症感到不爽 :sob:  

  - 编写 **.gitignore**放在GIt**工作区**的根目录下，将需要忽略的文件名填入，Git就会自动忽略这些文件

  - 忽略文件的原则是：

     	1. 忽略操作系统自动生成的文件，比如缩略图等
    	2. 忽略编译生成的中间文件、可执行文件等。如Java编译产生的.class文件，使用通配符***.class**进行对所有.class文件的忽略
    	3. 忽略自己的带有敏感信息的配置文件，比如存放口令的配置文件

  - 检查**.gitignore** 某个规则是否写错：**git check-ignore -v xxx.class**

    Git会告诉我，**.gitignore**的第几行规则忽略了该文件，就可以知道应该修订哪个规则

  - 注意：:grinning: **.gitignore** 文件本身要放到版本库里，并且可以对其进行**版本管理**

    


### 为了偷懒  配置别名 :wink:

- 用**st**表示**status**：**git config --global alias.st status**  

  命令执行成功后，用到status的地方都可以用st代替

- --global为全局参数，即该命令在这台电脑的所有Git仓库下都可以使用；**加上它是针对当前童虎起作用，不加则只对当前的仓库起作用**

- 每个仓库的Git配置文件放在：**.git/config** 文件中

- 当前用户的Git配置文件放在Users主目录下的：**.gitconfig **文件中；配置别名可以直接修改这个文件，删除同

- 删除别名：**git config --unset alias.<aliasname>**

  





