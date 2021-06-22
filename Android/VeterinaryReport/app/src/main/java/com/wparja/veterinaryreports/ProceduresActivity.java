package com.wparja.veterinaryreports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wparja.veterinaryreports.data.DataProvider;
import com.wparja.veterinaryreports.logging.LoggerHelper;
import com.wparja.veterinaryreports.persistence.entities.Report;
import com.wparja.veterinaryreports.utils.PhotoUtils;
import com.wparja.veterinaryreports.utils.PictureUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProceduresActivity extends AppCompatActivity {

    Toolbar mToolbar;
    EditText mEditTextSearch;
    List<Report> mReports = new ArrayList<>();
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedures);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.report);

        mEditTextSearch = findViewById(R.id.search_edit_text);
        mRecyclerView = findViewById(R.id.patient_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void search(View view) {
        try {
            mReports = DataProvider.getInstance().searchByName(mEditTextSearch.getText().toString());
            mRecyclerView.setAdapter(new PatientAdapter());
        } catch (Exception e) {
            LoggerHelper.getInstance().logError(e.getMessage());
        }
    }


    class PatientHolder extends RecyclerView.ViewHolder {

        TextView mTextViewPatientName;
        TextView mTextViewClinicName;
        TextView mTextViewProcedureName;
        TextView mTextViewProcedureDate;
        ImageView mImageViewPatientPhoto;
        Report mReport;

        public PatientHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewPatientName = itemView.findViewById(R.id.patient_name);
            mTextViewClinicName = itemView.findViewById(R.id.clinic_name);
            mTextViewProcedureName = itemView.findViewById(R.id.procedure_name);
            mTextViewProcedureDate = itemView.findViewById(R.id.procedure_date);
            mImageViewPatientPhoto = itemView.findViewById(R.id.patient_photo);
            itemView.setOnClickListener( v -> startActivity(NewProcedureActivity.newInstance(ProceduresActivity.this, mReport)));
        }

        private void bind(Report report) {
            mReport = report;
            mTextViewPatientName.setText(mReport.getPatientName());
            mTextViewClinicName.setText(mReport.getClinicName());
            mTextViewProcedureName.setText(mReport.getProcedurePerformed());
            mImageViewPatientPhoto.setImageBitmap(PictureUtils.getThumbnail(mReport.getMainPhoto()));
        }
    }

    class PatientAdapter extends RecyclerView.Adapter<PatientHolder> {

        @NonNull
        @Override
        public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PatientHolder(LayoutInflater.from(ProceduresActivity.this).inflate(R.layout.patient_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
            Report report = mReports.get(position);
            holder.bind(report);
        }

        @Override
        public int getItemCount() {
            return mReports.size();
        }
    }
}
