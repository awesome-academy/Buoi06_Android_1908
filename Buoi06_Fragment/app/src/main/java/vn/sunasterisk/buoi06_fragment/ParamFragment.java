package vn.sunasterisk.buoi06_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ParamFragment extends Fragment
        implements View.OnClickListener {
    private EditText mTextA;
    private EditText mTextB;

    private Button mButtonAdd;
    private Button mButtonSub;
    private Button mButtonMul;
    private Button mButtonDiv;

    private OnCalculatorListeners mListeners;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_param, container, false);
    }

    public void setListeners(OnCalculatorListeners listeners) {
        mListeners = listeners;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intViews(view);
        registerListeners();
    }

    private void registerListeners() {
        mButtonAdd.setOnClickListener(this);
        mButtonSub.setOnClickListener(this);
        mButtonMul.setOnClickListener(this);
        mButtonDiv.setOnClickListener(this);
    }

    private void intViews(View view) {
        mTextA = view.findViewById(R.id.text_a);
        mTextB = view.findViewById(R.id.text_b);
        mButtonAdd = view.findViewById(R.id.button_add);
        mButtonSub = view.findViewById(R.id.button_sub);
        mButtonMul = view.findViewById(R.id.button_mul);
        mButtonDiv = view.findViewById(R.id.button_div);
    }

    @Override
    public void onClick(View v) {
        String strA = mTextA.getText().toString().trim();
        String strB = mTextB.getText().toString().trim();
        int a = 0;
        int b = 0;
        try {
            a = Integer.parseInt(strA);
            b = Integer.parseInt(strB);
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(),
                    "A or B is not Number",
                    Toast.LENGTH_SHORT).show();
        }

        switch (v.getId()) {
            case R.id.button_add:
                /*Toast.makeText(getActivity(),
                        String.valueOf(a + b),
                        Toast.LENGTH_SHORT).show();*/
                mListeners.onAdd(a, b);
                break;
            case R.id.button_sub:
                mListeners.onSub(a, b);
                break;
            case R.id.button_mul:
                mListeners.onMul(a, b);
                break;
            case R.id.button_div:
                mListeners.onDiv(a, b);
                break;
            default:
                break;
        }
    }

    public interface OnCalculatorListeners {
        //default cac pt trong nay la abstract
        void onAdd(int a, int b);

        void onSub(int a, int b);

        void onMul(int a, int b);

        void onDiv(int a, int b);
    }
}
