package com.tamer.plank.ui.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tamer.plank.R;
import com.tamer.plank.model.CardLab;

/**
 * Created by liangzr on 16-5-12.
 */
public class EntryptionDialog extends AppCompatActivity implements View.OnClickListener{

    public static final String PWD = "2016";
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_entryption);

         editText= (EditText) findViewById(R.id.encryption);
        Button button = (Button) findViewById(R.id.btn_encryption);

        assert button != null;
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (editText.getText().toString().equals(PWD)) {
            CardLab.getInstance(getApplicationContext()).setShortEncryptionFlag(true);
            Toast.makeText(getApplicationContext(), "已解锁", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_SHORT).show();
        }
        this.finish();
    }
}
