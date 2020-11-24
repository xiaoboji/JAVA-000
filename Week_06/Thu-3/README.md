23种设计模式总结

# 一、概述
 ## 1.1 模式的三个层次

 1. 解决方案层面(架构模式)  
 2. 组件层次(框架模式)  
 3. 代码层次(GOF设计模式)

 ## 1.2 GOF设计模式的分类 
 - **创建型** 
 
怎么创建新对象，封装复杂的创建过程，解耦对象的创建代码和使用代码。(单例模式、工厂模式、抽象工厂、创建者模式)
 - **结构型** 
 
 怎么封装和组合对象，解决特定应用场景的问题(代理模式、桥接模式、装饰者模式、适配器模式、门面模式、组合模式、享元模式)
 - **行为型**
 
 怎么来处理对象的，一般是针对某种使用场景，主要解决的就是“类或对象之间的交互”问题。(观察者模式、模板模式、策略模式、责任链模式、迭代器模式、状态模式、访问者模式、备忘录模式、命令模式、解释器模式、中介模式)

# 二、创建型设计模式
## 2.1 单例模式(Singleton)
- **概念：**

单例模式用来创建全局唯一的对象。一个类只允许创建一个对象（或者叫实例），那这个类就是一个单例类，这种设计模式就叫作单例模式。

单例有几种经典的实现方式，它们分别是：饿汉式、懒汉式、双重检测、静态内部类、枚举。

- **应用：**

1. 从业务概念上，有些数据在系统中只应该保存一份，就比较适合设计为单例类。比如，系统的配置信息类，资源访问冲突。
2. Spring种默认的注入就是单例，通过 IOC 容器来实现对象的唯一性的控制。不过这样实现的单例并非真正的单例，它的唯一性的作用范围仅仅在同一个 IOC 容器内。
## 2.2 工厂方法和抽象工厂(Factory Method & Abstract Factory)
- **概念**

工厂模式用来创建不同但是相关类型的对象（继承同一父类或者接口的一组子类），由给定的参数来决定创建哪种类型的对象。

当每个对象的创建逻辑都比较简单的时候，推荐使用简单工厂模式，将多个对象的创建逻辑放到一个工厂类中。当每个对象的创建逻辑都比较复杂的时候，为了避免设计一个过于庞大的工厂类，我们推荐使用工厂方法模式，将创建逻辑拆分得更细，每个对象的创建逻辑独立到各自的工厂类中。

抽象工厂的应用场景比较特殊，所以很少用到.

- **应用**

1. 依赖注入框架，比如 Spring IOC、Google Guice，它用来集中创建、组装、管理对象，跟具体业务代码解耦，让程序员聚焦在业务代码的开发上。Spring 源码主要是 BeanFactory 类和 ApplicationContext 相关类（AbstractApplicationContext、ClassPathXmlApplicationContext、FileSystemXmlApplicationContext…）。
2. 不同但是相关类型的对象（继承同一父类或者接口的一组子类的时候。
## 2.3 创建者模式(Builder)
- **概念**

建造者模式用来创建复杂对象，可以通过设置不同的可选参数，“定制化”地创建不同的对象。

如果一个类中有很多属性，为了避免构造函数的参数列表过长，影响代码的可读性和易用性，可以通过构造函数配合 set() 方法来解决。但是，如果存在下面情况中的任意一种，就要考虑使用建造者模式了。

- **应用**
1. 必填的属性有很多，把这些必填属性都放到构造函数中设置，那构造函数就又会出现参数列表很长的问题。如果把必填属性通过 set() 方法设置，那校验这些必填属性是否已经填写的逻辑就无处安放了。
2. 如果类的属性之间有一定的依赖关系或者约束条件，继续使用构造函数配合 set() 方法的设计思路，那这些依赖关系或约束条件的校验逻辑就无处安放了。
3. 如果希望创建不可变对象，也就是说，对象在创建好之后，就不能再修改内部的属性值，要实现这个功能，就不能在类中暴露 set() 方法。
## 2.4 原型模式(Prototype)
- **概念**

对象的创建成本比较大，而同一个类的不同对象之间差别不大（大部分字段都相同），在这种情况下，我们可以利用对已有对象（原型）进行复制（或者叫拷贝）的方式，来创建新对象，以达到节省创建时间的目的。这种基于原型来创建对象的方式就叫作原型模式。

JAVA原型模式有两种实现方法，深拷贝和浅拷贝。浅拷贝只会复制对象中基本数据类型数据和引用对象的内存地址，不会递归地复制引用对象，以及引用对象的引用对象……而深拷贝得到的是一份完完全全独立的对象。所以，深拷贝比起浅拷贝来说，更加耗时，更加耗内存空间。

- **应用**

java这种基于类的面向对象的编程语言，其实还是比较少用到原型模式。而JavaScript这种基于原型的面向对象编程语言，原型模式很常用。
1. 类初始化需要消化非常多的资源,这个资源包括数据、硬件资源等,通过原型拷贝避免这些消耗。
2. 通过new一个对象需要非常繁琐的数据准备或访问权限,可以使用原型模式。
3. 一个对象需要提供给其他对象访问,而且各个调用者可能需要修改其值,可以考虑使用原型模式拷贝多个对象供调用者使用,即保护性拷贝


# 三、结构性设计模式
## 3.1 代理模式(Proxy)
- **概念**

代理模式在不改变原始类接口的条件下，为原始类定义一个代理类，主要目的是控制访问，而非加强功能，这是它跟装饰器模式最大的不同。

代理模式一般分为基于接口的实现和基于继承的实现，一般情况下，让代理类和原始类实现同样的接口。如果原始类并没有定义接口，并且原始类代码并不是我们开发维护的。在这种情况下，也可以通过让代理类继承原始类的方法来实现代理模式。

静态代理需要针对每个类都创建一个代理类，并且每个代理类中的代码都有点像模板式的“重复”代码，增加了维护成本和开发成本。对于静态代理存在的问题，可以通过动态代理来解决。不事先为每个原始类编写代理类，而是在运行的时候动态地创建原始类对应的代理类，然后在系统中用代理类替换掉原始类。

- **应用**

1. 代理模式常用在业务系统中开发一些非功能性需求，比如：监控、统计、鉴权、限流、事务、幂等、日志。
2. RPC 框架也可以看作一种代理模式，GoF 的《设计模式》一书中把它称作远程代理。通过远程代理，将网络通信、数据编解码等细节隐藏起来。客户端在使用 RPC 服务的时候，就像使用本地函数一样，无需了解跟服务器交互的细节。
3. 如果是基于 Spring 框架来开发的话，那就可以在 AOP 切面中完成接口缓存的功能。在应用启动的时候，从配置文件中加载需要支持缓存的接口，以及相应的缓存策略（比如过期时间）等。当请求到来的时候，在 AOP 切面中拦截请求，如果请求中带有支持缓存的字段（比如 http://…?..&cached=true），便从缓存（内存缓存或者 Redis 缓存等）中获取数据直接返回。
## 3.2 桥接模式(Bridge)
- **概念**

第一种理解方式是“将抽象和实现解耦，让它们能独立开发”。这种理解方式比较特别，应用场景也不多。

另一种理解方式更加简单，类似“组合优于继承”设计原则，这种理解方式更加通用，应用场景比较多。不管是哪种理解方式，它们的代码结构都是相同的，都是一种类之间的组合关系。

(1)将实现予以解耦，让它和实现之间不再永久绑定。
(2)抽象和实现可以独立扩展，不会影响到对方。
(3)对于”具体的抽象类”所做的改变，不会影响到实现

**应用**

  1. 各种数据库jdbc的实现，只需要切换驱动名称就可以切换数据库
  
  3. 需要用不同的方式改变接口和实现的时候
## 3.3 装饰者模式(Decorator)
- **概念**

装饰器模式主要解决继承关系过于复杂的问题，继承多了会爆炸，通过组合来替代继承，给原始类添加增强功能。这也是判断是否该用装饰器模式的一个重要的依据。除此之外，装饰器模式还有一个特点，那就是可以对原始类嵌套使用多个装饰器。为了满足这样的需求，在设计的时候，装饰器类需要跟原始类继承相同的抽象类或者接口。

- **应用**
1. JAVA IO中的装饰者模式
## 3.4 适配器模式(Adapter)
- **概念**

代理模式、装饰器模式提供的都是跟原始类相同的接口，而适配器提供跟原始类不同的接口。适配器模式是用来做适配的，它将不兼容的接口转换为可兼容的接口，让原本由于接口不兼容而不能一起工作的类可以一起工作。适配器模式有两种实现方式：类适配器和对象适配器。其中，类适配器使用继承关系来实现，对象适配器使用组合关系来实现。

- **应用**
1. 封装有缺陷的接口设计
2. 统一多个类的接口设计
3. 替换依赖的外部系统
4. 兼容老版本接口适
5. 配不同格式的数据
## 3.5 门面模式(Facade)
- **概念**

接口粒度设计得太大，太小都不好。太大会导致接口不可复用，太小会导致接口不易用。在实际的开发中，接口的可复用性和易用性需要“微妙”的权衡。针对这个问题，我的一个基本的处理原则是，尽量保持接口的可复用性，但针对特殊情况，允许提供冗余的门面接口，来提供更易用的接口。

- **应用**
1. 支持两个接口调用在一个事务中执行，设计一个包裹这两个操作的新接口，让新接口在一个事务中执行两个 SQL 操作。
## 3.6 组合模式(Composite)
- **概念**

就是被共享的单元。享元模式的意图是复用对象，节省内存，前提是享元对象是不可变对象。

是对业务场景的一种数据结构和算法的抽象。其中，数据可以表示成树这种数据结构，业务需求可以通过在树上的递归遍历算法来实现。组合模式，将一组对象组织成树形结构，将单个对象和组合对象都看作树中的节点，以统一处理逻辑，并且它利用树形结构的特点，递归地处理每个子树，依次简化代码实现。
- **应用**
1. 树形结构，如组织树，设备树等
## 3.7 享元模式(Flyweight)
- **概念**

就是被共享的单元。享元模式的意图是复用对象，节省内存，前提是享元对象是不可变对象。

一个系统中存在大量重复对象的时候，就可以利用享元模式，将对象设计成享元，在内存中只保留一份实例，供多处代码引用，这样可以减少内存中对象的数量，以起到节省内存的目的。

- **应用**
1. 享元模式在Java Integer中的应用，缓存了对于大部分应用来说最常用的整型值，也就是一个字节的大小（-128 到 127 之间的数据）
2. 享元模式在Java String中的应用，String 类利用享元模式来复用相同的字符串常量（也就是代码中的“科比”）。JVM 会专门开辟一块存储区来存储字符串常量，这块存储区叫作“字符串常量池”。
# 四、行为性设计模式
## 4.1 观察者模式(Observer)
- **概念**

在对象之间定义一个一对多的依赖，当一个对象状态改变的时候，所有依赖的对象都会自动收到通知。

一般情况下，被依赖的对象叫作被观察者（Observable），依赖的对象叫作观察者（Observer）。不过，在实际的项目开发中，这两种对象的称呼是比较灵活的，有各种不同的叫法，比如：Subject-Observer、Publisher-Subscriber、Producer-Consumer、EventEmitter-EventListener、Dispatcher-Listener。

具体实现分为同步阻塞实现、异步非阻塞实现方式、进程间的观察者模式。

同步阻塞是最经典的实现方式，主要是为了代码解耦；异步非阻塞除了能实现代码解耦之外，还能提高代码的执行效率；进程间的观察者模式解耦更加彻底，一般是基于消息队列来实现，用来实现不同进程间的被观察者和观察者之间的交互。

- **应用**
1. 各种事件联动类
2. 各种MQ算是进程间的观察者模式
3. google guava eventBus

## 4.2 模板模式(Template)
- **概念**
模板方法模式在一个方法中定义一个算法骨架，并将某些步骤推迟到子类中实现。模板方法模式可以让子类在不改变算法整体结构的情况下，重新定义算法中的某些步骤。这里的“算法”，我们可以理解为广义上的“业务逻辑”，并不特指数据结构和算法中的“算法”。这里的算法骨架就是“模板”，包含算法骨架的方法就是“模板方法”，这也是模板方法模式名字的由来。

在模板模式经典的实现中，模板方法定义为 final，可以避免被子类重写。需要子类重写的方法定义为 abstract，可以强迫子类去实现。不过，在实际项目开发中，模板模式的实现比较灵活，以上两点都不是必须的。

模板模式有两大作用：复用和扩展。其中，复用指的是，所有的子类可以复用父类中提供的模板方法的代码。扩展指的是，框架通过模板模式提供功能扩展点，让框架用户可以在不修改框架源码的情况下，基于扩展点定制化框架的功能。

- **应用**
1. Java InputStream中的read() 函数是一个模板方法，定义了读取数据的整个流程，并且暴露了一个可以由子类来定制的抽象方法。
2. Java AbstractList 类中，addAll() 函数可以看作模板方法，add() 是子类需要重写的方法。
3. JUnit 框架通过模板模式提供了一些功能扩展点（setUp()、tearDown() 等），让框架用户可以在这些扩展点上扩展功能。

## 4.3 策略模式(Stragegy)
- **概念**

定义一族算法类，将每个算法分别封装起来，让它们可以互相替换。策略模式可以使算法的变化独立于使用它们的客户端（这里的客户端代指使用算法的代码）。

策略类的定义比较简单，包含一个策略接口和一组实现这个接口的策略类。因为所有的策略类都实现相同的接口，所以，客户端代码基于接口而非实现编程，可以灵活地替换不同的策略。

策略的创建由工厂类来完成，封装策略创建的细节。策略模式包含一组策略可选，客户端代码选择使用哪个策略，有两种确定方法：编译时静态确定和运行时动态确定。其中，“运行时动态确定”才是策略模式最典型的应用场景。

- **应用**
1. 在实际的项目开发中，策略模式也比较常用。最常见的应用场景是，利用它来避免冗长的 if-else 或 switch 分支判断。

## 4.4 职责链模式(Chain of Reponsibility)
- **概念**

在职责链模式中，多个处理器（也就是刚刚定义中说的“接收对象”）依次处理同一个请求。一个请求先经过 A 处理器处理，然后再把请求传递给 B 处理器，B 处理器处理完后再传递给 C 处理器，以此类推，形成一个链条。链条上的每个处理器各自承担各自的处理职责，所以叫作职责链模式。

具体来说有两种常见的实现方式，基于链表来存储处理器，另一种是使用数组来存储处理器。

- **应用**
1. 职责链模式常用在框架开发中，用来实现过滤器、拦截器功能，让框架的使用者在不需要修改框架源码的情况下，添加新的过滤、拦截功能。
2.  各个论坛，blog等，对用户生成的内容（比如，在论坛中发表的帖子）可能会包含一些敏感词（比如涉黄、广告、反动等词汇）
## 4.5 迭代器模式(Iterator)
- **概念**
迭代器模式也叫游标模式，它用来遍历集合对象。这里说的“集合对象”，我们也可以叫“容器”“聚合对象”，实际上就是包含一组对象的对象，比如，数组、链表、树、图、跳表。迭代器模式主要作用是解耦容器代码和遍历代码。

遍历集合一般有三种方式：for 循环、foreach 循环、迭代器遍历。后两种本质上属于一种，都可以看作迭代器遍历。相对于 for 循环遍历，利用迭代器来遍历有 3 个优势：

（1）迭代器模式封装集合内部的复杂数据结构，开发者不需要了解如何遍历，直接使用容器提供的迭代器即可；
（2）迭代器模式将集合对象的遍历操作从集合类中拆分出来，放到迭代器类中，让两者的职责更加单一；
（3）迭代器模式让添加新的遍历算法更加容易，更符合开闭原则。除此之外，因为迭代器都实现自相同的接口，在开发中，基于接口而非实现编程，替换迭代器也变得更加容易。

一个完整的迭代器模式，一般会涉及容器和容器迭代器两部分内容。为了达到基于接口而非实现编程的目的，容器又包含容器接口、容器实现类，迭代器又包含迭代器接口、迭代器实现类。容器中需要定义 iterator() 方法，用来创建迭代器。迭代器接口中需要定义 hasNext()、currentItem()、next() 三个最基本的方法。容器对象通过依赖注入传递到迭代器类中。

在通过迭代器来遍历集合元素的同时，增加或者删除集合中的元素，有可能会导致某个元素被重复遍历或遍历不到。不过，并不是所有情况下都会遍历出错，有的时候也可以正常遍历，所以，这种行为称为结果不可预期行为或者未决行为。实际上，“不可预期”比直接出错更加可怕，有的时候运行正确，有的时候运行错误，一些隐藏很深、很难 debug 的 bug 就是这么产生的。

Java 语言就是采用的这种解决方案。增删元素之后，我们选择 fail-fast 解决方式，让遍历操作直接抛出运行时异常。

- **应用**
1. 各个语言中的迭代器
## 4.6 状态模式(State)
- **概念**

状态模式一般用来实现状态机，而状态机常用在游戏、工作流引擎等系统开发中。状态机又叫有限状态机，它由 3 个部分组成：状态、事件、动作。其中，事件也称为转移条件。事件触发状态的转移及动作的执行。不过，动作不是必须的，也可能只转移状态，不执行任何动作。

- **应用**
1. 游戏里角色的各种变身，比如超级玛丽
## 4.7 访问者模式(Visitor)
- **概念**

允许一个或者多个操作应用到一组对象上，解耦操作和对象本身。

访问者模式，学习的主要难点在代码实现。而代码实现比较复杂的主要原因是，函数重载在大部分面向对象编程语言中是静态绑定的。也就是说，调用类的哪个重载函数，是在编译期间，由参数的声明类型决定的，而非运行时，根据参数的实际类型决定的。除此之外，我们还讲到 Double Disptach。如果某种语言支持 Double Dispatch，那就不需要访问者模式了。

- **应用**
1. 尽量不要用
## 4.8 备忘录模式(Memento)
- **概念**

在不违背封装原则的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，以便之后恢复对象为先前的状态

简单来说是分为两步，一部分是，存储副本以便后期恢复。这一部分很好理解。另一部分是，要在不违背封装原则的前提下，进行对象的备份和恢复。

备忘录模式的应用场景也比较明确和有限，主要用来防丢失、撤销、恢复等。它跟平时我们常说的“备份”很相似。两者的主要区别在于，备忘录模式更侧重于代码的设计和实现，备份更侧重架构设计或产品设计。


- **应用**
1. 某些编辑器撤销、恢复等操作的实现
## 4.9 命令模式(Command)
- **概念**

命令模式用到最核心的实现手段，就是将函数封装成对象。

在大部分编程语言中，函数是没法作为参数传递给其他函数的，也没法赋值给变量。借助命令模式，我们将函数封装成对象，这样就可以实现把函数像对象一样使用。

- **应用**
1. 命令模式的主要作用和应用场景，是用来控制命令的执行，比如，异步、延迟、排队执行命令、撤销重做命令、存储命令、给命令记录日志等，这才是命令模式能发挥独一无二作用的地方。
## 4.10 解释器模式(Interpreter)
- **概念**

解释器模式为某个语言定义它的语法（或者叫文法）表示，并定义一个解释器用来处理这个语法。

它的代码实现的核心思想，就是将语法解析的工作拆分到各个小类中，以此来避免大而全的解析类。一般的做法是，将语法规则拆分一些小的独立的单元，然后对每个单元进行解析，最终合并为对整个语法规则的解析。

- **应用**
1. 一些简单的规则加密解密、校验
## 4.11 中介模式(Mediator)
- **概念**
中介模式的设计思想跟中间层很像，通过引入中介这个中间层，将一组对象之间的交互关系（或者说依赖关系）从多对多（网状关系）转换为一对多（星状关系）。类似于航站楼的作用。

原来一个对象要跟 n 个对象交互，现在只需要跟一个中介对象交互，从而最小化对象之间的交互关系，降低了代码的复杂度，提高了代码的可读性和可维护性。

- **应用**
1. 各种不同事件的处理杂乱，需要统一管理的时候。