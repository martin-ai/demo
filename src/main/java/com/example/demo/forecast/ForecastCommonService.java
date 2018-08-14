package com.example.demo.forecast;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.demo.date.MyDateUtils.addMonth;

@Service
public class ForecastCommonService {

    public Map<Integer, BigDecimal> filterInvalidData(List<DateQty> historyDateQties) {
        if (CollectionUtils.isEmpty(historyDateQties)) {
            return Maps.newHashMap();
        }
        //从有销量的那期开始使用预测算法计算，之前数据全部舍弃；
        historyDateQties = historyDateQties.stream().sorted((x, y) -> y.getDate() - x.getDate()).collect(Collectors.toList());
        Iterator<DateQty> dateQtyIterator = historyDateQties.iterator();
        while (dateQtyIterator.hasNext()) {
            DateQty tmp = dateQtyIterator.next();
            if (BigDecimal.ZERO.compareTo(tmp.getQty()) == 0) {
                dateQtyIterator.remove();
            } else {
                break;
            }
        }
        return historyDateQties.stream().collect(Collectors.toMap(DateQty::getDate, DateQty::getQty));
    }

    public void filterInvalidData(Map<Integer, BigDecimal> historyDateQties, int maxBreakPeriods) {
        if (CollectionUtils.isEmpty(historyDateQties)) {
            return;
        }
        maxBreakPeriods = maxBreakPeriods <= 0 ? 0 : maxBreakPeriods;
        //大于连续maxBreakPeriods期数断的数据舍弃，用后续的需求数据继续使用预测算法计算；
        int minDate = historyDateQties.keySet().stream().min(Integer::compareTo).get();
        int maxDate = historyDateQties.keySet().stream().max(Integer::compareTo).get();
        List<DateQty> usableDataQties = Lists.newArrayList();

        int count = 0;
        while (minDate <= maxDate) {
            BigDecimal qty = BigDecimal.ZERO;
            if (!historyDateQties.containsKey(minDate) || BigDecimal.ZERO.compareTo(historyDateQties.get(minDate)) == 0) {
                count++;
            } else {
                if (count >= maxBreakPeriods) {
                    count = 0;
                    usableDataQties.clear();
                }
                qty = historyDateQties.get(minDate);
            }
            usableDataQties.add(new DateQty(minDate, qty));
            minDate = addMonth(minDate, 1);
        }
        System.out.println(usableDataQties);
    }

}
