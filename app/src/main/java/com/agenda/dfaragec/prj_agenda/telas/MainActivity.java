package com.agenda.dfaragec.prj_agenda.telas;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.agenda.dfaragec.prj_agenda.R;
import com.agenda.dfaragec.prj_agenda.telas.EditFragment.EditFragment;
import com.agenda.dfaragec.prj_agenda.telas.ListFragment.ListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.main_frag_content, new EditFragment());
        ft.commit();

    }
}
