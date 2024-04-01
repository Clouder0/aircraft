# AircraftWar

This is HITSZ OOP course lab.

这是我们学校的面向对象课程实验。


## 吐槽

### Factory 

Java 的表达力很多时候还是让人非常痛苦。写了很多乱七八糟的代码。

假如我有若干个 class，我希望能够有类似于：

```python
item_types = [type1, type2, type3]
items = []
for item_type in item_types:
    items.append(new item_type(...args))
```

的写法，那么我需要做的事情是……

```java
interface ItemTypeFactoryInterface {
    ItemTypeBase generate();
}
class ItemType1Factory implements ItemTypeFactoryInterface {}
class ItemType2Factory implements ItemTypeFactoryInterface {}
class ItemType3Factory implements ItemTypeFactoryInterface {}

List<ItemTypeFactoryInterface> item_types;
// ...
```

或许这就是设计模式的巧妙吧(

### Closure

没有 closure，痛苦的又一天。
