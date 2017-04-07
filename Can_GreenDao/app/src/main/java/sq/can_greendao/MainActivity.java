package sq.can_greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import others.MyAdapter;
import tools.DBTool;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rv;
    private MyAdapter myAdapter;
    private Button btnInsert,btnQuery,btnDelete,btnDeleteByName;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnQuery = (Button) findViewById(R.id.btn_query);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnDeleteByName = (Button) findViewById(R.id.btn_deleteByName);
        et = (EditText) findViewById(R.id.et);
        rv = (RecyclerView) findViewById(R.id.rv);
        myAdapter = new MyAdapter(this);

        btnInsert.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnDeleteByName.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 添加一条数据
            case R.id.btn_insert:
                String data = et.getText().toString();
                Student student = new Student(null,data);
                if (!DBTool.getInstance().isSave(data)){
                    DBTool.getInstance().insertPerson(student);
                }
                et.setText(null);
                break;
            // 查找全部
            case R.id.btn_query:
                List<Student> allData = DBTool.getInstance().queryAll();
                ArrayList a = new ArrayList();
                for (int i = 0; i < allData.size(); i++) {
                    a.add(allData.get(i).getName());
                }
                myAdapter.setData(a);
                rv.setAdapter(myAdapter);
                LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
                rv.setLayoutManager(manager);
                et.setText(null);
                break;
            // 删除所有
            case R.id.btn_delete:
                DBTool.getInstance().deleteAll();
                et.setText(null);
                break;
            // 根据条件删除
            case R.id.btn_deleteByName:
                if (DBTool.getInstance().isSave(et.getText().toString())){
                    DBTool.getInstance().deleteByName(et.getText().toString());
                }
                et.setText(null);
                break;

            // 啊啊
        }
    }
}
