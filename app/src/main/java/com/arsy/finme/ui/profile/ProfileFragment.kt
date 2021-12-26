package com.arsy.finme.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsy.finme.R
import com.arsy.finme.databinding.FragmentHomeBinding
import com.arsy.finme.databinding.FragmentProfileBinding
import com.arsy.finme.ui.login.LoginActivity
import com.arsy.finme.ui.register.RegisterActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    private var bindingProfile: FragmentProfileBinding? = null
    private val binding get() = bindingProfile

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingProfile = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val user = Firebase.auth.currentUser
            binding?.username?.text = user?.email
            binding?.tvEmail?.text = user?.email
            binding?.tvJoin?.text = user?.displayName
        }

        binding?.btnLogout?.setOnClickListener {
            Firebase.auth.signOut();
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}