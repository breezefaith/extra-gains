
执行前，请先用浏览器打开对应的 url 看是否能打开，能打开再继续

脚本运行步骤：
1. 确保电脑已安装 nodejs 环境，具体请百度即可；cmd 命令行执行 node --version 能显示出版本号即安装完成；

2. cmd 进入脚本所对应的目录，先执行 npm install 安装所需的依赖包；

3. 打开 mysql.js 文件，修改 mysql 数据库信息；

3. 然后执行 node main.js [board url] 即可对对应的地址进行抓取；

备注：
	1. 若执行第三步时有报错的，说找不到什么依赖的，请执行: npm install 报错对应的依赖名称
	2. 抓取过程中会生成 pageErrorIndex.txt、userErrorIndex.txt 等文件，不用管，只是用来记录抓取过程中
	   网络发生错误时已经执行到哪里，所有数据抓取完会自动删除；
	3. 如果需要更新数据库中的信息的话，可以执行命令：node getUser.js
