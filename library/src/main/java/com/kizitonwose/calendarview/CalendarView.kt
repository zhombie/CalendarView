package com.kizitonwose.calendarview

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kizitonwose.calendarview.adapter.CalendarAdapter
import com.kizitonwose.calendarview.adapter.DateClickListener
import com.kizitonwose.calendarview.adapter.DateViewBinder
import com.kizitonwose.calendarview.adapter.MonthHeaderFooterBinder
import org.threeten.bp.LocalDate

class CalendarView : RecyclerView {

    private lateinit var adapter: CalendarAdapter

    var onDateClick: DateClickListener? = null

    var dateViewBinder: DateViewBinder? = null

    var monthHeaderBinder: MonthHeaderFooterBinder? = null

    var monthFooterBinder: MonthHeaderFooterBinder? = null

    var allowsMultipleSelection: Boolean = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs, defStyleAttr, 0)
    }

    private fun init(attributeSet: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        val a = context.obtainStyledAttributes(attributeSet, R.styleable.CalendarView, defStyleAttr, defStyleRes)
        val dayViewRes = a.getResourceId(R.styleable.CalendarView_dayViewResource, 0)
        val monthHeaderRes = a.getResourceId(R.styleable.CalendarView_monthHeaderResource, 0)
        val monthFooterRes = a.getResourceId(R.styleable.CalendarView_monthFooterResource, 0)
        val orientation = a.getInt(R.styleable.CalendarView_calendarOrientation, RecyclerView.VERTICAL)
        a.recycle()

        clipToPadding = false
        layoutManager = LinearLayoutManager(context, orientation, false)
        adapter = CalendarAdapter(dayViewRes, monthHeaderRes, monthFooterRes)
        setAdapter(adapter)
    }

    var monthPaddingStart = 0
        set(value) {
            field = value
            invalidateViewHolders()
        }

    var monthPaddingEnd = 0
        set(value) {
            field = value
            invalidateViewHolders()
        }

    var monthPaddingTop = 0
        set(value) {
            field = value
            invalidateViewHolders()
        }

    var monthPaddingBottom = 0
        set(value) {
            field = value
            invalidateViewHolders()
        }

    var monthWidth = ViewGroup.LayoutParams.MATCH_PARENT
        set(value) {
            field = value
            invalidateViewHolders()
        }

    var monthHeight = ViewGroup.LayoutParams.WRAP_CONTENT
        set(value) {
            field = value
            invalidateViewHolders()
        }

    private fun invalidateViewHolders() {
        recycledViewPool.clear()
    }


    fun reloadCalendar() {
        adapter.notifyDataSetChanged()
    }

    fun scrollToMonth(date: LocalDate) {
        adapter.scrollToMonth(date)
    }

    fun clearSelection() {
        adapter.clearSelection()
    }
}
