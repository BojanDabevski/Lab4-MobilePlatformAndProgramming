package mk.finki.ukim.firebaseapp181115

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import mk.finki.ukim.firebaseapp181115.adapter.PostAdapter
import mk.finki.ukim.firebaseapp181115.databinding.FragmentSecondBinding
import mk.finki.ukim.firebaseapp181115.models.Post


class SecondFragment : Fragment() {

    val mAuth = FirebaseAuth.getInstance()

    var database = FirebaseDatabase.getInstance()
    var postsReference = database.getReference("posts")

    private val _student = MutableLiveData<Post>()
    val student: LiveData<Post> get()= _student

    private lateinit var studentsRecyclerView: RecyclerView
    lateinit var recyclerViewAdapter: PostAdapter

    private lateinit var fragment: FirstFragment




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonSecond: Button = view.findViewById(R.id.button_second)
        buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        recyclerViewAdapter = PostAdapter( mutableListOf<Post>())

        studentsRecyclerView = view.findViewById(R.id.studentIndex)
        studentsRecyclerView.layoutManager = LinearLayoutManager(activity)
        studentsRecyclerView.adapter=recyclerViewAdapter


    }

    private val childEventListener = object : ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val student = snapshot.getValue(Post::class.java)
            student?.postIndex= snapshot.key!!
            _student.value = student!!
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            TODO("Not yet implemented")
        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    }
    fun getRealtimeUpdate(){
        postsReference.addChildEventListener(childEventListener)
    }




}