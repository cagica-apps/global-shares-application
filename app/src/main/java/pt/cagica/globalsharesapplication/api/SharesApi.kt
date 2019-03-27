package pt.cagica.globalsharesapplication.api

import com.google.gson.GsonBuilder
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pt.cagica.globalsharesapplication.api.data.MyCertificate
import pt.cagica.globalsharesapplication.api.data.Share
import pt.cagica.globalsharesapplication.api.retrofit.RetrofitHelper
import pt.cagica.globalsharesapplication.api.typeadapters.DateTypeAdapter
import pt.cagica.globalsharesapplication.utils.Constants
import retrofit2.Retrofit
import java.io.File
import java.util.*


class SharesApi {

    private var retrofit: Retrofit? = null

    init {
        val gson = GsonBuilder()
            .disableHtmlEscaping()
            .registerTypeAdapter(Date::class.java, DateTypeAdapter())
            .create()

        retrofit = RetrofitHelper.buildRetrofitClient(RetrofitHelper.setupOkHttpClient(), gson)
    }

    fun getCertificateList(isNetworkAvailable: Boolean): Single<List<MyCertificate>> {
        if (retrofit == null || !isNetworkAvailable) {
            return Single.error(Throwable())
        }

        return retrofit!!.create(SharesEndpoint::class.java)
            .getCertificatesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getShare(isNetworkAvailable: Boolean): Single<Share> {
        if (retrofit == null || !isNetworkAvailable) {
            return Single.error(Throwable())
        }

        return retrofit!!.create(SharesEndpoint::class.java)
            .getShare()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun sellShare(numberOfShares: Int?, certificateId: Int?, isNetworkAvailable: Boolean): Completable {
        if (retrofit == null || certificateId == null || numberOfShares == null || !isNetworkAvailable) {
            return Completable.error(Throwable())
        }

        return retrofit!!.create(SharesEndpoint::class.java)
            .sellShare(Constants.ENDPOINT_POST, certificateId, numberOfShares)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
