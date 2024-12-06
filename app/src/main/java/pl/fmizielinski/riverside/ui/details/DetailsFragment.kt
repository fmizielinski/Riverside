package pl.fmizielinski.riverside.ui.details

import android.os.Bundle
import android.view.View
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.fmizielinski.riverside.databinding.FragmentDetailsBinding
import pl.fmizielinski.riverside.databinding.FragmentDetailsBinding.inflate
import pl.fmizielinski.riverside.ui.base.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(::inflate) {

    private val viewModel: DetailsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.setContent {
            MaterialTheme {
                val details by viewModel.details.observeAsState()
                details?.let { DetailsScreen(it) }
            }
        }
    }
}
