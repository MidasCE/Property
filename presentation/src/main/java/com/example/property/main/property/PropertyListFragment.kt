package com.example.property.main.property

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.property.core.SchedulerFactory
import com.example.property.databinding.FragmentPropertyListBinding
import com.example.property.main.property.viewentity.PropertyItemEntity
import com.example.property.main.property.viewmodel.PropertyListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject


@AndroidEntryPoint
class PropertyListFragment : Fragment(), PropertyItemAdapter.ItemInteractionListener {

    @Inject
    lateinit var viewModel : PropertyListViewModel

    @Inject
    lateinit var schedulerFactory : SchedulerFactory

    @Inject
    lateinit var adapter: PropertyItemAdapter

    private val viewDisposable = CompositeDisposable()

    private var _binding: FragmentPropertyListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPropertyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Start observing the targets
        this.viewModel.loadingLiveData.observe(viewLifecycleOwner, this.loadingObserver)
        this.viewModel.propertyItemLiveData.observe(viewLifecycleOwner, this.propertyListObserver)
        this.viewModel.errorLiveData.observe(viewLifecycleOwner, this.errorObserver)

        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerView.context,
            layoutManager.orientation
        )

        adapter.setOnItemClickListener(this);
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
        binding.recyclerView.adapter = adapter

        viewModel.getPropertyItems()
    }

    private val loadingObserver = Observer<Boolean> { visible ->
        // Show hide loading view
        if (visible){
            binding.progressBar.visibility = View.VISIBLE
        } else{
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    private val errorObserver = Observer<String> { errorMessage ->
        if (!errorMessage.isNullOrEmpty()){
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    private val propertyListObserver = Observer<List<PropertyItemEntity>> { list ->
        adapter.updatePropertyItems(list)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        viewDisposable.clear()
        viewModel.onViewDestroy()
    }

    override fun onItemClick(propertyItemEntity: PropertyItemEntity) {
    }
}
