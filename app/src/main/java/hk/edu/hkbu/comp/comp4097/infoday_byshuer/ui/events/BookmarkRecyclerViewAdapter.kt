package hk.edu.hkbu.comp.comp4097.infoday_byshuer.ui.events

import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.snackbar.Snackbar
import edu.hkbu.comp.comp4097.infoday_byshuer.data.AppDatabase
import edu.hkbu.comp.comp4097.infoday_byshuer.data.Event
import hk.edu.hkbu.comp.comp4097.infoday_byshuer.R

import hk.edu.hkbu.comp.comp4097.infoday_byshuer.ui.events.dummy.DummyContent.DummyItem
import kotlinx.coroutines.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class BookmarkRecyclerViewAdapter(
//    private val values: List<DummyItem>
//    private val values: List<Event>
    private val values: MutableList<Event>

) : RecyclerView.Adapter<BookmarkRecyclerViewAdapter.ViewHolder>() {

    /** Begin of insert **/
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        ItemTouchHelper(myCallback()).attachToRecyclerView(recyclerView)
        super.onAttachedToRecyclerView(
            recyclerView
        )
    }

    inner class myCallback : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val holder = viewHolder as ViewHolder
            //assume your idView contains deptID and contentView contains event's title
            val unbookmarkItem =
                values.find { it.deptId == holder.bookmarkTitle.text && it.title == holder.bookmarkContent.text }
            if (unbookmarkItem != null) CoroutineScope(Dispatchers.IO).launch {
//                unbookmarkItem.bookmarked = false
//                AppDatabase.getInstance(viewHolder.itemView.context).eventDao()
//                    .update(unbookmarkItem)
                val job = CoroutineScope(Dispatchers.IO).launch {
                    delay(3000L)
                    if (isActive) {
                        unbookmarkItem.bookmarked = false
                        AppDatabase.getInstance(viewHolder.itemView.context).eventDao()
                            .update(unbookmarkItem)
                        values.remove(unbookmarkItem)
                        CoroutineScope(Dispatchers.Main).launch {
                            notifyDataSetChanged()
                        }
                    }
                }
                Snackbar.make(viewHolder.itemView, "The bookmark is deleted", Snackbar.LENGTH_LONG)
                    .setAction("Undo") {
                        job.cancel()
                        notifyDataSetChanged()
                    }.show()
            }
        }
    }

    /** End of insert **/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_bookmark_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkRecyclerViewAdapter.ViewHolder, position: Int) {
        val item = values[position]
        holder.bookmarkTitle.text = item.deptId
        holder.bookmarkContent.text = item.title

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val bookmarkTitle: TextView = view.findViewById(R.id.bookmarkTitle)
        val bookmarkContent: TextView = view.findViewById(R.id.bookmarkContent)

//        init { //add this
//            view.addRipple()
//            view.isClickable = false
//        }

        override fun toString(): String {
            return super.toString() + " '" + bookmarkContent.text + "'"
        }
    }


    private fun View.addRipple() = with(TypedValue()) {
        context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
        setBackgroundResource(resourceId)
    }
}