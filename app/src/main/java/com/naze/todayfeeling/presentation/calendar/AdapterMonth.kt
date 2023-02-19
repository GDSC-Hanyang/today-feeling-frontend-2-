package com.naze.todayfeeling.presentation.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naze.todayfeeling.R
import java.util.Calendar
import java.util.Date

class AdapterMonth: RecyclerView.Adapter<AdapterMonth.MonthView>() {
    private val center = Int.MAX_VALUE/2
    private var calendar = Calendar.getInstance()

    inner class MonthView(val layout: View) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_month, parent, false)
        return MonthView(view)
    }

    override fun onBindViewHolder(holder: MonthView, position: Int) {
        calendar.apply {
            time = Date()
            set(Calendar.DAY_OF_MONTH,1)
            add(Calendar.MONTH, position - center)
        }
        holder.layout.findViewById<TextView>(R.id.item_month_text).text = "${calendar.get(Calendar.YEAR)}.${String.format("%02d",calendar.get(Calendar.MONTH)+1)}."

        val tempMonth = calendar.get(Calendar.MONTH)

        val dayList: MutableList<Date> = MutableList(6*7) { Date() }
        for (i in 0..5) {
            for (j in 0..6) {
                calendar.add(Calendar.DAY_OF_MONTH, (1-calendar.get(Calendar.DAY_OF_WEEK))+j)
                dayList[i*7+j] = calendar.time
            }
            calendar.add(Calendar.WEEK_OF_MONTH, 1)
        }

        val dayListManager = GridLayoutManager(holder.layout.context, 7)
        val dayListAdapter = AdapterDay(tempMonth,dayList)

        holder.layout.findViewById<RecyclerView>(R.id.item_month_day_list).apply {
            layoutManager = dayListManager
            adapter = dayListAdapter
        }
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }


}