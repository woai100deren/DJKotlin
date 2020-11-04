package com.dj.kotlin.network

/**
 * 请求结果处理类
 */
class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?) = Resource(Status.SUCCESS, data, null)
        fun <T> error(msg: String?, data: T?) = Resource(Status.ERROR, data, msg)
        fun start() = Resource(Status.START, null, null)
        fun finish() = Resource(Status.FINISH, null, null)
    }
}