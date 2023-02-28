package com.example.aimedical;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.FirestoreClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Doctor_profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Doctor_profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Doctor_profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Doctor_profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Doctor_profile newInstance(String param1, String param2) {
        Doctor_profile fragment = new Doctor_profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private FirebaseFirestore db;
    TextView Eemail,Ename,Ephone;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Eemail=(TextView) getActivity().findViewById(R.id.DoctorEmailView);
        Ename=(TextView) getActivity().findViewById(R.id.Doctor_nameView);
        Ephone=(TextView) getActivity().findViewById(R.id.Doctor_phone_view);
        db.collection("Doctors").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(task -> {
            if(task.isSuccessful() && task.getResult() != null){
                String name = task.getResult().getString("Name");
                String email = task.getResult().getString("Email");
                String phone = task.getResult().getString("Phone");
                Ename.setText(name);
                Eemail.setText(email);
                Ephone.setText(phone);
            }else{
                Ename.setText("Your name will be displayed here");
                Eemail.setText("Your email will be displayed here");
                Ephone.setText("Your phone will be displayed here");
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_profile, container, false);
    }



    }