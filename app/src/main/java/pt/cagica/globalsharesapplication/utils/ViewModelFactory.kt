import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pt.cagica.globalsharesapplication.GlobalSharesApplication
import pt.cagica.globalsharesapplication.api.SharesApi
import pt.cagica.globalsharesapplication.viewmodel.ShareListViewModel

class ViewModelFactory private constructor(
    private val application: Application,
    private val sharesApi: SharesApi
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel> create(modelClass: Class<T>) =
      with(modelClass) {
        when {
          isAssignableFrom(ShareListViewModel::class.java) ->
            ShareListViewModel(application, sharesApi)
          else ->
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
      } as T

  companion object {

    @Volatile private var INSTANCE: ViewModelFactory? = null

    fun getInstance(application: Application): ViewModelFactory? {

      if (INSTANCE == null) {
        synchronized(ViewModelFactory::class.java) {
          if (INSTANCE == null) {
            INSTANCE = ViewModelFactory(application, (application as GlobalSharesApplication).getSharesApi())
          }
        }
      }
      return INSTANCE
    }
  }
}
