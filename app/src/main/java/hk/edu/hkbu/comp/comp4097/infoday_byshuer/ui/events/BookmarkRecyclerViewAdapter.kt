package hk.edu.hkbu.comp.comp4097.infoday_byshuer.ui.events

import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import edu.hkbu.comp.comp4097.infoday_byshuer.data.AppDatabase
import edu.hkbu.comp.comp4097.infoday_byshuer.data.Event
import hk.edu.hkbu.comp.comp4097.infoday_byshuer.R

import hk.edu.hkbu.comp.comp4097.infoday_byshuer.ui.events.dummy.DummyContent.DummyItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class BookmarkRecyclerViewAdapter(
//    private val values: List<DummyItem>
    private val values: List<Event>

) : RecyclerView.Adapter<BookmarkRecyclerViewAdapter.ViewHolder>() {

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

        init { //add this
            view.addRipple()
            view.isClickable = false
        }

        override fun toString(): String {
            return super.toString() + " '" + bookmarkContent.text + "'"
        }
    }


    private fun View.addRipple() = with(TypedValue()) {
        context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
        setBackgroundResource(resourceId)
    }
}