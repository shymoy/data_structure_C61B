# 伯克利C61B课的笔记

>## 学习内容包括
>### java基础  
>### [git等工具](https://github.com/shymoy/data_structure_C61B/tree/main?tab=readme-ov-file#git%E7%AE%A1%E7%90%86%E6%96%B9%E6%B3%95)
>### 数据结构
# 正文
## java基础  
### 基本特性
- 强类型语言
- 面向对象的语言  
- 静态类型语言  
>变量和函数返回值的类型永远不能改变
所有java程序在运行之前就被验证了
(更好的捕捉错误，方便了debug，在用户端永远不会发生类型错误)

#### 语法特性  
- **文件的名称必须与class名一致** 否则无法运行
- 结尾有分号;作为结束
- 所有的代码都必须是类的一部分
- 注释为/* **/
- String[] args是用来读取command line后面的字符的
>e.g:java HelloWorld 10 20
args[0] = 10

#### 与其他语言的区别  
- Array的命名为int[] num = new int[10];  
- new与c的区别:
>- c中new在堆上分配之后需要自己delete掉  
Java会自己释放
>- java的new返回的是reference（引用）  
c返回的是指针

- **Enhanced loop**:
  ```
  int[] numbers = {1, 2, 3, 4, 5};
    for (int number : numbers) {
        System.out.println(number);
    }
  ```

#### 在terminal使用java  
>1. 先javac xx.java文件 进行编译把它变成一个.class file
>2. 再java class文件
 >3. 为什么要进行编译=》
     a. 进行类型检查，确保应用分发到用户手里不会出现类型错误
     b. 让电脑更容易执行
     c. 利于安全
 >4. 注意：文件名必须与类名一致
>**只编译主函数类也会自动编译其他的类**
## git管理方法

定义：一个代码的版本管理工具

### 保存原理

就像手机先拍一张全景图，再局部把部分拼接上去，最后完成了 就commmit上传

### 指令

#### `git init`
git init 用于初始化一个新的 Git 仓库。这意味着它会在当前目录中创建一个隐藏的 .git 文件夹，用于存储所有版本控制信息。
命令: git init
用法: 在你想要开始版本控制的项目文件夹中运行这个命令。
结果: 该文件夹现在成为一个 Git 仓库，你可以开始跟踪文件的更改。

####  `git add`
git add 命令用于将文件添加到暂存区（也叫“索引”），以便它们可以在下次提交时被包含在内。你可以选择添加单个文件、多个文件，或者整个文件夹。
命令: git add <文件名或路径>
用法: 当你修改了文件并准备好保存更改时，使用这个命令将它们放入暂存区。
>例子:
添加单个文件: git add example.txt
添加所有更改的文件: git add .（git add . 表示添加当前目录下的所有文件）

#### `git commit`
git commit 命令用于将暂存区中的更改保存到仓库历史中。每次提交都会创建一个独立的快照，记录当前状态下的文件。
命令: git commit -m "提交信息"
用法: 在你使用 git add 将文件添加到暂存区后，使用这个命令提交这些更改，并附上一条简短的描述。
>例子: git commit -m "添加了新的功能"

#### `git status` 
命令：git status
功能：显示当前项目的状态，包括哪些文件已更改、哪些文件已被暂存等待提交，以及哪些文件未被跟踪。  
>主要输出信息：  
**Changes not staged for commit**（未暂存的更改）：表示已修改但未添加到暂存区的文件。  
**Changes to be committed**（待提交的更改）：表示已暂存并准备提交的文件。  
**Untracked files**（未跟踪的文件）：表示未被添加到版本控制中的新文件。


#### `git log`  
是一个非常有用的 Git 命令，用于查看仓库的提交历史。它会显示每个提交的详细信息，包括提交的哈希值、作者、日期和提交信息。  
git log 基本用法：  
>命令：git log  
功能：显示提交历史记录，从最新的提交开始，按时间顺序向后排列。
输出内容：
提交哈希值：每个提交都有一个唯一的 SHA-1 哈希值，用于标识该提交。
作者信息：提交的作者。
日期：提交的时间和日期。
提交信息：你在执行 git commit 时输入的提交信息

#### `git checkout`
git checkout 是 Git 中非常常用的命令，用于切换分支、恢复文件或查看某个特定的提交。  
>**切换分支**：用于切换到另一个分支。  
命令: git checkout <分支名>   
示例: git checkout main 切换到 main 分支。    
**恢复文件**：恢复工作目录中的文件到上一个提交的状态，或恢复为暂存区的状态。  
命令: git checkout -- <文件名>  
>示例: git checkout -- file1.txt 将 file1.txt 恢复为上一次提交的状态。  
**查看某个特定提交**：用于切换到某个提交的状态（注意，这种切换会进入“分离头指针”状态）。  
命令: git checkout <提交哈希>  
示例: git checkout 1a2b3c4d 切换到指定的提交。


#### `git reset`
移除文件从暂存区中：  
git reset HEAD filename.txt  
这会将 filename.txt 从暂存区中移除，但文件本身的修改仍然存在于工作目录中。

#### `git commit --amend`
作用：用来补充提交或者修改上次的提交  
**注意：会替换最新的一次提交，哈希值也会变换**

#### `git pull`

git pull 命令用于从远程仓库拉取最新的更改并将其合并到当前分支。它实际上是 git fetch 和 git merge 的组合，即首先从远程仓库获取最新的提交记录，然后将这些更改合并到你的本地分支中。  
>**基本用法:**
git pull <远程仓库> <分支>  
<远程仓库>：通常是 origin，代表你克隆的远程仓库。  
<分支>：是你想要拉取的远程分支，通常是 main 或 master。
示例：  
git pull origin master
这会从远程仓库 origin 的 master 分支拉取更改并将它们合并到你当前的本地分支。  
不指定分支  
如果你的当前分支已经设置了上游分支（也就是跟踪了远程分支），你可以直接使用 git pull 而不指定远程仓库和分支：

#### `git remote`
>**查看远程仓库**
你可以使用以下命令查看当前配置的远程仓库：  
git remote -v  
这将显示所有已配置的远程仓库的名称和它们的 URL。  
**添加远程仓库**
要为你的本地仓库添加一个远程仓库，可以使用以下命令：  
git remote add <远程名称> <仓库URL>  
**删除**  
git remote remove <远程名称>  
**重命名**  
你可以重命名一个远程仓库：  
git remote rename <旧名称> <新名称>  

#### `git push`
**基本用法**
>git push <远程名称> <分支名称>
这会将本地的 master 分支推送到远程仓库 origin 的 master 分支。

**常用选项**  
`-u` ：设置上游分支
>e.g:git push -u origin master
这会将 origin/master 设置为 master 分支的上游分支，以后可以只使用 git push 直接推送到远程。

`-f`:强制推送
>有时候，当远程仓库的历史与你的本地仓库的历史不一致时（例如你使用了 git rebase 重新整理了提交历史），你可能需要强制推送：**注意：强制推送会覆盖远程仓库中的历史记录，可能导致其他人的工作丢失，因此要谨慎使用**

`--delete`:删除远程分支
>git push origin --delete <分支名称>

### 问题

- git clone的同时会remote与远程库建立连接，reo在文件夹里面。
- rm -rf .git强制删除库
- 远程仓库和本地库文件不匹配也无法上传
- 
