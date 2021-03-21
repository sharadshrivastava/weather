package com.test.app.ui.recent

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.app.R
import com.test.app.ui.common.SwipeToDeleteCallback
import com.test.app.ui.common.VerticalSpaceItemDecoration
import com.test.app.util.Utils
import kotlinx.android.synthetic.main.recent_search_fragment.*

class RecentSearchFragment : Fragment(), RecentSearchAdapter.OnItemClickListener {

    private val vm: RecentSearchViewModel by viewModels()
    private var adapter: RecentSearchAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recent_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context)
            .inflate(R.layout.recent_search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSearchList(recentSearchList)
        setupSwipeToDelete(recentSearchList)
        getAllCity()
    }

    private fun setupSearchList(listView: RecyclerView?) {
        if (adapter == null) {
            adapter = RecentSearchAdapter(null, this)
        }
        setupListView(listView, adapter)
    }

    private fun setupListView(listView: RecyclerView?, adapter: RecentSearchAdapter?) {
        listView?.setHasFixedSize(true)
        listView?.layoutManager = LinearLayoutManager(context)
        listView?.adapter = adapter
        listView?.addItemDecoration(
            VerticalSpaceItemDecoration(
                context,
                Utils.pxFromDp(context, 1f).toInt()
            )
        )
    }

    private fun setupSwipeToDelete(listView: RecyclerView?) {
        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteRow(viewHolder.absoluteAdapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(listView)
    }

    private fun getAllCity() {
        vm.cityList?.observe(viewLifecycleOwner, Observer {
            adapter?.setData(it)
        })
    }

    private fun deleteRow(pos: Int) {
        val recentAdapter = recentSearchList.adapter as RecentSearchAdapter
        vm.delete(recentAdapter.cityList?.get(pos)?.id ?: Utils.INVALID)
        recentAdapter.removeAt(pos)
    }

    override fun onItemClick(id: Int?) {
        val action = RecentSearchFragmentDirections.recentSearchFragmentAction(id ?: Utils.INVALID)
        findNavController(this).navigate(action)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all -> {
                vm.deleteAll(); true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}