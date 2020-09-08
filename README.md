# Locker

## 开发环境
 - JDK1.8+
 
## 业务需求

需求描述：储物柜(Locker)可以存包、取包
![locker](./locker.png)

评分标准：参考Classroom中的评分标准Excel文档

### 需求澄清总结：
1. 储物柜没有容量限制
2. 储物柜没有尺寸限制，默认多大的包都能存
3. 硬件系统功能不需要考虑（开门/关门/按钮/停电/没票纸）
4. 存包失败，需要提示用户是因为储物柜满了
5. 取包失败，需要提示用户是因为票据无效
6. 存包的位置是随机，没有顺序
7. 不要脑补需求，及时和PO确认
8. 不考虑并发

Tasking:
1. given `locker`有空闲，待存包裹 when 存包 then 返回票据
2. given `locker`无空闲、待存包裹 when 存包 then 抛出异常-柜已满
3. given `locker`、有效票据 when 取包 then 返回包裹
4. given `locker`、重复票据 when 取包 then 抛出异常-重复票据
5. given `locker`、无效票据 when 取包 then 抛出异常-无效票据


## ---------v2--------

### 需求：作为一个初级储物柜机器人，我能够按储物柜的顺序来存包，也能取包

#### 需求澄清：
1. PrimaryLockerRobot存包是按照locker顺序存包
2. PrimaryLockerRobot在某个Locker内存包的位置是随机的
3. 报错信息和Locker是一致的
4. PrimaryLockerRobot至少管理一个Locker
5. PrimaryLockerRobot会回收取过包的票据

#### Tasking：

##### 存放：
1. given `PrimaryLockerRobot`管理单个`locker`，`locker`有空闲 when `PrimaryLockerRobot`存包 then 返回票据，包裹存放在`locker`中
2. given `PrimaryLockerRobot`管理多个`locker`，`locker`全有空闲 when `PrimaryLockerRobot`存包 then 返回票据，包裹存放在第一序位`locker`中
3. given `PrimaryLockerRobot`管理多个`locker`，第一序位`locker`无空闲，其他`locker`有空闲 when `PrimaryLockerRobot`存包 then 返回票据，包裹存放在第一个有空闲的`locker`中
4. given `PrimaryLockerRobot`管理多个`locker`，`locker`均无空闲 when `PrimaryLockerRobot`存包 then 抛出异常-柜已满

##### 取包：
1. given `PrimaryLockerRobot`管理多个`locker`，有效票据 when `PrimaryLockerRobot`取包 then 取得包裹
2. given `PrimaryLockerRobot`管理多个`locker`，无效票据 when `PrimaryLockerRobot`取包 then 抛出异常-无效票据
3. given `PrimaryLockerRobot`管理多个`locker`，重复票据 when `PrimaryLockerRobot`取包 then 抛出异常-无效票据， 销毁重复票据

## ---------v3--------

### 需求：作为一个聪明的储物柜机器人,我能够将包存在空格最多的那个储物柜,并可以取出

#### Tasking：

##### 存放：
1. given `SmartLockerRobot`管理一个`locker`，`locker`有空闲容量 when `SmartLockerRobot`存包 then 返回票据，包裹在`locker`中
4. given `SmartLockerRobot`管理一个`locker`，`locker`无空闲 when `SmartLockerRobot`存包 then 抛出异常-无空闲
1. given `SmartLockerRobot`管理多个`locker`, 例如管理了两个`locker`，第一个`locker`有5个空闲容量， 第二个`locker`有2个空闲容量 when `SmartLockerRobot`存包 then 返回票据，包裹存放在第一个`locker`中
2. given `SmartLockerRobot`管理多个`locker`, 例如管理了两个`locker`，第一个`locker`有2个空闲容量， 第二个`locker`有3个空闲容量 when `SmartLockerRobot`存包 then 返回票据，包裹存放在第二个`locker`中
3. given `SmartLockerRobot`管理多个`locker`, 例如管理了两个`locker`，`locker`最大空闲容量相同 when `SmartLockerRobot`存包 then 返回票据，包裹存放在第一个`locker`中
4. given `SmartLockerRobot`管理多个`locker`, 例如管理了两个`locker`，`locker`均无空闲 when `SmartLockerRobot`存包 then 抛出异常-无空闲

##### 取包：
1. given `SmartLockerRobot`管理多个`locker`，有效票据 when `SmartLockerRobot`取包 then 取得包裹
2. given `SmartLockerRobot`管理多个`locker`，无效票据 when `SmartLockerRobot`取包 then 抛出异常-无效票据

## ---------v4--------
需求：作为储物柜机器人的经理，我要管理多个机器人，我可以让机器人存包，也可以自己存包
需求澄清：
1. LockerRobotManager至少管理一个Robot或者Locker
2. LockerRobotManager会让Robot先存包，然后再自己存包
3. LockerRobotManager管理的Robot/Locker按顺序存包

#### Tasking：

##### 存放：
1. given `Locker Robot Manager` 管理了多个`locker`， 无`robot`， 例如管理了两个`locker`， 均有空闲容量，when`Locker Robot Manager`存包， then 返回票据，包裹存在第一个`locker`中
2. given `Locker Robot Manager` 管理了多个`locker`， 无`robot`， 例如管理了两个`locker`， 第一个`locker`已满， 第二个`locker`有空闲容量，when `Locker Robot Manager`存包， then 返回票据，包裹存在第二个`locker`中
3. given `Locker Robot Manager` 管理了多个`locker`， 无`robot`， 例如管理了两个`locker`， `locker`均无空闲容量，when`Locker Robot Manager`存包， then 抛出异常-无空闲
4. given `Locker Robot Manager` 管理了多个`robot`， 无`locker`， 例如管理了两个`robot`， 所有`robot`管理的`locker`均有空闲容量，when`Locker Robot Manager`存包， then 返回票据， 由第一个`robot`存包 
5. given `Locker Robot Manager` 管理了多个`robot`， 无`locker`， 例如管理了两个`robot`， 第一个`robot`管理的`locker`已满， 第二个`robot`管理的`locker`有空闲容量，when`Locker Robot Manager`存包， then 返回票据， 由第二个`robot`存包
6. given `Locker Robot Manager` 管理了多个`robot`， 无`locker`， 例如管理了两个`robot`， 所有`robot`管理的`locker`均无空闲容量，when`Locker Robot Manager`存包， then 抛出异常-无空闲
7. given `Locker Robot Manager` 管理一个`robot`， 一个`locker`， `robot`管理的`locker`和`Locker Robot Manager` 管理的`locker`均有空闲容量，when`Locker Robot Manager`存包， then 返回票据， 由`robot`存包
8. given `Locker Robot Manager` 管理一个`robot`， 一个`locker`， `robot`管理的`locker`已满， `Locker Robot Manager` 管理的`locker`有空闲容量，when`Locker Robot Manager`存包， then 返回票据， 由`locker`存包
9. given `Locker Robot Manager` 管理一个`robot`， 一个`locker`， `robot`管理的`locker`和`Locker Robot Manager` 管理的`locker`均无空闲容量，when`Locker Robot Manager`存包， then 抛出异常-无空闲

##### 取包：
1. given `Locker Robot Manager` 管理了多个`locker`， 无`robot`， 例如管理了两个`locker`，有效票据，when`Locker Robot Manager`取包， then 取得包裹
2. given `Locker Robot Manager` 管理了多个`locker`， 无`robot`， 例如管理了两个`locker`，无效票据，when`Locker Robot Manager`取包， then 抛出异常-无效票据
3. given `Locker Robot Manager` 管理了多个`robot`， 无`locker`， 例如管理了两个`locker`，有效票据，when`Locker Robot Manager`取包， then 取得包裹
4. given `Locker Robot Manager` 管理了多个`robot`， 无`locker`， 例如管理了两个`locker`，无效票据，when`Locker Robot Manager`取包， then 抛出异常-无效票据
5. given `Locker Robot Manager` 管理一个`robot`， 一个`locker`，有效票据，when`Locker Robot Manager`取包， then 取得包裹
6. given `Locker Robot Manager` 管理一个`robot`， 一个`locker`，无效票据，when`Locker Robot Manager`取包， then 抛出异常-无效票据
