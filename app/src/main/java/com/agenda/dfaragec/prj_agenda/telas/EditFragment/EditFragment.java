package com.agenda.dfaragec.prj_agenda.telas.EditFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.agenda.dfaragec.prj_agenda.R;
import com.agenda.dfaragec.prj_agenda.model.Contact;
import com.agenda.dfaragec.prj_agenda.viewModel.ContactViewModel;

public class EditFragment extends Fragment {

    private TextInputEditText et_nome, et_tel;
    private Button bt_add;
    private ContactViewModel contactViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_edit, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarVariavel(view);
        inicializarAcao();
    }

    private void inicializarVariavel(View view) {
        et_nome = view.findViewById(R.id.edit_et_nome);
        et_tel = view.findViewById(R.id.edit_et_tel);
        bt_add = view.findViewById(R.id.edit_bt_save);

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
    }

    private void inicializarAcao() {
        contactViewModel.getAll(getContext());
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactViewModel.save(
                        et_nome.getText().toString(),
                        et_tel.getText().toString(),
                        getContext()
                );
            }
        });



        contactViewModel.mensagem.observe(getViewLifecycleOwner(),
                new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                    }
                });

    }


}
