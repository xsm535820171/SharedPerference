package com.my.appshare;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * SharedPreferance:用于保存少量的数据，而且这些数据的格式很简单。
 * 一般是配置信息格式的数据(例如：是否打开音效，是否使用震动)，还有一些游戏玩家的积分
 *保存的数据主要是简单类型的（key-value）对
 *
 * SharedPreferance接口 主要负责读取应用程序的Preferance数据，
 * SharedPreferance本身是接口，只能通过Context提供的getSharedPreferences（String name,int mode）获取实例
 *
 * 另外SharedPreferance接口没有提供写入数据的能力，通过他自己的内部接口调用edit()方法获取他自己对应的Editor对象，
 * Editor对象提供了很多方法向SharedPreferance写入数据
 *
 * Context.MODE_PRIVATE：为默认操作模式,代表该文件是私有数据,只能被应用本身访问,在该模式下,写入的内容会覆盖原文件的内容
 Context.MODE_APPEND：模式会检查文件是否存在,存在就往文件追加内容,否则就创建新文件.
 Context.MODE_WORLD_READABLE和Context.MODE_WORLD_WRITEABLE用来控制其他应用是否有权限读写该文件.
 MODE_WORLD_READABLE：表示当前文件可以被其他应用读取.
 MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入.
* */


public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @ViewInject(R.id.et_text)
    EditText et_text;
    @ViewInject(R.id.share_read)
    Button share_read;
    @ViewInject(R.id.share_save)
    Button share_save;

    @OnClick({R.id.share_read,R.id.share_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.share_save:
                String text=et_text.getText().toString();
                editor.putString("et",text);
                Toast.makeText(MainActivity.this,"haha"+text,Toast.LENGTH_LONG).show();
                editor.commit();
                break;
            case R.id.share_read:
                Log.e("xsm","etet==");
                String et=sharedPreferences.getString("et","defaultname");
                Log.e("xsm","etet=="+et);
                Toast.makeText(MainActivity.this,"haha"+et,Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);

        /**
         * 第一个参数用于指定该文件的名称，名称不用带后缀，
         *
         *  第二个参数指定文件的操作模式，共有四种操作模式:
         * MODE_PRIVATE:数据只能被本应用程序读、写
         * MODE_WORLD_READABLE:数据能被其他应用程序读
         * MODE_WORLD_WRITEABLE：数据能被其他应用程序读、写
         *
         */
        sharedPreferences=getSharedPreferences("share", Context.MODE_WORLD_WRITEABLE|Context.MODE_WORLD_READABLE);
        editor=sharedPreferences.edit();
    }
}
