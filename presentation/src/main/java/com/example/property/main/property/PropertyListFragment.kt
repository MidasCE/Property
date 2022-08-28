package com.example.property.main.property

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.property.core.SchedulerFactory
import com.example.property.databinding.FragmentPropertyListBinding
import com.example.property.main.property.viewmodel.PropertyListViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject


@AndroidEntryPoint
class PropertyListFragment : Fragment() {

    @Inject
    lateinit var viewModel : PropertyListViewModel

    @Inject
    lateinit var schedulerFactory : SchedulerFactory

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
}
