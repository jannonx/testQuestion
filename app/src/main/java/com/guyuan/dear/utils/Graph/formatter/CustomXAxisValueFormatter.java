package com.guyuan.dear.utils.Graph.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.guyuan.dear.utils.Graph.entity.BarEntity;

import java.util.List;

/**
 * Created by TL
 * on 2020/1/15
 */
public class CustomXAxisValueFormatter implements IAxisValueFormatter {

  private List<BarEntity> entityList;


  public CustomXAxisValueFormatter(List<BarEntity> entityList) {
    this.entityList = entityList;
  }

  @Override
  public String getFormattedValue(float value, AxisBase axis) {
    int xData = (int) value;
    return entityList.get(xData).getName();
  }
}

