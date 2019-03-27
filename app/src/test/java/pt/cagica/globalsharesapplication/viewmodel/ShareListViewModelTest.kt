package pt.cagica.globalsharesapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import pt.cagica.globalsharesapplication.GlobalSharesApplication
import pt.cagica.globalsharesapplication.api.SharesApi
import pt.cagica.globalsharesapplication.api.data.MyCertificate
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import pt.cagica.globalsharesapplication.utils.CommonUtils


class ShareListViewModelTest {

    private lateinit var certificateList: List<MyCertificate>

    private lateinit var viewModel: ShareListViewModel

    @MockK
    private lateinit var application: GlobalSharesApplication

    @MockK
    private lateinit var api: SharesApi

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        MockKAnnotations.init(this)
    }

    @Before
    fun setUp() {
        certificateList = CertificateData.certificateList

        application = mockk()
        api = mockk()

        viewModel = ShareListViewModel(application, api)
    }

    @Test
    fun testCertificateData() {
        Assert.assertEquals("0", certificateList[0].getCertificateIdString())
        Assert.assertEquals(0, certificateList[0].getCertificateId())
        Assert.assertEquals("100", certificateList[0].getNumberOfShares())
    }

    @Test
    fun testValidateInput() {
        Assert.assertTrue(viewModel.validateInput(certificateList[0], 10))
        Assert.assertFalse(viewModel.validateInput(certificateList[0], 1000))
    }

    @Test
    fun testGetCertificateList() {

        every { api.getCertificateList(true) } returns Single.just(CertificateData.certificateList)
        every { CommonUtils.isNetworkAvailable(application) } returns true

        viewModel.items.observeOnce { list ->
            assert(list.size == 3)
            assert(list[0].getCertificateIdString() == "0")
        }

        viewModel.getCertificateList()
    }

    @Test
    fun testGetShare() {
        every { api.getShare(true) } returns Single.just(CertificateData.share)
        every { CommonUtils.isNetworkAvailable(application) } returns true

        viewModel.share.observeOnce { share ->
            assert(share.getSymbol() == "ABC")
        }

        viewModel.getShare()
    }
}
