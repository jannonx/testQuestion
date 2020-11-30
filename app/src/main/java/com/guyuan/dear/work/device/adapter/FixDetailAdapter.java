package com.guyuan.dear.work.device.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;

import java.util.ArrayList;
import java.util.List;

public class FixDetailAdapter extends RecyclerView.Adapter<FixDetailAdapter.ViewHolder> {
  private Context context;
  private List<String> dataList;
  private List<String> checkedList=new ArrayList<>();

  public FixDetailAdapter(Context context, List<String> dataList) {
    this.context = context;
    this.dataList = dataList;
  }



  @NonNull
  @Override
  public FixDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(context).inflate(R.layout.item_fix_detail,null);
    return new FixDetailAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull FixDetailAdapter.ViewHolder holder, int position) {
    holder.fix_detail_cb.setText(dataList.get(position));
  }

  @Override
  public int getItemCount() {
    return dataList.size();
  }

  public class  ViewHolder extends RecyclerView.ViewHolder{
    private CheckBox fix_detail_cb;
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      fix_detail_cb=itemView.findViewById(R.id.fix_detail_cb);

      fix_detail_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          String str=fix_detail_cb.getText().toString();
          if(isChecked){
            if(!checkedList.contains(str)){
              checkedList.add(str);
            }
          }else {
            if(checkedList.contains(str)){
              checkedList.remove(str);
            }
          }
        }
      });
    }
  }

  public List<String> getCheckedList(){
    return checkedList;
  }
}
