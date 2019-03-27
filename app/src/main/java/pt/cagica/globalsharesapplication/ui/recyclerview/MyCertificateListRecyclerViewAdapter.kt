package pt.cagica.globalsharesapplication.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import pt.cagica.globalsharesapplication.api.data.MyCertificate
import pt.cagica.globalsharesapplication.api.data.Share
import pt.cagica.globalsharesapplication.databinding.FragmentCertificatelistItemBinding
import pt.cagica.globalsharesapplication.viewmodel.ShareListViewModel

import java.util.ArrayList

class MyCertificateListRecyclerViewAdapter : RecyclerView.Adapter<MyCertificateListRecyclerViewAdapter.ViewHolder>() {

    private var items: List<MyCertificate>
    private lateinit var viewModel: ShareListViewModel

    init {
        items = ArrayList()
    }

    fun setItems(list: List<MyCertificate>?) {
        if (list != null)
            items = list
        notifyDataSetChanged()
    }

    fun setViewModel(shareListViewModel: ShareListViewModel) {
        viewModel = shareListViewModel
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): MyCertificateListRecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = FragmentCertificatelistItemBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items.get(position), position)

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: FragmentCertificatelistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(certificate: MyCertificate, position: Int) {
            certificate.position = position
            binding.certificate = certificate
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}
