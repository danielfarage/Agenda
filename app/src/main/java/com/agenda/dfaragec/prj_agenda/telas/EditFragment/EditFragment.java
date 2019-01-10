package com.agenda.dfaragec.prj_agenda.telas.EditFragment;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.agenda.dfaragec.prj_agenda.R;
import com.agenda.dfaragec.prj_agenda.model.Contact;
import com.agenda.dfaragec.prj_agenda.viewModel.ContactViewModel;

public class EditFragment extends Fragment {

    private com.agenda.dfaragec.prj_agenda.telas.ListFragment.ListFragment.OnItemSelectedListener listener;

    private TextInputEditText et_nome, et_tel;
    private Button bt_add;
    private ContactViewModel contactViewModel;
    private Contact contactEdit;



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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof com.agenda.dfaragec.prj_agenda.telas.ListFragment.ListFragment.OnItemSelectedListener) {
            listener = (com.agenda.dfaragec.prj_agenda.telas.ListFragment.ListFragment.OnItemSelectedListener) context;
        } else {
            throw new ClassCastException();
        }
    }

    private void inicializarVariavel(View view) {
        et_nome = view.findViewById(R.id.edit_et_nome);
        et_tel = view.findViewById(R.id.edit_et_tel);
        bt_add = view.findViewById(R.id.edit_bt_save);
        contactEdit = listener.getContactToEdit();
        contactViewModel = ViewModelProviders.of(getActivity()).get(ContactViewModel.class);

    }



    private void inicializarAcao() {
        getActivity().findViewById(R.id.main_bt_add).setVisibility(View.INVISIBLE);

        if(contactEdit != null){
            et_nome.setText(contactEdit.getName());
            et_tel.setText(contactEdit.getTelephone());
        }

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_nome.getText().toString().trim();
                String tel = et_tel.getText().toString().trim();

                if(name.isEmpty() || tel.isEmpty()){
                    Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                Contact contact = new Contact(name, tel);

                if(contactEdit != null){
                    contact.setId(contactEdit.getId());
                    contactViewModel.update(contact);
                    Toast.makeText(getContext(), "Contato editado!", Toast.LENGTH_SHORT).show();

                }else{
                    contactViewModel.insert(contact);
                    Toast.makeText(getContext(), "Contato adicionado!", Toast.LENGTH_SHORT).show();
                }

                FragmentManager fm = getFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_frag_content, new com.agenda.dfaragec.prj_agenda.telas.ListFragment.ListFragment());
                ft.commit();
            }
        });

    }


}
