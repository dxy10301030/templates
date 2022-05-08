package com.wisoft.template.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class test {
    public static IMaeaYqWeek getWeekTime(int weekCount, boolean isThisWeek) throws Exception {
        IMaeaYqWeek week = new IMaeaYqWeek();
        if (weekCount == 0) {
            return null;
        }
        ArrayList<String> weekStr = new ArrayList<>();
        ArrayList<String> noworkList = new ArrayList<>();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int a = w == 0 ? 7 : w;
        if (a == 6 || a == 7) {
            isThisWeek = false;
        }
        int b = a;
        int nowork = 0;
        ArrayList<Integer> noworks = new ArrayList<>();
        for (int i = 0; i < weekCount; i++) {
            for (int j = i * 7; j < (i + 1) * 7; j++) {
                String day = DateUtil.getByAddDays((1 - b) >= 0 ? j + 1 - a : (1 - b));
                b--;
                int isWork = issoWbjkManager.isWorkday(day);
                if (isWork != 1) {
                    noworkList.add(day.substring(0, 10));
                    nowork++;
                }
            }
            if (nowork == 7) {
                noworks.add(i);
                nowork = 0;
            }
        }
        if (isThisWeek) {
            weekStr.add(DateUtil.getByAddDays(1 - a).substring(0, 10).replaceFirst("-", "年").replace("-", "月") + "日-" +
                    DateUtil.getByAddDays(1 - a + 6).substring(0, 10).replaceFirst("-", "年").replace("-", "月") + "日");
            weekCount--;
        }
        for (int i = 0; i < weekCount + noworks.size(); i++) {
            if (noworks.contains(i + 1)) {
                continue;
            }
            weekStr.add(DateUtil.getByAddDays(7 - a + 1 + i * 7).substring(0, 10).replaceFirst("-", "年").replace("-", "月") + "日-" +
                    DateUtil.getByAddDays(7 - a + 6 + 1 + i * 7).substring(0, 10).replaceFirst("-", "年").replace("-", "月") + "日");
        }
        week.setWeekStrList(weekStr);
        week.setNoWorkDay(noworkList);
        return week;
    }

    static class IMaeaYqWeek {
        ArrayList<String> weekStrList;
        ArrayList<String> noWorkDay;


        public ArrayList<String> getWeekStrList() {
            return weekStrList;
        }

        public void setWeekStrList(ArrayList<String> weekStrList) {
            this.weekStrList = weekStrList;
        }

        public ArrayList<String> getNoWorkDay() {
            return noWorkDay;
        }

        public void setNoWorkDay(ArrayList<String> noWorkDay) {
            this.noWorkDay = noWorkDay;
        }

        @Override
        public String toString() {
            return "IMaeaYqWeek{" +
                    "weekStrList=" + weekStrList +
                    ", noWorkDay=" + noWorkDay +
                    '}';
        }
    }

    static class DateUtil {
        public static String getByAddDays(int days) {
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar tt = Calendar.getInstance();
            tt.add(5, days);
            return dateFormat1.format(tt.getTime());
        }
    }

    static class issoWbjkManager {

        public static Integer isWorkday(String daystr) {
            String[] days = {"2022-05-09", "2022-05-10", "2022-05-11", "2022-05-12", "2022-05-13", "2022-05-14", "2022-05-15",
                    "2022-05-23", "2022-05-24", "2022-05-25", "2022-05-26", "2022-05-27", "2022-05-28", "2022-05-29"};
            daystr = daystr.substring(0, 10);
            for (String day : days) {
                if (day.equals(daystr)) {
                    return 0;
                }
            }
            return 1;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getWeekTime(5, true));
    }
}
