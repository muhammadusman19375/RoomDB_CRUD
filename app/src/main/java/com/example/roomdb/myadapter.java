package com.example.roomdb;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class myadapter extends RecyclerView.Adapter <myadapter.myViewHolder> {
    List<User> users;

    public myadapter(List<User>users){
        this.users = users;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.id.setText(String.valueOf(users.get(position).getUid()));
        holder.first_name.setText(users.get(position).getFirstName());
        holder.last_name.setText(users.get(position).getLastName());
        holder.btn_delete.setOnClickListener(new View.OnClickListener()     {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(holder.id.getContext(),
                        AppDatabase.class,"room_db").allowMainThreadQueries().build();
                UserDao userDao = db.userDao();

                //To get the specific postion of holder
                int pos = holder.getAdapterPosition();

                // To delete the record from database
                userDao.deleteById(users.get(pos).getUid());

                // To delete the record form ArrayList
                users.remove(pos);

                //Update the fresh list of ArrayList data to recycler view
                notifyDataSetChanged();
            }
        });
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = holder.getAdapterPosition();

                Intent intent = new Intent(holder.btn_edit.getContext(), UpdateData.class);
                intent.putExtra("uid",String.valueOf(users.get(pos).getUid()));
                intent.putExtra("Fname",users.get(pos).getFirstName());
                intent.putExtra("Lname",users.get(pos).getLastName());
                holder.btn_edit.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView id,first_name,last_name;
        ImageButton btn_delete,btn_edit;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.userid);
            first_name = itemView.findViewById(R.id.userfname);
            last_name = itemView.findViewById(R.id.userlname);
            btn_delete = itemView.findViewById(R.id.btn_dlt);
            btn_edit = itemView.findViewById(R.id.btn_edit);


        }
    }


}
