
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sksinha2410.ananotherreyclerview.ChildAdapter
import com.sksinha2410.ananotherreyclerview.ParentItem
import com.sksinha2410.ananotherreyclerview.R

class ParentAdapter(private val parentList: List<ParentItem>) :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_parent, parent, false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.bind(parentList[position])
    }

    override fun getItemCount(): Int = parentList.size

    class ParentViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val childRecyclerView: RecyclerView = view.findViewById(R.id.child_recyclerview)

        fun bind(parentItem: ParentItem) {
            view.findViewById<TextView>(R.id.parent_title).text = parentItem.title
            childRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
            childRecyclerView.adapter = ChildAdapter(parentItem.childList)
        }
    }
}