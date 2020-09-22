package hk.edu.hkbu.comp.comp4097.infoday_byshuer.ui.events

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import edu.hkbu.comp.comp4097.infoday_byshuer.data.Dept
import edu.hkbu.comp.comp4097.infoday_byshuer.data.Event
import hk.edu.hkbu.comp.comp4097.infoday_byshuer.R

import hk.edu.hkbu.comp.comp4097.infoday_byshuer.ui.events.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class DeptRecyclerViewAdapter(
//    private val values: List<DummyItem>
    private val values: List<Dept>

) : RecyclerView.Adapter<DeptRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_event_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.deptNameView.text = item.id
        holder.eventTextView.text = item.name
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val deptNameView: TextView = view.findViewById(R.id.deptNameView)
        val eventTextView: TextView = view.findViewById(R.id.eventTextView)
        //        val idVTextiew: TextView = view.findViewById(R.id.idTextView)
        //        val bookmarked: TextView = view.findViewById(R.id.detailTextView)

        init { view.setOnClickListener {
            it.findNavController().navigate( R.id.action_eventFragment_self, bundleOf(Pair("dept_id", deptNameView.text.toString()))
            ) }
        }
        override fun toString(): String {
            return super.toString() + " '" + eventTextView.text + "'"
        }
    }
}