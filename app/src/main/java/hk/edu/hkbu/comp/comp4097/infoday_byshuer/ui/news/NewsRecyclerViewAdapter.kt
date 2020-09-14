package hk.edu.hkbu.comp.comp4097.infoday_byshuer.ui.news

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import edu.hkbu.comp.comp4097.infoday_byshuer.data.News
import hk.edu.hkbu.comp.comp4097.infoday_byshuer.R

import hk.edu.hkbu.comp.comp4097.infoday_byshuer.ui.news.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class NewsRecyclerViewAdapter(
    private val values: List<News>
) : RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_news_item, parent, false)
        return ViewHolder(view)
    }

    //    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = values[position]
//        holder.idView.text = item.id
//        holder.contentView.text = item.content
//    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleTextView.text = item.title
        holder.detailTextView.text = item.detail
//        Picasso.get().load(item.image).into(holder.newsImageView)
    }

    override fun getItemCount(): Int = values.size

    //    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val idView: TextView = view.findViewById(R.id.item_number)
//        val contentView: TextView = view.findViewById(R.id.content)
//
//        override fun toString(): String {
//            return super.toString() + " '" + contentView.text + "'"
//        }
//    }
//Change this class
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsImageView: ImageView = view.findViewById(R.id.newsImageView)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val detailTextView: TextView = view.findViewById(R.id.detailTextView)
        override fun toString(): String {
            return super.toString() + " '" + titleTextView.text + "'"
        }
    }
}