package com.agenda.dfaragec.prj_agenda.telas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.agenda.dfaragec.prj_agenda.R;
import com.agenda.dfaragec.prj_agenda.model.Contact;
import com.agenda.dfaragec.prj_agenda.telas.EditFragment.EditFragment;
import com.agenda.dfaragec.prj_agenda.telas.ListFragment.ListFragment;

public class MainActivity extends AppCompatActivity
    implements ListFragment.OnItemSelectedListener{

    private Button btn_add;
    private Contact contactToEdit = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        inicializarVariavel();
        inicializarAcao();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frag_content, new com.agenda.dfaragec.prj_agenda.telas.ListFragment.ListFragment());
        ft.commit();
    }

    private void inicializarVariavel() {

        btn_add = findViewById(R.id.main_bt_add);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.main_frag_content, new ListFragment());
        ft.commit();


    }

    private void inicializarAcao() {


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactToEdit = null;

                FragmentManager fm = getSupportFragmentManager();

                Fragment fragment = fm.findFragmentById(R.id.main_frag_content);
                FragmentTransaction ft = fm.beginTransaction();
                ft.remove(fragment);
                ft.commit();

                FragmentTransaction fragT = fm.beginTransaction();
                fragT.add(R.id.main_frag_content, new EditFragment());
                fragT.commit();
            }
        });
    }

    @Override
    public void onItemSelected(Contact contact) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frag_content, new EditFragment());
        ft.commit();

        contactToEdit = contact;
    }

    @Override
    public Contact getContactToEdit() {
        return contactToEdit;
    }
}
