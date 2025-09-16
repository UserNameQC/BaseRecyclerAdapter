package com.qiao.baserecycleradapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.qiao.baseadapter.BaseRecyclerAdapter;
import com.qiao.baserecycleradapter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ObservableArrayList<TestModel> list = new ObservableArrayList<>();
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initAdapter();
    }

    public void initAdapter() {
        for (int i = 0; i < 5; i++) {
            TestModel testModel = new TestModel();
            testModel.setName("测试" + i);
            list.add(testModel);
        }

        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        TestAdapter testAdapter = new TestAdapter(this, list);
        binding.recycler.setAdapter(testAdapter);

        testAdapter.setOnItemClickListener(((view, integer) -> {
            Toast.makeText(this, "点击了" + list.get(integer), Toast.LENGTH_SHORT).show();
            return null;
        }));

        testAdapter.setonLongItemClickListener((view, position) ->{

            return null;
        });
    }
}