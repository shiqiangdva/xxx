package others;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import sq.can_greendao.R;

/**
 * Created by sp01 on 2017/3/30.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private Context context;
    private List<String> data;

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
