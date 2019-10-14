package vn.sunasterisk.buoi06_fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements ParamFragment.OnCalculatorListeners,
        View.OnClickListener {

    private ParamFragment mParamFragment;
    private ResultFragment mResultFragment;

    private TextView mTextResult;

    private Button mButtonAdd;
    private Button mButtonReplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_02);

        intViews();
        registerListeners();
        addParamFragment();
        addResultFragment();
    }

    private void addResultFragment() {
        if (mResultFragment == null) {
            mResultFragment = new ResultFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_result, mResultFragment)
                    .commit();
        }
    }

    private void addParamFragment() {
        if (mParamFragment == null) {
            mParamFragment = new ParamFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_param, mParamFragment)
                    .commit();
            mParamFragment.setListeners(this);
        }
    }

    private void registerListeners() {
        mButtonAdd.setOnClickListener(this);
        mButtonReplace.setOnClickListener(this);
    }

    private void intViews() {
        mTextResult = findViewById(R.id.text_result);
        mButtonAdd = findViewById(R.id.button_add_more_fragment);
        mButtonReplace = findViewById(R.id.button_replace);
    }

    @Override
    public void onAdd(int a, int b) {
        mTextResult.setText(String.valueOf(a + b));
        mResultFragment.updateResult(a + b);
    }

    @Override
    public void onSub(int a, int b) {
        mTextResult.setText(String.valueOf(a - b));
    }

    @Override
    public void onMul(int a, int b) {
        mTextResult.setText(String.valueOf(a * b));
    }

    @Override
    public void onDiv(int a, int b) {
        mTextResult.setText(String.valueOf(a / b));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_more_fragment:
                addMoreFragment();
                break;
            case R.id.button_replace:
                replaceFragment();
                break;
            default:
                break;
        }
    }

    private void replaceFragment() {
        SecondFragment secondFragment = new SecondFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_param, secondFragment)
                .addToBackStack(null)
                .commit();
    }

    private void addMoreFragment() {
        FirstFragment firstFragment = new FirstFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_param, firstFragment)
                .commit();
    }
}
