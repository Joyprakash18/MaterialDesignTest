package com.sayan.sample.materialdesigntest;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class RunningProcessActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_proccess);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            fetchAllCurrentlyRunningProcessBeforeLollipop();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private void fetchCurrentUserStatisticsFromLollipop() {
        Calendar beginCal = Calendar.getInstance();
        beginCal.set(Calendar.DATE, 1);
        beginCal.set(Calendar.MONTH, 10);
        beginCal.set(Calendar.YEAR, 2018);

        Calendar endCal = Calendar.getInstance();
        endCal.set(Calendar.DATE, 3);
        endCal.set(Calendar.MONTH, 10);
        endCal.set(Calendar.YEAR, 2018);
        final UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        final Map<String, UsageStats> queryUsageStats = usageStatsManager.queryAndAggregateUsageStats(beginCal.getTimeInMillis(), endCal.getTimeInMillis());
        Log.d("Executed app", "Application executed : " + queryUsageStats.get(0).getPackageName());
    }

    private void fetchAllCurrentlyRunningProcessBeforeLollipop() {
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningTaskInfo> recentTasks;
        if (activityManager != null) {
            recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
            for (int i = 0; i < recentTasks.size(); i++) {
                Log.d("Executed app", "Application executed : " + recentTasks.get(i).baseActivity.toShortString() + "\t\t ID: " + recentTasks.get(i).id + "");
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {
            fetchCurrentUserStatisticsFromLollipop();
        }
    }
}
