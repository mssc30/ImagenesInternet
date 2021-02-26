package net.mssc.imagenesinternet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

//CLASE ADAPTADOR PARA LA CARD VIEW
public class AdaptadorSuper extends RecyclerView.Adapter<AdaptadorSuper.SuperViewHolder> {

    List<MainActivity.Super> listaSuper;
    Context context;

    public AdaptadorSuper(Context context, List<MainActivity.Super> list) {
        this.context = context;
        this.listaSuper = list;
    }


    @NonNull
    @Override
    public SuperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.heroes_cardview, parent, false);
        return new SuperViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperViewHolder holder, int position) {
        holder.txtNameSuper.setText(listaSuper.get(position).getName());
        Picasso.with(context).load(listaSuper.get(position).getUrl()).into(holder.imgSuper);
    }

    @Override
    public int getItemCount() {
        return listaSuper.size();
    }


    public class SuperViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSuper;
        TextView txtNameSuper;

        public SuperViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSuper = itemView.findViewById(R.id.imgCard);
            txtNameSuper = itemView.findViewById(R.id.txtNombre);
        }
    }
}
