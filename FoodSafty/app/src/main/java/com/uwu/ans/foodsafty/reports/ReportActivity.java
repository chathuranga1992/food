package com.uwu.ans.foodsafty.reports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.uwu.ans.foodsafty.R;

public class ReportActivity extends AppCompatActivity {

    private DatabaseReference rootRef;
    private RecyclerView reportlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_report);
        rootRef = FirebaseDatabase.getInstance().getReference();
        reportlist = (RecyclerView) findViewById(R.id.recyclerview_reportList);

        reportlist.setLayoutManager(new LinearLayoutManager(this));

        Query SortList = rootRef.child("Inspections").orderByChild("restaurant_name");

        FirebaseRecyclerAdapter<Report,ReportViewHolder> adapter =
                new FirebaseRecyclerAdapter<Report, ReportViewHolder>(Report.class,R.layout.reporter_item,ReportViewHolder.class,SortList) {
                    @Override
                    protected void populateViewHolder(ReportViewHolder viewHolder, Report model, int position) {

                        final String RepKey = getRef(position).getKey();

                        viewHolder.setRestName(model.getRestaurant_name());
                        viewHolder.setRestAddress(model.getPrivate_address());

                        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ReportActivity.this,ReportDetailsActivity.class);
                                intent.putExtra("SelectedRep",RepKey);
                                startActivity(intent);
                            }
                        });
                    }
                };
        reportlist.setAdapter(adapter);


    }


    public static class ReportViewHolder extends RecyclerView.ViewHolder
    {
        View mView;

        TextView restName;
        TextView restAddress;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            restName = itemView.findViewById(R.id.rest_name);
            restAddress = itemView.findViewById(R.id.rest_address);
        }

        public void setRestName(String name)
        {
            TextView expname = (TextView) mView.findViewById(R.id.rest_name);
            expname.setText(name);
        }


        public void setRestAddress(String address)
        {
            TextView expadd = (TextView) mView.findViewById(R.id.rest_address);
            expadd.setText(address);
        }
    }

}
