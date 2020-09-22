package hk.edu.hkbu.comp.comp4097.infoday_byshuer.ui.events

import android.util.Log
import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import edu.hkbu.comp.comp4097.infoday_byshuer.data.AppDatabase
import edu.hkbu.comp.comp4097.infoday_byshuer.data.Event
import hk.edu.hkbu.comp.comp4097.infoday_byshuer.R

import hk.edu.hkbu.comp.comp4097.infoday_byshuer.ui.events.dummy.DummyContent.DummyItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class EventRecyclerViewAdapter(
//    private val values: List<DummyItem>
    private val values: List<Event>

) : RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_event_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.deptNameView.text = item.deptId + ": " + item.id
        holder.eventTextView.text = item.title
        //EventRecyclerView.onBindViewHolder

        holder.itemView.setOnClickListener { v ->
            CoroutineScope(IO).launch {
                val dao = AppDatabase.getInstance(v.context).eventDao()
                dao.update(values[position].also { it.bookmarked = true })
                dao.findAllBookmarkedEvents()
                    .forEach { Log.d("EventRecyclerViewAdapter", "onBindViewHolder: $it") }
            }
            Toast.makeText(v.context, "${item.title} is bookmarked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val deptNameView: TextView = view.findViewById(R.id.deptNameView)
        val eventTextView: TextView = view.findViewById(R.id.eventTextView)

        init { //add this
            view.addRipple()
            view.isClickable = true
        }

        override fun toString(): String {
            return super.toString() + " '" + eventTextView.text + "'"
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        ItemTouchHelper(MyCallback()).attachToRecyclerView(recyclerView)
        super.onAttachedToRecyclerView(recyclerView)
    }

    inner class MyCallback : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val holder = viewHolder as EventRecyclerViewAdapter.ViewHolder
            //assume your idView contains deptID and contentView contains event's title
            val unbookmarkItem =
                values.find { it.deptId == holder.deptNameView.text && it.title == holder.eventTextView.text }
            unbookmarkItem?.bookmarked = false

            if (unbookmarkItem != null) CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getInstance(viewHolder.itemView.context).eventDao()
                    .update(unbookmarkItem)
            }
        }
    }


    private fun View.addRipple() = with(TypedValue()) {
        context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
        setBackgroundResource(resourceId)
    }

}