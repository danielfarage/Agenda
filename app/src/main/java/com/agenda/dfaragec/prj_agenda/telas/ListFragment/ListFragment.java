package com.agenda.dfaragec.prj_agenda.telas.ListFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.agenda.dfaragec.prj_agenda.R;
import com.agenda.dfaragec.prj_agenda.adapter.ContactAdapter;
import com.agenda.dfaragec.prj_agenda.model.Contact;
import com.agenda.dfaragec.prj_agenda.viewModel.ContactViewModel;

import java.util.List;


public class ListFragment extends Fragment {

    private ContactViewModel contactViewModel;
    private RecyclerView rv_list;
    private Context context;
    private ContactAdapter adapter;

    private OnItemSelectedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializarVariavel(view);
        inicializarAcao();
    }

    public interface OnItemSelectedListener {
        public void onItemSelected(Contact contact);
        public Contact getContactToEdit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException();
        }
    }

    private void inicializarVariavel(View view) {

        context = getContext();
        rv_list = view.findViewById(R.id.list_rv_contacts);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ContactAdapter(getContext());

        adapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Contact contact) {
                listener.onItemSelected(contact);
            }
        });

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        contactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {
                adapter.setListContacts(contacts);
                rv_list.setAdapter(adapter);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                contactViewModel.delete(adapter.getContactAt(viewHolder.getAdapterPosition()));
                Toast.makeText(context, "Contato apagado!", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(rv_list);
    }

    private void inicializarAcao() {
        getActivity().findViewById(R.id.main_bt_add).setVisibility(View.VISIBLE);
    }
}
