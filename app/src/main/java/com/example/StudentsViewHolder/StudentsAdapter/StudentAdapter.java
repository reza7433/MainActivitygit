package com.example.StudentsViewHolder.StudentsAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;
import com.example.StudentsViewHolder.MyDatabase;
import com.example.StudentsViewHolder.Students;
import com.example.mainactivity.DetailActivity;

import java.util.ArrayList;
import java.util.List;


public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ADS = 1;
    private static final int STUDENTROW = 3;
    MyDatabase myDatabase;
    private List studentsList;
    private Context contex;
    private List<Integer> positions;
    private List<Integer> testId;


    public StudentAdapter(Context contex, List<Students> studentsList) {
        this.contex = contex;
        this.studentsList = studentsList;
        myDatabase = new MyDatabase(contex);
        positions = new ArrayList<>();


    }

    @Override
    public int getItemViewType(int position) {
        if (((Students) studentsList.get(position)).isAds()) {
            return ADS;

        } else {
            return STUDENTROW;

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(contex);

        if (viewType == ADS) {
            View view = layoutInflater.inflate(R.layout.ads_row, viewGroup, false);
            return new AdsViewHolder(view);
        } else {

            View view = layoutInflater.inflate(R.layout.student_row, viewGroup, false);
            return new StudentsViewHolder(view);

        }
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int position) {

        final Students students = (Students) studentsList.get(position);

        if (students.isAds()) {
            ((AdsViewHolder) viewHolder).imgAds.setImageResource(R.drawable.nature);
        } else {

            ((StudentsViewHolder) viewHolder).txtName.setText(students.getName());
            ((StudentsViewHolder) viewHolder).txtFamily.setText(students.getFamily());
            ((StudentsViewHolder) viewHolder).txtField.setText(students.getField());
            ((StudentsViewHolder) viewHolder).id = (students.getId());


            ((StudentsViewHolder) viewHolder).imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    myDatabase.delet(students.getId());
                    studentsList.remove(students);
                    Toast.makeText(contex, position + "", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();

                }
            });


            ((StudentsViewHolder) viewHolder).imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDatabase.updateRow(students.getId(), "nnnnn");
                    students.setName("nnnnn");
                    notifyItemChanged(position);
                    // notifyItemRangeChanged(position);

                }
            });

            ((StudentsViewHolder) viewHolder).parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(contex, "id:" + students.getId() + "/ position:" + position, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(contex, DetailActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("name", students.getName());
                    intent.putExtra("family", students.getFamily());
                    intent.putExtra("field", students.getField());
                    contex.startActivity(intent);

                }
            });
        }


    }


    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    class StudentsViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtFamily, txtField;
        int id;
        CardView parent;
        ImageView imgDelete, imgEdit;


        public StudentsViewHolder(@NonNull View itemView) {

            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_studentRow_name);
            txtFamily = (TextView) itemView.findViewById(R.id.txt_studentRow_family);
            txtField = (TextView) itemView.findViewById(R.id.txt_studentRow_field);
            parent = (CardView) itemView.findViewById(R.id.card_studentRow_parent);
            imgDelete = (ImageView) itemView.findViewById(R.id.img_studentRow_delet);
            imgEdit = (ImageView) itemView.findViewById(R.id.img_studentRow_edit);
        }
    }

    class AdsViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAds;

        public AdsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAds = (ImageView) itemView.findViewById(R.id.img_adsRow_icon);
        }
    }


}

