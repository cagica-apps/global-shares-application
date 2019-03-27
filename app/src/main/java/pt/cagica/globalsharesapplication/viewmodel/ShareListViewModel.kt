package pt.cagica.globalsharesapplication.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import io.reactivex.disposables.CompositeDisposable
import pt.cagica.globalsharesapplication.api.SharesApi
import pt.cagica.globalsharesapplication.api.data.MyCertificate
import pt.cagica.globalsharesapplication.api.data.Share
import pt.cagica.globalsharesapplication.utils.CommonUtils
import java.util.concurrent.TimeUnit

class ShareListViewModel(
    @NonNull application: Application, private val api: SharesApi
) : AndroidViewModel(application) {

    val isLoading = MutableLiveData<Boolean>()
    val listError = MutableLiveData<Boolean>()
    val errorCode = MutableLiveData<Int>()
    val share = MutableLiveData<Share>()
    val numberOfShares = MutableLiveData<List<String>>()
    val items = MutableLiveData<List<MyCertificate>>()

    private val compositeDisposable = CompositeDisposable()

    fun start() {
        getCertificateList()
        getShare()
    }

    fun getCertificateList() {
        isLoading.value = true
        compositeDisposable.add(api.getCertificateList(CommonUtils.isNetworkAvailable(getApplication()))
            .subscribe({
                isLoading.value = false
                listError.value = false
                items.postValue(it)
                val arr = arrayListOf<String>()
                for(item in it)
                    arr.add("0")
                numberOfShares.value = arr
            }, {
                isLoading.value = false
                listError.value = true
            })
        )
    }

    fun getShare() {
        isLoading.value = true
        compositeDisposable.add(api.getShare(CommonUtils.isNetworkAvailable(getApplication()))
            .repeatWhen { completed -> completed.delay(30, TimeUnit.SECONDS) }
            .subscribe({
                share.value = it
            }, {
                isLoading.value = false
            })
        )
    }

    fun sellShares(certificate: MyCertificate) {
        isLoading.value = true

        val numberOfShares = numberOfShares.value!![certificate.position].toInt()
        if (!validateInput(certificate , numberOfShares)) return

        compositeDisposable.add(api.sellShare(numberOfShares, certificate.getCertificateId(), CommonUtils.isNetworkAvailable(getApplication()))
            .subscribe({
                isLoading.value = false
                getCertificateList()
            }, {
                isLoading.value = false
            })
        )
    }

    fun onTextChanged(s: CharSequence, position: Int) {
        val list = numberOfShares.value!!
        (list as ArrayList)[position] = s.toString()
        numberOfShares.postValue(list)
    }

    fun clearCompositeDisposable() {
        this.compositeDisposable.clear()
    }

    fun validateInput(certificate: MyCertificate, numberOfShares: Int): Boolean {

        errorCode.value = -1
        if(numberOfShares > certificate.getNumberOfShares().toInt() / 2) {
            errorCode.value = 0
            return false
        }

        if(numberOfShares < 1) {
            errorCode.value = 1
            return false
        }

        if(numberOfShares > certificate.getNumberOfShares().toInt()) {
            errorCode.value = 2
            return false
        }

        return true
    }
}
