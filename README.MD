# 日志

- 更新至1.20.1
- 无限水取消 [可关闭] 2024/6/2 21:48
- 磨砂的砂石 2024/6/6 22:08
- 磨砂的工具 2024/6/7 20:53
  - 目前已添加 所有的采集速度受其影响 2024/6/7 20:54
- 添加了修仙体系 2024/6/10 20:39
  - 凶兽待添加
  - 灵兽待添加
  - 丹药
    - 基础类已建成 品级颜色已定义 2024/6/13 21:50
    - 聚气丹(Awaken charge pellet)
    - 辟谷丹(Grain avoidance pellet)
    - 筑基丹(Foundation pellet)
  - 灵草待添加
  - 武器待添加
- 配方除了本mod和kubejs的和crafttweaker的其余全部移除 [可配置] 2024/6/13 21:52
- 采矿机制
  - 疲劳值 初始100 最终int最大数值-3
- 连锁机制
  - 玩家破坏范围默认4x4
  - 玩家连锁的方块列表设定 [多模组兼容强化]
  - 三种模式， 不启用 潜行 按键`
  - 多种矿物一起连锁
- 钓鱼机制
  - 钓鱼水质
    - S+
    - S
    - S-
    - A+
    - A
    - A-
    - B+
    - B
    - B-
    - C+
    - C
    - C-
    - D+
    - D
    - D-
    - E+
    - E
    - E-
    - F+
    - F
    - F-
  - 钓竿等级
    - 1 钓鱼进度条 [特长] 但是无法钓B-级别的水质(硬钓会损耗水质)
    - 2 钓鱼进度条 [超长] 但是无法钓A+级别的水质(硬钓会损耗水质)
    - 3 钓鱼进度条 [中等] 所有水质都能钓
    - 4 钓鱼进度条 [短]  钓低于B级别的水质等级会缓缓增加
    - 5 钓鱼进度条 [超短] 钓鱼低于S-级别水质等级会缓缓增加
    - 6 钓鱼进度条 [无] 神级钓竿，可能会有意想不到的作用

# package name
|     模块名     |    说明     |  前置  | 前置  |  前置   | 前置  |  前置  |  前置   |     联动      |
|:-----------:|:---------:|:----:|:---:|:-----:|:---:|:----:|:-----:|:-----------:|
|     lib     |     库     | none |     |       |     |      |       |    none     |
|     db      |   数据存档    | none |     |       |     |      |       |    none     |
| permissions |    权限组    |  db  |     |       |     |      |       |    none     |
|    miner    |    连锁     |  db  |     |       |     |      |       | permissions |
|     god     |    修仙     |  db  |     |       |     |      |       | permissions |
|   datagen   | data和资源生成 | none |     |       |     |      |       |    none     |
|    team     |    组队     |  db  |     |       |     |      |       | permissions |
|    quest    |    任务书    |  db  |     |       |     |      |       | permissions |
|     map     |    地图     |  db  |     |       |     |      |       | permissions |
|    query    |    查询     |  db  |     |       |     |      |       | permissions |
|   command   |    命令     |  db  |     |       |     |      |       | permissions |
|  dovehome   |    本体     |  db  | lib | miner | god | team | quest | permissions |
|   viafix    | 原版修复原版修改  |      |     |       |

# Config Example

```toml
#No Infinite Water
NoInfiniteWater = true
#Remove All Recipe Include Mod
RemoveAllRecipeIncludeMod = "dovehomemod,kubejs,crafttweaker"
#auto save sql
AutoSaveSQL = 3000
```