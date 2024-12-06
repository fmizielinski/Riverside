package pl.fmizielinski.riverside.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.fmizielinski.riverside.R
import pl.fmizielinski.riverside.databinding.FragmentSearchBinding
import pl.fmizielinski.riverside.databinding.FragmentSearchBinding.inflate
import pl.fmizielinski.riverside.ui.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(::inflate) {

    private val viewModel: SearchViewModel by viewModel()
    private val adapter = MovieAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        setUpSearchInput()
        setUpBackPressedCallback()
    }

    override fun onStart() {
        super.onStart()
        observeResults()
        observeOpenDetails()
        observeClicks()
    }

    private fun setUpRecycler() {
        binding.moviesRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.moviesRecycler.adapter = adapter

        val screenHeight = resources.displayMetrics.heightPixels
        val itemHeight = resources.getDimensionPixelSize(R.dimen.movie_item_height)
        val padding = (screenHeight / 2) - (itemHeight / 2)
        binding.moviesRecycler.setPadding(0, padding, 0, padding)
        binding.moviesRecycler.clipToPadding = false

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.moviesRecycler)

        binding.moviesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                applyTransformationToSnappedView(recyclerView, snapHelper)
            }
        })
    }

    private fun setUpSearchInput() {
        binding.searchButton.setOnClickListener {
            performSearch()
        }
        binding.searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            } else {
                false
            }
        }
    }

    private fun setUpBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            SearchOnBackPressedCallback(binding.slidingPaneLayout),
        )
    }

    private fun performSearch() {
        viewModel.searchMovies(binding.searchInput.text.toString())
        hideKeyboard()
    }

    private fun observeResults() {
        viewModel.results.observe(viewLifecycleOwner, adapter::submitList)
    }

    private fun observeOpenDetails() {
        viewModel.openDetails.observe(viewLifecycleOwner) {
            binding.slidingPaneLayout.openPane()
        }
    }

    private fun observeClicks() {
        adapter.clicks.observe(viewLifecycleOwner) {
            viewModel.itemClicked(it)
        }
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.search.windowToken, 0)
    }

    private fun applyTransformationToSnappedView(
        recyclerView: RecyclerView,
        snapHelper: PagerSnapHelper
    ) {
        val animationDuration = 200L
        val layoutManager = recyclerView.layoutManager ?: return
        val snapView = snapHelper.findSnapView(layoutManager) ?: return

        snapView.animate()
            .scaleY(1.1f)
            .scaleX(1.1f)
            .setDuration(animationDuration)
            .start()

        for (i in 0 until layoutManager.childCount) {
            val child = layoutManager.getChildAt(i)
            if (child != snapView) {
                child?.animate()
                    ?.scaleY(1.0f)
                    ?.scaleX(1.0f)
                    ?.setDuration(animationDuration)
                    ?.start()
            }
        }
    }

    class SearchOnBackPressedCallback(
        private val slidingPaneLayout: SlidingPaneLayout
    ) : OnBackPressedCallback(
        slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen
    ), SlidingPaneLayout.PanelSlideListener {

        init {
            slidingPaneLayout.addPanelSlideListener(this)
        }

        override fun handleOnBackPressed() {
            slidingPaneLayout.closePane()
        }

        override fun onPanelSlide(panel: View, slideOffset: Float) {
            // No-op
        }

        override fun onPanelOpened(panel: View) {
            isEnabled = true
        }

        override fun onPanelClosed(panel: View) {
            isEnabled = false
        }
    }
}
