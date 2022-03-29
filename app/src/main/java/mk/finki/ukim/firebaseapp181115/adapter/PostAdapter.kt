package mk.finki.ukim.firebaseapp181115.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import mk.finki.ukim.firebaseapp181115.R
import mk.finki.ukim.firebaseapp181115.models.Post

class PostAdapter(var allStudents:MutableList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>()   {


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val studentIndex: TextView
        val studentName: TextView
        val studentSurname: TextView

        init {
            studentIndex = view.findViewById(R.id.studentIndex)
            studentName= view.findViewById(R.id.studentName)
            studentSurname = view.findViewById(R.id.studentSurname)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentStudent = allStudents[position]


        holder.studentIndex.text = currentStudent.postIndex
        holder.studentName.text = currentStudent.postName
        holder.studentSurname.text = currentStudent.postSurname

    }

    override fun getItemCount(): Int {
        return allStudents.size

    }
    fun addStudent(student:Post){
        if (!allStudents.contains(student)){
            allStudents.add(student)
        }
        notifyDataSetChanged()
    }

}