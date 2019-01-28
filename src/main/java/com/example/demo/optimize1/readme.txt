写在前边的实现需求：
1.总共10万个电话号码；
2.电话号码中有重复；
3.查找出不重复的号码；

一、优化前的实现方式：
1.用List.Contains验证重复数据，List.Add添加不重复数据；
2.最终从List中取出正确的数据。

二、优化后的实现方式：
1.先对10万数据排序；
2.对比前后两条数据
3.筛选出正确数据。

三、优化前后对比：
generate test data time ：237
----------- before optimize -----------
find time ：831025
repetition : 703036
correction : 296964
----------- after optimize -----------
find time ：670
repetition : 703036
correction : 296964
