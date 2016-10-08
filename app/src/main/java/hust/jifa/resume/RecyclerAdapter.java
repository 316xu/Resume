package hust.jifa.resume;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jfxu on 16/10/2.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Experience> mData;

    public RecyclerAdapter(List<Experience> list) {
       mData = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.experience_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView toggle;
        TextView name;
        TextView nature;
        TextView work;
        TextView time;
        TextView detail;
        View itemView;
        View timeLayout;
        int shortH = 0;
        int longH = 0;
        boolean isOpen = false;

        public MyViewHolder(View itemView) {

            super(itemView);
            this.itemView = itemView;
            timeLayout = itemView.findViewById(R.id.time_layout);
            toggle = (TextView) itemView.findViewById(R.id.toggle);
            name = (TextView) itemView.findViewById(R.id.name);
            nature = (TextView) itemView.findViewById(R.id.nature);
            work = (TextView) itemView.findViewById(R.id.work);
            time = (TextView) itemView.findViewById(R.id.time);
            detail = (TextView) itemView.findViewById(R.id.detail);
            toggle.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (isOpen) {
                        toggle.setText("展开");
                        close();
                    } else {
                        toggle.setText("关闭");
                        expand();
                    }
                    isOpen = !isOpen;
                }
            });
        }

        private void bind(Experience e) {
            name.setText(e.getName());
            nature.setText(e.getNature());
            work.setText(e.getWork());
            time.setText(e.getStartTime()+ " - " + e.getEndTime());
            detail.setText(e.getDetail());
        }

        private void expand() {

            if (shortH == 0) {
                final int startHeight = itemView.getHeight();
                shortH = startHeight;
                timeLayout.setVisibility(View.VISIBLE);
                detail.setVisibility(View.VISIBLE);
                itemView.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                final int targetHeight = itemView.getMeasuredHeight();
                longH = targetHeight + startHeight;
            }
            timeLayout.setVisibility(View.VISIBLE);
            detail.setVisibility(View.VISIBLE);
            Animation a = new Animation()
            {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    itemView.getLayoutParams().height = (int) (shortH + (longH - shortH) * interpolatedTime);
                    itemView.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            a.setDuration(1500);
            itemView.startAnimation(a);
        }

        private void close() {


            Animation a = new Animation()
            {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    itemView.getLayoutParams().height = (int) (longH + (shortH - longH) * interpolatedTime);
                    itemView.requestLayout();

                    if (interpolatedTime == 1f) {
                        timeLayout.setVisibility(View.GONE);
                        detail.setVisibility(View.GONE);
                    }
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            a.setDuration(1500);
            itemView.startAnimation(a);
        }
    }

}
