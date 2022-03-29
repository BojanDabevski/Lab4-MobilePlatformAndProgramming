package mk.finki.ukim.firebaseapp181115

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import mk.finki.ukim.firebaseapp181115.models.Post

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    val mAuth = FirebaseAuth.getInstance()

    var database = FirebaseDatabase.getInstance()
    var postsReference = database.getReference("posts")

    private lateinit var etIndex: EditText
    private lateinit var etName: EditText
    private lateinit var etSurname: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etAddress: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etIndex = view.findViewById(R.id.etIndex)
        etName = view.findViewById(R.id.etName)
        etSurname = view.findViewById(R.id.etSurname)
        etPhoneNumber = view.findViewById(R.id.etPhoneNumber)
        etAddress = view.findViewById(R.id.etAddress)


        val submitButton: Button = view.findViewById(R.id.btnUpload)

        submitButton.setOnClickListener {
            val index: String = etIndex.text.toString()
            val name: String = etName.text.toString()
            val surname: String = etSurname.text.toString()
            val phoneNumber: String = etPhoneNumber.text.toString()
            val address: String = etAddress.text.toString()

            if(index.isNullOrEmpty() || name.isNullOrEmpty() || surname.isNullOrEmpty()|| phoneNumber.isNullOrEmpty() || address.isNullOrEmpty()) {
                return@setOnClickListener
            }
            uploadData(index, name,surname,phoneNumber,address)
        }

        val secondfragmentButon: Button = view.findViewById(R.id.secondFragment)
        secondfragmentButon.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }


    }

    private fun uploadData(index: String, name: String, surname: String, phoneNumber: String, address: String) {
        val currentPost = Post(mAuth.uid!!, index, name,surname,phoneNumber,address)


        postsReference.push().setValue(currentPost)
            .addOnCompleteListener(OnCompleteListener<Void?> { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "Success", Toast.LENGTH_LONG).show()
                    etIndex.setText("")
                    etName.setText("")
                    etSurname.setText("")
                    etPhoneNumber.setText("")
                    etAddress.setText("")

                } else {
                    Toast.makeText(
                        activity,
                        "Error: " + task.exception!!.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}