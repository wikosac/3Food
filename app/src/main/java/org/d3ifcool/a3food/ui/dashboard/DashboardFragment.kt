package org.d3ifcool.a3food.ui.dashboard

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import org.d3ifcool.a3food.R
import org.d3ifcool.a3food.data.Food
import org.d3ifcool.a3food.data.FoodDb
import org.d3ifcool.a3food.databinding.FragmentDashboardBinding
import org.d3ifcool.a3food.MainActivity
import org.d3ifcool.a3food.storage.ImageAdapter
import org.d3ifcool.a3food.storage.ImageData

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var myAdapter: DashboardAdapter
    private lateinit var listImages: ArrayList<ImageData>
    private lateinit var databaseReference: DatabaseReference


    private var actionMode: ActionMode? = null
    private val actionModeCallback = object : ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?,
                                         item: MenuItem?): Boolean {
            if (item?.itemId == R.id.action_navigation_search_to_mapsFragment) {
                deleteData()
                return true
            }
            return false
        }
        override fun onCreateActionMode(mode: ActionMode?,
                                        menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.main_menu, menu)
            return true
        }
        override fun onPrepareActionMode(mode: ActionMode?,
                                         menu: Menu?): Boolean {
            mode?.title = myAdapter.getSelection().size.toString()
            return true
        }
        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
            myAdapter.resetSelection()
        }
    }

    private val handler = object : DashboardAdapter.ClickHandler {
        override fun onClick(position: Int, food: Food) {
            if (actionMode != null) {
                myAdapter.toggleSelection(position)
                if (myAdapter.getSelection().isEmpty())
                    actionMode?.finish()
                else
                    actionMode?.invalidate()
                return
            }
            val message = getString(R.string.food_klik, food.toko)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        override fun onLongClick(position: Int): Boolean {
            val activity: MainActivity? = activity as MainActivity?
            if (actionMode != null) return false
            myAdapter.toggleSelection(position)
            actionMode = activity?.startSupportActionMode(actionModeCallback)
            return true
        }
    }

    private fun deleteData() = AlertDialog.Builder(requireContext()).apply {
        setMessage(R.string.pesan_hapus)
        setPositiveButton(R.string.hapus) { _, _ ->
            viewModel.deleteData(myAdapter.getSelection())
            actionMode?.finish()
        }
        setNegativeButton(R.string.batal) { dialog, _ ->
            dialog.cancel()
            actionMode?.finish()
        }
        show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //image from storage
        binding.horizontalRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        listImages = arrayListOf()

        databaseReference = FirebaseDatabase.getInstance().getReference("food")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val list = dataSnapshot.getValue(ImageData::class.java)
                        listImages.add(list!!)
                    }
                    binding.horizontalRecyclerView.adapter = ImageAdapter(requireContext(), listImages)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

        })

        //recommendation
        myAdapter = DashboardAdapter(handler)
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            setHasFixedSize(true)
            adapter = myAdapter
        }

        //if null
        viewModel.data.observe(viewLifecycleOwner) {
            myAdapter.submitList(it)
            binding.emptyView.visibility = if (it.isEmpty())
                View.VISIBLE
            else
                View.GONE
        }
    }

    private val viewModel: DashboardViewModel by lazy {
        val dataSource = FoodDb.getInstance().dao
        val factory = DashboardViewModelFactory(dataSource)
        ViewModelProvider(this, factory).get(DashboardViewModel::class.java)
    }
}