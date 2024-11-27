package com.grupo1.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PreguntasAdapter extends RecyclerView.Adapter<PreguntasAdapter.ViewHolder> {

    List<Preguntas> preguntasList;

    public PreguntasAdapter(List<Preguntas> preguntasList) {
        this.preguntasList = preguntasList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Preguntas preguntas = preguntasList.get(position);
        holder.preguntaTxt.setText(preguntas.getPregunta());
        holder.respuestaTxt.setText(preguntas.getRespuesta());
        boolean isExpandible = preguntasList.get(position).isExpandable();
        holder.relativeLayout.setVisibility(isExpandible ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount(){
        return preguntasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView respuestaTxt,preguntaTxt;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            respuestaTxt = itemView.findViewById(R.id.Respuesta);
            preguntaTxt = itemView.findViewById(R.id.Pregunta);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Preguntas preguntas = preguntasList.get(getAdapterPosition());
                    preguntas.setExpandable(!preguntas.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
