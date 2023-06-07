package com.example.laboratoire5_1;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends ListAdapter<User, UserAdapter.UserViewHolder> {

    public RecyclerViewCallback<User> callback = (U) -> {};
    public UserAdapter() {
        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(User oldUser, User newUser){
                return oldUser.equals(newUser);

            }
            @Override
            public boolean areContentsTheSame(User oldUser, User newUser){
                return oldUser.getEmail().equals(newUser.getEmail()) && oldUser.getName().equals(newUser.getName());
            }
        });
    }



    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        User user;
        private final TextView name_textview;
        private final TextView email_textview;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name_textview = itemView.findViewById(R.id.textView_fullname);
            email_textview = itemView.findViewById(R.id.textView_email);
            itemView.setOnClickListener(view -> {
                callback.returnValue(user);
            });
        }

        public void bind(User user) {
            name_textview.setText(user.getName());
            email_textview.setText(user.getEmail());
            this.user = user;
        }

    }
}
