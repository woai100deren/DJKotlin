package com.dj.kotlin.network

/**
 * 请求结果处理类
 */
class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    //companion object 修饰为伴生对象,伴生对象在类中只能存在一个，类似于java中的静态方法 Java 中使用类访问静态成员，静态方法。
    companion object {
        fun <T> success(data: T?) = Resource(Status.SUCCESS, data, null)
        fun <T> error(msg: String?, data: T?) = Resource(Status.ERROR, data, msg)
        fun start() = Resource(Status.START, null, null)
        fun finish() = Resource(Status.FINISH, null, null)
    }
}