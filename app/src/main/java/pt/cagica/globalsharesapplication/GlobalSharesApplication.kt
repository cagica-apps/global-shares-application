package pt.cagica.globalsharesapplication

import android.app.Application
import pt.cagica.globalsharesapplication.api.SharesApi

class GlobalSharesApplication : Application() {

    private lateinit var sharesApi: SharesApi

    override fun onCreate() {
        super.onCreate()

        sharesApi = SharesApi()
    }

    fun getSharesApi(): SharesApi {
        return sharesApi
    }
}
