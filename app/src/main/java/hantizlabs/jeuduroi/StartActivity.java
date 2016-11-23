package hantizlabs.jeuduroi;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(StartActivity.this, GameActivity.class);
                startActivity(intent);
                break;
        }

    }
}
