package pt.cagica.globalsharesapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import kotlinx.android.synthetic.main.activity_main.*

import pt.cagica.globalsharesapplication.R
import pt.cagica.globalsharesapplication.api.data.Share
import pt.cagica.globalsharesapplication.viewmodel.ShareListViewModel

class MainActivity : AppCompatActivity() {

    private var shareListViewModel: ShareListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shareListViewModel = ViewModelProviders.of(this, ViewModelFactory
            .getInstance(application))
            .get(ShareListViewModel::class.java)

        // load list fragment on activity creation
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, CertificateListFragment())
                .commit()
        }
    }

    fun <T : ViewModel> obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)
}
