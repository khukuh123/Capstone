package com.example.capstoneproject.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.capstoneproject.R;
import com.example.capstoneproject.ui.account.SettingAccountActivity;
import com.example.capstoneproject.ui.account.SettingHowToUseActivity;
import com.example.capstoneproject.ui.account.SettingLanguageActivity;
import com.example.capstoneproject.ui.account.SettingTermsServiceActivity;
import com.example.capstoneproject.ui.login.LoginActivity;
import com.example.capstoneproject.ui.user.UserModel;
import com.example.capstoneproject.viewmodel.AccountViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class AccountFragment extends Fragment {
    ImageButton imageButton, btnLanguage, btnHowToUse, btnTerms;

    Button btnLogout;

    TextView tvFullName, tvPhoneNumber, tvEmail;

    FirebaseAuth auth;

    AccountViewModel accountViewModel = AccountViewModel.getInstance();

//    private FirebaseUser user;
//    private DatabaseReference userRef;
//    private FirebaseDatabase database;

//    private static final String USERS = "users";

//    String email;

//    private String userID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

//        Intent intent = getIntent();
//        email = intent.getStringExtra("email");

        initUI(view);
        initAction();
        initObserver();

//        database = FirebaseDatabase.getInstance();
//        userRef = database.getReference(USERS);

        auth = FirebaseAuth.getInstance();

        //Realtime Database
//        userRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    if (ds.child("email").getValue().equals(email)) {
//                        tvFullName.setText(ds.child("fullName").getValue(String.class));
//                        tvEmail.setText(email);
//                        tvPhoneNumber.setText(ds.child("phoneNumber").getValue(String.class));
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });

//        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getActivity());
//        if (signInAccount != null) {
//            tvFullName.setText(signInAccount.getDisplayName());
//            tvEmail.setText(signInAccount.getEmail());
//        }

//        user = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.getInstance().getReference("Users");
//        userID = user.getUid();

//        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                UserModel userProfile = snapshot.getValue(UserModel.class);
//
//                if (userProfile != null) {
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//                Toast.makeText(getActivity(), "Something wrong happened.", Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }

    private void initAction() {
        btnLogout.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });

        btnLanguage.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingLanguageActivity.class);
            startActivity(intent);
        });

        btnHowToUse.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingHowToUseActivity.class);
            startActivity(intent);
        });

        btnTerms.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingTermsServiceActivity.class);
            startActivity(intent);
        });


        imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingAccountActivity.class);
            startActivity(intent);
        });
    }

    private void initObserver() {
        String uid = FirebaseAuth.getInstance().getUid();
        if(uid != null) {
            accountViewModel.getMyInformation(uid).observe(getViewLifecycleOwner(), this::setUser);
        }
    }

    private void setUser(UserModel user) {
        String fullName = user.fullName;
        String email = user.email;
        String phoneNumber = user.phoneNumber;

        tvFullName.setText(fullName);
        tvEmail.setText(email);
        tvPhoneNumber.setText(phoneNumber);
    }

    private void initUI(View view) {
        imageButton = view.findViewById(R.id.btn_edit);
        btnLogout = view.findViewById(R.id.btn_signout);
        btnLanguage = view.findViewById(R.id.btn_languageoption);
        btnHowToUse = view.findViewById(R.id.btn_howtouse);
        btnTerms = view.findViewById(R.id.btn_termofservice);
        tvFullName = view.findViewById(R.id.tv_full_name);
        tvPhoneNumber = view.findViewById(R.id.tv_phone_number);
        tvEmail = view.findViewById(R.id.tv_email);
    }
}
