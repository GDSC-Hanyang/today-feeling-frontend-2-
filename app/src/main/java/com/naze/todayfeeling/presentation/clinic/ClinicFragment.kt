package com.naze.todayfeeling.presentation.clinic

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naze.todayfeeling.R
import com.naze.todayfeeling.databinding.FragmentClinicBinding
import com.naze.todayfeeling.domain.model.ClinicData
import com.naze.todayfeeling.util.binding.BindingFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ClinicFragment: BindingFragment<FragmentClinicBinding>(R.layout.fragment_clinic) {
    private lateinit var clinicAdapter: ClinicAdapter
    private lateinit var model: ClinicViewModel

    private var dtoList: ArrayList<ClinicData> = ArrayList()
    private var items: ArrayList<ClinicData?> = ArrayList()
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = requireActivity()
        }

        setData()

        initLayout()
        initObserver()
        initScrollListener()
    }

    private fun initLayout() {
        binding.rvClinic.apply {
            layoutManager = LinearLayoutManager(context)
            clinicAdapter = ClinicAdapter(items)
            adapter = clinicAdapter
        }
    }

    private fun initScrollListener() {
        binding.rvClinic.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!isLoading) {
                    if ((recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() == items.size - 1) {
                        Log.d("Scroll Test" , "True")
                        moreItems()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun initObserver() {

    }

    private fun setData() {
        for (i in 0 until 100) {
            dtoList.add(ClinicData(i,"Test Title $i", "Test Contents",Calendar.getInstance().time,
                Random().nextInt(120),false))
        }
        for (i in 0 until 10) {
            items.add(dtoList[i])
        }
    }

    fun moreItems() {
        val runnable = Runnable {
            items.add(null)
            clinicAdapter.notifyItemInserted(items.size - 1)
        }
        binding.rvClinic.post(runnable)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            val runnable2 = Runnable {
                items.removeAt(items.size-1)
                val scrollPosition = items.size
                clinicAdapter.notifyItemRemoved(scrollPosition)

                var currentSize = scrollPosition
                val nextLimit = currentSize + 9
                Log.d("Scroll Test","$scrollPosition $nextLimit $currentSize")
                if (currentSize < dtoList.size - 10) {
                    while (currentSize-1 < nextLimit) {
                        items.add(dtoList[currentSize])
                        currentSize++
                    }
                } else {
                    while(currentSize!=dtoList.size) {
                        items.add(dtoList[currentSize])
                        currentSize++
                    }
                }
                clinicAdapter.updateItem(items)
                isLoading = false
            }
            runnable2.run()
        }
    }
}