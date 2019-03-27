package pt.cagica.globalsharesapplication.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_certificatelist.*
import kotlinx.android.synthetic.main.fragment_certificatelist_item.*
import pt.cagica.globalsharesapplication.R
import pt.cagica.globalsharesapplication.api.data.MyCertificate
import pt.cagica.globalsharesapplication.api.data.Share
import pt.cagica.globalsharesapplication.databinding.FragmentCertificatelistBinding
import pt.cagica.globalsharesapplication.ui.recyclerview.MyCertificateListRecyclerViewAdapter
import pt.cagica.globalsharesapplication.viewmodel.ShareListViewModel

class CertificateListFragment : Fragment() {

    private var viewModel: ShareListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (activity as MainActivity).obtainViewModel(ShareListViewModel::class.java)

        val binding: FragmentCertificatelistBinding = DataBindingUtil.setContentView((activity as Activity), R.layout.fragment_certificatelist)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel!!.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel!!.clearCompositeDisposable()
    }

    private fun setupObservers() {
        //TODO missing observers for error messages
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bind:items")
        fun setItems(view: RecyclerView, list: List<MyCertificate>?) {
            getRecyclerViewAdapter(view).setItems(list)
        }

        @JvmStatic
        @BindingAdapter("bind:onItemSelected")
        fun setViewModel(view: RecyclerView, viewModel: ShareListViewModel) {
            getRecyclerViewAdapter(view).setViewModel(viewModel)
        }

        private fun getRecyclerViewAdapter(view: RecyclerView): MyCertificateListRecyclerViewAdapter {
            if (view.adapter == null) {
                val layoutManager = LinearLayoutManager(view.context)
                view.layoutManager = layoutManager
                view.adapter = MyCertificateListRecyclerViewAdapter()
            }
            return view.adapter as MyCertificateListRecyclerViewAdapter
        }
    }
}
