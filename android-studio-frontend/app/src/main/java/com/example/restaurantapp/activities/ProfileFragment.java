package com.example.restaurantapp.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantapp.R;
import com.example.restaurantapp.services.RetrofitService;
import com.example.restaurantapp.services.ApiService;
import com.example.restaurantapp.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SharedPreferences sharedPreferences;
    private TextView fullNameTextView, emailTextView, roleTextView;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        fullNameTextView = view.findViewById(R.id.textFullName);
        emailTextView = view.findViewById(R.id.textEmail);
        roleTextView = view.findViewById(R.id.textRole);

        sharedPreferences = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);

        loadUser();


        return view;
    }

    private void loadUser() {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken.isEmpty()) {
            Toast.makeText(getActivity(), "Access token not found", Toast.LENGTH_SHORT).show();
        } else {
            ApiService apiService = RetrofitService.getApiService();

            String authHeader = "Bearer " + accessToken;

            Call<User> call = apiService.getUser(authHeader);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User user = response.body();
                        String fullName = user.getFullName();
                        String email = user.getEmail();
                        String role = user.getRole();

                        fullNameTextView.setText(fullName);
                        emailTextView.setText(email);
                        roleTextView.setText(role);
                    } else {
                        Toast.makeText(getActivity(), "Failed to load user", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
    }
}