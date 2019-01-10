package com.agenda.dfaragec.prj_agenda.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.agenda.dfaragec.prj_agenda.R;
import com.agenda.dfaragec.prj_agenda.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactVH> {

    private Context context;

    private List<Contact> listContacts = new ArrayList<>();
    private OnItemClickListener listener;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    public void setListContacts(List<Contact> listContacts){
        this.listContacts = listContacts;
        notifyDataSetChanged();
    }

    public Contact getContactAt(int position){
        return listContacts.get(position);
    }

    @NonNull
    @Override
    public ContactVH onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ContactVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactVH contactVH, int position) {
        Contact item = listContacts.get(position);
        contactVH.tv_name.setText(item.getName());
        contactVH.tv_tel.setText(item.getTelephone());
    }

    @Override
    public int getItemCount() {
        return listContacts.size();
    }

    public class ContactVH extends RecyclerView.ViewHolder{

        private TextView tv_name, tv_tel;

        public ContactVH(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.item_name);
            tv_tel = itemView.findViewById(R.id.item_tel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.OnItemClick(listContacts.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(Contact contact);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
