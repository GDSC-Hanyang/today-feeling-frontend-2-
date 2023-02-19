package com.naze.todayfeeling.presentation.calendar

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.naze.todayfeeling.R
import java.util.*


class AdapterDay(private val tempMonth: Int, private val dayList: MutableList<Date>): RecyclerView.Adapter<AdapterDay.DayView>() {
    private val row = dayList.size/7

    inner class DayView(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_day, parent, false)
        return DayView(view)
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        holder.layout.findViewById<TextView>(R.id.item_day_text).apply {
            setOnClickListener {
                Toast.makeText(holder.layout.context, "${dayList[position]}", Toast.LENGTH_SHORT).show()
            }
            text = dayList[position].date.toString()
            Log.d("언제 들어오는 거지","${dayList[position]} : $position")
            setTextColor(Color.BLACK) //일요일 토요일은 변경 가능

            if (tempMonth != dayList[position].month) {
                holder.layout.findViewById<TextView>(R.id.item_day_text).alpha = 0.4f
            }
        }
    }

    override fun getItemCount(): Int {
        return row * 7
    }


}