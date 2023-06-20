package com.example.laboratoire5_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laboratoire5_1.R;
import com.example.laboratoire5_1.RecyclerViewCallback;
import com.example.laboratoire5_1.data.Rental;
import com.example.laboratoire5_1.data.User;

public class RentalAdapter extends ListAdapter<Rental, RentalAdapter.UserViewHolder> {
    public RecyclerViewCallback<Rental> callback = (U) -> {};
    public RentalAdapter() {
        super(new DiffUtil.ItemCallback<Rental>() {
            @Override
            public boolean areItemsTheSame(Rental oldRental, Rental newRental){
                return oldRental.rentalId == newRental.rentalId;
            }
            @Override
            public boolean areContentsTheSame(Rental oldRental, Rental newRental){
                return oldRental.getName().equals(newRental.getName());
            }
        });
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rental_item, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        Rental rental;
        private final TextView rentalName_textview;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            rentalName_textview = itemView.findViewById(R.id.textView_rentalName);
            itemView.setOnClickListener(view -> {
                callback.returnValue(rental);
            });
        }
        public void bind(Rental rental) {
            rentalName_textview.setText(rental.getName());
            this.rental = rental;
        }
    }
}
