package com.naze.todayfeeling.presentation.report

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naze.todayfeeling.R
import com.naze.todayfeeling.databinding.FragmentReportBinding
import com.naze.todayfeeling.domain.model.ReportData
import com.naze.todayfeeling.util.binding.BindingFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ReportFragment: BindingFragment<FragmentReportBinding>(R.layout.fragment_report) {

    private lateinit var reportAdapter: ReportAdapter

    private var dtoList: ArrayList<ReportData> = ArrayList()
    private var items: ArrayList<ReportData?> = ArrayList()
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
        initScrollListener()
    }

    private fun initLayout() {
        binding.rvReport.apply {
            layoutManager = LinearLayoutManager(context)
            reportAdapter = ReportAdapter(items)
            adapter = reportAdapter
        }
    }

    private fun initScrollListener() {
        binding.rvReport.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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

    private fun setData() {
        for (i in 0 until 100) {
            dtoList.add(
                ReportData(i, "Test Contents",
                Calendar.getInstance().time,Random().nextInt(120),false)
            )
        }
        for (i in 0 until 10) {
            items.add(dtoList[i])
        }
    }

    fun moreItems() {
        val runnable = Runnable {
            items.add(null)
            reportAdapter.notifyItemInserted(items.size - 1)
        }
        binding.rvReport.post(runnable)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            val runnable2 = Runnable {
                items.removeAt(items.size-1)
                val scrollPosition = items.size
                reportAdapter.notifyItemRemoved(scrollPosition)

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
                reportAdapter.updateItem(items)
                isLoading = false
            }
            runnable2.run()
        }
    }
}