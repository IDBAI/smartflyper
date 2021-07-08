package com.allen.android.smartflyper

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.yy.core.yyp.smart.ISmartFlyper2
import com.yy.core.yyp.smart.SmartFlyperDelegate
import com.yy.core.yyp.smart.SmartFlyperFactory__app
import com.yy.core.yyp.smart.WrapperMethod
import com.yy.core.yyp.smart.anotation.SmartAppender
import com.yy.core.yyp.smart.anotation.SmartUri
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SmartFlyperFactory__app().getApi(InnerService::class.java)
        SmartFlyperDelegate.setSmartFlyper2(object : ISmartFlyper2 {
            override suspend fun <T> sendCoroutines(method: WrapperMethod?): T? {
                return getUser() as? T
            }
        })
        GlobalScope.launch(context = Dispatchers.IO) {
            //延时一秒
            delay(1000)
            val userEntity = ITestService2ImplAutoGenerated().getUserInfo2(18398343)
            Log.d("MainActivity", "onCreate: launch ${userEntity?.name}")
        }
    }

    interface InnerService {
        @SmartUri(max = 101, req = 312, rsp = 313, appId = 60015)
        @SmartAppender
        fun roomInfo(): Observable<String>?
    }

    suspend fun getUser(): UserEntity {
        return UserEntity().apply {
            name = "allen"
        }
    }
}