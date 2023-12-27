该商店管理系统，是使用Java语言进行编写，基于jdbc连接mysql，使用了GUI，实现了最基础的增删查改，同时实现了在线客服，使用了TCP的网络编程。

需求分析：
登录功能：通过输入用户名和密码进入管理系统主页，当输入密码或用户名错误，会显示提示框，提示“用户名或密码错误！”；登录成功，会跳出提示框显示“登录成功”，并进入到管理系统主页面。

管理系统主页面：在管理系统主页面上，存在两个视窗，7个按钮，一个组合框，一个文本框，我们通过这些来进行对商品的增删查改。

增加页面：点击增加按钮，会出现增加页面，上面有商品的各个属性，往上面填写内容，点击确定，会增加商品记录。

删除页面：点击删除按钮，会出现删除页面，我们通过商品编号来查找删除内容，在删除页面上会显示商品的各个属性，点击确认，即可从商品记录中删除商品记录。

修改页面：点击修改按钮，会出现修改页面，我们可以修改商品的名字、种类、价格、数量，点击保存，商品的修改记录就保存了。

组合框：通过组合框来选择查找商品方式，我们可以挑选3个方式，分别是商品编号、商品名字、商品种类来进行查找商品，查找的商品会在两个视窗展示出来，一个展示视窗展示商品记录的首记录，另一个浏览区视窗展示所有查询记录，我们
可以通过点击浏览区视窗里的查询记录，在展示视窗查看商品信息；

查找按钮：通过组合框不同的选择及文本框里的内容，对商品进行查找。

添加按钮：该按钮可以跳转到添加页面添加商品内容。

修改按钮：该按钮可以跳转到修改页面修改商品记录。

删除按钮：该按钮可以跳转到删除页面删除商品记录。

浏览按钮：可以浏览所有商品记录，在浏览区窗口。

刷新按钮：刷新浏览区所有商品记录。

客服按钮：进入客服页面，跟客户端进行网络通信
