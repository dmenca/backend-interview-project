# 1. 什么是事务？
事务就是一组数据库执行命令的集合，一组要么全部成功、要么全部失败的数据库操作。

# 2. 为什么需要事务
以转账为例，假设一个转账场景，A向B转账，实际分为两步操作：
1. A账户扣除100元
2. B账户添加100元

如果第一步成功了，第二步失败了，那么就出现了A账户扣了钱，B账户却没有收到钱的情况，就会导致转账异常，钱凭空消失了。

事务就是用来避免这个情况发生，要么步骤1步骤2全部成功，要么步骤1步骤2全部失败。

# 3.事务的四大特性（ACID）
## 原子性（Atomocity）
一个事务中的所有操作，要么全部成功，要么全部失败，中间任何一个步骤出现问题，整个事务都会回滚。
由undo log（回滚日志）来保证原子性。

## 一致性（Consistency）
事务执行前后，数据都保证一致性状态。例如转账前后，A+B的总金额保持不变。一致性是最终目标，依赖其他三个特性共同实现的。


## 隔离性（Isolation）
多个并发事务之间相互隔离，互不干扰，避免因并发执行导致的数据不一致问题。MySQL通过隔离级别来量化隔离程度，不同级别对应不同的并发问题解决方案，底层依赖锁机制和多版本并发控制（MVCC）实现。

## 持久性（Durability）
事务一旦提交，数据就会永久保存。即使 MySQL 崩溃，数据也不会丢。由redo log保证。

# 4.事务的隔离级别
## 并发事务会带来的问题
在没有隔离性保障的情况下，并发事务可能导致以下问题（按严重程度排序）：

## 脏读
一个事务读到其他事务未提交的修改数据。

1. 事务A修改balance，将balance的值从100修改为500，但是未提交事务。
2. 此时事务B读取balance时，查询到balance为500。

如果事务B回滚，那么事务A读取到的就是脏数据。

## 不可重复读
一个事务内多次读取同一个数据，出现前后结果不一致的情况。

1. 事务A读取balance值，查询到balance是100.
2. 事务B修改balance值为500，并且提交事务。
3. 事务A再次查询balance值，查询到balance值为500.

事务 A 多次读取同一数据，期间事务 B 修改并提交了该数据，导致 A 多次读取结果不一致。

## 幻读
在一个事务内多次查询某个符合查询条件的记录数量，出现前后结果不一致的情况。

1. 事务A查询balance大于100的记录数，此时有2条记录
2. 事务B插入了一条balance大于100的记录
3. 事务A再次查询balance大于100的记录数，此时变成了3条记录

事务 A 按条件查询数据，期间事务 B 插入 / 删除了符合条件的记录，导致 A 再次查询时结果集行数变化（像 “幻觉”）。


## MySQL的四种隔离级别

| 隔离级别|	脏读 | 不可重复读 |	幻读 | 说明|
|----|----|----|----|---|
|READ UNCOMMITTED（读未提交）|❌| ❌|	❌|最低级别，允许读取未提交的事务，性能最好，有并发问题|
|READ COMMITTED（读已提交））|✅|❌	|❌|只能读取已经提交的事务，解决脏读。但同一事务多次读取可能结果不一致（不可重复读）|
|REPEATABLE READ（可重复读）|✅|	✅|❌|MySQL 默认级别，保证同一事务内多次读取数据一致，解决脏读、不可重复读；通过 MVCC 避免幻读（InnoDB 特有）。|
|SERIALIZABLE（串行化）|✅|	✅|✅|最高级别，事务串行执行（加表级锁），完全隔离，无并发问题，但性能极差。|

1.READ UNCOMMITTED（读未提交）
```sql
-- 会话1
START TRANSACTION;
UPDATE user SET balance = 1000 WHERE id = 1; -- 未提交

-- 会话2（隔离级别设为 READ UNCOMMITTED）
START TRANSACTION;
SELECT balance FROM user WHERE id = 1; -- 读取到 1000（脏读）

-- 会话1回滚
ROLLBACK;

-- 会话2再次查询
SELECT balance FROM user WHERE id = 1; -- 回到原数值，之前读的是脏数据
```

2. READ COMMITTED（读已提交）
```sql
-- 会话1
START TRANSACTION;
UPDATE user SET balance = 1000 WHERE id = 1;
COMMIT;

-- 会话2（隔离级别设为 READ COMMITTED）
START TRANSACTION;
SELECT balance FROM user WHERE id = 1; -- 读旧值（如 0）
-- 会话1提交后
SELECT balance FROM user WHERE id = 1; -- 读新值 1000（不可重复读）
COMMIT;
```
3. REPEATABLE READ（可重复读，MySQL 默认）
```sql
-- 会话1（隔离级别 REPEATABLE READ）
START TRANSACTION;
SELECT balance FROM user WHERE id = 1; -- 读旧值 0

-- 会话2
UPDATE user SET balance = 1000 WHERE id = 1;
COMMIT;

-- 会话1再次查询
SELECT balance FROM user WHERE id = 1; -- 仍读 0（可重复读）
COMMIT;

-- 会话1提交后查询
SELECT balance FROM user WHERE id = 1; -- 读 1000
```

4. SERIALIZABLE（串行化）
```sql
-- 会话1（隔离级别 SERIALIZABLE）
START TRANSACTION;
SELECT * FROM user WHERE id > 0; -- 加表级锁

-- 会话2
INSERT INTO user (id, balance) VALUES (2, 500); -- 阻塞，直到会话1提交/回滚
```

