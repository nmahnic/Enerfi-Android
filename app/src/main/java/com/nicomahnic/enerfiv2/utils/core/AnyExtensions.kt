package com.nicomahnic.enerfiv2.utils.core

val Any.TAG: String
    get() {
        return if(!javaClass.isAnonymousClass){
            val name = javaClass.simpleName
            if(name.length <=23) name else name.substring(0,23)
        }else{
            val name = javaClass.name
            if(name.length <=23) name else name.substring(name.length - 23, name.length)
        }
    }