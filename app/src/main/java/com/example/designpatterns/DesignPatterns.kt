package com.example.designpatterns

/**
 *@package com.example.designpatterns
 *@author https://github.com/asd3590058
 *@fileName DesignPatterns
 *@date 2022/11/8 11:27
 *@description
 * 开闭原则 ： 对扩展开发，对修改关闭
 * 里氏代换原则：子类可以扩展父类的功能，但不能改变原有父类的功能
 * 依赖倒转原则：变量或者传参数，尽量使用抽象类，或者接口
 * 接口隔离原则：复杂的接口，根据业务拆分成多个简单接口。
 * 迪米特法则：一个对象应该对其他对象有最少的了解。
 * 单一原则：一个类或者一个方法只负责一个职责  合理分配类和函数的职责。
 */

/**
 *  定义亿抽象类，实现单例类只需集成此类即可
 * @param in P
 * @param out T
 * @property instance T?
 */
abstract class BaseSingleton<in P, out T> {
    @Volatile
    private var instance: T? = null
    protected abstract fun creator(param: P): T

    fun getInstance(param: P): T =
        instance ?: synchronized(this) {
            instance ?: creator(param).also { instance = it }
        }
}

class PersonManager private constructor(name: String) {
    companion object : BaseSingleton<String, PersonManager>() {
        override fun creator(param: String): PersonManager = PersonManager(param)
    }
}

class UserManager private constructor(name: String) {
    companion object : BaseSingleton<String, UserManager>() {
        override fun creator(param: String): UserManager = UserManager(param)
    }
}