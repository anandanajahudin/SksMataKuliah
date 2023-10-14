package com.example.sksmatakuliah
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_course.view.*

class CourseAdapter(
    private val courseList: List<CourseModel>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currentItem = courseList[position]
        holder.itemView.sksTextView.text = "SKS: \t" + currentItem.sks.toString()
        holder.itemView.nilaiTextView.text = "Score: \t" + currentItem.nilai.toString()
        holder.itemView.mataKuliahTextView.text = "Name: \t" + currentItem.namaMataKuliah

        holder.itemView.deleteButton.setOnClickListener { onDeleteClick(position) }
    }

    override fun getItemCount() = courseList.size

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}