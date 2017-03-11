package com.example.jmusch;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity {
    private ActionBar mActionbar;
    private Toolbar mtoolbar;
    private SearchView msearchView;
    private Button mbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setFinishOnTouchOutside(false);
        init();
        mbutton=(Button)findViewById(R.id.button);
        mbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                switch (v.getId())
                {
                    case R.id.button:
                    {
                        msearchView.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        );
    }
    private void init()
    {
        mtoolbar=(Toolbar) findViewById(R.id.tool_bar);
        //得到Activity的Actiobar
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mtoolbar.setTitle("课表查询");
    }
    //返回键action
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
    //查询课表方法
}
