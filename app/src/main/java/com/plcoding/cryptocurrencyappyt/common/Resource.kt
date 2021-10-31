package com.plcoding.cryptocurrencyappyt.common

sealed class Resource {
    class Success<T>( var data: T ): Resource()
    class Error( var message: String): Resource()
    class Loading: Resource()
}
