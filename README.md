# 伯克利C61B课的笔记
>## 学习内容包括
>### java基础  
>### [git等工具](https://github.com/shymoy/data_structure_C61B/tree/main?tab=readme-ov-file#git%E7%AE%A1%E7%90%86%E6%96%B9%E6%B3%95)
>### 数据结构


# 正文

## git管理方法

定义：一个代码的版本管理工具

### 保存原理

就像手机先拍一张全景图，再局部把部分拼接上去，最后完成了 就commmit上传

### 指令

#### `git init`
>git init 用于初始化一个新的 Git 仓库。这意味着它会在当前目录中创建一个隐藏的 .git 文件夹，用于存储所有版本控制信息。
>>命令: git init
用法: 在你想要开始版本控制的项目文件夹中运行这个命令。
>>>结果: 该文件夹现在成为一个 Git 仓库，你可以开始跟踪文件的更改。

####  `git add`
>git add 命令用于将文件添加到暂存区（也叫“索引”），以便它们可以在下次提交时被包含在内。你可以选择添加单个文件、多个文件，或者整个文件夹。
>>命令: git add <文件名或路径>
用法: 当你修改了文件并准备好保存更改时，使用这个命令将它们放入暂存区。
>>>例子:
添加单个文件: git add example.txt
添加所有更改的文件: git add .（git add . 表示添加当前目录下的所有文件）

#### `git commit`
>git commit 命令用于将暂存区中的更改保存到仓库历史中。每次提交都会创建一个独立的快照，记录当前状态下的文件。
>>命令: git commit -m "提交信息"
>>>用法: 在你使用 git add 将文件添加到暂存区后，使用这个命令提交这些更改，并附上一条简短的描述。
>>>>例子: git commit -m "添加了新的功能"

#### `git status` 
>命令：git status
>>功能：显示当前项目的状态，包括哪些文件已更改、哪些文件已被暂存等待提交，以及哪些文件未被跟踪。  
>>>主要输出信息：  
**Changes not staged for commit**（未暂存的更改）：表示已修改但未添加到暂存区的文件。  
**Changes to be committed**（待提交的更改）：表示已暂存并准备提交的文件。  
**Untracked files**（未跟踪的文件）：表示未被添加到版本控制中的新文件。


#### `git log`  
>是一个非常有用的 Git 命令，用于查看仓库的提交历史。它会显示每个提交的详细信息，包括提交的哈希值、作者、日期和提交信息。  
>>git log 基本用法：  
命令：git log
功能：显示提交历史记录，从最新的提交开始，按时间顺序向后排列。
输出内容：
提交哈希值：每个提交都有一个唯一的 SHA-1 哈希值，用于标识该提交。
作者信息：提交的作者。
日期：提交的时间和日期。
提交信息：你在执行 git commit 时输入的提交信息
