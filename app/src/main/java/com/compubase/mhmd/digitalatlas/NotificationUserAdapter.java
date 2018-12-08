package com.compubase.mhmd.digitalatlas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationUserAdapter extends RecyclerView.Adapter<NotificationUserAdapter.ViewHolder> {

     private List<NotificationUser> notificationUsers ;

     private Context context;

    NotificationUserAdapter(List<NotificationUser> notificationUsers) {

        this.notificationUsers = notificationUsers;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView sender,title, body;
        Button dismiss;

        ViewHolder(View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.sender);
            title = itemView.findViewById(R.id.titile);
            body = itemView.findViewById(R.id.body);
            dismiss = itemView.findViewById(R.id.dismiss);
        }


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistnotificatoin,parent,false);

        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.sender.setText(notificationUsers.get(position).getSender());
        holder.title.setText(notificationUsers.get(position).getTitle());
        holder.body.setText(notificationUsers.get(position).getBody());

        holder.dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                volleyConnection(notificationUsers.get(position).getID());

                notificationUsers.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,notificationUsers.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return notificationUsers.size();
    }


    private void volleyConnection(final String id)
    {
        String GET_JSON_DATA_HTTP_URL = "http://atlas.alosboiya.com.sa/atlasnew.asmx/del_note?";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_JSON_DATA_HTTP_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        showMessage("Notification Dismissed");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                showMessage(error.toString());

            }

        }) {

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("id", id);

                return params;
            }



        };

        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    private void showMessage(String _s) {
        Toast.makeText(context, _s, Toast.LENGTH_LONG).show();
    }


}
